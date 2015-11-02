package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.tacademy.qodbtn41.gosurf.adapter.MainTabsAdapter;
import com.tacademy.qodbtn41.gosurf.fragment.SpotFragment;
import com.tacademy.qodbtn41.gosurf.fragment.TimelineFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    TabHost tabHost;
    ViewPager pager;
    MainTabsAdapter weatherAdapter;
    Toolbar toolbar;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        pager = (ViewPager)findViewById(R.id.pager_main);
        weatherAdapter = new MainTabsAdapter(this, getSupportFragmentManager(), tabHost, pager);

        weatherAdapter.addTab(tabHost.newTabSpec("spot")
                .setIndicator(getString(R.string.surfing_spot), getResources().getDrawable(R.mipmap.ic_launcher))
                , SpotFragment.class, null);
        weatherAdapter.addTab(tabHost.newTabSpec("timeline")
                .setIndicator(getString(R.string.timeline), getResources().getDrawable(R.mipmap.ic_launcher))
                , TimelineFragment.class, null);

        if (savedInstanceState != null) {
            tabHost.setCurrentTab(savedInstanceState.getInt("tabIndex"));
            weatherAdapter.onRestoreInstanceState(savedInstanceState);
        }
    }

    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        toolbar.setTitle(R.string.home_title);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        toolbar.getNavigationIcon();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_main));
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("tabIndex", tabHost.getCurrentTab());
        weatherAdapter.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item1 : {
                startActivity(new Intent(MainActivity.this, NearbyShopActivity.class));
                break;
            }
            case R.id.menu_item2 : {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
                break;
            }
            case android.R.id.home:{
                //내비게이션뷰를 띄워줄 부분
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}
