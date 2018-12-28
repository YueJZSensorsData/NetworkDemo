package com.example.yuejz.networkdemo.network;

import android.util.Log;

import com.example.yuejz.networkdemo.Configurator;
import com.example.yuejz.networkdemo.RequestURL;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpNetwork implements INetwork {

    private IClient mClient;
    private OkHttpClient mOkHttpClient;
    private String TAG = "SA.OkHttpNetwork";

    private OkHttpNetwork() {

    }

    public OkHttpNetwork(IClient client) {
        this.mClient = client;
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public void getData() {

        Request request = new Request.Builder().url(Configurator.getInstance().getUrl(RequestURL.GET_URL_BAIDU)).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mClient.failure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG + ".GET", String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    mClient.success();
                }
            }
        });


    }

    @Override
    public void postData() {
        String postBody = "{\"username\":\"admin\",\"password\":\"1qaz2wsx\"}";

        Request request = new Request.Builder()
                .url(Configurator.getInstance().getUrl(RequestURL.POST_URL_YJZ))
                .addHeader("content-type", "application/json;charset:utf-8")
                .post(RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        postBody))// post json提交
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                mClient.failure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG + ".POST", String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    mClient.success();
                }
            }
        });
    }
}
