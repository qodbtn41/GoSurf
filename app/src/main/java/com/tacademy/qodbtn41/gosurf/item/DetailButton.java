package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.R;

/**
 * Created by UserPC on 2015-11-16.
 */
public class DetailButton extends FrameLayout {
    ImageView imageView;
    TextView textView;
    public DetailButton(Context context) {
        super(context);
        init();
    }

    public DetailButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_detail_btn, this);
        imageView = (ImageView)findViewById(R.id.image_btn_shop_detail);
        textView = (TextView)findViewById(R.id.text_btn_shop_detail);
    }

    public void setData(String text, Drawable image) {
        imageView.setImageDrawable(image);
        textView.setText(text);
    }

    public void setTextViewColor(boolean isliked){
        if(isliked){
            textView.setTextColor(getResources().getColor(R.color.like_text_color));
        }else{
            textView.setTextColor(getResources().getColor(R.color.unlike_text_color));
        }
    }
}
