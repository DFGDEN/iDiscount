package com.idiscount.dfgden.idiscount.ui.activities.base_activity;


public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
