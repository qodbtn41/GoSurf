package com.tacademy.qodbtn41.gosurf.data;

import java.util.List;

/**
 * Created by UserPC on 2015-11-30.
 */
public class LocationData {
    String location_id;
    String location_category;
    String location_name;
    List<DateData> weather_weeks;
    List<Bookmark> bookmarks;
    boolean is_bookmarked;
    int weather_indicator;

    public boolean is_bookmarked() {
        return is_bookmarked;
    }

    public void setIs_bookmarked(boolean is_bookmarked) {
        this.is_bookmarked = is_bookmarked;
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public int getWeather_indicator() {
        return weather_indicator;
    }

    public String getLocation_id() {
        return location_id;
    }

    public String getLocation_category() {
        return location_category;
    }

    public String getLocation_name() {
        return location_name;
    }

    public List<DateData> getWeather_weeks() {
        return weather_weeks;
    }
}
