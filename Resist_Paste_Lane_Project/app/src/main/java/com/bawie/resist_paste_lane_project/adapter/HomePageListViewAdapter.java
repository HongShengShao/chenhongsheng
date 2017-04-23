package com.bawie.resist_paste_lane_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.resist_paste_lane_project.R;
import com.bawie.resist_paste_lane_project.activity.ParticularsActivity;
import com.bawie.resist_paste_lane_project.bean.HomePageBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/13 14:10
 */
public class HomePageListViewAdapter extends BaseAdapter {

    private Context context;
    private List<HomePageBean.DataBean.SubjectsBean> list;

    public HomePageListViewAdapter(Context context, List<HomePageBean.DataBean.SubjectsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

            String image = list.get(position).getImage();
            final List<HomePageBean.DataBean.SubjectsBean.GoodsListBean> goodsList = list.get(position).getGoodsList();
//            View secondView = LayoutInflater.from(context).inflate(R.layout.item_text_2,false);
            convertView=View.inflate(context,R.layout.item_text_2,null);
            LinearLayout hot_spring_linear= (LinearLayout) convertView.findViewById(R.id.hot_special_linear);
            ImageView hot_spring_image= (ImageView) convertView.findViewById(R.id.hot_special_image);
            Glide.with(context).load(image).into(hot_spring_image);
            for (int j=0;j<goodsList.size();j++){
                View view = LayoutInflater.from(context).inflate(R.layout.item_text, hot_spring_linear,false);
                //通过View寻找ID实例化控件
                ImageView img = (ImageView) view.findViewById(R.id.imageView_commodity);
                //实例化TextView控件
                TextView tv = (TextView) view.findViewById(R.id.textView_name);
                TextView tv_price = (TextView) view.findViewById(R.id.textView_price);
                TextView tv_market_price= (TextView) view.findViewById(R.id.textView_market_price);
                //将集合中的数据放到ImageView中
                Glide.with(context).load(goodsList.get(j).getGoods_img()).into(img);
                //给TextView添加文字
                tv.setText(goodsList.get(j).getGoods_name());
                tv_price.setText("￥"+goodsList.get(j).getShop_price());
                tv_market_price.setText("￥"+goodsList.get(j).getMarket_price());
                tv_market_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
                //把行布局放到linear里
                hot_spring_linear.addView(view);
                final int finalJ = j;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "点击", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, ParticularsActivity.class);
                        intent.putExtra("id", goodsList.get(finalJ).getId());
                        context.startActivity(intent);
                    }
                });
            }
        return convertView;
    }
}
