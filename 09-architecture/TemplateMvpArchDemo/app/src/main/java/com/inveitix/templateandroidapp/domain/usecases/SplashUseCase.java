package com.inveitix.templateandroidapp.domain.usecases;

import javax.inject.Inject;

public class SplashUseCase {

    private ViewListener listener;

    @Inject
    public SplashUseCase() {
    }

    public void setListener(ViewListener listener) {
        this.listener = listener;
    }

    public void decideNextScreen() {
    }

    public interface ViewListener {
        void goToLogin();
        void goToToday();
    }
}
