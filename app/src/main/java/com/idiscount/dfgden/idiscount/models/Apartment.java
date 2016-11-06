package com.idiscount.dfgden.idiscount.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;


public class Apartment {

    private int id;
    private Price price;

    @SerializedName("rent_type")
    private RentType rentType;
    private Location location;
    private Contact contact;
    private String photo;

    @SerializedName("created_at")
    private String createAt;

    @SerializedName("last_time_up")
    private String lastTimeUp;

    @SerializedName("up_available_in")
    private long availableIn;
    private String url;

    public int getId() {
        return id;
    }

    public Price getPrice() {
        return price;
    }

    public RentType getRentType() {
        return rentType;
    }

    public Location getLocation() {
        return location;
    }

    public Contact getContact() {
        return contact;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getLastTimeUp() {
        return lastTimeUp;
    }

    public long getAvailableIn() {
        return availableIn;
    }

    public String getUrl() {
        return url;
    }
}
