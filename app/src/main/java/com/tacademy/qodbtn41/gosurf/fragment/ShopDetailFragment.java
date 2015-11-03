package com.tacademy.qodbtn41.gosurf.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.adapter.CommentListAdapter;
import com.tacademy.qodbtn41.gosurf.data.CommentItemData;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopDetailFragment extends android.support.v4.app.Fragment {
    ListView commentList;
    CommentListAdapter commentListAdapter;
    private View view;
    String shopName;

    public ShopDetailFragment(String shopName) {
        // Required empty public constructor
        this.shopName = shopName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.init(inflater, container, savedInstanceState);
        this.setToolbar();
        this.setData();
        return view;
    }

    private void init(LayoutInflater inflater, ViewGroup container,
                      Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_shop_detail, container, false);
        View headerView = inflater.inflate(R.layout.header_shop_detail, null);

        Button btn = (Button)headerView.findViewById(R.id.btn_comment);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //댓글달기 write페이지로 넘어가자
            }
        });
        commentList = (ListView)view.findViewById(R.id.list_comment);
        commentList.addHeaderView(headerView, null, false);
        commentListAdapter = new CommentListAdapter();
        commentList.setAdapter(commentListAdapter);
    }

    private void setToolbar(){
        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Shop");
    }
    //역시 아무거나 넣기
    private void setData(){
        String userName = "sini1598";
        String time = "17분";
        String content = "오늘 서해 만리포 가을바다에서 서핑 캠핑...! 아들과 멋진 추억 남겼습니다. 아이들에게 가을바다에서 좋은 추억을 남겨준 ...";
        for (int i = 0; i < 10; i++) {
            CommentItemData tempData = new CommentItemData();
            tempData.setUserName(userName);
            tempData.setContent(content);
            tempData.setTime(time);

            commentListAdapter.add(tempData);
        }
    }
}
