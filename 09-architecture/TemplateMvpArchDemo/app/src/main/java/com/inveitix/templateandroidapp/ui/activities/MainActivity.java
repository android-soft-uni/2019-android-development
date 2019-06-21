package com.inveitix.templateandroidapp.ui.activities;

import com.inveitix.templateandroidapp.R;
import com.inveitix.templateandroidapp.injection.component.ActivityComponent;
import com.inveitix.templateandroidapp.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onViewCreated() {
        setToolBar();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void doInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
