package com.example.yuejz.networkdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mOkHttpButton;
    private Button mAsyncButton;
    private Button mIonButton;
    private Button mVolleyButton;
    private Button mRetrofitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOkHttpButton = findViewById(R.id.bt_okhttp);
        mOkHttpButton.setOnClickListener(this);

        mAsyncButton = findViewById(R.id.bt_async);
        mAsyncButton.setOnClickListener(this);

        mIonButton = findViewById(R.id.bt_ion);
        mIonButton.setOnClickListener(this);

        mVolleyButton = findViewById(R.id.bt_volley);
        mVolleyButton.setOnClickListener(this);

        mRetrofitButton = findViewById(R.id.bt_retrofit);
        mRetrofitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, NetworkActivity.class);
        switch (v.getId()) {
            case R.id.bt_okhttp:
                intent.putExtra("type", "okhttp");
                startActivity(intent);
                break;
            case R.id.bt_async:
                intent.putExtra("type", "asynchttp");
                startActivity(intent);
                break;
            case R.id.bt_ion:
                intent.putExtra("type", "ion");
                startActivity(intent);
                break;
            case R.id.bt_volley:
                intent.putExtra("type", "volley");
                startActivity(intent);
                break;
            case R.id.bt_retrofit:
                intent.putExtra("type", "retrofit");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
