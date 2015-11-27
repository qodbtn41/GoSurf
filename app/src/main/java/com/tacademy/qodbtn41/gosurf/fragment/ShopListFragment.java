package com.tacademy.qodbtn41.gosurf.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.ShopDetailActivity;
import com.tacademy.qodbtn41.gosurf.adapter.ShopListAdapter;
import com.tacademy.qodbtn41.gosurf.data.ShopItem;
import com.tacademy.qodbtn41.gosurf.data.ShopListData;
import com.tacademy.qodbtn41.gosurf.item.ShopItemView;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopListFragment extends android.support.v4.app.Fragment {

    ListView shopList;
    ShopListAdapter shopListAdapter;
    private View view;

    private String locationCategory;
    boolean isUpdate = false;
    boolean isLastItem = false;
    private static final int LIMIT = 10;

    public ShopListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        init(inflater, container, savedInstanceState);
        this.setData();
        return view;
    }

    private void init(LayoutInflater inflater, ViewGroup container,
                      Bundle savedInstanceState) {
        locationCategory = getArguments().getString("location");

        view = inflater.inflate(R.layout.fragment_shop_list, container, false);
        shopList = (ListView)view.findViewById(R.id.list_shop);

        //스크롤해서 마지막에 도달했을 때
        shopList.setOnScrollListener(new AbsListView.OnScrollListener() {
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

        shopListAdapter = new ShopListAdapter();
        shopList.setAdapter(shopListAdapter);
        shopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(parent.getContext(), ShopDetailActivity.class);
                intent.putExtra("_id", ((ShopItemView) view).get_id());
                startActivity(intent);
            }
        });
        shopList.setDivider(null);
    }


    /*데이터가 없으므로 가짜 데이터를 집어넣자*/
    private void setData() {
        NetworkManager.getInstance().getShopList(getContext(), locationCategory, 0, LIMIT, new NetworkManager.OnResultListener<ShopListData>() {
            @Override
            public void onSuccess(ShopListData result) {
                shopListAdapter.clear();
                for(ShopItem s : result.getItems()) {
                    shopListAdapter.add(s);
                }
            }

            @Override
            public void onFail(int code) {

            }
        });
    }

    private void getMoreItem() {
        if (!isUpdate){
            int startIndex = shopListAdapter.getStartIndex();
            if (startIndex != -1) {
                isUpdate = true;
                NetworkManager.getInstance().getShopList(getContext(), locationCategory, startIndex, LIMIT, new NetworkManager.OnResultListener<ShopListData>() {
                    @Override
                    public void onSuccess(ShopListData result) {
                        for(ShopItem s : result.getItems()) {
                            shopListAdapter.add(s);
                            isUpdate = false;
                        }
                    }

                    @Override
                    public void onFail(int code) {
                        isUpdate = false;
                    }
                });
            }
        }
    }
}
