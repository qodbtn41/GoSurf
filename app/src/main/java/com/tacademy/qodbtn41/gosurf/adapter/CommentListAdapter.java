package com.tacademy.qodbtn41.gosurf.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.qodbtn41.gosurf.data.CommentItemData;
import com.tacademy.qodbtn41.gosurf.fragment.item.CommentItemView;

import java.util.ArrayList;

/**
 * Created by UserPC on 2015-11-03.
 */
public class CommentListAdapter extends BaseAdapter {
    ArrayList<CommentItemData> items = new ArrayList<CommentItemData>();

    public void add(CommentItemData data){
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
        CommentItemView view;
        if(convertView != null && convertView instanceof CommentItemView){
            view = (CommentItemView)convertView;
        }else{
            view = new CommentItemView(parent.getContext());
        }
        view.setData(items.get(position));
        return view;
    }
}
