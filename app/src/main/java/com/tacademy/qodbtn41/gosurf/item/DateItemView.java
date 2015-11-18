package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.DateItem;

/**
 * Created by UserPC on 2015-11-02.
 */
public class DateItemView extends FrameLayout {
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
    }
    public void setData(DateItem data){

    }
}
