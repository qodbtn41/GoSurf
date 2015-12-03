package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.DateData;
import com.tacademy.qodbtn41.gosurf.data.DateItem;
import com.tacademy.qodbtn41.gosurf.data.LocationData;
import com.tacademy.qodbtn41.gosurf.data.ShopLinkItem;
import com.tacademy.qodbtn41.gosurf.data.SpotDetailItem;
import com.tacademy.qodbtn41.gosurf.manager.TimeManager;

import java.util.Calendar;
import java.util.List;

/**
 * Created by UserPC on 2015-11-01.
 */
public class SpotItemView extends FrameLayout implements Checkable {
    LocationData locationData;
    ImageView statusImageView;
    TextView statusView, spotNameView;
    ImageView starView;

    DateItemView dateItemView;
    SpotDetailItemView spotDetailItemView1, spotDetailItemView2, spotDetailItemView3, spotDetailItemView4, spotDetailItemView5, spotDetailItemView6, spotDetailItemView7, spotDetailItemView8;
    ShopLinkItemView shopLinkItemView;

    String _id;

    public static final int WEATHER_GREAT = 1;
    public static final int WEATHER_NORMAL = 2;
    public static final int WEATHER_BAD = 3;
    public static final int WEATHER_WARNING = 4;

    DisplayImageOptions options;

    public SpotItemView(Context context) {
        super(context);
        this.init();
    }
    public SpotItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public interface OnStarClickListener {
        public void onStarClick(SpotItemView view, LocationData data);
    }
    OnStarClickListener mListener;
    public void setOnStarClickListener(OnStarClickListener listener){
        mListener = listener;
    }

    public interface  OnShopLinkClickListener {
        public void onShopLinkClick(SpotItemView view, LocationData data);
    }
    OnShopLinkClickListener shopLinkListener;
    public void setOnShopLinkClickListener(OnShopLinkClickListener listener){
        shopLinkListener = listener;
    }

    public interface  OnSpotClickListener {
        public void onSpotClick(SpotItemView view, LocationData data);
    }
    OnSpotClickListener spotClickListener;
    public void setOnSpotClickListener(OnSpotClickListener listener){
        spotClickListener = listener;
    }

    private void init() {
        inflate(getContext(), R.layout.item_spot, this);
        this.statusImageView = (ImageView)findViewById(R.id.image_status);
        this.statusView = (TextView)findViewById(R.id.text_status);
        this.spotNameView = (TextView)findViewById(R.id.text_spot_name);
        this.starView = (ImageView)findViewById(R.id.image_star);

        dateItemView = (DateItemView)findViewById(R.id.view_date);
        spotDetailItemView1 = (SpotDetailItemView)findViewById(R.id.view_detail_0);
        spotDetailItemView2 = (SpotDetailItemView)findViewById(R.id.view_detail_1);
        spotDetailItemView3 = (SpotDetailItemView)findViewById(R.id.view_detail_2);
        spotDetailItemView4 = (SpotDetailItemView)findViewById(R.id.view_detail_3);
        spotDetailItemView5 = (SpotDetailItemView)findViewById(R.id.view_detail_4);
        spotDetailItemView6 = (SpotDetailItemView)findViewById(R.id.view_detail_5);
        spotDetailItemView7 = (SpotDetailItemView)findViewById(R.id.view_detail_6);
        spotDetailItemView8 = (SpotDetailItemView)findViewById(R.id.view_detail_7);
        shopLinkItemView = (ShopLinkItemView)findViewById(R.id.view_shop_link);


        dateItemView.setOnImage1ClickListener(new DateItemView.OnImage1ClickListener() {
            @Override
            public void onImageClick(DateItemView view, Calendar calendar) {
                setDateData(0);
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.DATE, 0);
                view.textDate.setText(TimeManager.getInstance().getSpotTime(calendar.getTimeInMillis()));
                view.image1.setSelected(true);
                view.image2.setSelected(false);
                view.image3.setSelected(false);
                view.image4.setSelected(false);
                view.image5.setSelected(false);
                view.image6.setSelected(false);
                view.image7.setSelected(false);
            }
        });
        dateItemView.setOnImage2ClickListener(new DateItemView.OnImage2ClickListener() {
            @Override
            public void onImageClick(DateItemView view, Calendar calendar) {
                setDateData(1);
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.DATE, 1);
                view.textDate.setText(TimeManager.getInstance().getSpotTime(calendar.getTimeInMillis()));
                view.image1.setSelected(false);
                view.image2.setSelected(true);
                view.image3.setSelected(false);
                view.image4.setSelected(false);
                view.image5.setSelected(false);
                view.image6.setSelected(false);
                view.image7.setSelected(false);
            }
        });
        dateItemView.setOnImage3ClickListener(new DateItemView.OnImage3ClickListener() {
            @Override
            public void onImageClick(DateItemView view, Calendar calendar) {
                setDateData(2);
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.DATE, 2);
                view.textDate.setText(TimeManager.getInstance().getSpotTime(calendar.getTimeInMillis()));
                view.image1.setSelected(false);
                view.image2.setSelected(false);
                view.image3.setSelected(true);
                view.image4.setSelected(false);
                view.image5.setSelected(false);
                view.image6.setSelected(false);
                view.image7.setSelected(false);
            }
        });
        dateItemView.setOnImage4ClickListener(new DateItemView.OnImage4ClickListener() {
            @Override
            public void onImageClick(DateItemView view, Calendar calendar) {
                setDateData(3);
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.DATE, 3);
                view.textDate.setText(TimeManager.getInstance().getSpotTime(calendar.getTimeInMillis()));
                view.image1.setSelected(false);
                view.image2.setSelected(false);
                view.image3.setSelected(false);
                view.image4.setSelected(true);
                view.image5.setSelected(false);
                view.image6.setSelected(false);
                view.image7.setSelected(false);
            }
        });
        dateItemView.setOnImage5ClickListener(new DateItemView.OnImage5ClickListener() {
            @Override
            public void onImageClick(DateItemView view, Calendar calendar) {
                setDateData(4);
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.DATE, 4);
                view.textDate.setText(TimeManager.getInstance().getSpotTime(calendar.getTimeInMillis()));
                view.image1.setSelected(false);
                view.image2.setSelected(false);
                view.image3.setSelected(false);
                view.image4.setSelected(false);
                view.image5.setSelected(true);
                view.image6.setSelected(false);
                view.image7.setSelected(false);
            }
        });
        dateItemView.setOnImage6ClickListener(new DateItemView.OnImage6ClickListener() {
            @Override
            public void onImageClick(DateItemView view, Calendar calendar) {
                setDateData(5);
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.DATE, 5);
                view.textDate.setText(TimeManager.getInstance().getSpotTime(calendar.getTimeInMillis()));
                view.image1.setSelected(false);
                view.image2.setSelected(false);
                view.image3.setSelected(false);
                view.image4.setSelected(false);
                view.image5.setSelected(false);
                view.image6.setSelected(true);
                view.image7.setSelected(false);
            }
        });
        dateItemView.setOnImage7ClickListener(new DateItemView.OnImage7ClickListener() {
            @Override
            public void onImageClick(DateItemView view, Calendar calendar) {
                setDateData(6);
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.DATE, 6);
                view.textDate.setText(TimeManager.getInstance().getSpotTime(calendar.getTimeInMillis()));
                view.image1.setSelected(false);
                view.image2.setSelected(false);
                view.image3.setSelected(false);
                view.image4.setSelected(false);
                view.image5.setSelected(false);
                view.image6.setSelected(false);
                view.image7.setSelected(true);
            }
        });

        starView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onStarClick(SpotItemView.this, locationData);
                }
            }
        });

        shopLinkItemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopLinkListener != null){
                    shopLinkListener.onShopLinkClick(SpotItemView.this, locationData);
                }
            }
        });

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .build();
    }

    boolean isChecked;

    private void drawCheck() {
        if (isChecked) {
            open();
        } else {
            close();
        }
    }

    @Override
    public void setChecked(boolean checked) {
        if (isChecked != checked) {
            isChecked = checked;
            drawCheck();
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }

    private void setDateData(int day){
        SpotDetailItem item = new SpotDetailItem();
        List<DateData> dateDatas = locationData.getWeather_weeks();
        String[] hour = {"12am", "3am", "6am", "9am", "12pm", "3pm", "6pm", "9pm"};
        item.setData(dateDatas.get(day).getWeather_hours().get(0));
        item.setTime(hour[0]);
        spotDetailItemView1.setData(item);

        item.setData(dateDatas.get(day).getWeather_hours().get(1));
        item.setTime(hour[1]);
        spotDetailItemView2.setData(item);

        item.setData(dateDatas.get(day).getWeather_hours().get(2));
        item.setTime(hour[2]);
        spotDetailItemView3.setData(item);

        item.setData(dateDatas.get(day).getWeather_hours().get(3));
        item.setTime(hour[3]);
        spotDetailItemView4.setData(item);

        item.setData(dateDatas.get(day).getWeather_hours().get(4));
        item.setTime(hour[4]);
        spotDetailItemView5.setData(item);

        item.setData(dateDatas.get(day).getWeather_hours().get(5));
        item.setTime(hour[5]);
        spotDetailItemView6.setData(item);

        item.setData(dateDatas.get(day).getWeather_hours().get(6));
        item.setTime(hour[6]);
        spotDetailItemView7.setData(item);

        item.setData(dateDatas.get(day).getWeather_hours().get(7));
        item.setTime(hour[7]);
        spotDetailItemView8.setData(item);
    }

    public LocationData getLocationData() {
        return locationData;
    }

    public void setData(LocationData weatherData){
        this.locationData = weatherData;
        setStatus(locationData);
        spotNameView.setText(locationData.getLocation_name());

        if(locationData.is_bookmarked()){
            starView.setImageDrawable(getResources().getDrawable(R.drawable.bookmark_activated));
        }else{
            starView.setImageDrawable(getResources().getDrawable(R.drawable.bookmark_inactivated));
        }

        DateItem dateItem = new DateItem();
        dateItemView.setData(dateItem);

        ShopLinkItem shopLinkItem = new ShopLinkItem();
        shopLinkItem.setLocationCategory(weatherData.getLocation_category());
        shopLinkItemView.setData(shopLinkItem);

        setDateData(0);
    }



    private void setStatus(LocationData locationData){
        int position = TimeManager.getInstance().getCurrent();
        switch(locationData.getWeather_weeks().get(0).getWeather_hours().get(position).getWeather_indicator()){
            case WEATHER_GREAT :{
                statusView.setText(getResources().getString(R.string.status_great));
                statusView.setTextColor(getResources().getColor(R.color.status_text_color_great));
                statusImageView.setImageDrawable(getResources().getDrawable(R.drawable.spot_status_great));
                break;
            }
            case WEATHER_NORMAL : {
                statusView.setText(getResources().getString(R.string.status_normal));
                statusView.setTextColor(getResources().getColor(R.color.status_text_color_normal));
                statusImageView.setImageDrawable(getResources().getDrawable(R.drawable.spot_status_normal));
                break;
            }
            case WEATHER_BAD : {
                statusView.setText(getResources().getString(R.string.status_bad));
                statusView.setTextColor(getResources().getColor(R.color.status_text_color_bad));
                statusImageView.setImageDrawable(getResources().getDrawable(R.drawable.spot_status_bad));
                break;
            }
            case WEATHER_WARNING : {
                statusView.setText(getResources().getString(R.string.status_warning));
                statusView.setTextColor(getResources().getColor(R.color.status_text_color_warning));
                statusImageView.setImageDrawable(getResources().getDrawable(R.drawable.spot_status_warning));
                break;
            }
        }
    }

    public ImageView getStarView() {
        return starView;
    }

    public void open(){
        View relative = findViewById(R.id.spot_detail);
        relative.setVisibility(VISIBLE);
//        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.top_down);
//        relative.startAnimation(anim);
//        dateItemView.setVisibility(VISIBLE);
//        spotDetailItemView1.setVisibility(VISIBLE);
//        spotDetailItemView2.setVisibility(VISIBLE);
//        spotDetailItemView3.setVisibility(VISIBLE);
//        spotDetailItemView4.setVisibility(VISIBLE);
//        spotDetailItemView5.setVisibility(VISIBLE);
//        spotDetailItemView6.setVisibility(VISIBLE);
//        spotDetailItemView7.setVisibility(VISIBLE);
//        spotDetailItemView8.setVisibility(VISIBLE);
//        shopLinkItemView .setVisibility(VISIBLE);
    }

    public void close(){
        View relative = findViewById(R.id.spot_detail);


        relative.setVisibility(GONE);
//        dateItemView.setVisibility(GONE);
//        spotDetailItemView1.setVisibility(GONE);
//        spotDetailItemView2.setVisibility(GONE);
//        spotDetailItemView3.setVisibility(GONE);
//        spotDetailItemView4.setVisibility(GONE);
//        spotDetailItemView5.setVisibility(GONE);
//        spotDetailItemView6.setVisibility(GONE);
//        spotDetailItemView7.setVisibility(GONE);
//        spotDetailItemView8.setVisibility(GONE);
//        shopLinkItemView .setVisibility(GONE);
    }

    String dayS;
    private String getDay(int day){
        switch(day){
            case 1:{
                dayS = "월요일";
            }
            case 2:{
                dayS = "화요일";

            }
            case 3:{
                dayS = "수요일";

            }
            case 4:{
                dayS = "목요일";

            }
            case 5:{
                dayS = "금요일";

            }
            case 6:{
                dayS = "토요일";

            }
            case 7:
            default:{
                dayS = "일요일";
            }
        }
        return dayS;
    }
}
