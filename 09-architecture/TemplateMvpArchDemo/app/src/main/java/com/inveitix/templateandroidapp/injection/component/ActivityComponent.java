package com.inveitix.templateandroidapp.injection.component;

import com.inveitix.templateandroidapp.injection.PerActivity;
import com.inveitix.templateandroidapp.injection.module.ActivityModule;
import com.inveitix.templateandroidapp.ui.activities.MainActivity;
import com.inveitix.templateandroidapp.ui.activities.SplashActivity;
import com.inveitix.templateandroidapp.ui.base.BaseActivity;

import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);
    void inject(MainActivity mainActivity);
    void inject(SplashActivity mainActivity);
}


