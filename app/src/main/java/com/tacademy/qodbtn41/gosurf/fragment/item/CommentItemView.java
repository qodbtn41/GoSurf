package com.tacademy.qodbtn41.gosurf.fragment.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.CommentItemData;

/**
 * Created by UserPC on 2015-11-03.
 */
public class CommentItemView extends FrameLayout {
    CommentItemData data;
    TextView timeView, userNameView, contentView;
    public CommentItemView(Context context) {
        super(context);
        init();
    }

    public CommentItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.item_comment, this);
        this.timeView = (TextView)findViewById(R.id.text_time);
        this.userNameView = (TextView)findViewById(R.id.text_user_name);
        this.contentView = (TextView)findViewById(R.id.text_content);
    }

    public CommentItemData getData(){
        return data;
    }
    public void setData(CommentItemData data){
        this.data = data;

        this.timeView.setText(this.data.getTime());
        this.contentView.setText(this.data.getContent());
        this.userNameView.setText(this.data.getUserName());
    }
}
