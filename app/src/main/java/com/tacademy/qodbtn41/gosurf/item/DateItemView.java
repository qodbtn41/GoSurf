package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.DateItem;
import com.tacademy.qodbtn41.gosurf.data.LocationData;
import com.tacademy.qodbtn41.gosurf.manager.TimeManager;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by UserPC on 2015-11-02.
 */
public class DateItemView extends FrameLayout {
    ImageView image1, image2, image3, image4, image5, image6, image7;
    TextView textDate;
    int month, dateInt, dayInt;
    Calendar calendar;
    String day;
    public DateItemView(Context context) {
        super(context);
        init();
    }

    public DateItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public interface OnStarClickListener {
        public void onStarClick(SpotItemView view, LocationData data);
    }

    OnStarClickListener mListener;
    public void setOnStarClickListener(OnStarClickListener listener){
        mListener = listener;
    }


    public interface OnImage1ClickListener {
        public void onImageClick(DateItemView view, Calendar calendar);
    }
    OnImage1ClickListener image1ClickListener;
    public void setOnImage1ClickListener(OnImage1ClickListener listener){
        image1ClickListener = listener;
    }
    public interface OnImage2ClickListener {
        public void onImageClick(DateItemView view, Calendar calendar);
    }
    OnImage2ClickListener image2ClickListener;
    public void setOnImage2ClickListener(OnImage2ClickListener listener){
        image2ClickListener = listener;
    }
    public interface OnImage3ClickListener {
        public void onImageClick(DateItemView view, Calendar calendar);
    }
    OnImage3ClickListener image3ClickListener;
    public void setOnImage3ClickListener(OnImage3ClickListener listener){
        image3ClickListener = listener;
    }
    public interface OnImage4ClickListener {
        public void onImageClick(DateItemView view, Calendar calendar);
    }
    OnImage4ClickListener image4ClickListener;
    public void setOnImage4ClickListener(OnImage4ClickListener listener){
        image4ClickListener = listener;
    }
    public interface OnImage5ClickListener {
        public void onImageClick(DateItemView view, Calendar calendar);
    }
    OnImage5ClickListener image5ClickListener;
    public void setOnImage5ClickListener(OnImage5ClickListener listener){
        image5ClickListener = listener;
    }
    public interface OnImage6ClickListener {
        public void onImageClick(DateItemView view, Calendar calendar);
    }
    OnImage6ClickListener image6ClickListener;
    public void setOnImage6ClickListener(OnImage6ClickListener listener){
        image6ClickListener = listener;
    }
    public interface OnImage7ClickListener {
        public void onImageClick(DateItemView view, Calendar calendar);
    }
    OnImage7ClickListener image7ClickListener;
    public void setOnImage7ClickListener(OnImage7ClickListener listener){
        image7ClickListener = listener;
    }

    private void init() {
        inflate(getContext(), R.layout.item_date, this);
        textDate = (TextView)findViewById(R.id.text_email);
        image1 = (ImageView)findViewById(R.id.image_mon);
        image1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image1ClickListener != null){
                    image1ClickListener.onImageClick(DateItemView.this, calendar);
                }
            }
        });
        image2 = (ImageView)findViewById(R.id.image_tue);
        image2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image1ClickListener != null){
                    image2ClickListener.onImageClick(DateItemView.this, calendar);
                }
            }
        });
        image3 = (ImageView)findViewById(R.id.image_wen);
        image3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image1ClickListener != null){
                    image3ClickListener.onImageClick(DateItemView.this, calendar);
                }
            }
        });
        image4 = (ImageView)findViewById(R.id.image_thu);
        image4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image1ClickListener != null){
                    image4ClickListener.onImageClick(DateItemView.this, calendar);
                }
            }
        });
        image5 = (ImageView)findViewById(R.id.image_fri);
        image5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image1ClickListener != null){
                    image5ClickListener.onImageClick(DateItemView.this, calendar);
                }
            }
        });
        image6 = (ImageView)findViewById(R.id.image_sat);
        image6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image1ClickListener != null){
                    image6ClickListener.onImageClick(DateItemView.this, calendar);
                }
            }
        });
        image7 = (ImageView)findViewById(R.id.image_sun);
        image7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image1ClickListener != null){
                    image7ClickListener.onImageClick(DateItemView.this, calendar);
                }
            }
        });

        setClickable(false);
    }
    public void setData(DateItem data){
        String date = TimeManager.getInstance().getSpotTime(System.currentTimeMillis());
        calendar = Calendar.getInstance(Locale.KOREA);

        textDate.setText(date);
        day = TimeManager.getInstance().getSpotDay(System.currentTimeMillis());

        if(day.equals("월요일")) {
            image1.setSelected(true);

            image1.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_mon_selector));
            image2.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_tue_selector));
            image3.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_wen_selector));
            image4.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_thu_selector));
            image5.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_fri_selector));
            image6.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_sat_selector));
            image7.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_sun_selector));
        }else if(day.equals("화요일")){
            image1.setSelected(true);

            image1.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_tue_selector));
            image2.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_wen_selector));
            image3.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_thu_selector));
            image4.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_fri_selector));
            image5.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_sat_selector));
            image6.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_sun_selector));
            image7.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_mon_selector));
        }else if(day.equals("수요일")){
            image1.setSelected(true);

            image1.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_wen_selector));
            image2.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_thu_selector));
            image3.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_fri_selector));
            image4.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_sat_selector));
            image5.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_sun_selector));
            image6.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_mon_selector));
            image7.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_tue_selector));
        }else if(day.equals("목요일")){
            image1.setSelected(true);

            image1.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_thu_selector));
            image2.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_fri_selector));
            image3.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_sat_selector));
            image4.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_sun_selector));
            image5.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_mon_selector));
            image6.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_tue_selector));
            image7.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_wen_selector));
        }else if(day.equals("금요일")){
            image1.setSelected(true);

            image1.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_fri_selector));
            image2.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_sat_selector));
            image3.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_sun_selector));
            image4.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_mon_selector));
            image5.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_tue_selector));
            image6.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_wen_selector));
            image7.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_thu_selector));
        }else if(day.equals("토요일")){
            image1.setSelected(true);

            image1.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_sat_selector));
            image2.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_sun_selector));
            image3.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_mon_selector));
            image4.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_tue_selector));
            image5.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_wen_selector));
            image6.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_thu_selector));
            image7.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_fri_selector));
        }else if(day.equals("일요일")){

            image1.setSelected(true);
            image1.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_sun_selector));
            image2.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_mon_selector));
            image3.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_tue_selector));
            image4.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_wen_selector));
            image5.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_thu_selector));
            image6.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_fri_selector));
            image7.setImageDrawable(getResources().getDrawable(R.drawable.spot_date_sat_selector));
        }
    }
}
