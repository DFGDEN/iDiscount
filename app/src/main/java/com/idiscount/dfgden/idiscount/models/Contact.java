package com.idiscount.dfgden.idiscount.models;

import com.google.gson.annotations.SerializedName;


public class Contact {

    @SerializedName("owner")
    private boolean isOwner;


    public boolean isOwner() {
        return isOwner;
    }
}
