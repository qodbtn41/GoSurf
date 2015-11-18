package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.VideoItem;

/**
 * Created by UserPC on 2015-11-04.
 */
public class VideoItemView extends FrameLayout implements Checkable{
    VideoItem videoItemData;
    VideoView videoView;
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
        this.checkDelete = (ImageView)findViewById(R.id.image_delete);
    }

    public VideoItem getPictureItemData() {
        return videoItemData;
    }

    public void setData(VideoItem videoItemData){
        this.videoItemData = videoItemData;
        this.videoView.setVideoPath(this.videoItemData.getVideo());
    }

    boolean isChecked = false;

    private void drawCheck() {
        if (isChecked) {
            checkDelete.setImageResource(android.R.drawable.checkbox_on_background);
        } else {
            checkDelete.setImageResource(android.R.drawable.checkbox_off_background);
        }
    }

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
