package com.tacademy.qodbtn41.gosurf.fragment.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.ShopItemData;

/**
 * Created by UserPC on 2015-11-03.
 */
public class ShopItemView extends FrameLayout{
    ShopItemData data;
    ImageView shopImageView;
    TextView addressVIew, shopNameView, rateView, commentCountView;
    public ShopItemView(Context context) {
        super(context);
        init();
    }
    public ShopItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.item_shop, this);
        shopImageView = (ImageView)findViewById(R.id.image_shop_little);
        addressVIew = (TextView)findViewById(R.id.text_address);
        shopNameView = (TextView)findViewById(R.id.text_shop_name);
        rateView = (TextView)findViewById(R.id.text_rate);
        commentCountView = (TextView)findViewById(R.id.text_comment_count);
    }

    public void setData(ShopItemData data){
        this.data = data;
        this.shopImageView.setImageDrawable(this.data.getImage());
        this.addressVIew.setText(this.data.getAddress());
        this.shopNameView.setText(this.data.getShopName());
        this.rateView.setText(this.data.getRate());
        this.commentCountView.setText(Integer.toString(this.data.getCommentCount()));
    }
}
