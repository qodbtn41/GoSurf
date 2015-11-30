package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.DateItem;

/**
 * Created by UserPC on 2015-11-02.
 */
public class DateItemView extends FrameLayout {
    ImageView image1, image2, image3, image4, image5, image6, image7;
    TextView textDate;

    public DateItemView(Context context) {
        super(context);
        init();
    }

    public DateItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.item_date, this);
        setClickable(false);
    }
    public void setData(DateItem data){
    }


}
