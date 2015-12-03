package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.ShopItem;

import java.text.DecimalFormat;

/**
 * Created by UserPC on 2015-11-03.
 */
public class ShopItemView extends FrameLayout{
    ShopItem data;
    ImageView shopImageView;
    TextView addressVIew, shopNameView, rateView, commentCountView;

    DisplayImageOptions options;
    private String _id;

    public String get_id() {
        return _id;
    }

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
        commentCountView = (TextView)findViewById(R.id.text_created_time);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading1)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.loading_error)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .build();
    }

    public void setData(ShopItem data){
        this.data = data;
        _id = data.get_id();
        this.addressVIew.setText(Html.fromHtml(data.getAddress()));
        this.shopNameView.setText(this.data.getName());
        DecimalFormat format = new DecimalFormat(".#");
        String result = format.format(this.data.getGrade());

        this.rateView.setText(result);
        this.commentCountView.setText(Integer.toString(this.data.getComments_count()) );

        ImageLoader.getInstance().displayImage(data.getImage_url(), shopImageView, options);
    }
}
