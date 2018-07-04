package com.itsh.zhiweather;

/**
 * Created by 沈辉 on 2018/4/27.
 */

public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
