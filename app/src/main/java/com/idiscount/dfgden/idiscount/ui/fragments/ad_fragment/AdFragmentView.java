package com.idiscount.dfgden.idiscount.ui.fragments.ad_fragment;

import com.idiscount.dfgden.idiscount.models.Apartment;
import com.idiscount.dfgden.idiscount.ui.fragments.base_fragment.MvpView;

import java.util.ArrayList;
import java.util.List;


public interface AdFragmentView extends MvpView {

    void addApartments(List<Apartment> apartments);

    void updApartments(List<Apartment> apartments);

    void updApartmentCount(int count);

    void showRefresh(boolean isRefresh);

    void showError(String error);

    List<Apartment> getApartment();

}
