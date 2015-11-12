package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.tacademy.qodbtn41.gosurf.adapter.ShopTabsAdapter;
import com.tacademy.qodbtn41.gosurf.fragment.ShopListFragment;

public class ShopActivity extends AppCompatActivity {
    TabHost tabHost;
    ViewPager pager;
    ShopTabsAdapter shopTabsAdapter;
    Toolbar toolbar;

    private String locationCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        init(savedInstanceState);
        this.setToolbar();
    }

    private void init(Bundle savedInstanceState) {
        tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        pager = (ViewPager)findViewById(R.id.pager_shop);
        shopTabsAdapter = new ShopTabsAdapter(this, getSupportFragmentManager(), tabHost, pager);

        Bundle args = new Bundle();
        args.putString("location", "east_n");
        shopTabsAdapter.addTab(tabHost.newTabSpec("east_n").setIndicator(getString(R.string.east_n_coast))
                , ShopListFragment.class, args);

        args = new Bundle();
        args.putString("location", "east_s");
        shopTabsAdapter.addTab(tabHost.newTabSpec("east_s").setIndicator(getString(R.string.east_s_coast))
                , ShopListFragment.class, args);

        args = new Bundle();
        args.putString("location", "west");
        shopTabsAdapter.addTab(tabHost.newTabSpec("west").setIndicator(getString(R.string.west_coast))
                , ShopListFragment.class, args);

        args = new Bundle();
        args.putString("location", "jeju");
        shopTabsAdapter.addTab(tabHost.newTabSpec("jeju").setIndicator(getString(R.string.jeju_coast))
                , ShopListFragment.class, args);


        if (savedInstanceState != null) {
            tabHost.setCurrentTab(savedInstanceState.getInt("tabIndex"));
            shopTabsAdapter.onRestoreInstanceState(savedInstanceState);
        }
    }


    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_shop);
        toolbar.setTitle(R.string.shop_title);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        toolbar.getNavigationIcon();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_shop));
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("tabIndex", tabHost.getCurrentTab());
        shopTabsAdapter.onSaveInstanceState(outState);
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
                Intent intent = new Intent(ShopActivity.this, MapActivity.class);
                startActivity(intent);
                break;
            }
            case android.R.id.home:{
                //내비게이션뷰를 띄워줄 부분
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
