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
import android.widget.VideoView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.tacademy.qodbtn41.gosurf.adapter.CommentListAdapter;
import com.tacademy.qodbtn41.gosurf.adapter.TimelineListAdapter;
import com.tacademy.qodbtn41.gosurf.data.CommentItem;
import com.tacademy.qodbtn41.gosurf.data.TimelineData;
import com.tacademy.qodbtn41.gosurf.data.TimelineDetailItem;
import com.tacademy.qodbtn41.gosurf.fragment.TimelineFragment;
import com.tacademy.qodbtn41.gosurf.item.DetailButton;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;
import com.tacademy.qodbtn41.gosurf.manager.PropertyManager;

public class TimelineDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView commentList;
    CommentListAdapter commentListAdapter;
    String articleId, userId, createdTime;//현재 글 작성자의 userId이다.
    DisplayImageOptions options;

    View contentView, textView, buttonsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_detail);
        this.init();
        this.setToolbar();
        this.setData();
    }

    private void init(){
        articleId = getIntent().getStringExtra("_id");
        commentList = (ListView)findViewById(R.id.list_comment_timeline);
        addHeaderView();

        commentListAdapter = new CommentListAdapter();
        commentList.setAdapter(commentListAdapter);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .build();
    }

    private void addHeaderView(){
        textView = getLayoutInflater().inflate(R.layout.item_text_detail, null);
        //사용자와 작성자가 같으면 옵션메뉴가 뜬다. 페이스북 아이디랑 유저아이디랑 같은지는 모르겠다. user_id는 닉네임인가?
        if(PropertyManager.getInstance().getFacebookId().equals(userId)){
            ImageView imageView = (ImageView)textView.findViewById(R.id.image_more_detail);

            // 자기 글이면 클릭가능하게 하고 아니면 클릭안되게 클릭안되면 클릭리스너도 작동안하겠지...
            //imageView.setClickable();

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //클릭하면 메뉴창 뜨게 만들자.
                }
            });
        }

        Intent intent = getIntent();
        int type = intent.getIntExtra("type", -1);
        switch(type){
            case TimelineListAdapter.TYPE_TEXT : {
                commentList.addHeaderView(textView, null, false);
                break;
            }
            case TimelineListAdapter.TYPE_VIDEO : {
                contentView = getLayoutInflater().inflate(R.layout.item_video_detail, null);
                commentList.addHeaderView(contentView, null, false);
                commentList.addHeaderView(textView, null, false);
                break;
            }
            case TimelineListAdapter.TYPE_PICTURE :
            default: {
                contentView = getLayoutInflater().inflate(R.layout.item_picture_detail, null);
                commentList.addHeaderView(contentView, null, false);
                commentList.addHeaderView(textView, null, false);
                break;
            }
        }

        buttonsView = getLayoutInflater().inflate(R.layout.view_detail_btns, null);
        DetailButton btn = (DetailButton)buttonsView.findViewById(R.id.btn_write_comment);
        btn.setData(getString(R.string.write_comment), getResources().getDrawable(R.drawable.comment_detail_button));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //댓글달기 write페이지로 넘어가자
                Intent intent = new Intent(TimelineDetailActivity.this, WriteActivity.class);
                intent.putExtra("type", WriteActivity.TYPE_COMMENT);
                intent.putExtra("article_id", articleId);
                startActivityForResult(intent, WriteActivity.TYPE_COMMENT);
            }
        });
        btn = (DetailButton)buttonsView.findViewById(R.id.btn_rating);
        btn.setData(getString(R.string.like), getResources().getDrawable(R.drawable.like_detail_off));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이미지 변경하고 숫자 하나올리고 네트워크에 전송하기
            }
        });
        btn = (DetailButton)buttonsView.findViewById(R.id.btn_show_location);
        btn.setData(getString(R.string.share), getResources().getDrawable(R.drawable.share_detail_button));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //페이스북 공유버튼 공유하기를 누르면 페이스북에 이 글내용을 공유할 수 있다.
            }
        });
        commentList.addHeaderView(buttonsView, null, false);
    }

    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_timeline_detail);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_button));
        toolbar.getNavigationIcon();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_timeline_detail));
    }

    private void setData(){
        NetworkManager.getInstance().getTimelineDetail(this, articleId, new NetworkManager.OnResultListener<TimelineData>() {
            @Override
            public void onSuccess(TimelineData result) {
                commentListAdapter.clear();

                for (TimelineDetailItem t : result.getItems()) {
                    switch (t.getAttachment().getType()) {
                        case TimelineFragment.TYPE_TEXT: {
                            break;
                        }
                        case TimelineFragment.TYPE_PICTURE: {
                            ImageView pictureView = (ImageView)contentView.findViewById(R.id.image_detail);
                            ImageLoader.getInstance().displayImage(t.getAttachment().getFile_url(), pictureView, options);
                            break;
                        }
                        case TimelineFragment.TYPE_VIDEO: {
                            VideoView videoView = (VideoView)contentView.findViewById(R.id.video_detail);
                            videoView.setVideoPath(t.getAttachment().getFile_url());
                            break;
                        }
                    }

                    TextView userIdView = (TextView) textView.findViewById(R.id.text_user_name);
                    userId = t.getUser_id();
                    userIdView.setText(userId);

                    TextView createdTimeView = (TextView) textView.findViewById(R.id.text_created_time);
                    createdTime = t.getCreated_date();
                    createdTimeView.setText(createdTime);

                    TextView contentView = (TextView) textView.findViewById(R.id.text_content);
                    contentView.setText(t.getContent());

                    for(CommentItem c : t.getComments()){
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
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkManager.getInstance().cancelAll(this);
    }
}
