package com.javierfspano.deturno.ui.main;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.javierfspano.deturno.data.Pharmacy;
import com.javierfspano.deturno.ui.base.BaseContract;

import java.util.List;

public interface MainContract {

    interface View extends BaseContract.BaseView {
        void addMarker(MarkerOptions markerOptions);

        void centerMap(LatLng latLng);

        void showErrorMessage();

        void showLoading();

        void hideLoading();

        void clearMapMarkers();

        void updateList(List<Pharmacy> list);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void onAddressSearch(String address, float radius);

        void onCreate(String stringExtra, @Nullable LatLng coordinates, String address);
    }
}
