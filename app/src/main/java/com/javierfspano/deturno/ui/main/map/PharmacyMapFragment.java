package com.javierfspano.deturno.ui.main.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
public class PharmacyMapFragment extends Fragment implements PharmacyMapContract.View, OnMapReadyCallback {

    @Inject
    PharmacyMapContract.Presenter presenter;

    private GoogleMap map;

    public PharmacyMapFragment() {
        // Required empty public constructor
    }

    public static PharmacyMapFragment newInstance() {
        return new PharmacyMapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.attachView(this);
        presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pharmacy_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
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
    public void clearMapMarkers() {
        map.clear();
    }
}