package com.itsh.zhiweather.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.itsh.zhiweather.DailyBean;
import com.itsh.zhiweather.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 沈辉 on 2018/5/10.
 */

public class DailyAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<DailyBean> mDatas;

    public DailyAdapter(Context context, List<DailyBean> Datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = Datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item_daily,parent,false);
            holder = new ViewHolder();
            holder.time = (TextView)convertView.findViewById(R.id.item_time);
            holder.weather = (TextView)convertView.findViewById(R.id.item_weather);
            holder.tmp = (TextView)convertView.findViewById(R.id.item_tmp);
            holder.img = (ImageView)convertView.findViewById(R.id.item_weather_image);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        DailyBean dailyBean = mDatas.get(position);
        holder.tmp.setText(dailyBean.getTmp_max()+"/"+dailyBean.getTmp_min()+"℃");
        holder.time.setText(dateFormate(dailyBean.getDate()));
        holder.weather.setText(dailyBean.getCond_txt_d());
        switch (Integer.parseInt(dailyBean.getCond_code_d())){
            case 100:holder.img.setImageResource(R.mipmap.p100);break;
            case 101:holder.img.setImageResource(R.mipmap.p101);break;
            case 102:holder.img.setImageResource(R.mipmap.p102);break;
            case 103:holder.img.setImageResource(R.mipmap.p103);break;
            case 104:holder.img.setImageResource(R.mipmap.p104);break;
            case 200:holder.img.setImageResource(R.mipmap.p200);break;
            case 201:holder.img.setImageResource(R.mipmap.p201);break;
            case 202:holder.img.setImageResource(R.mipmap.p202);break;
            case 203:holder.img.setImageResource(R.mipmap.p203);break;
            case 204:holder.img.setImageResource(R.mipmap.p204);break;
            case 205:holder.img.setImageResource(R.mipmap.p205);break;
            case 206:holder.img.setImageResource(R.mipmap.p206);break;
            case 207:holder.img.setImageResource(R.mipmap.p207);break;
            case 208:holder.img.setImageResource(R.mipmap.p208);break;
            case 209:holder.img.setImageResource(R.mipmap.p209);break;
            case 210:holder.img.setImageResource(R.mipmap.p210);break;
            case 211:holder.img.setImageResource(R.mipmap.p211);break;
            case 212:holder.img.setImageResource(R.mipmap.p212);break;
            case 213:holder.img.setImageResource(R.mipmap.p213);break;
            case 300:holder.img.setImageResource(R.mipmap.p300);break;
            case 301:holder.img.setImageResource(R.mipmap.p301);break;
            case 302:holder.img.setImageResource(R.mipmap.p302);break;
            case 303:holder.img.setImageResource(R.mipmap.p303);break;
            case 304:holder.img.setImageResource(R.mipmap.p304);break;
            case 305:holder.img.setImageResource(R.mipmap.p305);break;
            case 306:holder.img.setImageResource(R.mipmap.p306);break;
            case 307:holder.img.setImageResource(R.mipmap.p307);break;
            case 309:holder.img.setImageResource(R.mipmap.p309);break;
            case 310:holder.img.setImageResource(R.mipmap.p310);break;
            case 311:holder.img.setImageResource(R.mipmap.p311);break;
            case 312:holder.img.setImageResource(R.mipmap.p312);break;
            case 313:holder.img.setImageResource(R.mipmap.p313);break;
            case 314:holder.img.setImageResource(R.mipmap.p314);break;
            case 315:holder.img.setImageResource(R.mipmap.p315);break;
            case 316:holder.img.setImageResource(R.mipmap.p316);break;
            case 317:holder.img.setImageResource(R.mipmap.p317);break;
            case 318:holder.img.setImageResource(R.mipmap.p318);break;
            case 399:holder.img.setImageResource(R.mipmap.p399);break;
            case 400:holder.img.setImageResource(R.mipmap.p400);break;
            case 401:holder.img.setImageResource(R.mipmap.p401);break;
            case 402:holder.img.setImageResource(R.mipmap.p402);break;
            case 403:holder.img.setImageResource(R.mipmap.p403);break;
            case 404:holder.img.setImageResource(R.mipmap.p404);break;
            case 405:holder.img.setImageResource(R.mipmap.p405);break;
            case 406:holder.img.setImageResource(R.mipmap.p406);break;
            case 407:holder.img.setImageResource(R.mipmap.p407);break;
            case 408:holder.img.setImageResource(R.mipmap.p408);break;
            case 409:holder.img.setImageResource(R.mipmap.p409);break;
            case 410:holder.img.setImageResource(R.mipmap.p410);break;
            case 499:holder.img.setImageResource(R.mipmap.p499);break;
            case 500:holder.img.setImageResource(R.mipmap.p500);break;
            case 501:holder.img.setImageResource(R.mipmap.p501);break;
            case 502:holder.img.setImageResource(R.mipmap.p502);break;
            case 503:holder.img.setImageResource(R.mipmap.p503);break;
            case 504:holder.img.setImageResource(R.mipmap.p504);break;
            case 507:holder.img.setImageResource(R.mipmap.p507);break;
            case 508:holder.img.setImageResource(R.mipmap.p508);break;
            case 509:holder.img.setImageResource(R.mipmap.p509);break;
            case 510:holder.img.setImageResource(R.mipmap.p510);break;
            case 511:holder.img.setImageResource(R.mipmap.p511);break;
            case 512:holder.img.setImageResource(R.mipmap.p512);break;
            case 513:holder.img.setImageResource(R.mipmap.p513);break;
            case 514:holder.img.setImageResource(R.mipmap.p514);break;
            case 515:holder.img.setImageResource(R.mipmap.p515);break;
            case 900:holder.img.setImageResource(R.mipmap.p900);break;
            case 901:holder.img.setImageResource(R.mipmap.p901);break;
            case 999:holder.img.setImageResource(R.mipmap.p999);break;

            default:holder.img.setImageResource(R.mipmap.p999);
        }

        return convertView;
    }

    private String dateFormate(String date){
        Date mDate = null;
        String result = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            mDate = df.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
        result = sdf.format(mDate);
        return result;
    }


    private class ViewHolder{
        TextView time;
        TextView weather;
        TextView tmp;
        ImageView img;
    }
}
