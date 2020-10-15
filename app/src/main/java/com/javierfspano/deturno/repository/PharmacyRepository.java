package com.javierfspano.deturno.repository;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.javierfspano.deturno.data.PharmacyServiceResponse;
import com.javierfspano.deturno.util.GenericServiceCallback;

public interface PharmacyRepository {

    void getPharmacies(@Nullable String address, float radius, String idToken, GenericServiceCallback<PharmacyServiceResponse> callback);

    void getPharmacies(LatLng latLng, float radius, String idToken, GenericServiceCallback<PharmacyServiceResponse> callback);
}
