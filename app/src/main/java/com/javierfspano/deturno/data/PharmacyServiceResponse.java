package com.javierfspano.deturno.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PharmacyServiceResponse {

    @SerializedName("centro_del_mapa")
    private Coordinates mapCenter;

    @SerializedName("farmacias")
    private List<Pharmacy> pharmacies;

    public Coordinates getMapCenter() {
        return mapCenter;
    }

    public void setMapCenter(Coordinates mapCenter) {
        this.mapCenter = mapCenter;
    }

    public List<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }
}
