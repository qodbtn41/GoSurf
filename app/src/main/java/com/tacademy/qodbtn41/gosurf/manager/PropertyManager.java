package com.tacademy.qodbtn41.gosurf.manager;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by UserPC on 2015-11-20.
 * SharedPreferences에서 facebookId를 관리
 */
public class PropertyManager {
    private static PropertyManager instance;
    public static PropertyManager getInstance() {
        if( instance == null) {
            instance = new PropertyManager();
        }
        return instance;
    }

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    private PropertyManager() {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        mEditor = mPrefs.edit();
    }

    private static final String FIELD_FACEBOOK_ID = "facebook_id";

    public void setFacebookId(String id) {
        mEditor.putString(FIELD_FACEBOOK_ID, id);
        mEditor.commit();
    }

    public String getFacebookId() {
        return mPrefs.getString(FIELD_FACEBOOK_ID, "");
    }
}
