package com.javierfspano.deturno.service;

import com.javierfspano.deturno.data.PharmacyServiceResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PharmacyService {
    @GET("farmacias_cercanas?direccion=Alcaraz,5445,CABA")
    Call<PharmacyServiceResponse> listPharmacies();
}
