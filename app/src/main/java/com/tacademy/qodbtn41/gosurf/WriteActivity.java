package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WriteActivity extends AppCompatActivity {
    Toolbar toolbar;

    public static final int TYPE_COMMENT = 0;
    public static final int TYPE_TIMELINE = 1;
    public static final int TYPE_MODIFY_COMMENT = 2;
    public static final int TYPE_MODIFY_TIMELINE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        this.init();
        this.setToolbar();
    }

    private void init() {
        Intent intent = getIntent();
        int type = intent.getIntExtra("type", -1);
        switch(type){
            case TYPE_COMMENT :{
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
            case TYPE_MODIFY_COMMENT :{
                TextView title = (TextView)findViewById(R.id.write_title_text);
                title.setText(getString(R.string.modify_comment));
                break;
            }
            case TYPE_MODIFY_TIMELINE:{
                TextView title = (TextView)findViewById(R.id.write_title_text);
                title.setText(getString(R.string.modify_title));
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
            case R.id.menu_write: {//작성완료되면 글쓴걸 보내야한다.
                finish();
                break;
            }
            case android.R.id.home:{
                //글작성중 취소화고 이전화면으로 돌아간다.
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
