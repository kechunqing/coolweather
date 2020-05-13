package com.kcq.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kcq on 2020/5/13
 */
public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More{
        @SerializedName("txt")
        public String info;
    }
}
