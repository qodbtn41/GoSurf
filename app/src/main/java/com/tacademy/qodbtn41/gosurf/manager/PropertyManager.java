package com.tacademy.qodbtn41.gosurf.manager;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.tacademy.qodbtn41.gosurf.data.response.FacebookUserInfo;

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
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PROFILE_URL = "profile_url";
    private static final String _ID = "_id";
    private static final String REG_ID = "reg_token";
    private static final String PUSH_USE = "push_use";
    private static final String PUSH_SOUND = "push_sound";

    public void setPushSound(boolean isChecked){
        mEditor.putBoolean(PUSH_SOUND, isChecked);
        mEditor.commit();
    }
    public boolean getPushSound(){
        return mPrefs.getBoolean(PUSH_SOUND, false);
    }

    public void setPushUse(boolean isChecked){
        mEditor.putBoolean(PUSH_USE, isChecked);
        mEditor.commit();
    }
    public boolean getPushUse() {
        return mPrefs.getBoolean(PUSH_USE, false);
    }

    public void setFacebookId(String id) {
        mEditor.putString(FIELD_FACEBOOK_ID, id);
        mEditor.commit();
    }
    public String getFacebookId() {
        return mPrefs.getString(FIELD_FACEBOOK_ID, "");
    }

    public void set_Id(String _id) {
        mEditor.putString(_ID, _id);
        mEditor.commit();
    }
    public String get_Id() {
        return mPrefs.getString(_ID,"");
    }

    public void setName(String name) {
        mEditor.putString(NAME, name);
        mEditor.commit();
    }
    public String getName() {
        return mPrefs.getString(NAME, "");
    }

    public void setEmail(String email) {
        mEditor.putString(EMAIL, email);
        mEditor.commit();
    }
    public String getEmail() {
        return mPrefs.getString(EMAIL, "");
    }

    public void setProfileUrl(String profileUrl) {
        mEditor.putString(PROFILE_URL, profileUrl);
        mEditor.commit();
    }
    public String getProfileUrl() {
        return mPrefs.getString(PROFILE_URL, "");
    }

    public void setUserInfo(FacebookUserInfo userInfo){
        set_Id(userInfo.get_id());
        setEmail(userInfo.getEmail());
        setName(userInfo.getName());
        setProfileUrl(userInfo.getProfile_url());
    }


    public void setRegistrationToken(String regId) {
        mEditor.putString(REG_ID, regId);
        mEditor.commit();
    }

    public String getRegistrationToken() {
        return mPrefs.getString(REG_ID, "");
    }
}
