package com.tacademy.qodbtn41.gosurf.data;

import java.util.List;

/**
 * Created by UserPC on 2015-11-22.
 */
public class TimelineData {
    int total_count;
    List<TimelineDetailItem> items;

    public List<TimelineDetailItem> getItems() {
        return items;
    }
    public int getTotal_count() {
        return total_count;
    }
}
