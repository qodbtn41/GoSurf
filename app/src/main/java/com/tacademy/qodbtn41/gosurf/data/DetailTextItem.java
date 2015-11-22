package com.tacademy.qodbtn41.gosurf.data;

/**
 * Created by UserPC on 2015-11-19.
 */
public class DetailTextItem {
    String content;
    String userId;
    String createdTime;
    String profile;
    String articleId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setData(TimelineItem item){
        this.articleId = item.get_id();
        this.userId = item.getUser_id();
        this.content = item.getContent();
        this.createdTime = item.getCreated_date();
        //this.profile = item.getProfile(); 아직 프로필사진이 서버에 추가되지않았다.
    }
}
