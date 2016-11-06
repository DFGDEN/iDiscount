package com.idiscount.dfgden.idiscount.models;

import java.util.ArrayList;
import java.util.HashMap;


public class Price {

    private float amount;
    private Currency currency;
    private HashMap<Currency,Price> converted = new HashMap<>();

    public float getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public HashMap<Currency, Price> getConverted() {
        return converted;
    }
}
