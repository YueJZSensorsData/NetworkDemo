package com.example.yuejz.networkdemo.network;

import android.util.Log;

import com.example.yuejz.networkdemo.retrofit.GetRequest_Interface;
import com.example.yuejz.networkdemo.retrofit.PostRequest_Interface;
import com.example.yuejz.networkdemo.retrofit.Translation;
import com.example.yuejz.networkdemo.retrofit.Translation1;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetwork implements INetwork {

    private IClient mClient;
    private String TAG = "SA.RetrofitNetwork";

    private RetrofitNetwork() {

    }

    public RetrofitNetwork(IClient client) {
        this.mClient = client;
    }


    @Override
    public void getData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        Call<Translation> call = request.getCall();

        call.enqueue(new Callback<Translation>() {

            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {

                Log.d(TAG + ".GET",response.body().show());
                mClient.success();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                Log.d(TAG + ".GET","连接失败");
                mClient.failure();
            }
        });
    }

    @Override
    public void postData() {

        //:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 创建 网络请求接口 的实例
        PostRequest_Interface request = retrofit.create(PostRequest_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<Translation1> call = request.getCall("I love you");

        call.enqueue(new Callback<Translation1>() {

            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                Log.d(TAG + ".POST", response.body().getTranslateResult().get(0).get(0).getTgt());
                mClient.success();
            }

            @Override
            public void onFailure(Call<Translation1> call, Throwable throwable) {
                Log.d(TAG + ".POST","请求失败");
                mClient.failure();
            }
        });

    }
}
