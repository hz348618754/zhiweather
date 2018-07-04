package com.itsh.zhiweather;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.itsh.zhiweather.adapters.DailyAdapter;
import com.itsh.zhiweather.adapters.HourlyAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    public static final int TEMP_NOW = 1;

    private TextView location,temperature_now,weather_now;
    private RecyclerView rv_hourly;
    private ListView lv_daily;

    Weather weather = new Weather();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        getTemperature();

    }

    //初始化组件
    private void initView() {
        location = (TextView)findViewById(R.id.tv_location);
        temperature_now = (TextView)findViewById(R.id.tv_temprature_now);
        weather_now = (TextView)findViewById(R.id.tv_weather_now);
        lv_daily = (ListView) findViewById(R.id.lv_daily);
        rv_hourly = (RecyclerView)findViewById(R.id.rv_hourly);

        LinearLayoutManager linearLayoutManager_hourly = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rv_hourly.setLayoutManager(linearLayoutManager_hourly);
//        rv_hourly.addItemDecoration( new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));


    }


    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what){
                case TEMP_NOW:
                    Map<String,String> mapNow = new HashMap<>();
                    Map<String,String> mapBasic = new HashMap<>();

                    List<DailyBean> listDaily = new ArrayList<>();
                    List<HourlyBean> listHourly = new ArrayList<>();

                    mapNow = ParseJSON.parseJsonNow((String) msg.obj);
                    mapBasic = ParseJSON.parseJsonBasic((String)msg.obj);
                    listDaily = ParseJSON.parseJsonDaily((String)msg.obj);
                    listHourly = ParseJSON.parseJsonHourly((String)msg.obj);
                    weather.setTemperature(mapNow.get("tmp"));
                    weather.setLocation(mapBasic.get("location"));
                    weather.setWeather(mapNow.get("cond_txt"));
                    temperature_now.setText(weather.getTemperature()+"℃");
                    location.setText(weather.getLocation());
                    weather_now.setText(weather.getWeather());

                    rv_hourly.setAdapter(new HourlyAdapter(MainActivity.this,listHourly));
                    DailyAdapter adapter = new DailyAdapter(MainActivity.this,listDaily);
                    lv_daily.setAdapter(adapter);
            }
        }
    };


    private void getTemperature() {

//        cityName = URLEncoder.encode("杭州","utf-8");
        try{
            String weatherUrl = "https://free-api.heweather.com/s6/weather?location=杭州&key=3741d12c649547f0bbc675e484c49e32";
            HttpUtil.sendHttpRequest(weatherUrl, new HttpCallbackListener() {
                public void onFinish(String response) {
                    Message message = new Message();
                    message.what = TEMP_NOW;
                    message.obj = response;
                    handler.sendMessage(message);
                }
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
