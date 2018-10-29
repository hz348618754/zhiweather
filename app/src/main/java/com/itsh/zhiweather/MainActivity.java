package com.itsh.zhiweather;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itsh.zhiweather.adapters.DailyAdapter;
import com.itsh.zhiweather.adapters.HourlyAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    public static final int TEMP_NOW = 1;

    private static final int BAIDU_READ_PHONE_STATE =100;

    private TextView tv_location,temperature_now,weather_now;
    private RecyclerView rv_hourly;
    private ListView lv_daily;
    private LinearLayout linearLayout_top_right;
    private static String getCity = "北京";

    Weather weather = new Weather();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if( checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
                requestPermissions( new String[]{ Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                        BAIDU_READ_PHONE_STATE );
            }
            LocationUtil.getInstance(this).startLocation();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    getCity = LocationUtil.getInstance(MainActivity.this).getCity();
                }
            },1000);
            initView();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    getTemperature();
                    Toast.makeText(MainActivity.this,"天气已更新！if",Toast.LENGTH_SHORT).show();
                }
            },2000);
        }else {
            LocationUtil.getInstance(this).startLocation();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    getCity = LocationUtil.getInstance(MainActivity.this).getCity();
                }
            },1000);
            initView();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    getTemperature();
                    Toast.makeText(MainActivity.this,"天气已更新！else",Toast.LENGTH_SHORT).show();
                }
            },2000);
        }
        //点击跳转到空气质量页面
        linearLayout_top_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AirQuality.class);
                intent.putExtra("city",getCity);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        LocationUtil.getInstance(this).stopLocation();
        super.onDestroy();
    }


    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,grantResults);
        switch(requestCode) {
            //requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case 1:
                BAIDU_READ_PHONE_STATE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //获取到权限，做相应处理
                    //调用定位SDK应确保相关权限均被授权，否则会引起定位失败
                    LocationUtil.getInstance(this).startLocation();
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            getCity = LocationUtil.getInstance(MainActivity.this).getCity();
                        }
                    },1000);
                } else{
                    //没有获取到权限，做特殊处理
                    Toast.makeText(this,"请开启手机的定位功能！",Toast.LENGTH_LONG);
                }
                break;
            default:
                break;
        }
    }


    //初始化组件
    private void initView() {
        tv_location = (TextView)findViewById(R.id.tv_location);
        temperature_now = (TextView)findViewById(R.id.tv_temprature_now);
        weather_now = (TextView)findViewById(R.id.tv_weather_now);
        lv_daily = (ListView) findViewById(R.id.lv_daily);
        rv_hourly = (RecyclerView)findViewById(R.id.rv_hourly);
        linearLayout_top_right = (LinearLayout)findViewById(R.id.top_right);

        LinearLayoutManager linearLayoutManager_hourly = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rv_hourly.setLayoutManager(linearLayoutManager_hourly);

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
                    weather.setWeather(mapNow.get("cond_txt")+"\t\t"+mapNow.get("wind_dir"));
                    temperature_now.setText(weather.getTemperature()+"℃");
                    tv_location.setText(weather.getLocation());
                    weather_now.setText(weather.getWeather());

                    rv_hourly.setAdapter(new HourlyAdapter(MainActivity.this,listHourly));
                    DailyAdapter adapter = new DailyAdapter(MainActivity.this,listDaily);
                    lv_daily.setAdapter(adapter);
            }
        }

    };


    private void getTemperature() {
        String cityName = getCity;
        String weatherUrl = "https://free-api.heweather.com/s6/weather?location="+cityName+"&key=3741d12c649547f0bbc675e484c49e32";
            HttpUtil.sendHttpRequest(weatherUrl, new HttpCallbackListener() {
                public void onFinish(String response) {
                    Message message = new Message();
                    message.what = TEMP_NOW;
                    message.obj = response;
                    handler.sendMessage(message);
                }
                public void onError(Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"请检查当前网络状况！",Toast.LENGTH_SHORT).show();
                }
            });

    }



}
