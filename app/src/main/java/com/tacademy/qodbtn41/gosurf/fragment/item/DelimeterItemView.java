package com.tacademy.qodbtn41.gosurf.fragment.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.DelimeterItemData;

/**
 * Created by UserPC on 2015-11-02.
 */
public class DelimeterItemView extends FrameLayout {
    public DelimeterItemView(Context context) {
        super(context);
        init();
    }

    public DelimeterItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        inflate(getContext(), R.layout.item_delimeter, this);
    }
    public void setData(DelimeterItemData data){

    }
}
