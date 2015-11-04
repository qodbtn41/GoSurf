package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tacademy.qodbtn41.gosurf.adapter.TimelineListAdapter;
import com.tacademy.qodbtn41.gosurf.data.PictureItemData;
import com.tacademy.qodbtn41.gosurf.fragment.item.PictureItemView;
import com.tacademy.qodbtn41.gosurf.fragment.item.VideoItemView;

public class MyPageActivity extends AppCompatActivity {
    ListView myTimelineList;
    TimelineListAdapter timelineListAdapter;
    AdapterView.OnItemClickListener itemclickListener;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        this.init();
        this.setToolbar();
        this.setData();
    }

    private void setToolbar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar_mypage);
        toolbar.setTitle("My");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(android.R.drawable.ic_media_previous));
        toolbar.getNavigationIcon();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_mypage));
    }

    private void init() {
        View headerView = getLayoutInflater().inflate(R.layout.header_mypage, null);

        myTimelineList = (ListView)findViewById(R.id.list_mypage);
        myTimelineList.addHeaderView(headerView, null, false);

        timelineListAdapter = new TimelineListAdapter();
        myTimelineList.setAdapter(timelineListAdapter);
        itemclickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view instanceof PictureItemView) {
                    //뷰에서 어떤 스팟에 속했는지 받아서 다시전달
                    Intent intent = new Intent(MyPageActivity.this, TimelineDetailActivity.class);
                    intent.putExtra("type", TimelineListAdapter.TYPE_PICTURE);
                    startActivity(intent);
                } else if (view instanceof VideoItemView) {
                    Intent intent = new Intent(MyPageActivity.this, TimelineDetailActivity.class);
                    intent.putExtra("type", TimelineListAdapter.TYPE_VIDEO);
                    startActivity(intent);
                }
            }
        };
        myTimelineList.setOnItemClickListener(itemclickListener);
    }

    private void setData() {
        String userName = "sini1598";
        String time = "17분";
        String content = "오늘 서해 만리포 가을바다에서 서핑 캠핑...! 아들과 멋진 추억 남겼습니다. 아이들에게 가을바다에서 좋은 추억을 남겨준 ...";
        Drawable picture = getResources().getDrawable(android.R.drawable.ic_menu_camera);
        for (int i = 0; i < 10; i++) {
            PictureItemData tempData = new PictureItemData();
            tempData.setPicture(picture);
            tempData.setUserName(userName);
            tempData.setContent(content);
            tempData.setTime(time);

            timelineListAdapter.add(tempData);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_default, menu);
        return true;
    }
    boolean isDeleteStarted = false;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_default1: {
                if(!isDeleteStarted){
                    item.setTitle("완료");
                    myTimelineList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                    timelineListAdapter.setCheckMode(true);
                    myTimelineList.setOnItemClickListener(null);
                    isDeleteStarted = true;
                    break;
                }else{//삭제확정......
                    onChoiceItem();
                    myTimelineList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                    timelineListAdapter.setCheckMode(false);
                    myTimelineList.setOnItemClickListener(itemclickListener);
                    isDeleteStarted = false;
                    break;
                }

            }
            case android.R.id.home:{
                //내비게이션뷰를 띄워줄 부분
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void onChoiceItem() {
        if (myTimelineList.getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE) {
            SparseBooleanArray selection = myTimelineList.getCheckedItemPositions();
            StringBuilder sb = new StringBuilder();
            for (int index = 0; index < selection.size(); index++) {
                int position = selection.keyAt(index);
                if (selection.get(position)) {
                    String userName = ((PictureItemData)myTimelineList.getItemAtPosition(position)).getUserName();
                    sb.append(userName).append(",");
                }
            }
            Toast.makeText(MyPageActivity.this, "items : " + sb.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
