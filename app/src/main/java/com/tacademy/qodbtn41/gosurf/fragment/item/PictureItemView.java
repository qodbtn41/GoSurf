package com.tacademy.qodbtn41.gosurf.fragment.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.PictureItemData;

/**
 * Created by UserPC on 2015-11-04.
 */
public class PictureItemView extends FrameLayout implements Checkable{
    PictureItemData pictureItemData;
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

    public PictureItemData getPictureItemData() {
        return pictureItemData;
    }

    public void setData(PictureItemData pictureItemData){
        this.pictureItemData = pictureItemData;

        this.pictureView.setImageDrawable(this.pictureItemData.getPicture());
        this.userNameView.setText(this.pictureItemData.getUserName());
        this.timeView.setText(this.pictureItemData.getTime());
        this.contentView.setText(this.pictureItemData.getContent());
        this.commentCountView.setText(this.pictureItemData.getCommentCount());
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
