package com.inveitix.templateandroidapp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.inveitix.templateandroidapp.domain.usecases.SplashUseCase;
import com.inveitix.templateandroidapp.injection.component.ActivityComponent;
import com.inveitix.templateandroidapp.ui.base.BaseActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashUseCase.ViewListener {

    // -------------------------------------------
    // Fields
    // -------------------------------------------

    @Inject SplashUseCase useCase;

    // -------------------------------------------
    // New instance
    // -------------------------------------------

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    // -------------------------------------------
    // Lifecycle
    // -------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    // -------------------------------------------
    // Overrides
    // -------------------------------------------

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void onViewCreated() {
        getSupportActionBar().hide();
        useCase.setListener(this);
        useCase.decideNextScreen();
    }

    @Override
    protected void doInject(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    public void goToLogin() {

    }

    @Override
    public void goToToday() {

    }

    // -------------------------------------------
    // Private methods
    // -------------------------------------------

    private void startNext(Intent intent) {
        startActivity(intent);
        finish();
    }
}