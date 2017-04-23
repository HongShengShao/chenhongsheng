package com.bawie.resist_paste_lane_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawie.resist_paste_lane_project.R;
import com.bawie.resist_paste_lane_project.activity.ParticularsActivity;
import com.bawie.resist_paste_lane_project.bean.CommodityMaskBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/18 9:33
 */
public class CommodityMaskAdapter extends RecyclerView.Adapter<CommodityMaskAdapter.MyViewHolder> {

    private Context context;
    private List<CommodityMaskBean.DataBean> list;
    private onItemClickListener onItemClickListener;

    private interface onItemClickListener{
        void onItemClick(View view,int position);
    }

    public CommodityMaskAdapter(Context context, List<CommodityMaskBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.myclassifygridviewadapter,parent,false);
        final MyViewHolder myvh=new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = myvh.getLayoutPosition();
                Intent intent=new Intent(context, ParticularsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                context.startActivity(intent);

            }
        });
        return myvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getGoods_img()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).crossFade(2000).into(holder.imageView);
        holder.textView1.setText(list.get(position).getEfficacy());
        holder.textView2.setText(list.get(position).getGoods_name());
        holder.textView3.setText("￥"+list.get(position).getShop_price());
        holder.textView4.setText("￥"+list.get(position).getMarket_price());
        holder.textView4.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView1,textView2,textView3,textView4;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.image_myclassifygridviewadapter);
            textView1= (TextView) itemView.findViewById(R.id.textView_myclassifygridviewadapter_name);
            textView2= (TextView) itemView.findViewById(R.id.textView_myclassifygridviewadapter_jieshao);
            textView3= (TextView) itemView.findViewById(R.id.textView_myclassifygridviewadapter_price);
            textView4= (TextView) itemView.findViewById(R.id.textView_myclassifygridviewadapter_market_price);
        }
    }

}
