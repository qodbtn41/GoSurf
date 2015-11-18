package com.tacademy.qodbtn41.gosurf.data;

/**
 * Created by UserPC on 2015-11-04.
 */
public class VideoItem implements TImelineListItem {
    private String video;
    private String id;

    public String getVideo() {
        return video;
    }

    public String getId() {
        return id;
    }

    public void setData(TimelineItem item){
        this.id = item.get_id();
        this.video = item.getAttachment().getFile_url();
    }
}
