package com.tacademy.qodbtn41.gosurf.data;

import java.util.List;

/**
 * Created by UserPC on 2015-11-22.
 */
public class TimelineDetailItem {
    String _id;
    Attachment attachment;
    int comments_count;
    String content;
    String created_date;
    int like_count;
    String user_id;
    List<LikeParticipants> like_participants;
    List<CommentItem> comments;

    public String get_id() {
        return _id;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public int getComments_count() {
        return comments_count;
    }

    public String getContent() {
        return content;
    }

    public String getCreated_date() {
        return created_date;
    }

    public int getLike_count() {
        return like_count;
    }

    public String getUser_id() {
        return user_id;
    }

    public List<LikeParticipants> getLike_participants() {
        return like_participants;
    }

    public List<CommentItem> getComments() {
        return comments;
    }
}
