package com.javierfspano.deturno.ui.locationinput;

import android.app.Activity;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.javierfspano.deturno.ui.base.BaseContract;

public interface LocationInputContract {

    interface View extends BaseContract.BaseView {

        void goToNextActivity(String idToken, Class<? extends Activity> activity, LatLng latLng);

        void goToNextActivity(String idToken, Class<? extends Activity> activity, String address);

        void requestLocationPermissions();

        void showPermissionError();

        void getCurrentLocation();

        void showLoading();

        void hideLoading();

        void showEmptyFieldError();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void onTextInputLocationEntered(String address);

        void onUseMyLocationClick();

        void onPermissionsGranted();

        void onPermissionsDenied();

        void onLocationSuccess(Location location);

        void onLocationError();

        void onCreate(String idToken, Context context);
    }
}
