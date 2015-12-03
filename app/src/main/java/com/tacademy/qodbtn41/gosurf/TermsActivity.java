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

public class TermsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        setToolbar();
        setNavigationView();
    }

    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_terms);
        toolbar.setTitle(R.string.terms_menu_title);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        toolbar.getNavigationIcon();
        setSupportActionBar(toolbar);
    }

    private void setNavigationView(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_terms);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_terms);
        //headerview 설정
        View view = navigationView.inflateHeaderView(R.layout.nav_header);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TermsActivity.this, MyPageActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_invite_friends) {

        } else if (id == R.id.nav_push_menu) {
            Intent intent = new Intent(TermsActivity.this, PushActivity.class);
            startActivity(intent);
            finish();
        }
//        else if (id == R.id.nav_version_check) {
//            Intent intent = new Intent(TermsActivity.this, VersionCheckActivity.class);
//            startActivity(intent);
//            finish();
//        } else if (id == R.id.nav_terms) {
//            //Intent intent = new Intent(TermsActivity.this, TermsActivity.class);
//            //startActivity(intent);
//            //finish();
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_terms);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_terms);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
