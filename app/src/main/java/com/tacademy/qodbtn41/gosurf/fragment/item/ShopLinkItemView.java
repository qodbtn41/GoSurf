package com.tacademy.qodbtn41.gosurf.fragment.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.ShopLinkItem;

/**
 * Created by UserPC on 2015-11-02.
 */
public class ShopLinkItemView extends FrameLayout {
    String locationCategory;
    //어떤 스팟의 상세메뉴인지 가지고 있자. 생성할때 받기
    public ShopLinkItemView(Context context) {
        super(context);
        init();
    }

    public ShopLinkItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.item_shop_link, this);
    }

    public void setData(ShopLinkItem data){
        locationCategory = data.getLocationCategory();
    }

    public String getLocationCategory() {
        return locationCategory;
    }
}
