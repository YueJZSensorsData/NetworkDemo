package com.example.yuejz.networkdemo.network;

import android.util.Log;

import com.example.yuejz.networkdemo.Configurator;
import com.example.yuejz.networkdemo.MyApp;
import com.example.yuejz.networkdemo.RequestURL;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class IonNetwork implements INetwork {

    private IClient mClient;
    private String TAG = "SA.IonNetwork";

    private IonNetwork() {

    }

    public IonNetwork(IClient client) {
        this.mClient = client;
    }

    @Override
    public void getData() {
        Ion.with(MyApp.getInstance())
                .load(Configurator.getInstance().getUrl(RequestURL.GET_URL_BAIDU))
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, final String result) {
                        Log.d(TAG,result.substring(0,10));
                        mClient.success();
                    }
                });

    }

    @Override
    public void postData() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("username", "admin");
            jsonObject.addProperty("password", "1qaz2wsx");
            Ion.with(MyApp.getInstance())
                    .load(Configurator.getInstance().getUrl(RequestURL.POST_URL_YJZ))
                    .setJsonObjectBody(jsonObject)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            Log.d(TAG,result.substring(0,10));
                            mClient.success();
                        }
                    });
        } catch (JsonIOException e) {
            e.printStackTrace();
        }
    }
}
