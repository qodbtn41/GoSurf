package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.SpotDetailItem;

/**
 * Created by UserPC on 2015-11-01.
 */
public class SpotDetailItemView extends FrameLayout{
    public SpotDetailItemView(Context context) {
        super(context);
        init();
    }

    public SpotDetailItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.item_spot_detail, this);
    }
    public void setData(SpotDetailItem data){

    }
}
