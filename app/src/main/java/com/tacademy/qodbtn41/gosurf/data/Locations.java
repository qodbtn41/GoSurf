package com.tacademy.qodbtn41.gosurf.data;

import java.io.Serializable;

/**
 * Created by UserPC on 2015-11-10.
 */
public class Locations implements Serializable{
    double latitude;
    double longitude;

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
