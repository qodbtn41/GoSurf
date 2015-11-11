package com.tacademy.qodbtn41.gosurf.fragment.item;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.ShopItemData;

/**
 * Created by UserPC on 2015-11-03.
 */
public class ShopItemView extends FrameLayout{
    ShopItemData data;
    ImageView shopImageView;
    TextView addressVIew, shopNameView, rateView, commentCountView;

    DisplayImageOptions options;

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

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(50))
                .build();
    }

    public void setData(ShopItemData data){
        this.data = data;

        this.addressVIew.setText(Html.fromHtml(data.getAddress()));
        this.shopNameView.setText(this.data.getName());
        this.rateView.setText(Integer.toString(this.data.getGrade()));
        this.commentCountView.setText(Integer.toString(this.data.getComments_count()) );

        ImageLoader.getInstance().displayImage(data.getImage_url(), shopImageView, options);
    }
}
