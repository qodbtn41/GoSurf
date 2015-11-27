package com.tacademy.qodbtn41.gosurf.item.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.tacademy.qodbtn41.gosurf.R;

/**
 * Created by UserPC on 2015-11-23.
 */
public class CustomPopupWindow extends PopupWindow {
    Context mContext;
    ImageView modifyView, deleteView;

    public CustomPopupWindow(Context context) {
        super(context);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.popup_window_timeline, null);
        modifyView = (ImageView)view.findViewById(R.id.image_popup_modify);
        deleteView = (ImageView)view.findViewById(R.id.image_popup_delete);
        setContentView(view);

        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public CustomPopupWindow(Context context, String articleId, String commentId) {
        super(context);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.popup_window_timeline, null);
        ImageView modifyView = (ImageView)view.findViewById(R.id.image_popup_modify);
        ImageView deleteView = (ImageView)view.findViewById(R.id.image_popup_delete);
        setContentView(view);

        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public ImageView getModifyView() {
        return modifyView;
    }

    public ImageView getDeleteView() {
        return deleteView;
    }
}
