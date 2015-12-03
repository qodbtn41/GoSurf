package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.R;

/**
 * Created by UserPC on 2015-12-02.
 */
public class MenuTextView extends FrameLayout {
    TextView menuText, contentText;
    public MenuTextView(Context context) {
        super(context);
        init();
    }

    public MenuTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.item_push_menu_text, this);
        menuText = (TextView)findViewById(R.id.text_name);
        contentText = (TextView)findViewById(R.id.text_content);
    }

    public void setData(String name, String content){
        menuText.setText(name);
        contentText.setText(content);
    }
}
