package com.tacademy.qodbtn41.gosurf;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kakao.kakaolink.AppActionBuilder;
import com.kakao.kakaolink.AppActionInfoBuilder;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tacademy.qodbtn41.gosurf.fragment.PushMenuFragment;
import com.tacademy.qodbtn41.gosurf.manager.PropertyManager;

public class PushActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;

    public static final int TYPE_FRAGMENT_PUSH_MAIN = 0;
    public static final int TYPE_FRAGMENT_PUSH_LOCATION = 1;
    public static final int TYPE_FRAGMENT_PUSH_POPUP = 2;
    public static final int TYPE_FRAGMENT_PUSH_CONTENT = 3;
    public static final int TYPE_FRAGMENT_PUSH_VIBRATION = 4;

    private KakaoLink kakaoLink;
    private KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder;
    private final String imageSrc = "https://s3-ap-northeast-1.amazonaws.com/gosurfs3/file/shop/8236872_%EB%AA%A8%EC%BF%A0.PNG";

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
//        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_launcher));
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
        ImageView userImage = (ImageView)view.findViewById(R.id.image_nav_header_user);
        TextView textEmail = (TextView)view.findViewById(R.id.text_email);
        TextView textName = (TextView)view.findViewById(R.id.text_name);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .showImageOnLoading(R.drawable.loading1)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.loading_error)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(200))
                .build();
        ImageLoader.getInstance().displayImage(PropertyManager.getInstance().getProfileUrl(), userImage, options);
        textEmail.setText(PropertyManager.getInstance().getEmail());
        textName.setText(PropertyManager.getInstance().getName());

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(PushActivity.this, MyPageActivity.class);
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
            sendKakaoTalkLink();
            //한번 보냈고 새로 보내야되니까 만든다....
            kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
        } else if (id == R.id.nav_push_menu) {
            //Intent intent = new Intent(PushActivity.this, PushActivity.class);
            //startActivity(intent);
        }
//        else if (id == R.id.nav_version_check) {
//            Intent intent = new Intent(PushActivity.this, VersionCheckActivity.class);
//            startActivity(intent);
//            finish();
//        }
//        else if (id == R.id.nav_terms) {
//            Intent intent = new Intent(PushActivity.this, TermsActivity.class);
//            startActivity(intent);
//            finish();
//        }

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

    private void sendKakaoTalkLink() {
        try {
            kakaoTalkLinkMessageBuilder.addText(getString(R.string.kakaolink_text));
            kakaoTalkLinkMessageBuilder.addImage(imageSrc, 300, 200);
            kakaoTalkLinkMessageBuilder.addAppButton(getString(R.string.kakaolink_appbutton), new AppActionBuilder()
                    .addActionInfo(AppActionInfoBuilder.createAndroidActionInfoBuilder().setExecuteParam("execparamkey2=2222").setMarketParam("referrer=kakaotalklink").build())
                    .addActionInfo(AppActionInfoBuilder.createiOSActionInfoBuilder(AppActionBuilder.DEVICE_TYPE.PHONE).setExecuteParam("execparamkey2=2222").build())
                    .setUrl("http://www.kakao.com").build());
            kakaoTalkLinkMessageBuilder.setForwardable(true);
            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this);

        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }
    }
}
