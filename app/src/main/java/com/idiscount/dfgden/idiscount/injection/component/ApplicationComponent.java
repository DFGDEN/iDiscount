package com.idiscount.dfgden.idiscount.injection.component;

import android.app.Application;
import android.content.Context;


import com.idiscount.dfgden.idiscount.injection.ApplicationContext;
import com.idiscount.dfgden.idiscount.injection.module.ApplicationModule;
import com.idiscount.dfgden.idiscount.sharedpreference.PreferenceHelper;
import com.idiscount.dfgden.idiscount.ui.activities.main_activity.MainActivity;
import com.idiscount.dfgden.idiscount.ui.fragments.ad_fragment.AdFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(AdFragment adFragment);

    @ApplicationContext
    Context context();

    Application application();


    PreferenceHelper preference();


}
