package com.tacademy.qodbtn41.gosurf.data;

/**
 * Created by UserPC on 2015-11-03.
 * 샵 정보
 */
public class ShopItemData {
    String _id;
    String name;
    String phone;
    String location_category;
    String image_url;
    int __v;
    Locations locations;

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation_category() {
        return location_category;
    }

    public String getImage_url() {
        return image_url;
    }

    public int get__v() {
        return __v;
    }

    public Locations getLocations() {
        return locations;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation_category(String location_category) {
        this.location_category = location_category;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public void setLocations(Locations locations) {
        this.locations = locations;
    }
}
