package com.inveitix.a09_architecture;

public class MainPresenter {

    private ViewListener viewListener;

    public void setView(ViewListener vL) {
        this.viewListener = vL;
    }

    public void onLoginClicked() {
        viewListener.showProgress();
    }

    public interface ViewListener {
        void showProgress();
    }
}
