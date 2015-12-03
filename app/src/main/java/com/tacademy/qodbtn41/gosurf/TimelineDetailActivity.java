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
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.tacademy.qodbtn41.gosurf.adapter.CommentListAdapter;
import com.tacademy.qodbtn41.gosurf.adapter.TimelineListAdapter;
import com.tacademy.qodbtn41.gosurf.data.CommentItem;
import com.tacademy.qodbtn41.gosurf.data.LikeParticipants;
import com.tacademy.qodbtn41.gosurf.data.TimelineData;
import com.tacademy.qodbtn41.gosurf.data.TimelineDetailItem;
import com.tacademy.qodbtn41.gosurf.fragment.TimelineFragment;
import com.tacademy.qodbtn41.gosurf.item.DetailButton;
import com.tacademy.qodbtn41.gosurf.item.menu.CustomPopupWindow;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;
import com.tacademy.qodbtn41.gosurf.manager.PropertyManager;
import com.tacademy.qodbtn41.gosurf.manager.TimeManager;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class TimelineDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView commentList;
    CommentListAdapter commentListAdapter;
    String articleId, userId, createdTime, articleWriterId;//현재 글 작성자의 userId이다.
    int likeCount;
    DisplayImageOptions options;
    View contentView, textView, buttonsView;
    CustomPopupWindow popup;
    DetailButton likeButton, commentButton, locationButton;
    CallbackManager callbackManager;
    LoginManager mLoginManager;

    private boolean isLiked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_detail);
        this.init();
        this.setToolbar();
        this.setData();
    }

    private void init(){
        mLoginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();

        articleId = getIntent().getStringExtra("_id");
        userId = PropertyManager.getInstance().get_Id();
        commentList = (ListView)findViewById(R.id.list_comment_timeline);
        addHeaderView();

        commentListAdapter = new CommentListAdapter();
        commentList.setAdapter(commentListAdapter);
        commentList.setDivider(null);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading1)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.loading_error)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .build();
    }

    private void addHeaderView(){
        textView = getLayoutInflater().inflate(R.layout.item_text_detail, null);
        setMoreMenu();

        Intent intent = getIntent();
        int type = intent.getIntExtra("type", -1);
        switch(type){
            case TimelineListAdapter.TYPE_TEXT : {
                commentList.addHeaderView(textView, null, false);
                break;
            }
            case TimelineListAdapter.TYPE_VIDEO : {
                contentView = getLayoutInflater().inflate(R.layout.item_video_detail, null);
                VideoView videoView = (VideoView)contentView.findViewById(R.id.video_detail);
                MediaController mediaController = new MediaController(this);
                videoView.setMediaController(mediaController);
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
        commentButton = (DetailButton)buttonsView.findViewById(R.id.btn_write_comment);
        commentButton.setData(getString(R.string.write_comment), getResources().getDrawable(R.drawable.comment_detail_button));
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //댓글달기 write페이지로 넘어가자
                Intent intent = new Intent(TimelineDetailActivity.this, WriteActivity.class);
                intent.putExtra("type", WriteActivity.TYPE_COMMENT_TIMELINE);
                intent.putExtra("article_id", articleId);
                startActivityForResult(intent, WriteActivity.TYPE_COMMENT_TIMELINE);
            }
        });

        likeButton = (DetailButton)buttonsView.findViewById(R.id.btn_rating);
        likeButton.setData(getString(R.string.like), getResources().getDrawable(R.drawable.like_detail_off));
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이미지 변경하고 숫자 하나올리고 네트워크에 전송하기
                if (isLiked) {
                    NetworkManager.getInstance().deleteArticleLike(TimelineDetailActivity.this, articleId, new NetworkManager.OnResultListener<String>() {
                        @Override
                        public void onSuccess(String result) {
                            likeCount -= 1;
                            likeButton.setData(likeCount + "", getResources().getDrawable(R.drawable.like_detail_off));
                            isLiked = false;
                            likeButton.setTextViewColor(isLiked);
                        }

                        @Override
                        public void onFail(int code) {

                        }
                    });
                } else {
                    NetworkManager.getInstance().addArticleLike(TimelineDetailActivity.this, articleId, new NetworkManager.OnResultListener<String>() {
                        @Override
                        public void onSuccess(String result) {
                            likeCount += 1;
                            likeButton.setData(likeCount + "", getResources().getDrawable(R.drawable.like_detail_on));
                            isLiked = true;
                            likeButton.setTextViewColor(isLiked);


                        }

                        @Override
                        public void onFail(int code) {

                        }
                    });
                }

            }
        });



        locationButton = (DetailButton)buttonsView.findViewById(R.id.btn_show_location);
        locationButton.setData(getString(R.string.share), getResources().getDrawable(R.drawable.share_detail_button));
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //페이스북 공유버튼 공유하기를 누르면 페이스북에 이 글내용을 공유할 수 있다.
                AccessToken token = AccessToken.getCurrentAccessToken();
                if (token == null || !token.getPermissions().contains("publish_actions")) {
                    login(Arrays.asList("publish_actions"), false);
                } else {
                    postMessage();
                }
            }
        });
        commentList.addHeaderView(buttonsView, null, false);
    }

    private void login(List<String> permissions, boolean isRead) {
        mLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                postMessage();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        mLoginManager.logInWithPublishPermissions(TimelineDetailActivity.this, permissions);
    }

    private void postMessage() {
        TextView userIdView = (TextView) textView.findViewById(R.id.text_user_name);
        TextView contentView = (TextView) textView.findViewById(R.id.text_content);

        String message = contentView.getText().toString();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        String graphPath = "/me/feed";
        Bundle parameters = new Bundle();
        parameters.putString("message",message);
        parameters.putString("link", "http://developers.facebook.com/docs/android");
        parameters.putString("picture", "https://s3-ap-northeast-1.amazonaws.com/gosurfs3/file/article/141822280_1448949450190.png");
        parameters.putString("name", userIdView.getText().toString());
        parameters.putString("description", "The 'Go Surf' sample description …");
        GraphRequest request = new GraphRequest(accessToken, graphPath, parameters, HttpMethod.POST,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        JSONObject data = response.getJSONObject();
                        String id = (data == null)?null:data.optString("id");
                        if (id == null) {
                            Toast.makeText(TimelineDetailActivity.this, "error : " + response.getError().getErrorMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TimelineDetailActivity.this, "post object id : " + id, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        request.executeAsync();
    }

    private void setMoreMenu(){
        ImageView imageView = (ImageView)textView.findViewById(R.id.image_more_detail);

        popup = new CustomPopupWindow(TimelineDetailActivity.this);
        ImageView deleteView = popup.getDeleteView();
        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkManager.getInstance().deleteArticle(TimelineDetailActivity.this, articleId, new NetworkManager.OnResultListener<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Intent intent = new Intent();
                        intent.putExtra(getString(R.string.article_id), articleId);
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                    @Override
                    public void onFail(int code) {

                    }
                });
            }
        });

        ImageView modifyView = popup.getModifyView();
        modifyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TimelineDetailActivity.this, WriteActivity.class);
                intent.putExtra(getString(R.string.type), WriteActivity.TYPE_MODIFY_TIMELINE);
                intent.putExtra(getString(R.string.article_id), articleId);

                TextView contentView = (TextView) textView.findViewById(R.id.text_content);
                intent.putExtra(getString(R.string.content), contentView.getText().toString());

                startActivityForResult(intent, WriteActivity.TYPE_MODIFY_TIMELINE);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup.setOutsideTouchable(true);
                popup.showAsDropDown(v);
            }
        });
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
                //데이터하난데 배열에들어있어서 for문에 넣는다.
                for (TimelineDetailItem t : result.getItems()) {
                    //내 id와 일치하는 값이 있는지 비교
                    likeCount = t.getLike_count();
                    String myId = PropertyManager.getInstance().get_Id();
                    articleWriterId = t.getUser_id();
                    ImageView imageView = (ImageView)textView.findViewById(R.id.image_more_detail);
                    if(PropertyManager.getInstance().get_Id().equals(articleWriterId)){
                        imageView.setVisibility(View.VISIBLE);
                    }else{
                        imageView.setVisibility(View.GONE);
                    }
                    likeButton.setData(likeCount + "", getResources().getDrawable(R.drawable.like_detail_off));
                    for(LikeParticipants p : t.getLike_participants()){
                        if(myId.equals(p.getUser_id())){
                            isLiked = true;
                            likeButton.setData(likeCount + "", getResources().getDrawable(R.drawable.like_detail_on));
                            likeButton.setTextViewColor(isLiked);
                            break;
                        }
                    }

                    switch (t.getAttachment().getType()) {
                        case TimelineFragment.TYPE_TEXT: {
                            break;
                        }
                        case TimelineFragment.TYPE_PICTURE: {
                            ImageView pictureView = (ImageView) contentView.findViewById(R.id.image_detail);
                            ImageLoader.getInstance().displayImage(t.getAttachment().getFile_url(), pictureView, options);
                            break;
                        }
                        case TimelineFragment.TYPE_VIDEO: {
                            VideoView videoView = (VideoView) contentView.findViewById(R.id.video_detail);
                            Uri uri = Uri.parse(t.getAttachment().getFile_url());

                            videoView.setVideoURI(uri);
                            videoView.start();
                            break;
                        }
                    }

                    TextView userIdView = (TextView) textView.findViewById(R.id.text_user_name);
                    String userName = t.getUser_name();
                    userIdView.setText(userName);

                    TextView createdTimeView = (TextView) textView.findViewById(R.id.text_created_time);
                    createdTime = TimeManager.getInstance().getArticleTime(t.getCreated_date());
                    createdTimeView.setText(createdTime);

                    TextView contentView = (TextView) textView.findViewById(R.id.text_content);
                    contentView.setText(t.getContent());

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
                    ImageView userProfileView = (ImageView)textView.findViewById(R.id.image_profile_mypage);
                    if(t.getUser_profile() == null || t.getUser_profile().equals("")){
                        userProfileView.setImageDrawable(getResources().getDrawable(R.drawable.user_profile_default));
                    }else{
                        ImageLoader.getInstance().displayImage(t.getUser_profile(), userProfileView, options);
                    }

                    for (CommentItem c : t.getComments()) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_CANCELED)
            return;

        switch (requestCode){
            case WriteActivity.TYPE_MODIFY_TIMELINE:{
                String modifiedContent = data.getStringExtra(getString(R.string.content));
                TextView contentView = (TextView) textView.findViewById(R.id.text_content);
                contentView.setText(modifiedContent);
                break;
            }
            case WriteActivity.TYPE_COMMENT_TIMELINE:{
                String content = data.getStringExtra("content");
                CommentItem commentItem = new CommentItem();
                commentItem.set_id(articleId);
                commentItem.setUser_id(PropertyManager.getInstance().get_Id());
                commentItem.setContent(content);
                commentItem.setCreated_date(((Long) System.currentTimeMillis()).toString());//시간도 받아오거나 시간처리하는 뷰를 만들어준 뒤 변경
                commentItem.setUser_name(PropertyManager.getInstance().getName());//이건 내 id니까 로그인후에 추가
                commentListAdapter.add(commentItem);
                break;
            }
            case WriteActivity.TYPE_MODIFY_COMMENT_TIMELINE:{
                break;
            }

        }
    }
}
