package com.tacademy.qodbtn41.gosurf.fragment.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.ShopItemData;

/**
 * Created by UserPC on 2015-11-02.
 */
public class ShopItemView extends FrameLayout {
    //어떤 스팟의 상세메뉴인지 가지고 있자. 생성할때 받기
    public ShopItemView(Context context) {
        super(context);
        init();
    }

    public ShopItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.item_nearby_shop_link, this);
    }

    public void setData(ShopItemData data){

    }
}
