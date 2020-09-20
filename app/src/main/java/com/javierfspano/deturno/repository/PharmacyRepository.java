package com.javierfspano.deturno.repository;

import com.javierfspano.deturno.data.PharmacyServiceResponse;
import com.javierfspano.deturno.util.GenericServiceCallback;

public interface PharmacyRepository {

    void getPharmacies(String idToken, GenericServiceCallback<PharmacyServiceResponse> callback);
}
