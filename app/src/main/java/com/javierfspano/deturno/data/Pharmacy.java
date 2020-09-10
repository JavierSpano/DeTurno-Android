package com.javierfspano.deturno.data;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Pharmacy {
    @SerializedName("barrio")
    private String neighborhood;

    @SerializedName("calle_altura")
    private String streetNumber;

    @SerializedName("calle_nombre")
    private String streetName;

    @SerializedName("codigo_postal")
    private String postalCode;

    @Nullable
    @SerializedName("nombre")
    private String name;

    @SerializedName("telefono")
    private String phoneNumber;

    private String lat;

    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
