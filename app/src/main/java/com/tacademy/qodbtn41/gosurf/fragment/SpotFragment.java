package com.tacademy.qodbtn41.gosurf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tacademy.qodbtn41.gosurf.NearbyShopActivity;
import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.adapter.SpotListAdapter;
import com.tacademy.qodbtn41.gosurf.data.Bookmark;
import com.tacademy.qodbtn41.gosurf.data.DateItem;
import com.tacademy.qodbtn41.gosurf.data.ShopLinkItem;
import com.tacademy.qodbtn41.gosurf.data.SpotData;
import com.tacademy.qodbtn41.gosurf.data.SpotDetailItem;
import com.tacademy.qodbtn41.gosurf.data.SpotItem;
import com.tacademy.qodbtn41.gosurf.item.ShopLinkItemView;
import com.tacademy.qodbtn41.gosurf.item.SpotItemView;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;
import com.tacademy.qodbtn41.gosurf.manager.PropertyManager;

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
                if (view instanceof ShopLinkItemView) {
                    //뷰에서 어떤 스팟에 속했는지 받아서 다시전달
                    Intent intent = new Intent(getContext(), NearbyShopActivity.class);
                    intent.putExtra("location", ((ShopLinkItemView) view).getLocationCategory());
                    startActivity(intent);
                } else if (view instanceof SpotItemView) {
                    SpotItemView spotItemView = (SpotItemView) view;

                    //열려있는지 닫혀있는지 보고 열려있으면 닫고 닫혀있으면 연다.
                    //8개로 정하면 문제 없는데 8가 아닐떄도 있따. 개수를 보고 거기까지만 visibility를 변경한다.
                    //상세날씨 아이템 개수+2 해주면 된다.
                    if (spotItemView.isOpened()) {
                        for (int i = position + 10; i > position; i--) {
                            if (spotListAdapter.getItem(i) instanceof ShopLinkItem) {
                                ((ShopLinkItem) spotListAdapter.getItem(i)).setIsOpened(false);
                            } else if (spotListAdapter.getItem(i) instanceof SpotDetailItem) {
                                ((SpotDetailItem) spotListAdapter.getItem(i)).setIsOpened(false);
                            } else if (spotListAdapter.getItem(i) instanceof DateItem) {
                                ((DateItem) spotListAdapter.getItem(i)).setIsOpened(false);
                            }
                        }
                        spotListAdapter.notifyDataSetChanged();
                        spotItemView.setIsOpened(false);
                    } else {
                        for (int i = position + 10; i > position; i--) {
                            if (spotListAdapter.getItem(i) instanceof ShopLinkItem) {
                                ((ShopLinkItem) spotListAdapter.getItem(i)).setIsOpened(true);
                            } else if (spotListAdapter.getItem(i) instanceof SpotDetailItem) {
                                ((SpotDetailItem) spotListAdapter.getItem(i)).setIsOpened(true);
                            } else if (spotListAdapter.getItem(i) instanceof DateItem) {
                                ((DateItem) spotListAdapter.getItem(i)).setIsOpened(true);
                            }
                        }
                        spotListAdapter.notifyDataSetChanged();
                        spotItemView.setIsOpened(true);
                    }
                }
            }
        });
        spotList.setDivider(null);
        spotListAdapter.setOnAdapterStarListener(new SpotListAdapter.OnAdapterStarListener() {
            @Override
            public void onAdapterStarClick(SpotListAdapter adapter, final SpotItemView view, final SpotItem data) {
                //현재 별 보고 반대로 동작.
                if(data.is_bookmarked()){
                    NetworkManager.getInstance().deleteBookmark(getContext(), data.get_id(), new NetworkManager.OnResultListener<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(getContext(), "삭제", Toast.LENGTH_SHORT).show();
                            view.getStarView().setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                            data.setIs_bookmarked(false);
                        }

                        @Override
                        public void onFail(int code) {

                        }
                    });


                }else{
                    NetworkManager.getInstance().postBookmark(getContext(), data.get_id(), new NetworkManager.OnResultListener<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(getContext(), "등록", Toast.LENGTH_SHORT).show();
                            view.getStarView().setImageDrawable(getResources().getDrawable(R.drawable.star_yellow));
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
        NetworkManager.getInstance().getSpot(getContext(), new NetworkManager.OnResultListener<SpotData>() {
            @Override
            public void onSuccess(SpotData result) {
                spotListAdapter.clear();
                //일단 그냥 순서대로 받는다 나중에는 리스트에 받았다가 정렬하는 작업이 필요하다.
                for (SpotItem s : result.getItems()) {
                    s.setIs_bookmarked(false);
                    for(Bookmark b : s.getBookmarks()){
                        if(PropertyManager.getInstance().get_Id().equals(b.getUser_id())){
                            s.setIs_bookmarked(true);
                            break;
                        }
                    }
                    spotListAdapter.add(s);

                    DateItem dateItem = new DateItem();
                    spotListAdapter.add(dateItem);
                    for(int i = 0 ; i < 8 ; i++){
                        SpotDetailItem spotDetailItem = new SpotDetailItem();
                        spotListAdapter.add(spotDetailItem);
                    }

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

