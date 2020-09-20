package com.javierfspano.deturno.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.javierfspano.deturno.R;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends FragmentActivity implements OnMapReadyCallback, MainContract.View {

    public static final String ID_TOKEN_EXTRA = "IdToken";

    @Inject
    MainContract.Presenter presenter;
    private String idToken;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.attachView(this);
        presenter.onCreate(getIntent().getStringExtra(ID_TOKEN_EXTRA));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        presenter.onMapReady();
    }

    @Override
    public void addMarker(MarkerOptions markerOptions) {
        map.addMarker(markerOptions);
    }

    @Override
    public void centerMap(LatLng latLng) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15), 2300, null);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(MainActivity.this, "Algo salio mal, intente mas tarde", Toast.LENGTH_SHORT).show();
    }
}