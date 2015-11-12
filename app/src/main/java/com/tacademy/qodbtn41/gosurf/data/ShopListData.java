package com.tacademy.qodbtn41.gosurf.data;

import java.util.List;

/**
 * Created by UserPC on 2015-11-11.
 */
public class ShopListData {
    List<ShopItemData> items;
    int total_count;

    public List<ShopItemData> getItems() {
        return items;
    }

    public void setItems(List<ShopItemData> items) {
        this.items = items;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
}
