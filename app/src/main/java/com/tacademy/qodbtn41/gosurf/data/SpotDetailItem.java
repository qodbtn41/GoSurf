package com.tacademy.qodbtn41.gosurf.data;

/**
 * Created by UserPC on 2015-11-02.
 */
public class SpotDetailItem implements SpotListItem {
    boolean isOpened = false;
    String spot_name;
    int weather_indicator;
    String swell_height;
    String swell_period;
    int swell_dir;
    int swell_dir_optimum;
    String wind_speed;
    int wind_dir;
    int wind_dir_optimum;
    String time;

    public String getSpot_name() {
        return spot_name;
    }

    public String getTime() {
        return time;
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

    public boolean isOpened() {
        return isOpened;
    }

    public void setIsOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }

    public int getSwell_dir_optimum() {
        return swell_dir_optimum;
    }

    public int getWind_dir_optimum() {
        return wind_dir_optimum;
    }

    public void setData(LocationDetailData data){

        weather_indicator = data.getWeather_indicator();
        swell_height = data.getSwell_height();
        swell_period = data.getSwell_period();
        swell_dir = data.getSwell_dir();
        wind_speed = data.getWind_speed();
        wind_dir = data.getWind_dir();
        wind_dir_optimum = data.getWind_dir_optimum();
        swell_dir_optimum = data.getSwell_dir_optimum();
    }

    public void setTime(String time){
        this.time = time;
    }
}
