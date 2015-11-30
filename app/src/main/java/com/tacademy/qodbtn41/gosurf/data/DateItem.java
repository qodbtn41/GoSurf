package com.tacademy.qodbtn41.gosurf.data;

/**
 * Created by UserPC on 2015-11-02.
 */
public class DateItem implements SpotListItem {
    String date;
    boolean isOpened = false;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setIsOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }
}
