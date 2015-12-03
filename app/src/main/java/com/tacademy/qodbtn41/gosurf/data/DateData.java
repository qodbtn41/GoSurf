package com.tacademy.qodbtn41.gosurf.data;

import java.util.List;

/**
 * Created by UserPC on 2015-11-30.
 */
public class DateData {
    String date;
    List<LocationDetailData> weather_hours;

    public String getDate() {
        return date;
    }

    public List<LocationDetailData> getWeather_hours() {
        return weather_hours;
    }
}
