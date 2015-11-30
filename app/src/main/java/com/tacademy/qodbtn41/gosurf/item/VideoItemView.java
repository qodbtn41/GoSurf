package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.VideoItem;

/**
 * Created by UserPC on 2015-11-04.
 */
public class VideoItemView extends FrameLayout {
    VideoItem videoItem;

    VideoView videoView;
    MediaPlayer mPlayer;
    ImageView controller;


    private String _id;

    public VideoItemView(Context context) {
        super(context);
        init(context);
    }

    public VideoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public String get_id() {
        return _id;
    }

    private void init(Context context) {
        inflate(getContext(), R.layout.item_video, this);
        videoView = (VideoView)findViewById(R.id.video);
        controller = (ImageView)findViewById(R.id.imageView14);
        controller.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    videoView.pause();
                }else{
                    videoView.start();
                }
            }
        });

    }

    public void setData(VideoItem data) {
        _id = data.getId();
        this.videoItem = data;
        Uri uri = Uri.parse(this.videoItem.getVideo());
        videoView.setVideoURI(uri);
    }
}
