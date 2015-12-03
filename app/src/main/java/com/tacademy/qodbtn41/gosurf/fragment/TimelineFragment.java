package com.tacademy.qodbtn41.gosurf.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.TimelineDetailActivity;
import com.tacademy.qodbtn41.gosurf.WriteActivity;
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
    FloatingActionButton fab;

    TimelineListAdapter timelineListAdapter;
    int clickedPosition;
    private View view;
    boolean isUpdate = false;
    boolean isLastItem = false;
    private static final int LIMIT = 10;
    public static final int TYPE_TEXT = 0;
    public static final int TYPE_PICTURE = 1;
    public static final int TYPE_VIDEO = 2;
    public static final int TYPE_DELIMITER = 3;

    public static final int WRITE_ARTICLE = 101;

    public static final int TYPE_TEXT_TEXT = 200;
    public static final int TYPE_PICTURE_TEXT = 201;
    public static final int TYPE_VIDEO_TEXT = 202;

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
        setTimelineList();
        setFloatingButton();


    }
    private void setTimelineList(){
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
        timelineListAdapter.setOnAdapterVideoClickListener(new TimelineListAdapter.OnAdapterVideoClickListener() {
            @Override
            public void onVideoClick(TimelineListAdapter adapter, VideoItemView view, String data) {
                Intent intent = new Intent(getContext(), TimelineDetailActivity.class);
                intent.putExtra("_id", ((VideoItemView) view).get_id());
                intent.putExtra("type", TimelineListAdapter.TYPE_VIDEO);
                startActivityForResult(intent, TYPE_VIDEO);
            }
        });

        timelineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), TimelineDetailActivity.class);
                clickedPosition = position;
                if (view instanceof PictureItemView) {
                    //뷰에서 어떤 스팟에 속했는지 받아서 다시전달
                    intent.putExtra("_id", ((PictureItemView) view).get_id());
                    intent.putExtra("type", TimelineListAdapter.TYPE_PICTURE);
                    startActivityForResult(intent, TYPE_PICTURE);
                } else if (view instanceof VideoItemView) {

                } else if (view instanceof TextItemView) {
                    intent.putExtra("_id", ((TextItemView) view).get_id());
                    int type = ((TextItemView) view).getType();
                    intent.putExtra("type", type);
                    switch (type) {
                        case TYPE_PICTURE: {
                            startActivityForResult(intent, TYPE_PICTURE_TEXT);
                            break;
                        }
                        case TYPE_VIDEO: {
                            startActivityForResult(intent, TYPE_VIDEO_TEXT);
                            break;

                        }
                        case TYPE_TEXT: {
                            startActivityForResult(intent, TYPE_TEXT_TEXT);
                            break;
                        }
                    }
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

    private void setFloatingButton(){
        fab = (FloatingActionButton)getActivity().findViewById(R.id.fab_write);
        fab.setImageDrawable(getResources().getDrawable(R.drawable.write_button));
        fab.setScaleType(ImageView.ScaleType.CENTER);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fab_background)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WriteActivity.class);
                intent.putExtra(getString(R.string.type), WriteActivity.TYPE_TIMELINE);
                startActivityForResult(intent, TimelineFragment.WRITE_ARTICLE);
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
                        timelineListAdapter.setTotalCount(result.getTotal_count());
                        isUpdate = false;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_CANCELED){
            return;
        }

        switch (requestCode){
            case TYPE_PICTURE:
            case TYPE_VIDEO:{
                timelineListAdapter.clear();
                setData();
                timelineListAdapter.notifyDataSetChanged();
                break;
            }
            case TYPE_PICTURE_TEXT:
            case TYPE_VIDEO_TEXT:{
                timelineListAdapter.clear();
                setData();
                timelineListAdapter.notifyDataSetChanged();

                break;
            }
            case TYPE_TEXT_TEXT:{
                timelineListAdapter.clear();
                setData();
                timelineListAdapter.notifyDataSetChanged();
                break;
            }
            case WRITE_ARTICLE:{
                timelineListAdapter.clear();
                setData();
                timelineListAdapter.notifyDataSetChanged();
                break;
            }
        }
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
            timelineListAdapter.counting();
        }
    }
}
