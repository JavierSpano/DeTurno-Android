package com.javierfspano.deturno.ui.main;

import androidx.annotation.Nullable;

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

        void clearMapMarkers();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void onMapReady();

        void onAddressSearch(String address, float radius);

        void onCreate(String stringExtra, @Nullable LatLng coordinates, String address);
    }
}
