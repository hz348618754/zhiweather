package com.itsh.zhiweather;

import android.test.AndroidTestCase;
import android.util.Log;

import java.net.URLEncoder;

/**
 * Created by 沈辉 on 2018/4/27.
 */

public class WeatherGetTest extends AndroidTestCase {
   public void testGetData(){
        String cityName;
        try{
            cityName = URLEncoder.encode("杭州","utf-8");
            String weatherUrl = "https://free-api.heweather.com/s6/weather/now?location=杭州&key=3741d12c649547f0bbc675e484c49e32";
            HttpUtil.sendHttpRequest(weatherUrl, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    Log.v("msg",response);
                }

                @Override
                public void onError(Exception e) {
                    Log.v("msg","失败了！！！");
                }
            });
        }catch (Exception e){

        }
    }
}
