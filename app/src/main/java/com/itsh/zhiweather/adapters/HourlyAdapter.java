package com.itsh.zhiweather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itsh.zhiweather.HourlyBean;
import com.itsh.zhiweather.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by 沈辉 on 2018/6/14.
 */

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.MyViewHolder> {
    private Context mContext;
    private List<HourlyBean> mDatas = new ArrayList<>();

    public HourlyAdapter(Context context, List<HourlyBean> datas) {
        super();
        this.mContext = context;
        this.mDatas = datas;
    }

    public HourlyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hourly,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    // 显示数据
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HourlyBean hourlyBean = mDatas.get(position);
        holder.time.setText(hourFormate(hourlyBean.getTime()));
        holder.tmp.setText(hourlyBean.getTmp()+"℃");
        holder.cond_txt.setText(hourlyBean.getCond_txt());
        holder.wind_sc.setText(hourlyBean.getWind_sc()+"级");
        holder.wind_dir.setText(hourlyBean.getWind_dir());
        switch (Integer.parseInt(hourlyBean.getCond_code())){
            case 100:holder.cond_img.setImageResource(R.mipmap.p100);break;
            case 101:holder.cond_img.setImageResource(R.mipmap.p101);break;
            case 102:holder.cond_img.setImageResource(R.mipmap.p102);break;
            case 103:holder.cond_img.setImageResource(R.mipmap.p103);break;
            case 104:holder.cond_img.setImageResource(R.mipmap.p104);break;
            case 200:holder.cond_img.setImageResource(R.mipmap.p200);break;
            case 201:holder.cond_img.setImageResource(R.mipmap.p201);break;
            case 202:holder.cond_img.setImageResource(R.mipmap.p202);break;
            case 203:holder.cond_img.setImageResource(R.mipmap.p203);break;
            case 204:holder.cond_img.setImageResource(R.mipmap.p204);break;
            case 205:holder.cond_img.setImageResource(R.mipmap.p205);break;
            case 206:holder.cond_img.setImageResource(R.mipmap.p206);break;
            case 207:holder.cond_img.setImageResource(R.mipmap.p207);break;
            case 208:holder.cond_img.setImageResource(R.mipmap.p208);break;
            case 209:holder.cond_img.setImageResource(R.mipmap.p209);break;
            case 210:holder.cond_img.setImageResource(R.mipmap.p210);break;
            case 211:holder.cond_img.setImageResource(R.mipmap.p211);break;
            case 212:holder.cond_img.setImageResource(R.mipmap.p212);break;
            case 213:holder.cond_img.setImageResource(R.mipmap.p213);break;
            case 300:holder.cond_img.setImageResource(R.mipmap.p300);break;
            case 301:holder.cond_img.setImageResource(R.mipmap.p301);break;
            case 302:holder.cond_img.setImageResource(R.mipmap.p302);break;
            case 303:holder.cond_img.setImageResource(R.mipmap.p303);break;
            case 304:holder.cond_img.setImageResource(R.mipmap.p304);break;
            case 305:holder.cond_img.setImageResource(R.mipmap.p305);break;
            case 306:holder.cond_img.setImageResource(R.mipmap.p306);break;
            case 307:holder.cond_img.setImageResource(R.mipmap.p307);break;
            case 309:holder.cond_img.setImageResource(R.mipmap.p309);break;
            case 310:holder.cond_img.setImageResource(R.mipmap.p310);break;
            case 311:holder.cond_img.setImageResource(R.mipmap.p311);break;
            case 312:holder.cond_img.setImageResource(R.mipmap.p312);break;
            case 313:holder.cond_img.setImageResource(R.mipmap.p313);break;
            case 314:holder.cond_img.setImageResource(R.mipmap.p314);break;
            case 315:holder.cond_img.setImageResource(R.mipmap.p315);break;
            case 316:holder.cond_img.setImageResource(R.mipmap.p316);break;
            case 317:holder.cond_img.setImageResource(R.mipmap.p317);break;
            case 318:holder.cond_img.setImageResource(R.mipmap.p318);break;
            case 399:holder.cond_img.setImageResource(R.mipmap.p399);break;
            case 400:holder.cond_img.setImageResource(R.mipmap.p400);break;
            case 401:holder.cond_img.setImageResource(R.mipmap.p401);break;
            case 402:holder.cond_img.setImageResource(R.mipmap.p402);break;
            case 403:holder.cond_img.setImageResource(R.mipmap.p403);break;
            case 404:holder.cond_img.setImageResource(R.mipmap.p404);break;
            case 405:holder.cond_img.setImageResource(R.mipmap.p405);break;
            case 406:holder.cond_img.setImageResource(R.mipmap.p406);break;
            case 407:holder.cond_img.setImageResource(R.mipmap.p407);break;
            case 408:holder.cond_img.setImageResource(R.mipmap.p408);break;
            case 409:holder.cond_img.setImageResource(R.mipmap.p409);break;
            case 410:holder.cond_img.setImageResource(R.mipmap.p410);break;
            case 499:holder.cond_img.setImageResource(R.mipmap.p499);break;
            case 500:holder.cond_img.setImageResource(R.mipmap.p500);break;
            case 501:holder.cond_img.setImageResource(R.mipmap.p501);break;
            case 502:holder.cond_img.setImageResource(R.mipmap.p502);break;
            case 503:holder.cond_img.setImageResource(R.mipmap.p503);break;
            case 504:holder.cond_img.setImageResource(R.mipmap.p504);break;
            case 507:holder.cond_img.setImageResource(R.mipmap.p507);break;
            case 508:holder.cond_img.setImageResource(R.mipmap.p508);break;
            case 509:holder.cond_img.setImageResource(R.mipmap.p509);break;
            case 510:holder.cond_img.setImageResource(R.mipmap.p510);break;
            case 511:holder.cond_img.setImageResource(R.mipmap.p511);break;
            case 512:holder.cond_img.setImageResource(R.mipmap.p512);break;
            case 513:holder.cond_img.setImageResource(R.mipmap.p513);break;
            case 514:holder.cond_img.setImageResource(R.mipmap.p514);break;
            case 515:holder.cond_img.setImageResource(R.mipmap.p515);break;
            case 900:holder.cond_img.setImageResource(R.mipmap.p900);break;
            case 901:holder.cond_img.setImageResource(R.mipmap.p901);break;
            case 999:holder.cond_img.setImageResource(R.mipmap.p999);break;

            default:holder.cond_img.setImageResource(R.mipmap.p999);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private String hourFormate(String date){
        Date mDate = null;
        String result = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            mDate = df.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        result = sdf.format(mDate);
        return result;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView time,tmp,cond_txt,wind_dir,wind_sc;
        private ImageView cond_img;
        public MyViewHolder(View view) {
            super(view);
            time = (TextView) view.findViewById(R.id.item_time);
            tmp = (TextView)view.findViewById(R.id.item_tmp);
            cond_txt = (TextView)view.findViewById(R.id.item_cond_txt);
            wind_dir = (TextView)view.findViewById(R.id.item_wind_dir);
            wind_sc = (TextView)view.findViewById(R.id.item_wind_sc);
            cond_img = (ImageView)view.findViewById(R.id.item_cond_image);
        }
    }
}
