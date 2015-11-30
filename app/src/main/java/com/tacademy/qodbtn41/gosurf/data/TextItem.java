package com.tacademy.qodbtn41.gosurf.data;

/**
 * Created by UserPC on 2015-11-19.
 */
public class TextItem implements TImelineListItem {
    String id;
    String userId;
    String userName;
    int commentCount;
    String content;
    String createdTime;
    int type;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public int getType() {
        return type;
    }

    public void setData(TimelineItem item){
        this.id = item.get_id();
        this.userId = item.getUser_id();
        this.userName = item.getUser_name();
        this.commentCount = item.getComments_count();
        this.content = item.getContent();
        this.createdTime = item.getCreated_date();
        this.type = item.getAttachment().getType();
    }
}
