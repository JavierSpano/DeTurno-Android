package com.javierfspano.deturno.domain;

import com.javierfspano.deturno.data.PharmacyServiceResponse;
import com.javierfspano.deturno.repository.PharmacyRepository;
import com.javierfspano.deturno.util.GenericServiceCallback;

public class GetPharmacyListUseCase {

    private PharmacyRepository pharmacyRepository;


    public GetPharmacyListUseCase(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public void getPharmacies(final GenericServiceCallback<PharmacyServiceResponse> callback) {
        pharmacyRepository.getPharmacies(callback);
    }
}
