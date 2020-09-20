package com.javierfspano.deturno.repository;

import androidx.annotation.NonNull;

import com.javierfspano.deturno.data.PharmacyServiceResponse;
import com.javierfspano.deturno.exception.NullIdTokenException;
import com.javierfspano.deturno.exception.PharmacyServiceException;
import com.javierfspano.deturno.service.PharmacyService;
import com.javierfspano.deturno.util.GenericServiceCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PharmacyRepositoryImpl implements PharmacyRepository {

    private PharmacyService service;

    public PharmacyRepositoryImpl(PharmacyService service) {
        this.service = service;
    }

    @Override
    public void getPharmacies(String idToken, final GenericServiceCallback<PharmacyServiceResponse> callback) {
        if (idToken == null) {
            callback.onError(new NullIdTokenException());
            return;
        }
        service.listPharmacies(idToken).enqueue(new Callback<PharmacyServiceResponse>() {
            @Override
            public void onResponse(@NonNull Call<PharmacyServiceResponse> call, @NonNull Response<PharmacyServiceResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                    return;
                }
                callback.onError(new PharmacyServiceException(response.errorBody()));
            }

            @Override
            public void onFailure(@NonNull Call<PharmacyServiceResponse> call, @NonNull Throwable t) {
                callback.onError(t);
            }
        });
    }
}
