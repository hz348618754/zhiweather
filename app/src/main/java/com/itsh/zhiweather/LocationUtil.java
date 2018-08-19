package com.itsh.zhiweather;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * Created by 沈辉 on 2018/8/18.
 */

public class LocationUtil {
    private static LocationUtil locationUtil;
    private Context context;
    String mCity;
    public static LocationClient mLocationClient;

    private LocationUtil(Context context) {
        this.context = context;
        mLocationClient = new LocationClient(context.getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
//        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);
    }

    /**
     * 定义一个单例方法，避免多线程并发的写法
     */
    public static LocationUtil getInstance(Context context) {
        if (locationUtil == null) {
            synchronized (LocationUtil.class) {
                if(locationUtil==null){
                    locationUtil = new LocationUtil(context);
                }
            }
        }
        return locationUtil;
    }

    /**
     * 开始进行百度定位
     */
    public void startLocation(){
        mLocationClient.start();
    }

    /**
     * 结束百度定位
     */
    public void stopLocation(){
        mLocationClient.stop();
    }


    public String getCity(){
        return mCity;
    }


    public class MyLocationListener implements BDLocationListener {
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation == null) {
                return;
            }
            String location = bdLocation.getCity();
            mCity = location.replace("市", "");  //去掉多余的“市”字
        }
    }
}
