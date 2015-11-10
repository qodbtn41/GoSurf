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

    ImageView statusImageView;
    TextView statusView, spotNameView;
    ImageView starView;

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
    }

    public SpotItemData getSpotData() {
        return spotData;
    }

    public void setData(SpotItemData spotData){
        this. spotData = spotData;

        this.statusImageView.setImageDrawable(this.spotData.getStatusImage());
        this.statusView.setText(this.spotData.getStatusText());
        this.spotNameView.setText(this.spotData.getSpotName());
        if(this.spotData.getChecked()){
            this.starView.setImageDrawable(getResources().getDrawable(R.drawable.bookmark_activated));
        }else {
            this.starView.setImageDrawable(getResources().getDrawable(R.drawable.bookmark_inactivated));
        }
    }
}
