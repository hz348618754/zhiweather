package com.itsh.zhiweather;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class LocationService extends Service {
    String city;

    public LocationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            public void run() {
                try {
                    LocationUtil.getInstance(getApplicationContext()).startLocation();
                    Thread.sleep(2000);
                    city = LocationUtil.getInstance(getApplicationContext()).getCity();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!city.equals("")){
                    Looper.prepare();
                    Toast.makeText(getApplicationContext(),"Service定位成功！",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("city", city);
                    intent.setAction("com.itsh.zhiweather.LocationService");
                    sendBroadcast(intent);
                    Looper.loop();
                }else {
                    Looper.prepare();
                    Toast.makeText(getApplicationContext(),"Service定位失败！",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    //待添加后续处理操作
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the com munication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
