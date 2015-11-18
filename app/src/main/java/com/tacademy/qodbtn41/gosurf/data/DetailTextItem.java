package com.tacademy.qodbtn41.gosurf.data;

import android.graphics.drawable.Drawable;

/**
 * Created by UserPC on 2015-11-19.
 */
public class DetailTextItem {
    String content;
    String userName;
    String createdTime;
    Drawable profile;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Drawable getProfile() {
        return profile;
    }

    public void setProfile(Drawable profile) {
        this.profile = profile;
    }
}
