package com.tacademy.qodbtn41.gosurf.fragment.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.SpotItemData;

/**
 * Created by UserPC on 2015-11-01.
 */
public class SpotItemView extends FrameLayout {
    SpotItemData spotData;

    ImageView statusImage;
    TextView statusText, spotName;
    ImageView star;

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
        this.statusImage = (ImageView)findViewById(R.id.image_status);
        this.statusText = (TextView)findViewById(R.id.text_status);
        this.spotName = (TextView)findViewById(R.id.text_spot_name);
        this.star = (ImageView)findViewById(R.id.image_star);
    }

    public SpotItemData getSpotData() {
        return spotData;
    }

    public void setData(SpotItemData spotData){
        this. spotData = spotData;

        this.statusImage.setImageDrawable(this.spotData.getStatusImage());
        this.statusText.setText(this.spotData.getStatusText());
        this.spotName.setText(this.spotData.getSpotName());
        if(this.spotData.getChecked()){
            this.star.setImageDrawable(getResources().getDrawable(R.drawable.star_yellow));
        }
        this.star.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
    }
}
