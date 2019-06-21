package com.inveitix.templateandroidapp.injection.component;

import android.app.Application;
import android.content.Context;

import com.inveitix.templateandroidapp.injection.ApplicationContext;
import com.inveitix.templateandroidapp.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();
}
