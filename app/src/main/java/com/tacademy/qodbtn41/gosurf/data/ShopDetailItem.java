package com.tacademy.qodbtn41.gosurf.data;

import java.util.List;

/**
 * Created by UserPC on 2015-11-12.
 */
public class ShopDetailItem {
    String _id;
    String location_category;

    String name;
    String phone;
    String image_url;
    String page_url;



    String address;
    String description;
    float grade;
    int comments_count;
    Locations locations;
    List<CommentItem> comments;
    List<GradeParticipant> grade_participants;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLocation_category() {
        return location_category;
    }

    public void setLocation_category(String location_category) {
        this.location_category = location_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPage_url() {
        return page_url;
    }

    public void setPage_url(String page_url) {
        this.page_url = page_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public Locations getLocations() {
        return locations;
    }

    public void setLocations(Locations locations) {
        this.locations = locations;
    }

    public List<CommentItem> getComments() {
        return comments;
    }

    public void setComments(List<CommentItem> comments) {
        this.comments = comments;
    }

    public List<GradeParticipant> getGrade_participants() {
        return grade_participants;
    }

    public void setGrade_participants(List<GradeParticipant> grade_participants) {
        this.grade_participants = grade_participants;
    }
}
