package com.bawie.resist_paste_lane_project.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawie.resist_paste_lane_project.R;
import com.bawie.resist_paste_lane_project.bean.HomePageBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/13 19:55
 */
public class HomePagerGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<HomePageBean.DataBean.DefaultGoodsListBean> defaultGoodsList;

    public HomePagerGridViewAdapter(Context context, List<HomePageBean.DataBean.DefaultGoodsListBean> defaultGoodsList) {
        this.context = context;
        this.defaultGoodsList = defaultGoodsList;
    }

    @Override
    public int getCount() {
        return defaultGoodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return defaultGoodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder v;
        if (convertView==null){
           v=new ViewHolder();
            convertView=View.inflate(context, R.layout.grid_item,null);
            v.imageView= (ImageView) convertView.findViewById(R.id.gridView_image);
            v.textView1= (TextView) convertView.findViewById(R.id.gridView_name);
            v.textView2= (TextView) convertView.findViewById(R.id.gridView_introduce);
            v.textView3= (TextView) convertView.findViewById(R.id.gridView_shop_price);
            v.textView4= (TextView) convertView.findViewById(R.id.gridView_market_price);
            convertView.setTag(v);
        }else{
            v= (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(defaultGoodsList.get(position).getGoods_img()).into(v.imageView);
        v.textView1.setText(defaultGoodsList.get(position).getEfficacy());
        v.textView2.setText(defaultGoodsList.get(position).getGoods_name());
        v.textView3.setText("￥"+defaultGoodsList.get(position).getShop_price());
        v.textView4.setText("￥"+defaultGoodsList.get(position).getMarket_price());

        return convertView;
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView textView1,textView2,textView3,textView4;
    }
}
