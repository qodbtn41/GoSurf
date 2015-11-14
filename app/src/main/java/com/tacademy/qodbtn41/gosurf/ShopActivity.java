package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.adapter.ShopTabsAdapter;
import com.tacademy.qodbtn41.gosurf.fragment.ShopListFragment;

public class ShopActivity extends AppCompatActivity {
    TabHost tabHost;
    ViewPager pager;
    ShopTabsAdapter shopTabsAdapter;
    Toolbar toolbar;



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
        View eastnTabView = getLayoutInflater().inflate(R.layout.view_shop_tab, null);
        TextView titleView = (TextView)eastnTabView.findViewById(R.id.text_shop_tab);
        titleView.setText(getString(R.string.east_n_coast));
        shopTabsAdapter.addTab(tabHost.newTabSpec("east_n").setIndicator(eastnTabView)
                , ShopListFragment.class, args);

        args = new Bundle();
        args.putString("location", "east_s");
        View eastsTabView = getLayoutInflater().inflate(R.layout.view_shop_tab, null);
        titleView = (TextView)eastsTabView.findViewById(R.id.text_shop_tab);
        titleView.setText(getString(R.string.east_s_coast));
        shopTabsAdapter.addTab(tabHost.newTabSpec("east_s").setIndicator(eastsTabView)
                , ShopListFragment.class, args);

        args = new Bundle();
        args.putString("location", "west");
        View westTabView = getLayoutInflater().inflate(R.layout.view_shop_tab, null);
        titleView = (TextView)westTabView.findViewById(R.id.text_shop_tab);
        titleView.setText(getString(R.string.west_coast));
        shopTabsAdapter.addTab(tabHost.newTabSpec("west").setIndicator(westTabView)
                , ShopListFragment.class, args);

        args = new Bundle();
        args.putString("location", "jeju");
        View jejuTabView = getLayoutInflater().inflate(R.layout.view_shop_tab, null);
        titleView = (TextView)jejuTabView.findViewById(R.id.text_shop_tab);
        titleView.setText(getString(R.string.jeju_coast));
        shopTabsAdapter.addTab(tabHost.newTabSpec("jeju").setIndicator(jejuTabView)
                , ShopListFragment.class, args);


        if (savedInstanceState != null) {
            tabHost.setCurrentTab(savedInstanceState.getInt("tabIndex"));
            shopTabsAdapter.onRestoreInstanceState(savedInstanceState);
        }
    }


    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_shop);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_button));
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
                //
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
