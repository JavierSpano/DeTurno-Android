package com.javierfspano.deturno.domain;

import com.google.android.gms.maps.model.LatLng;
import com.javierfspano.deturno.data.PharmacyServiceResponse;
import com.javierfspano.deturno.repository.PharmacyRepository;
import com.javierfspano.deturno.ui.main.MainActivity;
import com.javierfspano.deturno.util.GenericServiceCallback;

public class GetPharmaciesByCoordinatesUseCase {

    private PharmacyRepository pharmacyRepository;


    public GetPharmaciesByCoordinatesUseCase(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public void execute(LatLng latLng, String idToken, final GenericServiceCallback<PharmacyServiceResponse> callback) {
        pharmacyRepository.getPharmacies(latLng, MainActivity.DEFAULT_RADIUS, idToken, callback);
    }
}
