package com.idiscount.dfgden.idiscount.models;

import com.google.gson.annotations.SerializedName;


public class Location {

    private String address;
    @SerializedName("user_address")
    private String userAddress;
    private double latitude;
    private double longitude;


    public String getAddress() {
        return address;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
