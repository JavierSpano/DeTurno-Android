package com.javierfspano.deturno.ui.main.map;

import com.google.android.gms.maps.model.LatLng;
import com.javierfspano.deturno.ui.base.BasePresenter;

public class PharmacyMapPresenter extends BasePresenter<PharmacyMapContract.View> implements PharmacyMapContract.Presenter {

    private final LatLng defaultLocation = new LatLng(-34.603739, -58.38157);

    public PharmacyMapPresenter() {
    }

    public void onMapReady() {
        view.centerMap(defaultLocation);
    }


    @Override
    public void onCreate() {

    }

}
