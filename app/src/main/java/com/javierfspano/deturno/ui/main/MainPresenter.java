package com.javierfspano.deturno.ui.main;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.javierfspano.deturno.data.Coordinates;
import com.javierfspano.deturno.data.Pharmacy;
import com.javierfspano.deturno.data.PharmacyServiceResponse;
import com.javierfspano.deturno.domain.GetPharmacyListUseCase;
import com.javierfspano.deturno.ui.base.BasePresenter;
import com.javierfspano.deturno.util.GenericServiceCallback;

import java.util.List;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private final LatLng defaultLocation = new LatLng(-34.647076, -58.381592);
    private GetPharmacyListUseCase getPharmacyListUseCase;
    private String idToken;

    public MainPresenter(GetPharmacyListUseCase getPharmacyListUseCase) {
        this.getPharmacyListUseCase = getPharmacyListUseCase;
    }

    public void onMapReady() {
        view.centerMap(defaultLocation);
    }

    private void fetchNearbyPharmacies(@Nullable String address) {
        view.showLoading();
        getPharmacyListUseCase.execute(address, idToken, new GenericServiceCallback<PharmacyServiceResponse>() {

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
    public void onCreate(String idToken) {
        this.idToken = idToken;
    }

    @Override
    public void onAddressSearch(String address) {
        fetchNearbyPharmacies(address);
    }
}
