package com.example.asus.todayhead.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.bean.Attention;
import com.example.asus.todayhead.bean.ListAttention;
import com.example.asus.todayhead.utils.MyImageLoader;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.ArrayList;

/**
 * Created by asus on 2017/3/27.
 */
public class ListAdapterAttention extends BaseAdapter {

    private Context context;
    private ArrayList<ListAttention> list;
    private DbManager dbManager=new MyImageLoader().getDb();

    public ListAdapterAttention(Context context, ArrayList<ListAttention> list) {
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
        ViewHolder v;
        if (convertView==null){
            v=new ViewHolder();
            convertView=View.inflate(context, R.layout.listviewapter_attention,null);
            v.textView= (TextView) convertView.findViewById(R.id.text_attention_list);
            v.radioButton= (RadioButton) convertView.findViewById(R.id.radiobutton_attention_list);
            convertView.setTag(v);
        }else{
             v= (ViewHolder) convertView.getTag();
        }
        v.textView.setText(list.get(position).getName());
        v.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                WhereBuilder whereBuilder=WhereBuilder.b();
//                whereBuilder.and("id","=",list.get(position).getId());
//                whereBuilder.and("name","=",list.get(position).getName());
                try {
//                   dbManager.delete(Attention.class,whereBuilder);
                    dbManager.deleteById(Attention.class,list.get(position).getId());
                    list.remove(position);
                    notifyDataSetChanged();
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });
        return convertView;
    }

    class ViewHolder{
           private TextView textView;
           private RadioButton radioButton;
    }

}
