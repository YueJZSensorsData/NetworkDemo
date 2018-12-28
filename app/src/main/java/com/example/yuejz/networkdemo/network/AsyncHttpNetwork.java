package com.example.yuejz.networkdemo.network;

import android.util.Log;

import com.example.yuejz.networkdemo.Configurator;
import com.example.yuejz.networkdemo.MyApp;
import com.example.yuejz.networkdemo.RequestURL;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class AsyncHttpNetwork implements INetwork {

    private com.loopj.android.http.AsyncHttpClient mAsyncHttpClient;
    private IClient mClient;
    private String TAG = "SA.AsyncHttpNetwork";

    private AsyncHttpNetwork() {

    }

    public AsyncHttpNetwork(IClient client) {
        this.mClient = client;
        mAsyncHttpClient = new AsyncHttpClient();
    }

    @Override
    public void getData() {
        mAsyncHttpClient.get(Configurator.getInstance().getUrl(RequestURL.GET_URL_BAIDU), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    Log.d(TAG + ".GET", new String(responseBody).substring(0,10));
                   mClient.success();
                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                mClient.failure();
            }
        });
    }

    @Override
    public void postData() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", "admin");
            jsonObject.put("password", "1qaz2wsx");
            StringEntity stringEntity = new StringEntity(jsonObject.toString());
            mAsyncHttpClient.post(MyApp.getInstance(), Configurator.getInstance().getUrl(RequestURL.POST_URL_YJZ), stringEntity, "application/json", new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    if (statusCode == 200) {
                        try {
                            int user_id = response.getInt("user_id");

                            Log.d(TAG + ".POST", String.valueOf(user_id));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        mClient.success();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    mClient.failure();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
