package com.example.asus.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/13 13:08
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private ArrayList<Integer> list;
    private ArrayList<Integer> intList;

    public Adapter(Context context, ArrayList<Integer> list) {
        this.context = context;
        this.list = list;
        intList=new ArrayList<>();
        for (int i=0;i<100;i++){
           intList.add((int) (100+Math.random()*300));
        }
    }

    //创建viewholder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));

        return myViewHolder;
    }

    //绑定viewholder
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
             holder.textView.setImageResource(list.get(position));
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height=intList.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends ViewHolder {

        private ImageView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView= (ImageView) itemView.findViewById(R.id.textView);
        }
    }

}



