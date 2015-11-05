package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.tacademy.qodbtn41.gosurf.fragment.PushMenuFragment;

public class PushActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;

    public static final int TYPE_FRAGMENT_PUSH_MAIN = 0;
    public static final int TYPE_FRAGMENT_PUSH_LOCATION = 1;
    public static final int TYPE_FRAGMENT_PUSH_POPUP = 2;
    public static final int TYPE_FRAGMENT_PUSH_CONTENT = 3;
    public static final int TYPE_FRAGMENT_PUSH_VIBRATION = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_menu);
        setToolbar();
        setNavigationView();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container_push_menu, new PushMenuFragment()).commit();
        }
    }

    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_push_menu);
        toolbar.setTitle(R.string.push_menu);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        toolbar.getNavigationIcon();
        setSupportActionBar(toolbar);
    }

    private void setNavigationView(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_push_menu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_push_menu);
        //headerview 설정
        View view = navigationView.inflateHeaderView(R.layout.nav_header);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PushActivity.this, MyPageActivity.class);
                startActivity(intent);
                finish();
            }
        });
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_invite_friends) {

        } else if (id == R.id.nav_push_menu) {
            //Intent intent = new Intent(PushActivity.this, PushActivity.class);
            //startActivity(intent);
        } else if (id == R.id.nav_version_check) {
            Intent intent = new Intent(PushActivity.this, VersionCheckActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_terms) {
            Intent intent = new Intent(PushActivity.this, TermsActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_push_menu);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_push_menu);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
