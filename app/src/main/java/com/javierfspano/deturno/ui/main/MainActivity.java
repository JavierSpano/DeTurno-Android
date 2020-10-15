package com.javierfspano.deturno.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.slider.Slider;
import com.javierfspano.deturno.R;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, MainContract.View,
        View.OnClickListener, SearchView.OnCloseListener, SearchView.OnQueryTextListener {

    public static final String ID_TOKEN_EXTRA = "IdToken";
    public static final String COORDINATES_EXTRA = "Coordinates";
    public static final String ADDRESS_EXTRA = "Address";
    public static final float DEFAULT_RADIUS = 0.6f;

    @Inject
    MainContract.Presenter presenter;
    private float currentRadiusValue = DEFAULT_RADIUS;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.attachView(this);
        Intent intent = getIntent();
        String idToken = intent.getStringExtra(ID_TOKEN_EXTRA);
        LatLng coordinates = intent.getParcelableExtra(COORDINATES_EXTRA);
        String address = intent.getStringExtra(ADDRESS_EXTRA);
        presenter.onCreate(idToken, coordinates, address);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setupSlider();
        setSupportActionBar(findViewById(R.id.toolbar));

    }

    private void setupSlider() {
        Slider slider = findViewById(R.id.slider);
        TextView radiusValueLabel = findViewById(R.id.radiusValue);
        radiusValueLabel.setText(currentRadiusValue + "km");
        slider.addOnChangeListener((slider1, value, fromUser) -> radiusValueLabel.setText(value + "km"));
        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                currentRadiusValue = slider.getValue();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setOnCloseListener(this);
        searchView.setOnSearchClickListener(this);
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
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

    @Override
    public void showLoading() {
        findViewById(R.id.loading).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.loading).setVisibility(View.GONE);
    }

    @Override
    public void clearMapMarkers() {
        map.clear();
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onClose() {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenter.onAddressSearch(query, currentRadiusValue);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    @Override
    public Context getContext() {
        return this;
    }
}