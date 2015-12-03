package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;

import java.io.File;

public class WriteActivity extends AppCompatActivity {
    ProgressBar progressView;
    Toolbar toolbar;
    EditText editText;

    int type;
    int articleType = 0;
    String shopId, articleId, userName;
    File file;
    boolean isClicked = false;

    public static final int TYPE_COMMENT_SHOP = 300;
    public static final int TYPE_COMMENT_TIMELINE = 301;
    public static final int TYPE_TIMELINE = 302;
    public static final int TYPE_MODIFY_COMMENT_SHOP = 303;
    public static final int TYPE_MODIFY_COMMENT_TIMELINE = 304;
    public static final int TYPE_MODIFY_TIMELINE = 305;

    public static final int GALLERY = 100;
    public static final int VIDEO = 101;

    public static final int TYPE_NONE = 0;
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_VIDEO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        this.init();
        this.setToolbar();
    }

    private void init() {
        Intent intent = getIntent();
        progressView = (ProgressBar)findViewById(R.id.progressBar);
        progressView.setVisibility(View.GONE);
        type = intent.getIntExtra(getString(R.string.type), -1);
        shopId = intent.getStringExtra(getString(R.string.shop_id));
        articleId = intent.getStringExtra(getString(R.string.article_id));
        editText = (EditText)this.findViewById(R.id.edit_write_space);

        typeSetting();

        ImageView cameraImage = (ImageView)findViewById(R.id.image_btn_picture);
        cameraImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY);
            }
        });

        ImageView videoImage = (ImageView)findViewById(R.id.image_btn_video);
        videoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Video.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, VIDEO);
            }
        });

    }

    //이름이 이상한듯
    private void typeSetting(){
        switch(type){
            case TYPE_COMMENT_SHOP :{
                TextView title = (TextView)findViewById(R.id.write_title_text);
                title.setText(getString(R.string.write_comment_title));
                ImageView imageView = (ImageView)findViewById(R.id.image_btn_picture);
                imageView.setVisibility(View.GONE);
                imageView = (ImageView)findViewById(R.id.image_btn_video);
                imageView.setVisibility(View.GONE);
                break;
            }
            case TYPE_COMMENT_TIMELINE:{
                TextView title = (TextView)findViewById(R.id.write_title_text);
                title.setText(getString(R.string.write_comment_title));
                ImageView imageView = (ImageView)findViewById(R.id.image_btn_picture);
                imageView.setVisibility(View.GONE);
                imageView = (ImageView)findViewById(R.id.image_btn_video);
                imageView.setVisibility(View.GONE);
                break;
            }
            case TYPE_TIMELINE : {
                TextView title = (TextView)findViewById(R.id.write_title_text);
                title.setText(getString(R.string.write_title));
                break;
            }
            case TYPE_MODIFY_COMMENT_SHOP:{
                TextView title = (TextView)findViewById(R.id.write_title_text);
                title.setText(getString(R.string.modify_comment));
                ImageView imageView = (ImageView)findViewById(R.id.image_btn_picture);
                imageView.setVisibility(View.GONE);
                imageView = (ImageView)findViewById(R.id.image_btn_video);
                imageView.setVisibility(View.GONE);

                editText.setText(getIntent().getStringExtra(getString(R.string.content)));
                break;
            }
            case TYPE_MODIFY_COMMENT_TIMELINE:{
                TextView title = (TextView)findViewById(R.id.write_title_text);
                title.setText(getString(R.string.modify_comment));
                ImageView imageView = (ImageView)findViewById(R.id.image_btn_picture);
                imageView.setVisibility(View.GONE);
                imageView = (ImageView)findViewById(R.id.image_btn_video);
                imageView.setVisibility(View.GONE);

                editText.setText(getIntent().getStringExtra(getString(R.string.content)));
                break;
            }
            case TYPE_MODIFY_TIMELINE:{
                TextView title = (TextView)findViewById(R.id.write_title_text);
                title.setText(getString(R.string.modify_title));

                editText.setText(getIntent().getStringExtra(getString(R.string.content)));
                break;
            }
        }
    }

    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_write);
        toolbar.setTitle(R.string.write_title);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_button));
        toolbar.getNavigationIcon();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_write));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_write, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_write: {
                if(isClicked){
                    return false;
                }
                progressView.setVisibility(View.VISIBLE);
                final String content = editText.getText().toString();
                isClicked = true;
                switch (type){
                    case TYPE_COMMENT_SHOP:{
                        NetworkManager.getInstance().postShopComment(this, shopId, content, new NetworkManager.OnResultListener<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Intent intent = new Intent();
                                intent.putExtra(getString(R.string.content), content);
                                setResult(RESULT_OK, intent);
                                finish();
                            }

                            @Override
                            public void onFail(int code) {
                                isClicked = false;
                            }
                        });

                        break;
                    }
                    case TYPE_COMMENT_TIMELINE:{
                        NetworkManager.getInstance().postArticleComment(this, articleId, content, new NetworkManager.OnResultListener<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Intent intent = new Intent();
                                intent.putExtra(getString(R.string.content), content);
                                setResult(RESULT_OK, intent);
                                finish();
                            }

                            @Override
                            public void onFail(int code) {
                                isClicked = false;

                            }
                        });

                        break;
                    }
                    case TYPE_MODIFY_COMMENT_SHOP:{

                        break;
                    }
                    case TYPE_MODIFY_COMMENT_TIMELINE:{
                        break;
                    }
                    case TYPE_TIMELINE:{
                        NetworkManager.getInstance().postArticle(this, file, content, articleType, new NetworkManager.OnResultListener<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Intent intent = new Intent();
                                intent.putExtra("content", content);
                                setResult(RESULT_OK, intent);
                                finish();
                            }

                            @Override
                            public void onFail(int code) {
                                isClicked = false;

                            }
                        });

                        break;
                    }
                    case TYPE_MODIFY_TIMELINE:{
                        NetworkManager.getInstance().modifyArticle(this, articleId, content, new NetworkManager.OnResultListener<String>(){

                            @Override
                            public void onSuccess(String result) {
                                Intent intent = new Intent();
                                intent.putExtra(getString(R.string.content), content);
                                setResult(RESULT_OK, intent);
                                finish();
                            }

                            @Override
                            public void onFail(int code) {
                                isClicked = false;

                            }
                        });
                        break;
                    }
                }
                break;
            }
            case android.R.id.home:{
                //글작성중 취소화고 이전화면으로 돌아간다.
                setResult(RESULT_CANCELED);
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if ( resultCode == RESULT_CANCELED)
            return;

        switch(requestCode){
            case GALLERY: {
                Uri uri = intent.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(
                        uri, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);

                String path = uri.getPath();//content uri 2
                file = new File(filePath);
                articleType = TYPE_IMAGE;
                break;
            }
            case VIDEO: {
                Uri uri = intent.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(
                        uri, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);

                file = new File(filePath);
                articleType = TYPE_VIDEO;
                break;
            }
        }
    }

}
