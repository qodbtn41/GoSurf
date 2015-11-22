package com.tacademy.qodbtn41.gosurf.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.tacademy.qodbtn41.gosurf.data.ShopData;
import com.tacademy.qodbtn41.gosurf.data.ShopListData;
import com.tacademy.qodbtn41.gosurf.data.SpotData;
import com.tacademy.qodbtn41.gosurf.data.TimelineData;
import com.tacademy.qodbtn41.gosurf.data.TimelineListData;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Created by dongja94 on 2015-10-20.
 */
public class NetworkManager {
    private static NetworkManager instance;
    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    AsyncHttpClient client;
    Gson gson;
    private NetworkManager() {

        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            MySSLSocketFactory socketFactory = new MySSLSocketFactory(trustStore);
            socketFactory.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            client = new AsyncHttpClient();
            client.setSSLSocketFactory(socketFactory);
            client.setCookieStore(new PersistentCookieStore(MyApplication.getContext()));
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }


        gson = new Gson();
        client.setCookieStore(new PersistentCookieStore(MyApplication.getContext()));
    }

    public HttpClient getHttpClient() {
        return client.getHttpClient();
    }



    public interface OnResultListener<T> {
        public void onSuccess(T result);
        public void onFail(int code);
    }

    private static final String SHOP_LIST_URL = "http://52.68.67.248:3000/shops/";

    public void getShopList(Context context, String locationCategory, int offset, int limit, final OnResultListener<ShopListData> listener) {
        String url = SHOP_LIST_URL + locationCategory + "?offset=" + offset + "&limit=" + limit;
        client.get(context, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                ShopListData data = gson.fromJson(responseString, ShopListData.class);
                listener.onSuccess(data);
            }
        });
    }

    private static final String SHOP_DETAIL_URL = "http://52.68.67.248:3000/shops/";

    public void getShopDetail(Context context, String id, final OnResultListener<ShopData> listener) {
        String url = SHOP_DETAIL_URL + id;
        client.get(context, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                ShopData data = gson.fromJson(responseString, ShopData.class);
                listener.onSuccess(data);
            }
        });
    }

    private static final String SPOT_URL = "http://52.68.67.248:3000/spots/";

    public void getSpot(Context context, final OnResultListener<SpotData> listener) {
        client.get(context, SPOT_URL, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                SpotData data = gson.fromJson(responseString, SpotData.class);
                listener.onSuccess(data);
            }
        });
    }

    private static final String TIMELINE_LIST_URL = "http://52.68.67.248:3000/articles";

    public void getTimelineList(Context context, int offset, int limit, final OnResultListener<TimelineListData> listener) {
        String url = TIMELINE_LIST_URL + "?offset=" + offset + "&limit=" + limit;
        client.get(context, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                TimelineListData data = gson.fromJson(responseString, TimelineListData.class);
                listener.onSuccess(data);
            }
        });
    }

    private static final String TIMELINE_DETAIL_URL = "http://52.68.67.248:3000/articles/";

    public void getTimelineDetail(Context context, String articleId, final OnResultListener<TimelineData> listener) {
        String url = TIMELINE_DETAIL_URL + articleId;
        client.get(context, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                TimelineData data = gson.fromJson(responseString, TimelineData.class);
                listener.onSuccess(data);
            }
        });

    }

    //post는 작업안됨
    private static final String SHOP_COMMENT_WRITE = "http://52.68.67.248:3000/shops/";

    public void addShopComment(Context context, String id, String content, final OnResultListener<Boolean> listener) {
        RequestParams params = new RequestParams();
        params.put("content", content);
        client.post(context, SHOP_COMMENT_WRITE + "/" + id + "/" + "comments", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

            }
        });
    }

    private static final String FACEBOOK_LOGIN_URL = "";

    Handler mHandler = new Handler(Looper.getMainLooper());
    public void loginFacebookToken(Context context, String accessToken , final OnResultListener<String> listener) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess("OK");
            }
        }, 1000);
    }

    public void signupFacebook(Context context, String message, final OnResultListener<String> listener) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess("OK");
            }
        }, 1000);
    }

    public void cancelAll(Context context) {
        client.cancelRequests(context, true);
    }
}
