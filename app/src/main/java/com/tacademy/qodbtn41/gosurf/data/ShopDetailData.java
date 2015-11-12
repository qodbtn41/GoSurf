package com.tacademy.qodbtn41.gosurf.data;

import java.util.List;

/**
 * Created by UserPC on 2015-11-12.
 */
public class ShopDetailData {
    String _id;
    String location_category;

    String name;
    String phone;
    String image_url;
    String address;
    String description;
    int grade;
    int comments_count;
    Locations locations;
    List<CommentItemData> comments;

    public void set_id(String _id) {
        this._id = _id;
    }
    public void setLocation_category(String location_category) {
        this.location_category = location_category;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }
    public void setLocations(Locations locations) {
        this.locations = locations;
    }
    public void setComments(List<CommentItemData> comments) {
        this.comments = comments;
    }

    public String get_id() {
        return _id;
    }
    public String getLocation_category() {
        return location_category;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getImage_url() {
        return image_url;
    }
    public String getAddress() {
        return address;
    }
    public String getDescription() {
        return description;
    }
    public int getGrade() {
        return grade;
    }
    public int getComments_count() {
        return comments_count;
    }
    public Locations getLocations() {
        return locations;
    }
    public List<CommentItemData> getComments() {
        return comments;
    }
}