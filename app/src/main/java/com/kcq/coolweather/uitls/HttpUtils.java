package com.kcq.coolweather.uitls;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtils {
    public static final String QUERY_CITY_URL = "http://guolin.tech/api/china";
    public static final String QUERY_WEATHER_URL = "http://guolin.tech/api/weather?cityid=";
    public static final String KEY_WEATHER = "&key=865f46921cac489a9fb07906fc6dbde7";
    public static void sendOkhttpRequest(String address, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
