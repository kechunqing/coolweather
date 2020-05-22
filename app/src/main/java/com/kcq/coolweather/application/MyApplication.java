package com.kcq.coolweather.application;

import android.app.Application;
import android.util.Log;

import com.jd.tv.smarthelper.JDSmartSDK;
import com.jd.tv.smarthelper.interf.IConnect;

/**
 * Created by kcq on 2020/5/18
 */
public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        JDSmartSDK.getInstance().init(this);
    }
}
