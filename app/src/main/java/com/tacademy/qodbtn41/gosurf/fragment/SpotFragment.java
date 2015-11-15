package com.tacademy.qodbtn41.gosurf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tacademy.qodbtn41.gosurf.NearbyShopActivity;
import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.adapter.SpotListAdapter;
import com.tacademy.qodbtn41.gosurf.data.DelimeterItem;
import com.tacademy.qodbtn41.gosurf.data.ShopLinkItem;
import com.tacademy.qodbtn41.gosurf.data.SpotData;
import com.tacademy.qodbtn41.gosurf.data.SpotItem;
import com.tacademy.qodbtn41.gosurf.fragment.item.ShopLinkItemView;
import com.tacademy.qodbtn41.gosurf.fragment.item.SpotItemView;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;

/*
 */
public class SpotFragment extends android.support.v4.app.Fragment {

    ListView spotList;
    SpotListAdapter spotListAdapter;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        init(inflater, container, savedInstanceState);
        this.setData();
        return view;
    }

    private void init(LayoutInflater inflater, ViewGroup container,
                      Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_spot, container, false);
        spotList = (ListView)view.findViewById(R.id.list_spot);
        spotListAdapter = new SpotListAdapter();
        spotList.setAdapter(spotListAdapter);
        spotList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(view instanceof ShopLinkItemView){
                    //뷰에서 어떤 스팟에 속했는지 받아서 다시전달
                    Intent intent = new Intent(getContext(), NearbyShopActivity.class);
                    intent.putExtra("location", ((ShopLinkItemView)view).getLocationCategory());
                    startActivity(intent);
                }else if(view instanceof SpotItemView){
                    //열려있는지 닫혀있는지 보고 열려있으면 닫고 닫혀있으면 연다.
                }
            }
        });
        spotList.setDivider(null);

    }

    /*데이터가 없으므로 가짜 데이터를 집어넣자*/
    private void setData() {
        NetworkManager.getInstance().getSpot(getContext(), new NetworkManager.OnResultListener<SpotData>() {
            @Override
            public void onSuccess(SpotData result) {
                spotListAdapter.clear();
                spotListAdapter.add(new DelimeterItem());
                //일단 그냥 순서대로 받는다 나중에는 리스트에 받았다가 정렬하는 작업이 필요하다.
                for (SpotItem s : result.getItems()) {
                    spotListAdapter.add(s);
                    ShopLinkItem shopLink = new ShopLinkItem();
                    shopLink.setLocationCategory(s.getLocation_category());
                    spotListAdapter.add(shopLink);
                }
            }

            @Override
            public void onFail(int code) {

            }
        });
    }
}

