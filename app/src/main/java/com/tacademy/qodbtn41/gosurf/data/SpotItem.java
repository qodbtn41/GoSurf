package com.tacademy.qodbtn41.gosurf.data;

import java.util.List;

/**
 * Created by UserPC on 2015-11-01.
 */
public class SpotItem implements SpotListItem {
    String _id;

    String location_category;
    int weather_indicator;
    boolean is_bookmarked;

    String spot_name;
    String spot_image;
    List<UserId> bookmarks;
    Locations locations;

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

    public int getWeather_indicator() {
        return weather_indicator;
    }
    public void setWeather_indicator(int weather_indicator) {
        this.weather_indicator = weather_indicator;
    }

    public boolean is_bookmarked() {
        return is_bookmarked;
    }
    public void setIs_bookmarked(boolean is_bookmarked) {
        this.is_bookmarked = is_bookmarked;
    }

    public String getSpot_name() {
        return spot_name;
    }
    public void setSpot_name(String spot_name) {
        this.spot_name = spot_name;
    }

    public String getSpot_image() {
        return spot_image;
    }
    public void setSpot_image(String spot_image) {
        this.spot_image = spot_image;
    }

    public List<UserId> getBookmarks() {
        return bookmarks;
    }
    public void setBookmarks(List<UserId> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public Locations getLocations() {
        return locations;
    }
    public void setLocations(Locations locations) {
        this.locations = locations;
    }
}
