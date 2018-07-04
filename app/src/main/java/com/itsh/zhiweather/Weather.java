package com.itsh.zhiweather;

/**
 * Created by 沈辉 on 2018/4/29.
 */

public class Weather {
    private String temperature;
    private String location;
    private String weather;

    //构造器
    public Weather(){

    }

    public Weather(String temperature, String location, String weather) {
        this.temperature = temperature;
        this.location = location;
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
