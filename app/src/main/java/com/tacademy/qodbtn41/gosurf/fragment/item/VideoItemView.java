package com.tacademy.qodbtn41.gosurf.fragment.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.VideoItemData;

/**
 * Created by UserPC on 2015-11-04.
 */
public class VideoItemView extends FrameLayout implements Checkable{
    VideoItemData videoItemData;
    VideoView videoView;
    TextView userNameView, timeView, contentView, commentCountView;
    ImageView checkDelete;

    public VideoItemView(Context context) {
        super(context);
        init();
    }

    public VideoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.item_video, this);
        this.videoView = (VideoView)findViewById(R.id.video);
        this.userNameView = (TextView)findViewById(R.id.text_user_name);
        this.timeView = (TextView)findViewById(R.id.text_time);
        this.contentView = (TextView)findViewById(R.id.text_content);
        this.commentCountView = (TextView)findViewById(R.id.text_comment_count);
        this.checkDelete = (ImageView)findViewById(R.id.image_delete);
    }

    public VideoItemData getPictureItemData() {
        return videoItemData;
    }

    public void setData(VideoItemData videoItemData){
        this.videoItemData = videoItemData;

        this.videoView.setVideoURI(this.videoItemData.getVideo());
        this.userNameView.setText(this.videoItemData.getUserName());
        this.timeView.setText(this.videoItemData.getTime());
        this.contentView.setText(this.videoItemData.getContent());
        this.commentCountView.setText(this.videoItemData.getCommentCount());
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
