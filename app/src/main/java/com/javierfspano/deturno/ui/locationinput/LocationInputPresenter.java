package com.javierfspano.deturno.ui.locationinput;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.javierfspano.deturno.ui.base.BasePresenter;
import com.javierfspano.deturno.ui.main.MainActivity;

public class LocationInputPresenter extends BasePresenter<LocationInputContract.View> implements LocationInputContract.Presenter {

    private String idToken;

    @Override
    public void onTextInputLocationEntered(String address) {
        if (address != null && !address.isEmpty()) {
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
        view.requestLocationPermissions();
    }

    @Override
    public void onPermissionsGranted() {
        view.showLoading();
        view.getCurrentLocation();
    }

    @Override
    public void onPermissionsDenied() {
        view.showPermissionError();
    }

    @Override
    public void onLocationSuccess(Location location) {
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
        view.hideLoading();
        view.showPermissionError();
    }

    @Override
    public void onCreate(String idToken) {
        this.idToken = idToken;
    }
}
