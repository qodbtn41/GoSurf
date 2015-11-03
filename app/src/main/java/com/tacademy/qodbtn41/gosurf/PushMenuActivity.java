package com.tacademy.qodbtn41.gosurf;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tacademy.qodbtn41.gosurf.fragment.PushMenuFragment;

public class PushMenuActivity extends AppCompatActivity {
    Toolbar toolbar;

    public static final int TYPE_FRAGMENT_PUSH = 0;
    public static final int TYPE_FRAGMENT_LOCATION = 1;
    public static final int TYPE_FRAGMENT_POPUP = 2;
    public static final int TYPE_FRAGMENT_CONTENT = 3;
    public static final int TYPE_FRAGMENT_VIBRATION = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_menu);
        setToolbar();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PushMenuFragment()).commit();
        }
    }

    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_push_menu);
        toolbar.setTitle(R.string.push_menu);
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);
    }
}
