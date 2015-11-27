package com.tacademy.qodbtn41.gosurf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.TimelineDetailActivity;
import com.tacademy.qodbtn41.gosurf.adapter.TimelineListAdapter;
import com.tacademy.qodbtn41.gosurf.data.PictureItem;
import com.tacademy.qodbtn41.gosurf.data.TextItem;
import com.tacademy.qodbtn41.gosurf.data.TimelineItem;
import com.tacademy.qodbtn41.gosurf.data.TimelineListData;
import com.tacademy.qodbtn41.gosurf.data.VideoItem;
import com.tacademy.qodbtn41.gosurf.item.PictureItemView;
import com.tacademy.qodbtn41.gosurf.item.TextItemView;
import com.tacademy.qodbtn41.gosurf.item.VideoItemView;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;

public class TimelineFragment extends android.support.v4.app.Fragment {
    ListView timelineList;
    SwipeRefreshLayout refreshLayout;

    TimelineListAdapter timelineListAdapter;
    private View view;
    boolean isUpdate = false;
    boolean isLastItem = false;
    private static final int LIMIT = 10;
    public static final int TYPE_TEXT = 0;
    public static final int TYPE_PICTURE = 1;
    public static final int TYPE_VIDEO = 2;

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
                timelineListAdapter.clear();
                setData();
            }
        });
        timelineListAdapter = new TimelineListAdapter();
        timelineList.setAdapter(timelineListAdapter);
        timelineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view instanceof PictureItemView) {
                    //뷰에서 어떤 스팟에 속했는지 받아서 다시전달
                    Intent intent = new Intent(getContext(), TimelineDetailActivity.class);
                    intent.putExtra("_id", ((PictureItemView) view).get_id());
                    intent.putExtra("type", TimelineListAdapter.TYPE_PICTURE);
                    startActivity(intent);
                } else if (view instanceof VideoItemView) {
                    Intent intent = new Intent(getContext(), TimelineDetailActivity.class);
                    intent.putExtra("_id", ((VideoItemView)view).get_id());
                    intent.putExtra("type", TimelineListAdapter.TYPE_VIDEO);
                    startActivity(intent);
                } else if (view instanceof TextItemView) {
                    Intent intent = new Intent(getContext(), TimelineDetailActivity.class);
                    intent.putExtra("_id", ((TextItemView)view).get_id());
                    int type = ((TextItemView)view).getType();
                    intent.putExtra("type", type);
                    startActivity(intent);
                }
            }
        });
        timelineList.setDivider(null);
        //스크롤해서 마지막에 도달했을 때
        timelineList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isLastItem && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    getMoreItem();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (totalItemCount > 0 && (firstVisibleItem + visibleItemCount >= totalItemCount - 1)) {
                    isLastItem = true;
                } else {
                    isLastItem = false;
                }
            }
        });
    }

    private void setData() {
        //맨처음 delimiter부터 넣는다.
        NetworkManager.getInstance().getTimelineList(getContext(), 0, LIMIT, new NetworkManager.OnResultListener<TimelineListData>() {
            @Override
            public void onSuccess(TimelineListData result) {
                addToAdapter(result);
                timelineListAdapter.setTotalCount(result.getTotal_count());
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }

            @Override
            public void onFail(int code) {

            }
        });
    }

    private void getMoreItem() {
        if (!isUpdate){
            int startIndex = timelineListAdapter.getStartIndex();
            if (startIndex != -1) {
                isUpdate = true;
                NetworkManager.getInstance().getTimelineList(getContext(), startIndex, LIMIT, new NetworkManager.OnResultListener<TimelineListData>() {
                    @Override
                    public void onSuccess(TimelineListData result) {
                        addToAdapter(result);
                    }

                    @Override
                    public void onFail(int code) {
                        isUpdate = false;
                    }
                });
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NetworkManager.getInstance().cancelAll(getContext());
    }

    private void addToAdapter(TimelineListData result){
        for (TimelineItem t : result.getItems()) {
            switch (t.getAttachment().getType()) {
                case TYPE_TEXT: {
                    //여기들어가는건 TextItem라고하고, 상세페이지 텍스트아이템은 DetailTextItem이라고 하겠다.
                    TextItem item = new TextItem();
                    item.setData(t);
                    timelineListAdapter.add(item);
                    break;
                }
                case TYPE_PICTURE: {
                    PictureItem item = new PictureItem();
                    item.setData(t);
                    timelineListAdapter.add(item);

                    TextItem textItem = new TextItem();
                    textItem.setData(t);
                    timelineListAdapter.add(textItem);
                    break;
                }
                case TYPE_VIDEO: {
                    VideoItem item = new VideoItem();
                    item.setData(t);
                    timelineListAdapter.add(item);

                    TextItem textItem = new TextItem();
                    textItem.setData(t);
                    timelineListAdapter.add(textItem);
                    break;
                }
            }
        }
    }
}
