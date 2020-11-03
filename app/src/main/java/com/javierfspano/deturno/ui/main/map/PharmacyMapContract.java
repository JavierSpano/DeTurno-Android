package com.javierfspano.deturno.ui.main.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.javierfspano.deturno.ui.base.BaseContract;

public interface PharmacyMapContract {

    interface View extends BaseContract.BaseView {
        void addMarker(MarkerOptions markerOptions);

        void centerMap(LatLng latLng);

        void clearMapMarkers();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void onMapReady();

        void onCreate();
    }
}
