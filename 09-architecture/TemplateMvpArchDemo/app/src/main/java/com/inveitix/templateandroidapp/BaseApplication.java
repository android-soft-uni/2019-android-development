package com.inveitix.templateandroidapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.inveitix.templateandroidapp.injection.component.ApplicationComponent;
import com.inveitix.templateandroidapp.injection.component.DaggerApplicationComponent;
import com.inveitix.templateandroidapp.injection.module.ApplicationModule;

/**
 * Created by Tito on 5.9.2017 г..
 */

public class BaseApplication extends Application {

    ApplicationComponent mApplicationComponent;
    public static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
    }

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
}
