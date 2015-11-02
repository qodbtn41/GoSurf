package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
/*
* splash페이지에서는 다음화면인 메인화면의 데이터를 받아올때까지 기다리는 공간이다.*/
public class SplashActivity extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goLogin();
            }
        }, 1000);
    }

    private void goMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void goLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
