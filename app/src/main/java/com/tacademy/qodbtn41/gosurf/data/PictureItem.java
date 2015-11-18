package com.tacademy.qodbtn41.gosurf.data;

/**
 * Created by UserPC on 2015-11-04.
 */
public class PictureItem implements TImelineListItem {
    private String id;
    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setData(TimelineItem item){
        this.picture = item.getAttachment().getFile_url();
        this.id = item.get_id();
    }
}
