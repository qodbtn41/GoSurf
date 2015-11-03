package com.tacademy.qodbtn41.gosurf.data;

import android.graphics.drawable.Drawable;

/**
 * Created by UserPC on 2015-11-03.
 */
public class ShopItemData {
    private Drawable image;
    private String shopName;
    private String address;
    private String rate;
    private int commentCount;

    public Drawable getImage() {
        return image;
    }

    public String getShopName() {
        return shopName;
    }

    public String getAddress() {
        return address;
    }

    public String getRate() {
        return rate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
