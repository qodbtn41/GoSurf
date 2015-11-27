package com.tacademy.qodbtn41.gosurf.manager;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by UserPC on 2015-11-23.
 */
public class TimeManager {
    private static TimeManager instance;

    public static TimeManager getInstance() {
        if( instance == null) {
            instance = new TimeManager();
        }
        return instance;
    }

    Calendar calendar;
    String processedTime;

    private TimeManager() {
        calendar = Calendar.getInstance(Locale.KOREA);
    }

    public String getArticleTime(String timestamp) {
        long time = Long.parseLong(timestamp);
        calendar.setTimeInMillis(time);
        String amPm = calendar.get(Calendar.AM_PM) == Calendar.AM ? "오전" : "오후";
        processedTime = calendar.get(Calendar.YEAR) + ". " + calendar.get(Calendar.MONTH) + ". "
                + calendar.get(Calendar.DATE) + ". " + calendar.get(Calendar.HOUR) + ":"
                + calendar.get(Calendar.MINUTE) + " " + amPm;

        return processedTime;
    }

    public String getCommentTime(String timestamp) {
        long time = Long.parseLong(timestamp);
        long current = System.currentTimeMillis();

        long temp = (current - time) / 1000;

        if(temp < 60) {//초단위
            return "방금 전";
        }

        temp /= 60;
        if(temp < 60){
            return ((Long)temp).toString() + "분";
        }

        temp /= 60;
        if(temp<24){
            return ((Long)temp).toString() + "시간";
        }

        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DATE);
    }
}
