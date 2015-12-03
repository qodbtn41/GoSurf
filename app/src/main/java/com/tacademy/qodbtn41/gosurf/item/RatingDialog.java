package com.tacademy.qodbtn41.gosurf.item;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.R;

/**
 * Created by UserPC on 2015-12-03.
 */
public class RatingDialog extends DialogFragment implements RatingBar.OnRatingBarChangeListener {
    public RatingBar ratingBar;
    TextView shopNameView;
    Button btnFinish;
    String shopName = "testtest";
    float rating;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        Dialog dialog = getDialog();

        View view = inflater.inflate(R.layout.dialog_rating, container, false);
        ratingBar = (RatingBar)view.findViewById(R.id.rating_bar_dialog);
        shopNameView = (TextView)view.findViewById(R.id.textView);
        btnFinish = (Button)view.findViewById(R.id.btn_done);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null)
                    mListener.onRatingChanged(RatingDialog.this, rating);
                dismiss();
            }
        });
        ratingBar.setOnRatingBarChangeListener(RatingDialog.this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Dialog d = getDialog();
        shopNameView.setText(shopName);
        d.getWindow().setLayout(1000, 600);
        WindowManager.LayoutParams params = d.getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
//        params.x = 100;
//        params.y = 100;
        d.getWindow().setAttributes(params);
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public interface OnRatingDialogChangedListener {
        public void onRatingChanged(RatingDialog dialog, float rating);
    }
    OnRatingDialogChangedListener mListener;
    public void setOnRatingDialogChangedListener(OnRatingDialogChangedListener listener){
        mListener = listener;
    }
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        this.rating = rating;
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
