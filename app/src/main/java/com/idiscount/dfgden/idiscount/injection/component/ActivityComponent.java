package com.idiscount.dfgden.idiscount.injection.component;



import com.idiscount.dfgden.idiscount.injection.PerActivity;
import com.idiscount.dfgden.idiscount.injection.module.ActivityModule;
import com.idiscount.dfgden.idiscount.ui.activities.main_activity.MainActivity;

import dagger.Subcomponent;



@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
