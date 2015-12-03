package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.CommentItem;
import com.tacademy.qodbtn41.gosurf.manager.TimeManager;

/**
 * Created by UserPC on 2015-11-03.
 */
public class CommentItemView extends FrameLayout {
    CommentItem data;
    TextView timeView, userNameView, contentView;
    ImageView moreOptionsImage;
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
        this.moreOptionsImage = (ImageView)findViewById(R.id.image_comment_menu);

    }

    public CommentItem getData(){
        return data;
    }
    public void setData(CommentItem data){
        this.data = data;

        this.timeView.setText(TimeManager.getInstance().getCommentTime(this.data.getCreated_date()));
        this.contentView.setText(this.data.getContent());
        this.userNameView.setText(this.data.getUser_name());

    }
}
