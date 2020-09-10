package com.javierfspano.deturno.ui;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.javierfspano.deturno.base.BasePresenter;
import com.javierfspano.deturno.data.Coordinates;
import com.javierfspano.deturno.data.Pharmacy;
import com.javierfspano.deturno.data.PharmacyServiceResponse;
import com.javierfspano.deturno.domain.GetPharmacyListUseCase;
import com.javierfspano.deturno.util.GenericServiceCallback;

import java.util.List;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private GetPharmacyListUseCase getPharmacyListUseCase;

    public MainPresenter(GetPharmacyListUseCase getPharmacyListUseCase) {
        this.getPharmacyListUseCase = getPharmacyListUseCase;
    }

    public void onMapReady() {

        getPharmacyListUseCase.getPharmacies(new GenericServiceCallback<PharmacyServiceResponse>() {

            @Override
            public void onSuccess(PharmacyServiceResponse pharmacyServiceResponse) {
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
                        view.centerMap(latLng);
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                view.showErrorMessage();
            }
        });
    }
}
