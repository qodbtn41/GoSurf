package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.PictureItem;

/**
 * Created by UserPC on 2015-11-04.
 * 사진 뷰는 사진만 보여준다.
 */
public class PictureItemView extends FrameLayout implements Checkable{
    PictureItem pictureItem;
    ImageView pictureView;
    ImageView checkDelete;
    DisplayImageOptions options;

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
        this.checkDelete = (ImageView)findViewById(R.id.image_delete);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .build();
    }

    public PictureItem getPictureItem() {
        return pictureItem;
    }

    public void setData(PictureItem pictureItem){
        this.pictureItem = pictureItem;

        ImageLoader.getInstance().displayImage(pictureItem.getPicture(), pictureView, options);
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
