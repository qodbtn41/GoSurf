package com.tacademy.qodbtn41.gosurf.data;

/**
 * Created by UserPC on 2015-11-03.
 * 샵 정보
 */
public class ShopItem implements ShopListItem{
    String _id;
    String location_category;

    String name;
    String image_url;
    String address;
    int grade;
    int comments_count;

    public String get_id() {
        return _id;
    }

    public String getLocation_category() {
        return location_category;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getAddress() {
        return address;
    }

    public int getGrade() {
        return grade;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setLocation_category(String location_category) {
        this.location_category = location_category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }
}
