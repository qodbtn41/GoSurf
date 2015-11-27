package com.tacademy.qodbtn41.gosurf.data;

/**
 * Created by UserPC on 2015-11-19.
 */
public class TextItem implements TImelineListItem {
    String id;
    String userName;
    int commentCount;
    String content;
    String createdTime;
    int type;

    public int getType() {
        return type;
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

    public String getId() {
        return id;
    }

    public void setData(TimelineItem item){
        this.id = item.get_id();
        this.userName = item.getUser_id();
        this.commentCount = item.getComments_count();
        this.content = item.getContent();
        this.createdTime = item.getCreated_date();
        this.type = item.getAttachment().getType();
    }
}
