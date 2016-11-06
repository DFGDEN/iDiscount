package com.idiscount.dfgden.idiscount.rest;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.idiscount.dfgden.idiscount.R;
import com.idiscount.dfgden.idiscount.models.Info;
import com.idiscount.dfgden.idiscount.rest.listeners.OnGetDataListener;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RESTManager {

    private static final String BASE_URL = "https://ak.api.onliner.by";
    private OkHttpClient okHttpClient;
    private Gson gson;
    private Context context;

    public RESTManager(Context context) {
        this.okHttpClient = new OkHttpClient();
        this.context = context;
        this.gson = new Gson();
    }

    public void  getInfo(final OnGetDataListener<Info> onGetDataListener, final int page){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = BASE_URL +"/apartments?page=" + page;
                final Request request = new Request.Builder().url(url)
                        .addHeader("Content-Type", "application/json" )
                        .addHeader("Accept", "application/json")
                        .get()
                        .build();
                Response response = null;
                try {
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()){
                      Info info = convertJsonToInfo(response.body().string());
                        if (info != null){
                            onGetDataListener.onGetData(info);
                        } else {
                            onGetDataListener.onError(context.getString(R.string.rest_error_bad_data));
                        }
                    } else {
                        onGetDataListener.onError(context.getString(R.string.rest_error_bad_server_connection));
                    }
                } catch (IOException e) {
                    onGetDataListener.onError(context.getString(R.string.rest_error_connection));
                }

            }
        }).start();
    }

private Info convertJsonToInfo(String json){
    Info result = null;
    try {
        result = gson.fromJson(json, new TypeToken<Info>() {}.getType());
    } catch (Exception e) {
        e.printStackTrace();
    }

    return result;
}

}



