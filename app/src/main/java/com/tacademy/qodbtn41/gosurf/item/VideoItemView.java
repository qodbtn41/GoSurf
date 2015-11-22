package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.VideoItem;

/**
 * Created by UserPC on 2015-11-04.
 */
public class VideoItemView extends FrameLayout {
    VideoItem videoItem;
    VideoView videoView;
    private String _id;

    public VideoItemView(Context context) {
        super(context);
        init();
    }

    public VideoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public String get_id() {
        return _id;
    }

    private void init() {
        inflate(getContext(), R.layout.item_video, this);
        this.videoView = (VideoView) findViewById(R.id.video);
    }

    public VideoItem getPictureItemData() {
        return videoItem;
    }

    public void setData(VideoItem data) {
        _id = data.getId();
        this.videoItem = data;
        this.videoView.setVideoPath(this.videoItem.getVideo());
    }
}
