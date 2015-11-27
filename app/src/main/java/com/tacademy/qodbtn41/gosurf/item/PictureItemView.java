package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.PictureItem;

/**
 * Created by UserPC on 2015-11-04.
 * 사진 뷰는 사진만 보여준다.
 */
public class PictureItemView extends FrameLayout {
    PictureItem pictureItem;
    ImageView pictureView;
    DisplayImageOptions options;
    private String _id;

    public PictureItemView(Context context) {
        super(context);
        init();
    }

    public PictureItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public String get_id() {
        return _id;
    }

    private void init() {
        inflate(getContext(), R.layout.item_picture, this);
        this.pictureView = (ImageView)findViewById(R.id.image_picture_timeline);

        options = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .showImageOnLoading(R.drawable.loading1)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.loading_error)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .build();
    }

    public PictureItem getPictureItem() {
        return pictureItem;
    }

    public void setData(PictureItem data){
        this.pictureItem = data;
        _id = data.getId();
        ImageLoader.getInstance().displayImage(data.getPicture(), pictureView, options);
    }
}
