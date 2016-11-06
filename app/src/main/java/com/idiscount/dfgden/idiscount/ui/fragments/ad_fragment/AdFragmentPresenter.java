package com.idiscount.dfgden.idiscount.ui.fragments.ad_fragment;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.idiscount.dfgden.idiscount.models.Apartment;
import com.idiscount.dfgden.idiscount.models.Info;
import com.idiscount.dfgden.idiscount.rest.RESTManager;
import com.idiscount.dfgden.idiscount.rest.listeners.OnGetDataListener;
import com.idiscount.dfgden.idiscount.sharedpreference.PreferenceHelper;
import com.idiscount.dfgden.idiscount.ui.fragments.base_fragment.BasePresenter;

import java.util.ArrayList;
import java.util.List;


public class AdFragmentPresenter extends BasePresenter<AdFragmentView> implements OnGetDataListener<Info> {

    private RESTManager restManager;
    private PreferenceHelper preferenceHelper;
    private Gson gson;

    private int page = 0;

    public AdFragmentPresenter(RESTManager restManager, PreferenceHelper preferenceHelper) {
        this.restManager = restManager;
        this.preferenceHelper = preferenceHelper;
        this.gson = new Gson();
    }

    public void addApartments(){
        this.page++;
        getApartments();
    }

    public void updApartments(){
        this.page = 0;
        getApartments();
    }

    public void tryToUpdate(int cur, int maxPos){
        if (cur == maxPos - 1) {
            getMvpView().showRefresh(true);
            addApartments();
        }
    }

    @Override
    public void onGetData(Info data) {
        checkViewAttached();
        List<Apartment> apartments = data.getApartments();
        if (page == 0){
            getMvpView().updApartments(apartments);
        } else {
            getMvpView().addApartments(apartments);
        }

        getMvpView().updApartmentCount(data.getTotal());
    }

    @Override
    public void onError(String error) {
        checkViewAttached();


        Log.d("my",getMvpView().getApartment().size() + " getMvpView().getApartment().size(()" );


        if (getMvpView().getApartment().size() == 0 && getDataFromPreference() != null ){

            getMvpView().addApartments(getDataFromPreference());
        }

        getMvpView().showError(error);
    }

    public void saveDataToPreference(ArrayList<Apartment> apartments){
        String json = gson.toJson(apartments, new TypeToken<ArrayList<Apartment>>() {}.getType());
        preferenceHelper.putString(PreferenceHelper.KEY_PREFERENCE_TEMP, json);
    }

    private void getApartments(){
        restManager.getInfo(this, page);
    }

    private ArrayList<Apartment> getDataFromPreference(){
        String json = preferenceHelper.getString(PreferenceHelper.KEY_PREFERENCE_TEMP);
        ArrayList<Apartment> apartments = gson.fromJson(json, new TypeToken<ArrayList<Apartment>>() {}.getType());
        return apartments;
    }
}
