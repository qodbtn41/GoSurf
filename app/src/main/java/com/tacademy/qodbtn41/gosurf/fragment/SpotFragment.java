package com.tacademy.qodbtn41.gosurf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.tacademy.qodbtn41.gosurf.NearbyShopActivity;
import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.adapter.SpotListAdapter;
import com.tacademy.qodbtn41.gosurf.data.Bookmark;
import com.tacademy.qodbtn41.gosurf.data.LocationData;
import com.tacademy.qodbtn41.gosurf.data.WeatherData;
import com.tacademy.qodbtn41.gosurf.item.SpotItemView;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;
import com.tacademy.qodbtn41.gosurf.manager.PropertyManager;

import java.util.ArrayList;
import java.util.List;

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
        spotList.setDivider(null);

        spotList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        spotListAdapter.setOnAdapterShopLinkListener(new SpotListAdapter.OnAdapterShopLinkListener() {
            @Override
            public void onAdapterShopLinkClick(SpotListAdapter adapter, SpotItemView view, LocationData data) {
                Intent intent = new Intent(getContext(), NearbyShopActivity.class);
                intent.putExtra("location", view.getLocationData().getLocation_category());
                startActivity(intent);
            }
        });
        spotListAdapter.setOnAdapterStarListener(new SpotListAdapter.OnAdapterStarListener() {
            @Override
            public void onAdapterStarClick(SpotListAdapter adapter, final SpotItemView view, final LocationData data) {
                //현재 별 보고 반대로 동작.
                if (data.is_bookmarked()) {
                    NetworkManager.getInstance().deleteBookmark(getContext(), data.getLocation_id(), new NetworkManager.OnResultListener<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(getContext(), "삭제", Toast.LENGTH_SHORT).show();
                            view.getStarView().setImageDrawable(getResources().getDrawable(R.drawable.bookmark_inactivated));
                            data.setIs_bookmarked(false);
                        }

                        @Override
                        public void onFail(int code) {

                        }
                    });
                } else {
                    NetworkManager.getInstance().postBookmark(getContext(), data.getLocation_id(), new NetworkManager.OnResultListener<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(getContext(), "등록", Toast.LENGTH_SHORT).show();
                            view.getStarView().setImageDrawable(getResources().getDrawable(R.drawable.bookmark_activated));
                            data.setIs_bookmarked(true);
                        }

                        @Override
                        public void onFail(int code) {

                        }
                    });
                }
            }
        });
    }

    /*데이터가 없으므로 가짜 데이터를 집어넣자*/
    private void setData() {
        NetworkManager.getInstance().getWeathers(getContext(), new NetworkManager.OnResultListener<WeatherData>() {
            @Override
            public void onSuccess(WeatherData result) {
                spotListAdapter.clear();
                //일단 그냥 순서대로 받는다 나중에는 리스트에 받았다가 정렬하는 작업이 필요하다.

                List<LocationData> unBookmarked = new ArrayList<LocationData>();

                for (LocationData s : result.getItems()) {
                    s.setIs_bookmarked(false);

                    String myId = PropertyManager.getInstance().get_Id();
                    for (Bookmark b : s.getBookmarks()) {
                        if (myId.equals(b.getUser_id())) {
                            s.setIs_bookmarked(true);
                            spotListAdapter.add(s);
                            break;
                        }
                    }
                    //북마크 안된것은 모아뒀다가 마지막에 추가한다.
                    if(!s.is_bookmarked()){
                        unBookmarked.add(s);
                    }
                }

                for(LocationData l : unBookmarked){
                    spotListAdapter.add(l);
                }
            }

            @Override
            public void onFail(int code) {
                int test = code;
            }
        });
    }
}

