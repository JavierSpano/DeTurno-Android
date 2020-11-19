package com.javierfspano.deturno.ui.locationinput;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.javierfspano.deturno.ui.base.BasePresenter;
import com.javierfspano.deturno.ui.main.MainActivity;

public class LocationInputPresenter extends BasePresenter<LocationInputContract.View> implements LocationInputContract.Presenter {

    private String idToken;

    private FirebaseAnalytics firebaseAnalytics;

    @Override
    public void onTextInputLocationEntered(String address) {
        if (address != null && !address.isEmpty()) {
            firebaseAnalytics.logEvent("location_input_text_entered", null);
            view.showLoading();
            view.goToNextActivity(
                    idToken,
                    MainActivity.class,
                    address
            );
        } else {
            view.showEmptyFieldError();
        }
    }

    @Override
    public void onUseMyLocationClick() {
        firebaseAnalytics.logEvent("location_input_location_button_click", null);
        view.requestLocationPermissions();
    }

    @Override
    public void onPermissionsGranted() {
        firebaseAnalytics.logEvent("location_input_location_granted", null);
        view.showLoading();
        view.getCurrentLocation();
    }

    @Override
    public void onPermissionsDenied() {
        firebaseAnalytics.logEvent("location_input_location_denied", null);
        view.showPermissionError();
    }

    @Override
    public void onLocationSuccess(Location location) {
        firebaseAnalytics.logEvent("location_input_location_success", null);
        if (location != null) {
            view.goToNextActivity(
                    idToken,
                    MainActivity.class,
                    new LatLng(location.getLatitude(), location.getLongitude())
            );
        }

    }

    @Override
    public void onLocationError() {
        firebaseAnalytics.logEvent("location_input_location_error", null);
        view.hideLoading();
        view.showPermissionError();
    }

    @Override
    public void onCreate(String idToken, Context context) {
        this.idToken = idToken;
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);

        firebaseAnalytics.logEvent("location_input_view", null);
    }
}
