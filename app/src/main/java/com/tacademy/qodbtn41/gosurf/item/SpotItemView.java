package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.SpotItem;

/**
 * Created by UserPC on 2015-11-01.
 */
public class SpotItemView extends FrameLayout {
    SpotItem spotData;

    ImageView statusImageView;
    TextView statusView, spotNameView;
    ImageView starView;

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

    private void init() {
        inflate(getContext(), R.layout.item_spot, this);
        this.statusImageView = (ImageView)findViewById(R.id.image_status);
        this.statusView = (TextView)findViewById(R.id.text_status);
        this.spotNameView = (TextView)findViewById(R.id.text_spot_name);
        this.starView = (ImageView)findViewById(R.id.image_star);

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

    public SpotItem getSpotData() {
        return spotData;
    }

    public void setData(SpotItem spotData){
        this.spotData = spotData;
        setStatus(spotData);
        spotNameView.setText(spotData.getSpot_name());
        setStar(spotData);


    }

    //북마크를 받아서 내 아이디와 하나하나비교하여 같은게 있으면 노란별, 아니면 빈별을 그린다.
    private void setStar(SpotItem spotData) {

        starView.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
    }

    private void setStatus(SpotItem spotItem){
        switch(spotItem.getWeather_indicator()){
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
}
