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

public class VersionCheckActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_check);
        setToolbar();
        setNavigationView();
    }

    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_version_check);
        toolbar.setTitle(R.string.version_check_menu);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        toolbar.getNavigationIcon();
        setSupportActionBar(toolbar);
    }

    private void setNavigationView(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_version_check);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_version_check);
        //headerview 설정
        View view = navigationView.inflateHeaderView(R.layout.nav_header);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VersionCheckActivity.this, MyPageActivity.class);
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
            Intent intent = new Intent(VersionCheckActivity.this, PushActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_version_check) {
            //Intent intent = new Intent(VersionCheckActivity.this, VersionCheckActivity.class);
            //startActivity(intent);
        } else if (id == R.id.nav_terms) {
            Intent intent = new Intent(VersionCheckActivity.this, TermsActivity.class);
            startActivity(intent);
            finish();
        }//d

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_version_check);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_version_check);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
