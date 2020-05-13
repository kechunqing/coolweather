package com.kcq.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kcq on 2020/5/13
 */
public class AQI {

    public AQICity city;

    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
