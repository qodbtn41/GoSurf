package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
    MainTabsAdapter mainTabsAdapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        pager = (ViewPager)findViewById(R.id.pager_main);
        mainTabsAdapter = new MainTabsAdapter(this, getSupportFragmentManager(), tabHost, pager);

        mainTabsAdapter.addTab(tabHost.newTabSpec("spot")
                .setIndicator(getString(R.string.surfing_spot), getResources().getDrawable(R.mipmap.ic_launcher))
                , SpotFragment.class, null);
        mainTabsAdapter.addTab(tabHost.newTabSpec("timeline")
                .setIndicator(getString(R.string.timeline), getResources().getDrawable(R.mipmap.ic_launcher))
                , TimelineFragment.class, null);

        if (savedInstanceState != null) {
            tabHost.setCurrentTab(savedInstanceState.getInt("tabIndex"));
            mainTabsAdapter.onRestoreInstanceState(savedInstanceState);
        }

        setNavigationView();
    }

    private void setNavigationView(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        mainTabsAdapter.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_default, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_default1: {
                startActivity(new Intent(MainActivity.this, NearbyShopActivity.class));
                break;
            }
            case R.id.menu_item_default2: {
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //홈이니까 아무것도 안한다.
        } else if (id == R.id.nav_invite_friends) {

        } else if (id == R.id.nav_push_menu) {
            Intent intent = new Intent(MainActivity.this, PushMenuActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_version_check) {

        } else if (id == R.id.nav_terms) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
