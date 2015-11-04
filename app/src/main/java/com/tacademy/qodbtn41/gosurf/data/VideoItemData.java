package com.tacademy.qodbtn41.gosurf.data;

import android.net.Uri;

/**
 * Created by UserPC on 2015-11-04.
 */
public class VideoItemData implements TImelineListData{
    private Uri video;
    private String userName, time, commentCount, content;

    public Uri getVideo() {
        return video;
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

    public void setVideo(Uri video) {
        this.video = video;
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
