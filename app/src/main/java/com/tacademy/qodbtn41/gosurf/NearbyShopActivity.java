package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tacademy.qodbtn41.gosurf.fragment.NearbyShopFragment;
import com.tacademy.qodbtn41.gosurf.fragment.ShopDetailFragment;

public class NearbyShopActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_shop);
        this.setToolbar();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container_shop, new NearbyShopFragment()).commit();
        }
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

    public void pushShopDetailFragment(String shopName) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_shop, new ShopDetailFragment(shopName)).addToBackStack(null).commit();
    }
}
