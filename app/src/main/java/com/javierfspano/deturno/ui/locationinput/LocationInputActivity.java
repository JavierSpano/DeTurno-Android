package com.javierfspano.deturno.ui.locationinput;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.javierfspano.deturno.R;
import com.javierfspano.deturno.ui.main.MainActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LocationInputActivity extends AppCompatActivity implements LocationInputContract.View {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Inject
    LocationInputContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_input);
        presenter.attachView(this);
        presenter.onCreate(getIntent().getStringExtra(MainActivity.ID_TOKEN_EXTRA),this);
        setupViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        hideLoading();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "location_input");
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, LocationInputActivity.class.getName());
        FirebaseAnalytics.getInstance(this).logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }

    private void setupViews() {
        final MaterialButton useMyLocationButton = findViewById(R.id.use_my_location_button);
        useMyLocationButton.setOnClickListener(v -> presenter.onUseMyLocationClick());

        final TextInputLayout locationTextInput = findViewById(R.id.location_text_input);
        if (locationTextInput.getEditText() != null && locationTextInput.getEditText().getText() != null) {
            locationTextInput.setEndIconOnClickListener(v -> presenter.onTextInputLocationEntered(locationTextInput.getEditText().getText().toString()));
            locationTextInput.getEditText().setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.onTextInputLocationEntered(locationTextInput.getEditText().getText().toString());
                }
                return false;
            });
        }
    }

    @Override
    public void goToNextActivity(String idToken, Class<? extends Activity> activity, LatLng latLng) {
        Intent intent = new Intent(this, activity);
        intent.putExtra(MainActivity.COORDINATES_EXTRA, latLng);
        goToNextActivity(idToken, intent);
    }

    @Override
    public void goToNextActivity(String idToken, Class<? extends Activity> activity, String address) {
        Intent intent = new Intent(this, activity);
        intent.putExtra(MainActivity.ADDRESS_EXTRA, address);
        goToNextActivity(idToken, intent);
    }

    private void goToNextActivity(String idToken, Intent intent) {
        intent.putExtra(MainActivity.ID_TOKEN_EXTRA, idToken);
        startActivity(intent);
    }

    @Override
    public void requestLocationPermissions() {
        requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            presenter.onPermissionsGranted();
        } else {
            presenter.onPermissionsDenied();
        }
    }

    @Override
    public void showPermissionError() {
        new AlertDialog.Builder(this)
                .setMessage("se necesita permiso de ubicacion para usar esta funcion")
                .setNeutralButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public void getCurrentLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(presenter::onLocationSuccess)
                    .addOnFailureListener(e -> presenter.onLocationError());
        }
    }

    @Override
    public void showLoading() {
        findViewById(R.id.loading).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.loading).setVisibility(View.GONE);
    }

    @Override
    public void showEmptyFieldError() {

    }

    @Override
    public Context getContext() {
        return this;
    }
}