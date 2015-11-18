package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.TextItem;

/**
 * Created by UserPC on 2015-11-19.
 */
public class TextItemView extends FrameLayout {

    TextItem textItem;
    TextView contentView, userNameView, createdTimeView;
    ImageViewRounded profileView;

    public TextItemView(Context context) {
        super(context);
        init();
    }
    public TextItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextItem getDetailTextItem() {
        return textItem;
    }

    private void init() {
        inflate(getContext(), R.layout.item_text, this);
        this.contentView = (TextView)findViewById(R.id.text_content);
        this.userNameView = (TextView)findViewById(R.id.text_user_name);
        this.createdTimeView = (TextView)findViewById(R.id.text_created_time);
        this.profileView = (ImageViewRounded)findViewById(R.id.image_profile);
    }

    public void setData(TextItem data) {
        this.textItem = data;

        contentView.setText(data.getContent());
        userNameView.setText(data.getUserName());
        createdTimeView.setText(data.getCreatedTime());
        profileView.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));// 바꿔야한다.
    }
}
