package com.example.yuejz.networkdemo.retrofit;

import android.util.Log;

public class Translation {
    private int status;

    private content content;
    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }
    //定义 输出返回数据 的方法
    public String show() {
       return String.valueOf(status);
    }
}