package com.kcq.coolweather.uitls;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.kcq.coolweather.db.City;
import com.kcq.coolweather.db.Country;
import com.kcq.coolweather.db.Province;
import com.kcq.coolweather.gson.Weather;
import com.kcq.coolweather.jbtab.gson.SmartTabProductGroup;
import com.kcq.coolweather.jbtab.gson.SmartTabTitleGroup;
import com.kcq.coolweather.kotlin.LearnKotlinKt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import kotlin.jvm.functions.Function2;

/**
 * The type Utility.
 */
public class Utility {
    private static final String TAG = "JDTabActivity";
    /**
     * 处理并存储返回的省级数据
     *
     * @param response the response
     * @return the boolean
     */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvince = new JSONArray(response);
                for (int i = 0; i < allProvince.length(); i++) {
                    JSONObject jsonObject = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProviceCode(jsonObject.getInt("id"));
                    province.setProvinceName(jsonObject.getString("name"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String response,int proviceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCity = new JSONArray(response);
                for (int i = 0; i < allCity.length(); i++) {
                    JSONObject jsonObject = allCity.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(jsonObject.getInt("id"));
                    city.setCityName(jsonObject.getString("name"));
                    city.setProvinceId(proviceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountryResponse(String response,int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCountry = new JSONArray(response);
                for (int i = 0; i < allCountry.length(); i++) {
                    JSONObject jsonObject = allCountry.getJSONObject(i);
                    Country country = new Country();
                    country.setCityId(cityId);
                    country.setCountryName(jsonObject.getString("name"));
                    country.setCountryCode(jsonObject.getInt("id"));
                    country.setWeatherId(jsonObject.getString("weather_id"));
                    country.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //处理智能家居页获取到的数据
    public static void handleSmartTabInfo(String response,JsonHandleResult callBack,String filePath) {
        SmartTabTitleGroup smartTabTitleGroup=null;
        SmartTabProductGroup smartTabProductGroup = null;
        try {
            Log.d(TAG, "handleSmartTabInfo: no.2");
            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.has("jd_smart_open_iot_tencent_smart_response")) {
                //未登录
                Log.d(TAG, "handleSmartTabInfo: 未登录");
                JSONObject jsonObject1 = jsonObject.getJSONObject("jd_smart_open_iot_tencent_smart_response");
                JSONObject jsonObject2 = jsonObject1.getJSONObject("result");
                String responseData = jsonObject2.getString("data");
                JSONObject jsonObject3 = new JSONObject(responseData);
                JSONArray jsonArray = jsonObject3.getJSONArray("data");
                Log.d(TAG, "handleSmartTabInfo:");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject4 = jsonArray.getJSONObject(i);
                    if (jsonObject4.getInt("type") == 22) {
                        smartTabTitleGroup = new Gson().fromJson(jsonObject4.toString(), SmartTabTitleGroup.class);
                    } else if (jsonObject4.getInt("type") == 23) {
                        smartTabProductGroup = new Gson().fromJson(jsonObject4.toString(), SmartTabProductGroup.class);
                    }
                }
                if (smartTabProductGroup != null && smartTabTitleGroup != null)
                    callBack.smartData(smartTabTitleGroup, smartTabProductGroup);
            }else if(jsonObject.has("jd_smart_open_iot_tencent_smartByPin_response")){
                //已登录
                FileUtils.writeToFile(filePath+"/loginSmart_json.txt",response);
                JSONObject jsonObject1 = jsonObject.getJSONObject("jd_smart_open_iot_tencent_smartByPin_response");
                JSONObject jsonObject2 = jsonObject1.getJSONObject("result");
                String responseData = jsonObject2.getString("data");
                jsonObject1 = new JSONObject(responseData);

                JSONArray jsonArray = jsonObject1.getJSONArray("data");
                Log.d(TAG, "handleSmartTabInfo:");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject4 = jsonArray.getJSONObject(i);
                    if (jsonObject4.getInt("type") == 22) {
                        smartTabTitleGroup = new Gson().fromJson(jsonObject4.toString(), SmartTabTitleGroup.class);
                    } else if (jsonObject4.getInt("type") == 23) {
                        smartTabProductGroup = new Gson().fromJson(jsonObject4.toString(), SmartTabProductGroup.class);
                    }
                }
                if (smartTabProductGroup != null && smartTabTitleGroup != null)
                    callBack.smartData(smartTabTitleGroup, smartTabProductGroup);

                Log.d(TAG, "handleSmartTabInfo:已登录");
            } else if (jsonObject.has("jd_smart_open_iot_launcher_mall_response")) {
                FileUtils.writeToFile(filePath + "/shopping_tab_json.txt", response);
                Log.d(TAG, "handleSmartTabInfo: 购物");
            }
//            return responseData;

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG , "jsonerror"+e.getMessage());
//            return null;
        }
    }

    public void testKotlin(){
        /*LearnKotlinKt.num1AndNum2(100, 100, new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer invoke(Integer integer, Integer integer2) {
                return null;
            }
        });*/
        LearnKotlinKt.num1AndNum2(100, 100, new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer invoke(Integer integer, Integer integer2) {
                return null;
            }
        });
    }
}
