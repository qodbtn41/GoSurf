package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tacademy.qodbtn41.gosurf.adapter.ShopListAdapter;
import com.tacademy.qodbtn41.gosurf.data.ShopItemData;

public class NearbyShopActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView shopList;
    ShopListAdapter shopListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_shop);
        this.init();
        this.setToolbar();
        this.setData();
    }

    private void init() {
        shopList = (ListView)findViewById(R.id.list__nearby_shop);
        shopListAdapter = new ShopListAdapter();
        shopList.setAdapter(shopListAdapter);
        shopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String shopName = ((ShopItemData) shopList.getItemAtPosition(position)).getShopName();
                startActivity(new Intent(NearbyShopActivity.this, ShopDetailActivity.class));
            }
        });
    }

    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_nearby_shop);
        toolbar.setTitle("근처샵보기");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(android.R.drawable.ic_media_previous));
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
            case R.id.menu_item_shop2: {
                startActivity(new Intent(NearbyShopActivity.this, MapActivity.class));
                break;
            }
            case android.R.id.home:{
                //뒤로가기버튼
            }
        }
        return super.onOptionsItemSelected(item);
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
}
