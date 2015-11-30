package com.tacademy.qodbtn41.gosurf.data;

/**
 * Created by UserPC on 2015-11-02.
 */
public class ShopLinkItem implements SpotListItem {
    String locationCategory;
    boolean isOpened = false;

    public String getLocationCategory() {
        return locationCategory;
    }

    public void setLocationCategory(String locationCategory) {
        this.locationCategory = locationCategory;

    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setIsOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }
}
