package com.itsh.zhiweather;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class AirQuality extends Activity {
public static final int AIRQUALITY_NOW = 1;
private String city;
private TextView aqi,qlty,pm25,pm10,no2,so2,co,o3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_quality);

        city = getIntent().getStringExtra("city");
        if(!city.equals("")) {
            getAirQuality(city);
        }
        initView();

    }
    //初始化组件
    private void initView() {
        aqi = (TextView)findViewById(R.id.aqi);
        qlty = (TextView)findViewById(R.id.qlty);
        pm10 = (TextView)findViewById(R.id.PM10);
        pm25 = (TextView)findViewById(R.id.PM25);
        no2 = (TextView)findViewById(R.id.NO2);
        so2 = (TextView)findViewById(R.id.SO2);
        co = (TextView)findViewById(R.id.CO);
        o3 = (TextView)findViewById(R.id.O3);
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case AIRQUALITY_NOW:
                    Map<String,String> mapAirQuality  = new HashMap<>();
                    mapAirQuality = ParseJSON.parseJsonQuality((String) msg.obj);
                    //显示空气质量数据
                    showAirQuality(mapAirQuality);
            }
        }
    };

    private void showAirQuality(Map<String, String> mapAirQuality) {
        aqi.setText("空气质量指数："+mapAirQuality.get("aqi"));
        qlty.setText("当前空气质量："+mapAirQuality.get("qlty"));
        pm25.setText(mapAirQuality.get("pm25"));
        pm10.setText(mapAirQuality.get("pm10"));
        co.setText(mapAirQuality.get("co"));
        no2.setText(mapAirQuality.get("no2"));
        so2.setText(mapAirQuality.get("so2"));
        o3.setText(mapAirQuality.get("o3"));
    }

    //获取网络的空气质量数据
    private void getAirQuality(String cityName){
        cityName = city;
        String url = "https://free-api.heweather.com/s6/air/now?location="+cityName+"&key=3741d12c649547f0bbc675e484c49e32";
        HttpUtil.sendHttpRequest(url, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Message message = new Message();
                message.what = AIRQUALITY_NOW;
                message.obj = response;
                handler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"AirQuality获取失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
