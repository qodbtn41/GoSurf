package com.tacademy.qodbtn41.gosurf.data;

import android.graphics.drawable.Drawable;

/**
 * Created by UserPC on 2015-11-04.
 */
public class PictureItem implements TImelineListItem {
    private Drawable picture;
    private String userName, time, commentCount, content;

    public Drawable getPicture() {
        return picture;
    }

    public String getUserName() {
        return userName;
    }

    public String getTime() {
        return time;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public String getContent() {
        return content;
    }

    public void setPicture(Drawable picture) {
        this.picture = picture;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
