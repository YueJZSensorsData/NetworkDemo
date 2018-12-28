package com.example.yuejz.networkdemo;

import java.util.HashMap;

public class Configurator {
    private static final HashMap<RequestURL, String> URL = new HashMap<>();

    private Configurator() {

    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }


    public Configurator addUrl(RequestURL requestURL, String url) {
        URL.put(requestURL, url);
        return this;
    }

    public String getUrl(RequestURL requestURL) {
        return URL.get(requestURL);
    }
}
