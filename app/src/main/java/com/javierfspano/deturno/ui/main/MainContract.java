package com.javierfspano.deturno.ui.main;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.javierfspano.deturno.ui.base.BaseContract;

public interface MainContract {

    interface View extends BaseContract.BaseView {
        void addMarker(MarkerOptions markerOptions);

        void centerMap(LatLng latLng);

        void showErrorMessage();

        void showLoading();

        void hideLoading();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void onMapReady();

        void onCreate(String stringExtra);

        void onAddressSearch(String address);
    }
}
