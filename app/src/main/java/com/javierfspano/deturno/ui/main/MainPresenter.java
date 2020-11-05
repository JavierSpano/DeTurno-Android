package com.javierfspano.deturno.ui.main;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.javierfspano.deturno.R;
import com.javierfspano.deturno.data.Coordinates;
import com.javierfspano.deturno.data.Pharmacy;
import com.javierfspano.deturno.data.PharmacyServiceResponse;
import com.javierfspano.deturno.domain.GetPharmaciesByCoordinatesUseCase;
import com.javierfspano.deturno.domain.GetPharmaciesByTextUseCase;
import com.javierfspano.deturno.ui.base.BasePresenter;
import com.javierfspano.deturno.util.GenericServiceCallback;
import com.javierfspano.deturno.util.PendingFetch;

import java.util.List;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private GetPharmaciesByTextUseCase getPharmaciesByTextUseCase;
    private GetPharmaciesByCoordinatesUseCase getPharmaciesByCoordinatesUseCase;
    private String idToken;
    private PendingFetch pendingFetch;

    public MainPresenter(GetPharmaciesByTextUseCase getPharmaciesByTextUseCase, GetPharmaciesByCoordinatesUseCase getPharmaciesByCoordinatesUseCase) {
        this.getPharmaciesByTextUseCase = getPharmaciesByTextUseCase;
        this.getPharmaciesByCoordinatesUseCase = getPharmaciesByCoordinatesUseCase;
    }

    private void fetchNearbyPharmacies(@Nullable String address, float radius) {
        view.showLoading();
        getPharmaciesByTextUseCase.execute(address, radius, idToken, new GenericServiceCallback<PharmacyServiceResponse>() {

            @Override
            public void onSuccess(PharmacyServiceResponse pharmacyServiceResponse) {
                view.hideLoading();
                if (pharmacyServiceResponse != null) {
                    final List<Pharmacy> pharmacies = pharmacyServiceResponse.getPharmacies();
                    final Coordinates mapCenter = pharmacyServiceResponse.getMapCenter();
                    if (pharmacies != null) {
                        for (Pharmacy pharmacy : pharmacies) {
                            LatLng pharmacyLocation = new LatLng(Double.parseDouble(pharmacy.getLat()), Double.parseDouble(pharmacy.getLng()));
                            view.addMarker(new MarkerOptions()
                                    .position(pharmacyLocation)
                                    .title(pharmacy.getStreetName() + " " + pharmacy.getStreetNumber())
                                    .snippet(pharmacy.getName()));
                        }
                        LatLng latLng = new LatLng(Double.parseDouble(mapCenter.getLat()), Double.parseDouble(mapCenter.getLng()));
                        view.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title(address)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                        );
                        view.updateList(pharmacies);
                        view.centerMap(latLng);
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                view.hideLoading();
                view.showErrorMessage();
            }
        });
    }

    private void fetchNearbyPharmacies(LatLng latLng) {
        view.showLoading();
        getPharmaciesByCoordinatesUseCase.execute(latLng, idToken, new GenericServiceCallback<PharmacyServiceResponse>() {

            @Override
            public void onSuccess(PharmacyServiceResponse pharmacyServiceResponse) {
                view.hideLoading();
                if (pharmacyServiceResponse != null) {
                    final List<Pharmacy> pharmacies = pharmacyServiceResponse.getPharmacies();
                    final Coordinates mapCenter = pharmacyServiceResponse.getMapCenter();
                    if (pharmacies != null) {
                        for (Pharmacy pharmacy : pharmacies) {
                            LatLng pharmacyLocation = new LatLng(Double.parseDouble(pharmacy.getLat()), Double.parseDouble(pharmacy.getLng()));
                            view.addMarker(new MarkerOptions()
                                    .position(pharmacyLocation)
                                    .title(pharmacy.getStreetName() + " " + pharmacy.getStreetNumber())
                                    .snippet(pharmacy.getName()));
                        }
                        LatLng latLng = new LatLng(Double.parseDouble(mapCenter.getLat()), Double.parseDouble(mapCenter.getLng()));
                        view.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title(view.getContext().getString(R.string.you_are_here))
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                        );
                        view.updateList(pharmacies);
                        view.centerMap(latLng);
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                view.hideLoading();
                view.showErrorMessage();
            }
        });
    }

    @Override
    public void onCreate(String idToken, @Nullable LatLng location, String address) {
        this.idToken = idToken;

        pendingFetch = () -> {
            if (location != null) {
                fetchNearbyPharmacies(location);
            }

            if (address != null) {
                fetchNearbyPharmacies(address, MainActivity.DEFAULT_RADIUS);
            }
        };
    }

    @Override
    public void onAddressSearch(String address, float radius) {
        view.clearMapMarkers();
        fetchNearbyPharmacies(address, radius);
    }

    @Override
    public void onMapReady() {
        if (pendingFetch != null) {
            pendingFetch.fetch();
        }
    }
}
