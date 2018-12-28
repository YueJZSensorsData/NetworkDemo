package com.example.yuejz.networkdemo.network;

import android.content.Context;
import android.icu.util.LocaleData;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yuejz.networkdemo.Configurator;
import com.example.yuejz.networkdemo.MyApp;
import com.example.yuejz.networkdemo.RequestURL;

import org.json.JSONException;
import org.json.JSONObject;



public class VolleyNetwork implements INetwork {

    private IClient mClient;

    private String TAG = "SA.VolleyNetwork";

    private VolleyNetwork() {

    }

    public VolleyNetwork(IClient client) {
        this.mClient = client;
    }

    @Override
    public void getData() {
        RequestQueue queue = Volley.newRequestQueue(MyApp.getInstance());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Configurator.getInstance().getUrl(RequestURL.GET_URL_BAIDU),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG + ".GET", response.substring(0, 10));
                        mClient.success();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mClient.failure();
            }
        });

        queue.add(stringRequest);
    }

    @Override
    public void postData() {
        RequestQueue queue = Volley.newRequestQueue(MyApp.getInstance());
        String postBody = "{\"username\":\"admin\",\"password\":\"1qaz2wsx\"}";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(postBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                Configurator.getInstance().getUrl(RequestURL.POST_URL_YJZ), jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final int user_id = response.getInt("user_id");
                    Log.d(TAG + ".POST", String.valueOf(user_id));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mClient.success();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mClient.failure();
            }
        });
        queue.add(jsonObjectRequest);
    }
}
