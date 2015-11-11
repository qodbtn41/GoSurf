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
import com.tacademy.qodbtn41.gosurf.data.ShopLinkItemData;
import com.tacademy.qodbtn41.gosurf.data.SpotItemData;
import com.tacademy.qodbtn41.gosurf.fragment.item.ShopLinkItemView;
import com.tacademy.qodbtn41.gosurf.fragment.item.SpotItemView;

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
                    intent.putExtra("location", "jeju");
                    startActivity(intent);
                }else if(view instanceof SpotItemView){
                    //열려있는지 닫혀있는지 보고 열려있으면 닫고 닫혀있으면 연다.
                }
            }
        });
    }

    /*데이터가 없으므로 가짜 데이터를 집어넣자*/
    private void setData() {
        String[] statusText = {"GREAT", "WARNING", "NORMAL", "BAD", "WARNING", "NORMAL", "BAD", "WARNING", "NORMAL", "BAD", "GREAT"};
        String[] spotName = getResources().getStringArray(R.array.spot_name);
        Boolean[] checked = {true, false, true, false, true, false, true, false, true, false ,false};
        String locationCategory = "jeju";
        for (int i = 0; i < spotName.length; i++) {
            SpotItemData tempData = new SpotItemData();
            tempData.setChecked(checked[i]);
            tempData.setSpotName(spotName[i]);
            tempData.setStatusText(statusText[i]);
            tempData.setLocation_category(locationCategory);
            switch (statusText[i]){
                case "GREAT" :
                    tempData.setStatusImage(getResources().getDrawable(R.drawable.spot_status_great));
                    break;
                case "WARNING" :
                    tempData.setStatusImage(getResources().getDrawable(R.drawable.spot_status_warning));
                    break;
                case "NORMAL" :
                    tempData.setStatusImage(getResources().getDrawable(R.drawable.spot_status_normal));
                    break;
                case "BAD" :
                    tempData.setStatusImage(getResources().getDrawable(R.drawable.spot_status_bad));
                    break;
            }
            spotListAdapter.add(tempData);

            ShopLinkItemData shopData = new ShopLinkItemData();
            //shopData.setSpotName(spotName[i]);

            spotListAdapter.add(shopData);
        }
    }
}
