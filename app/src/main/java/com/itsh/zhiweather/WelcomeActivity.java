package com.itsh.zhiweather;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class WelcomeActivity extends Activity {
    private static final int BAIDU_READ_PHONE_STATE = 100;

    private LocationReceiver locationReceiver;
    private String getCity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                        BAIDU_READ_PHONE_STATE);
            }
            startService(new Intent(WelcomeActivity.this, LocationService.class));
            locationReceiver = new LocationReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction("com.itsh.zhiweather.LocationService");
            registerReceiver(locationReceiver, filter);

        }

        Toast.makeText(getApplicationContext(), "正在获取您的定位信息...", Toast.LENGTH_LONG).show();
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.putExtra("cityname",getCity);
                startActivity(intent);
            }
        }).start();
    }

    class LocationReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            getCity = bundle.getString("city");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(WelcomeActivity.this, LocationReceiver.class));
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case 1:
                BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //获取到权限，做相应处理
                    //调用定位SDK应确保相关权限均被授权，否则会引起定位失败
                    LocationUtil.getInstance(this).startLocation();
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            startService(new Intent(WelcomeActivity.this, LocationService.class));
                            locationReceiver = new LocationReceiver();
                            IntentFilter filter = new IntentFilter();
                            filter.addAction("com.itsh.zhiweather.LocationService");
                            registerReceiver(locationReceiver, filter);
                        }
                    }, 1000);
                } else {
                    //没有获取到权限，做特殊处理
                    Toast.makeText(this, "请开启手机的定位功能！", Toast.LENGTH_LONG);
                }
                break;
            default:
                break;
        }
    }
}
