package com.tacademy.qodbtn41.gosurf.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.qodbtn41.gosurf.data.DateItem;
import com.tacademy.qodbtn41.gosurf.data.DelimeterItem;
import com.tacademy.qodbtn41.gosurf.data.ShopLinkItem;
import com.tacademy.qodbtn41.gosurf.data.SpotDetailItem;
import com.tacademy.qodbtn41.gosurf.data.SpotItem;
import com.tacademy.qodbtn41.gosurf.data.SpotListItem;
import com.tacademy.qodbtn41.gosurf.fragment.item.DateItemView;
import com.tacademy.qodbtn41.gosurf.fragment.item.DelimeterItemView;
import com.tacademy.qodbtn41.gosurf.fragment.item.ShopLinkItemView;
import com.tacademy.qodbtn41.gosurf.fragment.item.SpotDetailItemView;
import com.tacademy.qodbtn41.gosurf.fragment.item.SpotItemView;

import java.util.ArrayList;

/**
 * Created by UserPC on 2015-11-01.
 * 스팟 리스트는 totalcount를 안쓴다. 한꺼번에 다 불러옴.
 */
public class SpotListAdapter extends BaseAdapter {
    ArrayList<SpotListItem> items = new ArrayList<SpotListItem>();

    int totalCount;

    private static final int VIEW_TYPE_COUNT = 5;

    private static final int TYPE_SPOT = 0;
    private static final int TYPE_SPOT_DETAIL = 1;
    private static final int TYPE_DELIMITER = 2;
    private static final int TYPE_DATE = 3;
    private static final int TYPE_SHOP = 4;

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

    public void add(SpotListItem data){
        items.add(data);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        SpotListItem s = items.get(position);
        if(s instanceof SpotItem){
            return TYPE_SPOT;
        }else if(s instanceof SpotDetailItem){
            return TYPE_SPOT_DETAIL;
        }else if(s instanceof ShopLinkItem){
            return TYPE_SHOP;
        }else if(s instanceof DateItem) {
            return TYPE_DATE;
        }else if(s instanceof DelimeterItem){
            return TYPE_DELIMITER;
        }
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch(getItemViewType(position)){
            case TYPE_SPOT: {
                SpotItemView spotItemView;
                if (convertView != null && convertView instanceof SpotItemView) {
                    spotItemView = (SpotItemView) convertView;
                } else {
                    spotItemView = new SpotItemView(parent.getContext());
                }
                spotItemView.setData((SpotItem) items.get(position));
                return spotItemView;
            }
            case TYPE_SPOT_DETAIL :{
                SpotDetailItemView spotDetailItemView;
                if(convertView != null && convertView instanceof SpotDetailItemView) {
                    spotDetailItemView = (SpotDetailItemView) convertView;
                }else {
                    spotDetailItemView = new SpotDetailItemView(parent.getContext());
                }
                spotDetailItemView.setData((SpotDetailItem)items.get(position));
                return spotDetailItemView;
            }
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
            case TYPE_DATE : {
                DateItemView dateItemView;
                if(convertView != null && convertView instanceof DateItemView){
                    dateItemView = (DateItemView)convertView;
                }else {
                    dateItemView = new DateItemView(parent.getContext());
                }
                dateItemView.setData((DateItem)items.get(position));
                return dateItemView;
            }
            case TYPE_SHOP :
            default:{
                ShopLinkItemView shopLinkItemView;
                if(convertView != null && convertView instanceof ShopLinkItemView){
                    shopLinkItemView = (ShopLinkItemView)convertView;
                }else {
                    shopLinkItemView = new ShopLinkItemView(parent.getContext());
                }
                shopLinkItemView.setData((ShopLinkItem)items.get(position));
                return shopLinkItemView;
            }
        }
    }
}
