package com.idiscount.dfgden.idiscount;

import android.app.Application;
import android.content.Context;

import com.idiscount.dfgden.idiscount.injection.component.ApplicationComponent;
import com.idiscount.dfgden.idiscount.injection.component.DaggerApplicationComponent;
import com.idiscount.dfgden.idiscount.injection.module.ApplicationModule;


public class IDiscountApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static IDiscountApp get(Context context) {
        return (IDiscountApp) context.getApplicationContext();
    }

}
