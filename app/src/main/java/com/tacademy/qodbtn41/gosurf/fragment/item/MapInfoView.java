package com.tacademy.qodbtn41.gosurf.fragment.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.MapActivity;
import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.SpotItem;

/**
 * Created by UserPC on 2015-11-15.
 */
public class MapInfoView extends FrameLayout {
    SpotItem spotData;
    ImageView spotIndicator;
    TextView spotNameView;

    public MapInfoView(Context context) {
        super(context);
        init();
    }

    public MapInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_spot_info, this);
        spotIndicator = (ImageView)findViewById(R.id.image_spot_indicator);
        spotNameView = (TextView)findViewById(R.id.text_selected_spot_name);
    }

    public void setData(SpotItem spotData){
        this.spotData = spotData;

        spotNameView.setText(spotData.getSpot_name());
        switch(spotData.getWeather_indicator()){
            case MapActivity.WEATHER_GREAT :{
                spotIndicator.setImageDrawable(getResources().getDrawable(R.drawable.great_marker));
                break;
            }
            case MapActivity.WEATHER_NORMAL:{
                spotIndicator.setImageDrawable(getResources().getDrawable(R.drawable.normal_marker));
                break;
            }
            case MapActivity.WEATHER_BAD:{
                spotIndicator.setImageDrawable(getResources().getDrawable(R.drawable.bad_marker));
                break;
            }
            case MapActivity.WEATHER_WARNING:{
                spotIndicator.setImageDrawable(getResources().getDrawable(R.drawable.warning_marker));
                break;
            }
        }
    }
}
