package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.tacademy.qodbtn41.gosurf.adapter.CommentListAdapter;
import com.tacademy.qodbtn41.gosurf.data.CommentItem;
import com.tacademy.qodbtn41.gosurf.data.Locations;
import com.tacademy.qodbtn41.gosurf.data.ShopData;
import com.tacademy.qodbtn41.gosurf.data.ShopDetailItem;
import com.tacademy.qodbtn41.gosurf.item.DetailButton;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;
import com.tacademy.qodbtn41.gosurf.manager.PropertyManager;

public class ShopDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView commentList;
    CommentListAdapter commentListAdapter;
    String shopId, phoneNumber, pageUrl, shopName;//이 액티비티시작하는 intent에서 받아온다.
    int rate;
    Locations locations;
    DisplayImageOptions options;

    View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        this.init();
        this.setToolbar();
        this.setData();

    }

    private void init(){
        shopId = getIntent().getStringExtra("_id");

        commentList = (ListView)findViewById(R.id.list_comment);
        addHeaderView();

        commentListAdapter = new CommentListAdapter();
        commentList.setAdapter(commentListAdapter);
        commentList.setDivider(null);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.animation_list)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.loading_error)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .build();
    }

    private void addHeaderView() {
        headerView = getLayoutInflater().inflate(R.layout.header_shop_detail, null);

        DetailButton btn = (DetailButton)headerView.findViewById(R.id.btn_write_comment);
        btn.setData(getString(R.string.write_comment), getResources().getDrawable(R.drawable.comment_detail_button));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //댓글달기 write페이지로 넘어가자
                Intent intent = new Intent(ShopDetailActivity.this, WriteActivity.class);
                intent.putExtra("type", WriteActivity.TYPE_COMMENT_SHOP);
                intent.putExtra("shop_id", shopId);
                startActivityForResult(intent, WriteActivity.TYPE_COMMENT_SHOP);
            }
        });
        btn = (DetailButton)headerView.findViewById(R.id.btn_rating);
        btn.setData(getString(R.string.rating), getResources().getDrawable(R.drawable.grade_detail_button));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn = (DetailButton)headerView.findViewById(R.id.btn_show_location);
        btn.setData(getString(R.string.show_location), getResources().getDrawable(R.drawable.map_detail_button));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopDetailActivity.this, MapActivity.class);
                intent.putExtra("locations", locations);
                startActivity(intent);
            }
        });

        ImageView imageButton = (ImageView)headerView.findViewById(R.id.image_btn_call);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });
        imageButton = (ImageView)headerView.findViewById(R.id.image_btn_search);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopDetailActivity.this, WebViewActivity.class);
                intent.putExtra("shopname", shopName);
                startActivity(intent);
            }
        });
        imageButton = (ImageView)headerView.findViewById(R.id.image_btn_site);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pageUrl != null){
                    Intent intent = new Intent(ShopDetailActivity.this, WebViewActivity.class);
                    intent.putExtra("url", pageUrl);
                    startActivity(intent);
                }else{
                    Toast.makeText(ShopDetailActivity.this, "페이지가 없으면 뜨는 창", Toast.LENGTH_SHORT).show();
                }
            }
        });

        commentList.addHeaderView(headerView, null, false);
    }

    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_shop_detail);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_button));
        toolbar.getNavigationIcon();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_shop_detail));
    }

    private void setData() {
        NetworkManager.getInstance().getShopDetail(ShopDetailActivity.this, shopId, new NetworkManager.OnResultListener<ShopData>() {
            @Override
            public void onSuccess(ShopData result) {
                commentListAdapter.clear();
                for(ShopDetailItem s : result.getItems()) {
                    phoneNumber = s.getPhone();
                    pageUrl = s.getPage_url();
                    rate = s.getGrade();
                    locations = s.getLocations();
                    commentListAdapter.setTotalCount(s.getComments_count());

                    TextView title = (TextView)headerView.findViewById(R.id.text_shop_title);
                    shopName = s.getName();
                    title.setText(shopName);

                    TextView description = (TextView)headerView.findViewById(R.id.text_shop_description);
                    description.setText(s.getDescription());

                    ImageView image = (ImageView)headerView.findViewById(R.id.image_shop_detail);
                    ImageLoader.getInstance().displayImage(s.getImage_url(), image, options);
                    for(CommentItem c : s.getComments()) {
                        commentListAdapter.add(c);
                    }
                }
            }

            @Override
            public void onFail(int code) {

            }
        });
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
                Intent intent = new Intent(ShopDetailActivity.this, MapActivity.class);
                startActivity(intent);
                break;
            }
            case android.R.id.home:{
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_CANCELED)
            return;

        switch(requestCode){
            case WriteActivity.TYPE_COMMENT_SHOP:{
                String content = data.getStringExtra("content");
                CommentItem commentItem = new CommentItem();
                commentItem.set_id(PropertyManager.getInstance().get_Id());
                commentItem.setContent(content);
                commentItem.setCreated_date(((Long) System.currentTimeMillis()).toString());//시간도 받아오거나 시간처리하는 뷰를 만들어준 뒤 변경
                commentItem.setUser_id(PropertyManager.getInstance().getName());//이건 내 id니까 로그인후에 추가
                commentListAdapter.add(commentItem);
                break;
            }
            case WriteActivity.TYPE_MODIFY_COMMENT_SHOP:{

                break;
            }

        }
    }
}
