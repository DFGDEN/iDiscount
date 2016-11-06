package com.idiscount.dfgden.idiscount.rest.listeners;


public interface OnGetDataListener<T> {

    void onGetData(T data);

    void onError(String error);
}
