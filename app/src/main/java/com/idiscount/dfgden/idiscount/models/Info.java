package com.idiscount.dfgden.idiscount.models;

import java.util.ArrayList;


public class Info {

    private ArrayList<Apartment> apartments = new ArrayList<>();
    private int total;
    private Page page;

    public ArrayList<Apartment> getApartments() {
        return apartments;
    }

    public int getTotal() {
        return total;
    }

    public Page getPage() {
        return page;
    }

}
