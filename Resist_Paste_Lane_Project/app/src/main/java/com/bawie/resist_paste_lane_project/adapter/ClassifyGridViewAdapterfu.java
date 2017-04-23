package com.bawie.resist_paste_lane_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.resist_paste_lane_project.R;
import com.bawie.resist_paste_lane_project.activity.EffectActivity;
import com.bawie.resist_paste_lane_project.bean.ClassifyGridViewAdapterBean;
import com.bawie.resist_paste_lane_project.bean.MaskEffectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/16 20:40
 */
public class ClassifyGridViewAdapterfu extends RecyclerView.Adapter<ClassifyGridViewAdapterfu.MyViewHolder> {

    private Context context;
    private List<ClassifyGridViewAdapterBean> list;
    private onItemClickListener itemClickListener;
    private onItemLongClickListener itemLongClickListener;

    public interface  onItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface  onItemLongClickListener{
        void onItemLomgClickListener(View view,int position);
    }


    public ClassifyGridViewAdapterfu(Context context, ArrayList<ClassifyGridViewAdapterBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.classifygridviewadapterfu,parent,false);
        final MyViewHolder myViewHolder=new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = myViewHolder.getLayoutPosition();
                Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();
                ArrayList<MaskEffectBean> list=new ArrayList<>();
                list.add(new MaskEffectBean("混合性肤质",14,0));
                list.add(new MaskEffectBean("中性肤质",13,1));
                list.add(new MaskEffectBean("干性肤质",29,2));
                list.add(new MaskEffectBean("油性肤质",28,3));
                list.add(new MaskEffectBean("痘痘性肤质",15,4));
                list.add(new MaskEffectBean("敏感性肤质",37,5));
                Intent intent=new Intent(context,EffectActivity.class);
                intent.putExtra("list",list);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       holder.textView.setText("#"+list.get(position).getStr()+"#");
        holder.relativeLayout.setBackgroundResource(list.get(position).getColor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        private RelativeLayout relativeLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.textView_classfiyGridView);
            relativeLayout= (RelativeLayout) itemView.findViewById(R.id.relativeLayout_classfiyGridView);
        }
    }
}
