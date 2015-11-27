package com.tacademy.qodbtn41.gosurf;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.tacademy.qodbtn41.gosurf.data.response.LoginResponse;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;
import com.tacademy.qodbtn41.gosurf.manager.PropertyManager;
import com.tacademy.qodbtn41.gosurf.service.RegistrationIntentService;

import java.util.ArrayList;
import java.util.List;

/*
* splash페이지에서는 LoginManager로 로그인을 시도하고 그에따른 callback 처리 받아온걸로 */
public class SplashActivity extends AppCompatActivity {

    Handler mHandler = new Handler(Looper.getMainLooper());
    CallbackManager callbackManager = CallbackManager.Factory.create();
    LoginManager mLoginManager = LoginManager.getInstance();
    AccessTokenTracker mTokenTracker;
    List<String> permissions = new ArrayList<String>();
    private BroadcastReceiver mRegistrationBroadCastReceiver;

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        permissions.add("email");
        mRegistrationBroadCastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                doRealStart();
            }
        };
        setUpIfNeeded();


    }

    private void setUpIfNeeded() {
        if(checkPlayServices()) {
            String regId = PropertyManager.getInstance().getRegistrationToken();
            if(!regId.equals("")){
                doRealStart();
            }else{
                Intent intent = new Intent(this, RegistrationIntentService.class);
                startService(intent);
            }
        }
    }

    private void doRealStart() {
        // activity start...
        /*
        new AsyncTask<Void,Void,Boolean>(){
            @Override
            protected Boolean doInBackground(Void... params) {
                String regid = PropertyManager.getInstance().getRegistrationToken();
                ServerUtilities.register(SplashActivity.this, regid);
                return null;
            }
        }.execute();
        */

        final String id = PropertyManager.getInstance().getFacebookId();
        if(!TextUtils.isEmpty(id)) {
            mTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                    if(currentAccessToken != null) {//로그인상태
                        if(currentAccessToken.getUserId().equals(id)) {
                            NetworkManager.getInstance().loginFacebookToken(SplashActivity.this, currentAccessToken.getToken(), new NetworkManager.OnResultListener<LoginResponse>() {
                                @Override
                                public void onSuccess(LoginResponse result) {
                                    PropertyManager.getInstance().setUserInfo(result.getMessage());
                                    goMain();
                                }

                                @Override
                                public void onFail(int code) {
                                    goLogin();
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

                }

                @Override
                public void onError(FacebookException error) {
                    //페이스북 로그인 실패
                    goLogin();
                }
            });

            mLoginManager.logInWithReadPermissions(this, permissions);
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
        if (requestCode == PLAY_SERVICES_RESOLUTION_REQUEST &&
                resultCode == Activity.RESULT_OK) {
            setUpIfNeeded();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTokenTracker != null) {
            mTokenTracker.stopTracking();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadCastReceiver,
                new IntentFilter(RegistrationIntentService.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadCastReceiver);
        super.onPause();
    }

    private void goMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void goLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                Dialog dialog = apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                });
                dialog.show();
            } else {
                finish();
            }
            return false;
        }
        return true;
    }
}
