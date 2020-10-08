package com.javierfspano.deturno.service;

import androidx.annotation.Nullable;

import com.javierfspano.deturno.data.PharmacyServiceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface PharmacyService {
    @GET("farmacias_cercanas")
    Call<PharmacyServiceResponse> listPharmacies(@Header("IdToken") String header,
                                                 @Nullable @Query("direccion") String direccion);
}
