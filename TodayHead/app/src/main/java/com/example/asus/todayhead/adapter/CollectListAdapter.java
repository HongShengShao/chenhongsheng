package com.example.asus.todayhead.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.bean.CollectList;

import java.util.ArrayList;

/**
 * Created by asus on 2017/3/23.
 */
public class CollectListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CollectList> list;

    public CollectListAdapter(Context context, ArrayList<CollectList> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder v;
        if (convertView==null){
            v=new ViewHolder();
            convertView=View.inflate(context, R.layout.activity_list_collect,null);
            v.textView= (TextView) convertView.findViewById(R.id.text_collect_title);
            v.textView2= (TextView) convertView.findViewById(R.id.text_collect_media_name);
            convertView.setTag(v);
        }else{
           v= (ViewHolder) convertView.getTag();
        }
         v.textView.setText(list.get(position).getTitle());
        v.textView2.setText(list.get(position).getMedia_name());
        return convertView;
    }

    class ViewHolder{
        private TextView textView,textView2;
    }

}
