package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.adapter.MainTabsAdapter;
import com.tacademy.qodbtn41.gosurf.fragment.SpotFragment;
import com.tacademy.qodbtn41.gosurf.fragment.TimelineFragment;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    TabHost tabHost;
    ViewPager pager;
    MainTabsAdapter mainTabsAdapter;
    Toolbar toolbar;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        fab = (FloatingActionButton) findViewById(R.id.fab_write);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.selected_tab_color)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WriteActivity.class);
                intent.putExtra("type", WriteActivity.TYPE_TIMELINE);
                startActivity(intent);
            }
        });

        tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        pager = (ViewPager)findViewById(R.id.pager_main);
        mainTabsAdapter = new MainTabsAdapter(this, getSupportFragmentManager(), tabHost, pager);

        View spotTabView = getLayoutInflater().inflate(R.layout.view_main_tab, null);
        ImageView image = (ImageView)spotTabView.findViewById(R.id.image_tab_view);
        TextView text = (TextView)spotTabView.findViewById(R.id.text_tab_view);
        image.setImageDrawable(getResources().getDrawable(R.drawable.main_tab_spot_selector));
        text.setText(getString(R.string.surfing_spot));

        mainTabsAdapter.addTab(tabHost.newTabSpec("spot")
                .setIndicator(spotTabView)
                , SpotFragment.class, null);

        View timelineTabView = getLayoutInflater().inflate(R.layout.view_main_tab, null);
        ImageView timelineImage = (ImageView)timelineTabView.findViewById(R.id.image_tab_view);
        TextView timelineText = (TextView)timelineTabView.findViewById(R.id.text_tab_view);
        timelineImage.setImageDrawable(getResources().getDrawable(R.drawable.main_tab_timeline_selector));
        timelineText.setText(getString(R.string.timeline));

        mainTabsAdapter.addTab(tabHost.newTabSpec("timeline")
                .setIndicator(timelineTabView)
                , TimelineFragment.class, null);

        mainTabsAdapter.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabHost.getCurrentTabTag() == "spot") {
                    fab.setVisibility(View.GONE);

                } else if (tabHost.getCurrentTabTag() == "timeline") {
                    fab.setVisibility(View.VISIBLE);
                }
            }
        });

        if (savedInstanceState != null) {
            tabHost.setCurrentTab(savedInstanceState.getInt("tabIndex"));
            mainTabsAdapter.onRestoreInstanceState(savedInstanceState);
        }

        setNavigationView();
    }

    private void setNavigationView(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_main);
        //headerview 설정
        View view = navigationView.inflateHeaderView(R.layout.nav_header);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyPageActivity.class);
                startActivity(intent);
            }
        });
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        toolbar.setTitle(R.string.home_title);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.menu_button));
        toolbar.getNavigationIcon();
        setSupportActionBar(toolbar);
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
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_item_default2: {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
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

        if (id == R.id.nav_invite_friends) {

        } else if (id == R.id.nav_push_menu) {
            Intent intent = new Intent(MainActivity.this, PushActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_version_check) {
            Intent intent = new Intent(MainActivity.this, VersionCheckActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_terms) {
            Intent intent = new Intent(MainActivity.this, TermsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkManager.getInstance().cancelAll(this);
    }

}
