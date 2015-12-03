package com.tacademy.qodbtn41.gosurf.data;

import java.util.List;

/**
 * Created by UserPC on 2015-11-22.
 */
public class TimelineDetailItem {
    String _id;
    String user_id;
    String user_name;
    String user_profile;
    String content;
    int comments_count;
    Attachment attachment;
    String created_date;
    int like_count;
    List<LikeParticipants> like_participants;
    List<CommentItem> comments;

    public String getUser_profile() {
        return user_profile;
    }

    public String get_id() {
        return _id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getContent() {
        return content;
    }

    public int getComments_count() {
        return comments_count;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public String getCreated_date() {
        return created_date;
    }

    public int getLike_count() {
        return like_count;
    }

    public List<LikeParticipants> getLike_participants() {
        return like_participants;
    }

    public List<CommentItem> getComments() {
        return comments;
    }
}
