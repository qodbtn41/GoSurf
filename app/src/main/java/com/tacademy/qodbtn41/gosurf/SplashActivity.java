package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;
import com.tacademy.qodbtn41.gosurf.manager.PropertyManager;

/*
* splash페이지에서는 LoginManager로 로그인을 시도하고 그에따른 callback 처리 받아온걸로 */
public class SplashActivity extends AppCompatActivity {

    Handler mHandler = new Handler(Looper.getMainLooper());
    CallbackManager callbackManager = CallbackManager.Factory.create();
    LoginManager mLoginManager = LoginManager.getInstance();
    AccessTokenTracker mTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final String id = PropertyManager.getInstance().getFacebookId();
        if(!TextUtils.isEmpty(id)) {
            mTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                    if(currentAccessToken != null) {//로그인상태
                        if(currentAccessToken.getUserId().equals(id)) {
                            NetworkManager.getInstance().loginFacebookToken(SplashActivity.this, currentAccessToken.getToken(), new NetworkManager.OnResultListener<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    goMain();
                                }

                                @Override
                                public void onFail(int code) {

                                }
                            });
                        } else {
                            //저장된 id와 변경된 id가 다르면
                            mLoginManager.logOut();
                            goLogin();
                        }
                    }
                }
            };
            //
            mLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    //성공하면 토큰이 변경되므로 토큰트래커에서 tokenchanged함수가 호출된다. 거기서 처리할듯
                }

                @Override
                public void onCancel() {
                    goLogin();
                }

                @Override
                public void onError(FacebookException error) {
                    //페이스북 로그인 실패
                    goLogin();
                }
            });

            mLoginManager.logInWithReadPermissions(this, null);
        }else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goLogin();
                }
            }, 1000);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTokenTracker != null) {
            mTokenTracker.stopTracking();
        }
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
