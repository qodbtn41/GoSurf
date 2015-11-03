package com.tacademy.qodbtn41.gosurf.fragment;


import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tacademy.qodbtn41.gosurf.NearbyShopActivity;
import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.adapter.ShopListAdapter;
import com.tacademy.qodbtn41.gosurf.data.ShopItemData;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyShopFragment extends android.support.v4.app.Fragment {
    ListView shopList;
    ShopListAdapter shopListAdapter;
    private View view;

    public NearbyShopFragment() {
        // Required empty public constructor
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
                      Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nearby_shop, container, false);
        shopList = (ListView)view.findViewById(R.id.list__nearby_shop);
        shopListAdapter = new ShopListAdapter();
        shopList.setAdapter(shopListAdapter);
        shopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String shopName = ((ShopItemData)shopList.getItemAtPosition(position)).getShopName();
                ((NearbyShopActivity) getActivity()).pushShopDetailFragment(shopName);
            }
        });
    }
    private void setData() {
        String[] spotName = getResources().getStringArray(R.array.spot_name);
        String address = "강원도 양양군 현남면 동산큰길 21-1";
        String rate = "4.0";
        int commentCount = 7;
        for (int i = 0; i < spotName.length; i++) {
            ShopItemData tempData = new ShopItemData();
            tempData.setShopName(spotName[i]);
            tempData.setAddress(address);
            tempData.setCommentCount(commentCount);
            tempData.setImage(getResources().getDrawable(android.R.drawable.sym_def_app_icon));
            tempData.setRate(rate);

            shopListAdapter.add(tempData);
        }
    }
    private void setToolbar(){
        ActionBar actionBar = getActivity().getActionBar();

    }
}
