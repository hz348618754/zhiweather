package com.itsh.zhiweather;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

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
                Intent intent = new Intent();
                intent.putExtra("city", city);
                intent.setAction("com.itsh.zhiweather.LocationService");
                sendBroadcast(intent);
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
