package com.javierfspano.deturno.repository;

import androidx.annotation.Nullable;

import com.javierfspano.deturno.data.PharmacyServiceResponse;
import com.javierfspano.deturno.util.GenericServiceCallback;

public interface PharmacyRepository {

    void getPharmacies(@Nullable String address, String idToken, GenericServiceCallback<PharmacyServiceResponse> callback);
}
