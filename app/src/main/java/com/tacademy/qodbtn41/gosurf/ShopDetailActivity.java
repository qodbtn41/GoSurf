package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.tacademy.qodbtn41.gosurf.adapter.CommentListAdapter;
import com.tacademy.qodbtn41.gosurf.data.CommentItem;
import com.tacademy.qodbtn41.gosurf.data.ShopData;
import com.tacademy.qodbtn41.gosurf.data.ShopDetailItem;
import com.tacademy.qodbtn41.gosurf.fragment.item.ShopDetailButton;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;

public class ShopDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView commentList;
    CommentListAdapter commentListAdapter;
    String id, phoneNumber;//이 액티비티시작하는 intent에서 받아온다.
    int rate;
    DisplayImageOptions options;

    View headerView;
    boolean isUpdate = false;
    boolean isLastItem = false;
    private static final int LIMIT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        this.init();
        this.setToolbar();
        this.setData();

    }

    private void init(){
        id = getIntent().getStringExtra("_id");
        headerView = getLayoutInflater().inflate(R.layout.header_shop_detail, null);

        ShopDetailButton btn = (ShopDetailButton)headerView.findViewById(R.id.btn_write_comment);
        btn.setData(getString(R.string.write_comment), getResources().getDrawable(R.drawable.comment_detail_button));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //댓글달기 write페이지로 넘어가자
                Intent intent = new Intent(ShopDetailActivity.this, WriteActivity.class);
                intent.putExtra("type", WriteActivity.TYPE_COMMENT);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        btn = (ShopDetailButton)headerView.findViewById(R.id.btn_rating);
        btn.setData(getString(R.string.rating), getResources().getDrawable(R.drawable.grade_detail_button));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn = (ShopDetailButton)headerView.findViewById(R.id.btn_show_location);
        btn.setData(getString(R.string.show_location), getResources().getDrawable(R.drawable.map_detail_button));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        commentList = (ListView)findViewById(R.id.list_comment);

        commentList.addHeaderView(headerView, null, false);
        commentListAdapter = new CommentListAdapter();
        commentList.setAdapter(commentListAdapter);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .build();
    }

    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_shop_detail);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_button));
        toolbar.getNavigationIcon();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_shop_detail));
    }

    private void setData() {
        NetworkManager.getInstance().getShopDetail(ShopDetailActivity.this, id, new NetworkManager.OnResultListener<ShopData>() {
            @Override
            public void onSuccess(ShopData result) {
                commentListAdapter.clear();
                for(ShopDetailItem s : result.getItems()) {
                    phoneNumber = s.getPhone();
                    rate = s.getGrade();
                    commentListAdapter.setTotalCount(s.getComments_count());

                    TextView title = (TextView)headerView.findViewById(R.id.text_shop_title);
                    title.setText(s.getName());

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
}
