package com.tacademy.qodbtn41.gosurf.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.ShopDetailActivity;
import com.tacademy.qodbtn41.gosurf.adapter.ShopListAdapter;
import com.tacademy.qodbtn41.gosurf.data.ShopItemData;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopListFragment extends android.support.v4.app.Fragment {

    ListView shopList;
    ShopListAdapter shopListAdapter;
    private View view;

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
        view = inflater.inflate(R.layout.fragment_shop_list, container, false);
        shopList = (ListView)view.findViewById(R.id.list_shop);
        shopListAdapter = new ShopListAdapter();
        shopList.setAdapter(shopListAdapter);
        shopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(parent.getContext(), ShopDetailActivity.class);
                startActivity(intent);
            }
        });
    }


    /*데이터가 없으므로 가짜 데이터를 집어넣자*/
    private void setData() {
        String[] spotName = getResources().getStringArray(R.array.spot_name);
        String address = "강원도 양양군 현남면 동산큰길 21-1";
        String rate = "4.0";
        int commentCount = 7;
        for (int i = 0; i < spotName.length; i++) {
            ShopItemData tempData = new ShopItemData();
            //tempData.setShopName(spotName[i]);
            //tempData.setAddress(address);
            //tempData.setCommentCount(commentCount);
            //tempData.setImage(getResources().getDrawable(android.R.drawable.sym_def_app_icon));
            //tempData.setRate(rate);

            shopListAdapter.add(tempData);
        }
    }
}
