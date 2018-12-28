package com.example.yuejz.networkdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yuejz.networkdemo.network.AsyncHttpNetwork;
import com.example.yuejz.networkdemo.network.IClient;
import com.example.yuejz.networkdemo.network.INetwork;
import com.example.yuejz.networkdemo.network.IonNetwork;
import com.example.yuejz.networkdemo.network.OkHttpNetwork;
import com.example.yuejz.networkdemo.network.RetrofitNetwork;
import com.example.yuejz.networkdemo.network.VolleyNetwork;

public class NetworkActivity extends AppCompatActivity implements IClient,View.OnClickListener {

    private Button mGetButton;
    private Button mPostButton;
    private INetwork mNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        mGetButton = findViewById(R.id.bt_get);
        mPostButton = findViewById(R.id.bt_post);
        mGetButton.setOnClickListener(this);
        mPostButton.setOnClickListener(this);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        mNetwork = getType(type);
    }

    private INetwork getType(String type) {
        switch (type) {
            case "volley":
                mNetwork =  new VolleyNetwork(this);
                setTitle("volley");
                break;
            case "retrofit":
                mNetwork = new RetrofitNetwork(this);
                setTitle("retrofit");
                break;
            case "asynchttp":
                mNetwork = new AsyncHttpNetwork(this);
                setTitle("asynchttp");
                break;
            case "okhttp":
                mNetwork = new OkHttpNetwork(this);
                setTitle("okhttp");
                break;
            case "ion":
                mNetwork = new IonNetwork(this);
                setTitle("ion");
            default:
                break;
        }

        return mNetwork;

    }

    @Override
    public void success() {
        NetworkActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(NetworkActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void failure() {
        NetworkActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(NetworkActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_get:
                mNetwork.getData();
                break;
            case R.id.bt_post:
                mNetwork.postData();
            default:
                break;
        }

    }
}
