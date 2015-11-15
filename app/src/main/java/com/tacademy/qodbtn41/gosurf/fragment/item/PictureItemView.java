package com.tacademy.qodbtn41.gosurf.fragment.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.PictureItem;

/**
 * Created by UserPC on 2015-11-04.
 */
public class PictureItemView extends FrameLayout implements Checkable{
    PictureItem pictureItem;
    ImageView pictureView;
    TextView userNameView, timeView, contentView, commentCountView;
    ImageView checkDelete;

    public PictureItemView(Context context) {
        super(context);
        init();
    }

    public PictureItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.item_picture, this);
        this.pictureView = (ImageView)findViewById(R.id.image_picture_timeline);
        this.userNameView = (TextView)findViewById(R.id.text_user_name);
        this.timeView = (TextView)findViewById(R.id.text_time);
        this.contentView = (TextView)findViewById(R.id.text_content);
        this.commentCountView = (TextView)findViewById(R.id.text_comment_count);
        this.checkDelete = (ImageView)findViewById(R.id.image_delete);
    }

    public PictureItem getPictureItem() {
        return pictureItem;
    }

    public void setData(PictureItem pictureItem){
        this.pictureItem = pictureItem;

        this.pictureView.setImageDrawable(this.pictureItem.getPicture());
        this.userNameView.setText(this.pictureItem.getUserName());
        this.timeView.setText(this.pictureItem.getTime());
        this.contentView.setText(this.pictureItem.getContent());
        this.commentCountView.setText(this.pictureItem.getCommentCount());
    }

    boolean isChecked = false;

    private void drawCheck() {
        if (isChecked) {
            checkDelete.setImageResource(android.R.drawable.checkbox_on_background);
        } else {
            checkDelete.setImageResource(android.R.drawable.checkbox_off_background);
        }    }

    @Override
    public void setChecked(boolean checked) {
        if (checked != isChecked) {
            isChecked = checked;
            drawCheck();
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }

}
