package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.R;

/**
 * Created by UserPC on 2015-11-20.
 */
public class FacebookLoginButton extends FrameLayout {
    TextView textView;
    ImageView imageView;
    public FacebookLoginButton(Context context) {
        super(context);
        init();
    }

    public FacebookLoginButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_facebook_login_btn, this);
        textView = (TextView)this.findViewById(R.id.text_facebook_login);
        imageView = (ImageView)this.findViewById(R.id.image_facebook_login);
        textView.setText(getResources().getString(R.string.facebook_login));
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.facebook_icon));
    }

    public void setText(String text){
        textView.setText(text);
    }
}
