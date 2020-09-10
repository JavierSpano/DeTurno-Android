package com.javierfspano.deturno.ui;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.javierfspano.deturno.base.BaseContract;

public interface MainContract {

    interface View extends BaseContract.BaseView {
        void addMarker(MarkerOptions markerOptions);

        void centerMap(LatLng latLng);

        void showErrorMessage();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void onMapReady();
    }
}
