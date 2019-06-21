package com.inveitix.templateandroidapp.data.local.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.inveitix.templateandroidapp.injection.ApplicationContext;

import javax.inject.Inject;

/**
 * Created by Tito on 5.9.2017 г..
 */

public class PreferencesUtils {

    //TODO change app name
    private static final String PREF_FILE_NAME = "app_name";
    private static SharedPreferences sPref;

    @Inject
    PreferencesUtils(@ApplicationContext Context context) {
        sPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }
}
