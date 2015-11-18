package com.tacademy.qodbtn41.gosurf.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.qodbtn41.gosurf.data.DelimeterItem;
import com.tacademy.qodbtn41.gosurf.data.ShopItem;
import com.tacademy.qodbtn41.gosurf.data.ShopListItem;
import com.tacademy.qodbtn41.gosurf.item.DateItemView;
import com.tacademy.qodbtn41.gosurf.item.DelimeterItemView;
import com.tacademy.qodbtn41.gosurf.item.ShopItemView;

import java.util.ArrayList;

/**
 * Created by UserPC on 2015-11-03.
 */
public class ShopListAdapter extends BaseAdapter {
    ArrayList<ShopListItem> items = new ArrayList<ShopListItem>();
    int totalCount;

    private static final int VIEW_TYPE_COUNT = 2;

    private static final int TYPE_SHOP = 0;
    private static final int TYPE_DELIMITER = 1;


    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int getTotalCount() {
        return totalCount;
    }

    public int getStartIndex() {
        if (items.size() < totalCount) {
            return items.size() + 1;
        }
        return -1;
    }

    public void add(ShopListItem data){
        items.add(data);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        ShopListItem s = items.get(position);
        if(s instanceof ShopItem){
            return TYPE_SHOP;
        }else if(s instanceof DelimeterItem) {
            return TYPE_DELIMITER;
        }
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        switch(getItemViewType(position)){
            case TYPE_DELIMITER :{
                DelimeterItemView delimeterItemView;
                if(convertView != null && convertView instanceof DateItemView){
                    delimeterItemView = (DelimeterItemView)convertView;
                }else {
                    delimeterItemView = new DelimeterItemView(parent.getContext());
                }
                delimeterItemView.setData((DelimeterItem) items.get(position));
                return delimeterItemView;
            }
            case TYPE_SHOP :
            default:{
                ShopItemView view;

                if(convertView != null && convertView instanceof ShopItemView){
                    view = (ShopItemView)convertView;
                }else{
                    view = new ShopItemView(parent.getContext());
                }
                view.setData((ShopItem)items.get(position));
                return view;
            }
        }
    }
}
