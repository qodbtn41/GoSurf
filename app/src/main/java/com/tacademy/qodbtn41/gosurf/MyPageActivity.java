package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tacademy.qodbtn41.gosurf.adapter.TimelineListAdapter;
import com.tacademy.qodbtn41.gosurf.item.PictureItemView;
import com.tacademy.qodbtn41.gosurf.item.VideoItemView;
import com.tacademy.qodbtn41.gosurf.manager.PropertyManager;

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
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_button));
        toolbar.getNavigationIcon();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_mypage));
    }

    private void init() {
        myTimelineList = (ListView)findViewById(R.id.list_mypage);

        View headerView = getLayoutInflater().inflate(R.layout.header_mypage, null);
        ImageView userImage = (ImageView)headerView.findViewById(R.id.image_profile_mypage);
        TextView textEmail = (TextView)headerView.findViewById(R.id.text_email_mypage);
        TextView textName = (TextView)headerView.findViewById(R.id.text_username_mypage);


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
                finish();
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
                    //String userName = ((PictureItem)myTimelineList.getItemAtPosition(position)).get();
                    //sb.append(userName).append(",");
                }
            }
            Toast.makeText(MyPageActivity.this, "items : " + sb.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
