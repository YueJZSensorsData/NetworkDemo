package com.example.yuejz.networkdemo;

import android.app.Application;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

import java.util.ArrayList;
import java.util.List;

public class MyApp extends Application {

    private static MyApp sMyApp;


    final String SA_SERVER_URL = "https://test-hechun.datasink.sensorsdata.cn/sa?project=yuejianzhong&token=d28b875ed9ac268f";
    final SensorsDataAPI.DebugMode SA_DEBUG_MODE = SensorsDataAPI.DebugMode.DEBUG_AND_TRACK;


    public static MyApp getInstance() {
        return sMyApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp = this;
        //设置get和post请求网址，Retrofit 框架网址不一样
        Configurator.getInstance()
                .addUrl(RequestURL.GET_URL_BAIDU, "https://www.baidu.com")
                .addUrl(RequestURL.POST_URL_YJZ, "https://test-hechun.cloud.sensorsdata.cn/api/auth/login?project=yuejianzhong");
        initSA();
    }

    // 初始化神策 SDK
    private void initSA() {

        SensorsDataAPI.sharedInstance(this, SA_SERVER_URL, SA_DEBUG_MODE);
        List<SensorsDataAPI.AutoTrackEventType> eventTypeList = new ArrayList<>();
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_START);
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_END);
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_CLICK);
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN);
        SensorsDataAPI.sharedInstance().enableAutoTrack(eventTypeList);

        SensorsDataAPI.sharedInstance().enableLog(false);
    }
}
