package com.idiscount.dfgden.idiscount.injection.module;

import android.app.Application;
import android.content.Context;


import com.idiscount.dfgden.idiscount.injection.ApplicationContext;
import com.idiscount.dfgden.idiscount.rest.RESTManager;
import com.idiscount.dfgden.idiscount.sharedpreference.PreferenceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    public PreferenceHelper providePreferenceHelper(@ApplicationContext Context context){
        return new PreferenceHelper(context);
    }

    @Provides
    @Singleton
    public RESTManager provideRESTManager(@ApplicationContext Context context){
        return new RESTManager(context);
    }


}
