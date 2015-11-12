package com.tacademy.qodbtn41.gosurf.data;

import java.util.List;

/**
 * Created by UserPC on 2015-11-12.
 * 상세페이지에서 쓰려고 받는 데이터
 */
public class ShopData {
    List<ShopDetailData> items;

    public List<ShopDetailData> getItems() {
        return items;
    }

    public void setItems(List<ShopDetailData> items) {
        this.items = items;
    }
}
