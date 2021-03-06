package com.javierfspano.deturno.domain;

import androidx.annotation.Nullable;

import com.javierfspano.deturno.data.PharmacyServiceResponse;
import com.javierfspano.deturno.repository.PharmacyRepository;
import com.javierfspano.deturno.util.GenericServiceCallback;

public class GetPharmaciesByTextUseCase {

    private PharmacyRepository pharmacyRepository;


    public GetPharmaciesByTextUseCase(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public void execute(@Nullable String address, float radius, String idToken, final GenericServiceCallback<PharmacyServiceResponse> callback) {
        pharmacyRepository.getPharmacies(address, radius, idToken, callback);
    }
}
