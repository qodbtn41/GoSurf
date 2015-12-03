package com.tacademy.qodbtn41.gosurf.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.qodbtn41.gosurf.data.LocationData;
import com.tacademy.qodbtn41.gosurf.item.SpotItemView;

import java.util.ArrayList;

/**
 * Created by UserPC on 2015-11-01.
 * 스팟 리스트는 totalcount를 안쓴다. 한꺼번에 다 불러옴.
 */
public class SpotListAdapter extends BaseAdapter implements SpotItemView.OnStarClickListener, SpotItemView.OnShopLinkClickListener, SpotItemView.OnSpotClickListener {
    ArrayList<LocationData> items = new ArrayList<LocationData>();
    View view;


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
    public void onStarClick(SpotItemView view, LocationData data) {
        if(starListener != null){
            starListener.onAdapterStarClick(this, view, data);
        }
    }

    @Override
    public void onShopLinkClick(SpotItemView view, LocationData data) {
        if(adapterShopLinkListener!= null){
            adapterShopLinkListener.onAdapterShopLinkClick(this, view, data);
        }
    }

    @Override
    public void onSpotClick(SpotItemView view, LocationData data) {
        if(adapterSpotClickListener != null){
            adapterSpotClickListener.onSpotClick(this, view, data);
        }
    }

    public interface OnAdapterSpotClickListener{
        public void onSpotClick(SpotListAdapter adapter, SpotItemView view, LocationData data);
    }
    OnAdapterSpotClickListener adapterSpotClickListener;
    public void setOnAdapterSpotClickListener(OnAdapterSpotClickListener listener){
        adapterSpotClickListener = listener;
    }
    SpotItemView.OnSpotClickListener spotClickListener;
    public void setOnSpotClickListener(SpotItemView.OnSpotClickListener listener){
        spotClickListener = listener;
        notifyDataSetChanged();
        notifyDataSetInvalidated();
    }
    public interface OnAdapterShopLinkListener {
        public void onAdapterShopLinkClick(SpotListAdapter adapter, SpotItemView view, LocationData data);
    }
    OnAdapterShopLinkListener adapterShopLinkListener;
    public void setOnAdapterShopLinkListener(OnAdapterShopLinkListener listener){
        adapterShopLinkListener = listener;
    }
    SpotItemView.OnShopLinkClickListener linkListener;
    public void setOnShopLinkClickListener(SpotItemView.OnShopLinkClickListener listener){
        linkListener = listener;
        notifyDataSetChanged();
        notifyDataSetInvalidated();
    }

    public interface OnAdapterStarListener {
        public void onAdapterStarClick(SpotListAdapter adapter, SpotItemView view, LocationData data);
    }
    OnAdapterStarListener starListener;
    public void setOnAdapterStarListener(OnAdapterStarListener listener){
        starListener = listener;
    }

    SpotItemView.OnStarClickListener mStarClickListener;
    public void setOnStarClickListener(SpotItemView.OnStarClickListener listener){
        mStarClickListener = listener;
        notifyDataSetChanged();
        notifyDataSetInvalidated();
    }

    public void add(LocationData data){
        items.add(data);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpotItemView spotItemView;
        if (convertView != null && convertView instanceof SpotItemView) {
            spotItemView = (SpotItemView) convertView;
        } else {
            spotItemView = new SpotItemView(parent.getContext());
            spotItemView.setOnStarClickListener(this);
            spotItemView.setOnShopLinkClickListener(this);
            spotItemView.setOnSpotClickListener(this);
        }
        spotItemView.setData(items.get(position));

        return spotItemView;
    }
}
