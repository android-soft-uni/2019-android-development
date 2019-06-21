package com.inveitix.templateandroidapp.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.inveitix.templateandroidapp.BaseApplication;
import com.inveitix.templateandroidapp.R;
import com.inveitix.templateandroidapp.injection.component.ActivityComponent;
import com.inveitix.templateandroidapp.injection.component.DaggerActivityComponent;
import com.inveitix.templateandroidapp.injection.module.ActivityModule;

import butterknife.ButterKnife;

/**
 * Created by Tito on 5.9.2017 г..
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(getLayoutRes());
        doInject(getActivityComponent());
        ButterKnife.bind(this);
        onViewCreated();
    }

    protected abstract void onViewCreated();

    protected abstract int getLayoutRes();

    protected abstract void doInject(ActivityComponent activityComponent);

    protected void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }

    protected void setToolBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }

    private ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(BaseApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }
}
