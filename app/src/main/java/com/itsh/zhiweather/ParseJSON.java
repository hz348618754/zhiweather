package com.itsh.zhiweather;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 沈辉 on 2018/5/3.
 */

public class ParseJSON {

    public static Map<String,String> parseJsonNow(String jsonData){
        Map<String,String> mapNow = new HashMap<>();
        try{
            JSONObject object = new JSONObject(jsonData);
            JSONArray obj = object.getJSONArray("HeWeather6");
            JSONObject jsonObject = obj.getJSONObject(0).getJSONObject("now");
            Iterator it = jsonObject.keys();
            while (it.hasNext()){
                String key = it.next().toString();
                String value = jsonObject.getString(key);
                mapNow.put(key,value);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return mapNow;
    }

    public static Map<String,String> parseJsonBasic(String jsonData){
        Map<String,String> mapBasic = new HashMap<>();
        try{
            JSONObject object = new JSONObject(jsonData);
            JSONArray obj = object.getJSONArray("HeWeather6");
            JSONObject jsonObject = obj.getJSONObject(0).getJSONObject("basic");
            Iterator it = jsonObject.keys();
            while (it.hasNext()){
                String key = it.next().toString();
                String value = jsonObject.getString(key);
                mapBasic.put(key,value);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return mapBasic;
    }

    public static List<DailyBean> parseJsonDaily(String jsonData){
        List<DailyBean> dailyBeanList = new ArrayList<>();
            try {
                JSONObject object = new JSONObject(jsonData);
                JSONArray obj = object.getJSONArray("HeWeather6");
                JSONArray jsonArray = obj.getJSONObject(0).getJSONArray("daily_forecast");
                Gson gson = new Gson();
                dailyBeanList = gson.fromJson(jsonArray.toString(), new TypeToken<List<DailyBean>>() {
                }.getType());
            }catch (Exception e){
                e.printStackTrace();
            }

        return dailyBeanList;
    }

    public static List<HourlyBean> parseJsonHourly(String jsonData) {
        List<HourlyBean> hourlyBeanList = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(jsonData);
            JSONArray obj = object.getJSONArray("HeWeather6");
            JSONArray jsonArray = obj.getJSONObject(0).getJSONArray("hourly");
            Gson gson = new Gson();
            hourlyBeanList = gson.fromJson(jsonArray.toString(),new TypeToken<List<HourlyBean>>(){}.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hourlyBeanList;
    }
}