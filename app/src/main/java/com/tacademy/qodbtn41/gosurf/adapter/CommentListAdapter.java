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
    int totalCount;

    //total count size세는데 headerview들어간걸 고려하자. 뭔가잘못되면 여기서 변경해보자.
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

    public void add(CommentItemData data){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentItemView view;
        if(convertView != null && convertView instanceof CommentItemView){
            view = (CommentItemView)convertView;
        }else{
            view = new CommentItemView(parent.getContext());
        }
        view.setData(items.get(position));
        view.setEnabled(false);//선택되는 UI를 없애기 위해
        view.setFocusable(false);//값을 받을 수 있는 상태
        view.setClickable(false);//touch를 받을 수 있는지 여부
        return view;
    }
}
