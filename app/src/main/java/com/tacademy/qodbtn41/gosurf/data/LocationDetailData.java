package com.tacademy.qodbtn41.gosurf.data;

/**
 * Created by UserPC on 2015-11-30.
 */
public class LocationDetailData {
    int weather_indicator;
    String swell_height;
    String swell_period;
    String wind_speed;
    int wind_dir;
    int swell_dir;
    int wind_dir_optimum;
    int swell_dir_optimum;

    public int getWind_dir_optimum() {
        return wind_dir_optimum;
    }

    public int getSwell_dir_optimum() {
        return swell_dir_optimum;
    }

    public int getWeather_indicator() {
        return weather_indicator;
    }

    public String getSwell_height() {
        return swell_height;
    }

    public String getSwell_period() {
        return swell_period;
    }

    public int getSwell_dir() {
        return swell_dir;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public int getWind_dir() {
        return wind_dir;
    }
}
