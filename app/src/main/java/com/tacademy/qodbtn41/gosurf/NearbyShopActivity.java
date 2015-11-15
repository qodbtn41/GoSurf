package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tacademy.qodbtn41.gosurf.adapter.ShopListAdapter;
import com.tacademy.qodbtn41.gosurf.data.ShopItem;
import com.tacademy.qodbtn41.gosurf.data.ShopListData;
import com.tacademy.qodbtn41.gosurf.fragment.item.ShopItemView;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;

public class NearbyShopActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView shopList;
    ShopListAdapter shopListAdapter;

    private String locationCategory;
    boolean isUpdate = false;
    boolean isLastItem = false;
    private static final int LIMIT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_shop);
        this.init();
        this.setToolbar();
        this.setData();
    }

    private void init() {
        locationCategory = getIntent().getStringExtra("location");

        shopList = (ListView)findViewById(R.id.list__nearby_shop);

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
                Intent intent = new Intent(NearbyShopActivity.this, ShopDetailActivity.class);
                intent.putExtra("_id", ((ShopItemView)view).get_id());
                startActivity(intent);
            }
        });
        shopList.setDivider(null);
    }

    private void getMoreItem() {
        if (!isUpdate){
            int startIndex = shopListAdapter.getStartIndex();
            if (startIndex != -1) {
                isUpdate = true;
                NetworkManager.getInstance().getShopList(NearbyShopActivity.this, locationCategory, startIndex, LIMIT, new NetworkManager.OnResultListener<ShopListData>() {
                    @Override
                    public void onSuccess(ShopListData result) {
                        shopListAdapter.setTotalCount(result.getTotal_count());
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

    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_nearby_shop);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_button));
        toolbar.getNavigationIcon();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_nearby_shop));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_no_shop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_no_shop: {
                startActivity(new Intent(NearbyShopActivity.this, MapActivity.class));
                break;
            }
            case android.R.id.home:{
                //뒤로가기 전달할게있으면 전달..
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setData() {
        NetworkManager.getInstance().getShopList(this, locationCategory, 0, LIMIT, new NetworkManager.OnResultListener<ShopListData>() {
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

    //백키누르면 무조건 메인으로 가게 하자.
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NearbyShopActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkManager.getInstance().cancelAll(this);
    }
}
