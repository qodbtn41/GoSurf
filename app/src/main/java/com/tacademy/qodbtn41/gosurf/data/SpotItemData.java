package com.tacademy.qodbtn41.gosurf.data;

import android.graphics.drawable.Drawable;

/**
 * Created by UserPC on 2015-11-01.
 */
public class SpotItemData implements SpotListData{
    private Drawable statusImage;
    private String statusText;
    private String spotName;
    private Boolean checked;

    public Drawable getStatusImage() {
        return statusImage;
    }
    public String getStatusText() {
        return statusText;
    }
    public String getSpotName() {
        return spotName;
    }
    public Boolean getChecked() {
        return checked;
    }

    public void setStatusImage(Drawable statusImage) {
        this.statusImage = statusImage;
    }
    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
