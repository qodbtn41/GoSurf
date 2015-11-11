package com.tacademy.qodbtn41.gosurf.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.TimelineDetailActivity;
import com.tacademy.qodbtn41.gosurf.adapter.TimelineListAdapter;
import com.tacademy.qodbtn41.gosurf.data.PictureItemData;
import com.tacademy.qodbtn41.gosurf.fragment.item.PictureItemView;
import com.tacademy.qodbtn41.gosurf.fragment.item.VideoItemView;

public class TimelineFragment extends android.support.v4.app.Fragment {
    ListView timelineList;
    SwipeRefreshLayout refreshLayout;

    TimelineListAdapter timelineListAdapter;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.init(inflater, container, savedInstanceState);
        this.setData();
        return view;
    }

    private void init(LayoutInflater inflater, ViewGroup container,
                      Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timeline, container, false);
        timelineList = (ListView)view.findViewById(R.id.list_timeline);
        refreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_timeline);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setData();
            }
        });
        timelineListAdapter = new TimelineListAdapter();
        timelineList.setAdapter(timelineListAdapter);
        timelineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(view instanceof PictureItemView){
                    //뷰에서 어떤 스팟에 속했는지 받아서 다시전달
                    Intent intent = new Intent(getContext(), TimelineDetailActivity.class);
                    intent.putExtra("type", TimelineListAdapter.TYPE_PICTURE);
                    startActivity(intent);
                }else if(view instanceof VideoItemView){
                    Intent intent = new Intent(getContext(), TimelineDetailActivity.class);
                    intent.putExtra("type", TimelineListAdapter.TYPE_VIDEO);
                    startActivity(intent);
                }
            }
        });
    }

    private void setData() {
        String userName = "sini1598";
        String time = "17분";
        String content = "오늘 서해 만리포 가을바다에서 서핑 캠핑...! 아들과 멋진 추억 남겼습니다. 아이들에게 가을바다에서 좋은 추억을 남겨준 ...";
        Drawable picture = getResources().getDrawable(android.R.drawable.ic_menu_camera);
        for (int i = 0; i < 10; i++) {
            PictureItemData tempData = new PictureItemData();
            tempData.setPicture(picture);
            tempData.setUserName(userName);
            tempData.setContent(content);
            tempData.setTime(time);

            timelineListAdapter.add(tempData);
        }
    }
}
