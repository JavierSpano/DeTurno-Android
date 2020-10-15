package com.javierfspano.deturno.service;

import androidx.annotation.Nullable;

import com.javierfspano.deturno.data.PharmacyServiceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface PharmacyService {
    @GET("farmacias_cercanas")
    Call<PharmacyServiceResponse> listPharmacies(@Header("IdToken") String idToken,
                                                 @Query("radio") Float radius, @Nullable @Query("direccion") String direccion);

    @GET("farmacias_cercanas-coordenadas")
    Call<PharmacyServiceResponse> listPharmacies(@Header("IdToken") String idToken,
                                                 @Query("radio") Float radius, @Query("lat") double lat, @Query("lng") double lng);
}
