package com.tacademy.qodbtn41.gosurf.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.qodbtn41.gosurf.data.ShopItemData;
import com.tacademy.qodbtn41.gosurf.fragment.item.ShopItemView;

import java.util.ArrayList;

/**
 * Created by UserPC on 2015-11-03.
 */
public class ShopListAdapter extends BaseAdapter {
    ArrayList<ShopItemData> items = new ArrayList<ShopItemData>();

    public void add(ShopItemData data){
        items.add(data);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ShopItemView view;

        if(convertView != null && convertView instanceof ShopItemView){
            view = (ShopItemView)convertView;
        }else{
            view = new ShopItemView(parent.getContext());
        }
        view.setData(items.get(position));
        return view;
    }
}
