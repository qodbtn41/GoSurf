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

    public String getSpotTime(long timestamp){
        calendar.setTimeInMillis(timestamp);

        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String dayS = getSpotDay(timestamp);

        String result = (calendar.get(Calendar.MONTH)+1) + "월 " + calendar.get(Calendar.DATE) + "일 " + dayS;
        return result;
    }

    public String getSpotDay(long timestamp){
        calendar.setTimeInMillis(timestamp);

        int day = calendar.get(Calendar.DAY_OF_WEEK);

        String dayS;
        switch(day){
            case 1:{
                dayS = "일요일";
                break;
            }
            case 2:{
                dayS = "월요일";
                break;
            }
            case 3:{
                dayS = "화요일";
                break;
            }
            case 4:{
                dayS = "수요일";
                break;
            }
            case 5:{
                dayS = "목요일";
                break;
            }
            case 6:{
                dayS = "금요일";
                break;
            }
            case 7:
            default:{
                dayS = "토요일";
                break;
            }
        }
        return dayS;
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

    public int getCurrent(){
        long current = System.currentTimeMillis();
        calendar.setTimeInMillis(current);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if(hour < 3){
            return 0;
        }else if(hour <6){
            return 1;
        }else if(hour <9){
            return 2;
        }else if(hour<12){
            return 3;
        }else if(hour < 15){
            return 4;
        }else if(hour <18){
            return 5;
        }else if(hour <21){
            return 6;
        }else{
            return 7;
        }
    }
}
