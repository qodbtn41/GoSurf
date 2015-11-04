package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.tacademy.qodbtn41.gosurf.adapter.CommentListAdapter;
import com.tacademy.qodbtn41.gosurf.adapter.TimelineListAdapter;
import com.tacademy.qodbtn41.gosurf.data.CommentItemData;

public class TimelineDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView commentList;
    CommentListAdapter commentListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_detail);
        this.init();
        this.setToolbar();
        this.setData();
    }

    private void init(){
        View headerView;
        commentList = (ListView)findViewById(R.id.list_comment_timeline);

        Intent intent = getIntent();
        int type = intent.getIntExtra("type", -1);
        switch(type){
            case TimelineListAdapter.TYPE_VIDEO : {
                headerView = getLayoutInflater().inflate(R.layout.item_video, null);
                break;
            }
            case TimelineListAdapter.TYPE_PICTURE :
            default: {
                headerView = getLayoutInflater().inflate(R.layout.item_picture, null);
                break;
            }
        }
        commentList.addHeaderView(headerView, null, false);


        headerView = getLayoutInflater().inflate(R.layout.item_button_timeline, null);
        Button btn = (Button)headerView.findViewById(R.id.btn_comment_timeline);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //댓글달기 write페이지로 넘어가자
                Intent intent = new Intent(TimelineDetailActivity.this, WriteActivity.class);
                intent.putExtra("type", WriteActivity.TYPE_COMMENT);
                startActivity(intent);
            }
        });
        commentList.addHeaderView(headerView, null, false);

        commentListAdapter = new CommentListAdapter();
        commentList.setAdapter(commentListAdapter);
    }

    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_timeline_detail);
        toolbar.setTitle("Timeline");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(android.R.drawable.ic_media_previous));
        toolbar.getNavigationIcon();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_timeline_detail));
    }

    private void setData(){
        String userName = "sini1598";
        String time = "17분";
        String content = "오늘 서해 만리포 가을바다에서 서핑 캠핑...! 아들과 멋진 추억 남겼습니다. 아이들에게 가을바다에서 좋은 추억을 남겨준 ...";
        for (int i = 0; i < 10; i++) {
            CommentItemData tempData = new CommentItemData();
            tempData.setUserName(userName);
            tempData.setContent(content);
            tempData.setTime(time);

            commentListAdapter.add(tempData);
        }
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
                Intent intent = new Intent(TimelineDetailActivity.this, ShopActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_item_default2: {
                Intent intent = new Intent(TimelineDetailActivity.this, MapActivity.class);
                startActivity(intent);
                break;
            }
            case android.R.id.home:{
                //내비게이션뷰를 띄워줄 부분
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
