package com.tacademy.qodbtn41.gosurf.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.DefaultAudience;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.tacademy.qodbtn41.gosurf.LoginActivity;
import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.item.FacebookLoginButton;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;
import com.tacademy.qodbtn41.gosurf.manager.PropertyManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends android.support.v4.app.Fragment {
    AccessTokenTracker mTokenTracker;
    private View view;

    public LoginFragment() {
        // Required empty public constructor
    }

    CallbackManager callbackManager = CallbackManager.Factory.create();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //로그인 성공해서 액세스토큰을 얻었으니까. 이를 다시 서버로 보내야한다.
                final AccessToken token = AccessToken.getCurrentAccessToken();
                NetworkManager.getInstance().signupFacebook(getContext(), token.getToken(), new NetworkManager.OnResultListener<String>() {//회원가입하고 성공실패?
                    @Override
                    public void onSuccess(String result) {

                        PropertyManager.getInstance().setFacebookId(token.getUserId());
                        ((LoginActivity) getActivity()).pushAfterLoginFragment();
                    }

                    @Override
                    public void onFail(int code) {

                    }
                });
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        FacebookLoginButton btn = (FacebookLoginButton)view.findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (accessToken == null) {
                    LoginManager.getInstance().setDefaultAudience(DefaultAudience.FRIENDS);
                    LoginManager.getInstance().setLoginBehavior(LoginBehavior.NATIVE_WITH_FALLBACK);
                    LoginManager.getInstance().logInWithReadPermissions(LoginFragment.this, null);
                } else {
                    LoginManager.getInstance().logOut();
                }
            }
        });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken != null){
            btn.setText(getString(R.string.facebook_logout));
        }else {
            btn.setText(getString(R.string.facebook_login));
        }

        mTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                FacebookLoginButton btn = (FacebookLoginButton)view.findViewById(R.id.btn_login);
                if(currentAccessToken != null) {
                    btn.setText(getString(R.string.facebook_logout));
                }else {
                    btn.setText(getString(R.string.facebook_login));
                }
            }
        };

        if(getActivity().getActionBar() != null){
            getActivity().getActionBar().hide();
        }else{
            getActivity().findViewById(R.id.toolbar_login).setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mTokenTracker != null) {
            mTokenTracker.stopTracking();
        }
    }
}
