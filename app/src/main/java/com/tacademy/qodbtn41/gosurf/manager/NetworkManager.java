package com.tacademy.qodbtn41.gosurf.manager;

import android.content.Context;

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
import com.tacademy.qodbtn41.gosurf.data.response.LoginResponse;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;

import java.io.File;
import java.io.FileNotFoundException;
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

    /*
    * 샵
    * */
    private static final String SHOP_LIST_URL = "http://52.68.67.248:3000/shops";
    public void getShopList(Context context, String locationCategory, int offset, int limit, final OnResultListener<ShopListData> listener) {
        String url = SHOP_LIST_URL + "?location_category=" + locationCategory + "&offset=" + offset + "&limit=" + limit;
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

    private static final String SHOP_DETAIL_URL = "http://52.68.67.248:3000/shops";
    public void getShopDetail(Context context, String id, final OnResultListener<ShopData> listener) {
        String url = SHOP_DETAIL_URL + "/" + id;
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

    private static final String SHOP_COMMENT_WRITE = "http://52.68.67.248:3000/shops";
    public void postShopComment(Context context, String id, String content, final OnResultListener<String> listener) {
        RequestParams params = new RequestParams();
        params.put("content", content);

        String url = SHOP_COMMENT_WRITE + "/" + id + "/comments";
        client.post(context, url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                listener.onSuccess(responseString);
            }
        });
    }

    private static final String MODIFY_SHOP_COMMENT = "http://52.68.67.248:3000/shops";
    public void modifyShopComment(Context context, String shopId, String commentId, String content, final OnResultListener<String> listener){
        String url = MODIFY_SHOP_COMMENT + "/" + shopId + "/comments/" + commentId;
        RequestParams params = new RequestParams();
        params.put("content", content);
        client.put(context, url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                //listener.onSuccess(null);
            }
        });
    }

    private static final String DELETE_SHOP_COMMENT = "http://52.68.67.248:3000";
    public void deleteShopComment(Context context, String shopId, String commentId, final OnResultListener<String> listener){
        String url = DELETE_SHOP_COMMENT + "/" + shopId + "/comments/" + commentId;
        client.delete(context, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                //listener.onSuccess(null);
            }
        });
    }

    private static final String POST_SHOP_GRADE = "http://52.68.67.248:3000/shops";
    public void postShopGrade(Context context, String shopId, int grade, final OnResultListener<String> listener){
        String url = DELETE_SHOP_GRADE + "/" + shopId + "/grade";
        RequestParams params = new RequestParams();
        params.put("grade", grade);
        client.post(context, url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                //listener.onSuccess(null);
            }
        });
    }

    private static final String DELETE_SHOP_GRADE = "http://52.68.67.248:3000/shops";
    public void deleteShopGrade(Context context, String shopId, final OnResultListener<String> listener){
        String url = DELETE_SHOP_GRADE + "/" + shopId + "/grade";
        client.delete(context, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                //listener.onSuccess(null);
            }
        });
    }

    /*
    * 스팟
    * */
    private static final String SPOT_URL = "http://52.68.67.248:3000/spots";
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

    private static final String POST_BOOKMARK = "http://52.68.67.248:3000/spots";
    public  void  postBookmark(Context context, String spotId, final OnResultListener<String> listener){
        String url = POST_BOOKMARK + "/" + spotId + "/bookmarks";
        client.post(context, url, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                listener.onSuccess(responseString);
            }
        });
    }

    private static final String DELETE_BOOKMARK = "http://52.68.67.248:3000/spots";
    public  void  deleteBookmark(Context context, String spotId, final OnResultListener<String> listener){
        String url = POST_BOOKMARK + "/" + spotId + "/bookmarks";
        client.delete(context, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                listener.onSuccess(responseString);
            }
        });
    }

    /*
    * 타임라인
    * */
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

    private static final String TIMELINE_DETAIL_URL = "http://52.68.67.248:3000/articles";
    public void getTimelineDetail(Context context, String articleId, final OnResultListener<TimelineData> listener) {
        String url = TIMELINE_DETAIL_URL + "/" + articleId;
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

    private static final String ARTICLE_COMMENT_WRITE = "http://52.68.67.248:3000/articles";
    public void postArticleComment(Context context, String id, String content, final OnResultListener<String> listener) {
        RequestParams params = new RequestParams();
        params.put("content", content);

        String url = ARTICLE_COMMENT_WRITE + "/" + id + "/comments";
        client.post(context, url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                listener.onSuccess(responseString);
            }
        });
    }

    private static final String MODIFY_ARTICLE_COMMENT = "http://52.68.67.248:3000/articles";
    public void modifyArticleComment(Context context, String articleId, String commentId, String content, final OnResultListener<String> listener){
        String url = MODIFY_ARTICLE_COMMENT + "/" + articleId + "/comments/" + commentId;
        RequestParams params = new RequestParams();
        params.put("content", content);
        client.put(context, url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                //listener.onSuccess(null);
            }
        });
    }

    private static final String DELETE_ARTICLE_COMMENT = "http://52.68.67.248:3000/articles";
    public void deleteArticleComment(Context context, String articleId, String commentId, final OnResultListener<String> listener){
        String url = DELETE_ARTICLE_COMMENT + "/" + articleId + "/comments/" + commentId;
        client.delete(context, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                //listener.onSuccess(null);
            }
        });
    }

    private static final String ARTICLE_WRITE = "http://52.68.67.248:3000/articles";
    public void postArticle(Context context, File file, String content, int type, final OnResultListener<String> listener) {
        String url = ARTICLE_WRITE;
        try {
            RequestParams params = new RequestParams();
            params.put("content", content);
            params.put("type", type);


            if(type != 0){
                params.put("file", file);
            }else
            {
                //params.put("file", "");
            }
            client.post(context, url, params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    listener.onFail(statusCode);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    listener.onSuccess(responseString);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static final String MODIFY_ARTICLE = "http://52.68.67.248:3000/articles";
    public void modifyArticle(Context context, String articleId, String content, final OnResultListener<String> listener){
        String url = MODIFY_ARTICLE + "/" + articleId;
        RequestParams params = new RequestParams();
        params.put("content", content);

        client.put(context, url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                listener.onSuccess(responseString);
            }
        });
    }

    private static final String DELETE_ARTICLE = "http://52.68.67.248:3000/articles";
    public void deleteArticle(Context context, String articleId, final OnResultListener<String> listener){
        String url = DELETE_ARTICLE_COMMENT + "/" + articleId;
        client.delete(context, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                listener.onSuccess(responseString);
            }
        });
    }

    private static final String ADD_ARTICLE_LIKE = "http://52.68.67.248:3000/articles";
    public void addArticleLike(Context context, String articleId, final OnResultListener<String> listener){
        String url = ADD_ARTICLE_LIKE + "/" + articleId + "/like";
        client.post(context, url, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                listener.onSuccess(responseString);
            }
        });
    }

    private static final String DELETE_ARTICLE_LIKE = "http://52.68.67.248:3000/articles";
    public void deleteArticleLike(Context context, String articleId, final OnResultListener<String> listener){
        String url = ADD_ARTICLE_LIKE + "/" + articleId + "/like";
        client.delete(context, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                String response = responseString;
                listener.onSuccess(response);
            }
        });
    }


    /*
    * 페이스북
    * */
    private static final String FACEBOOK_LOGIN_URL = "http://52.68.67.248:3000/users/fbauth";
    public void loginFacebookToken(Context context, String accessToken , final OnResultListener<LoginResponse> listener) {
        RequestParams params = new RequestParams();
        params.put("access_token", accessToken);
        client.post(context, FACEBOOK_LOGIN_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                LoginResponse response = gson.fromJson(responseString, LoginResponse.class);
                listener.onSuccess(response);
            }
        });
    }

    public void cancelAll(Context context) {
        client.cancelRequests(context, true);
    }
}
