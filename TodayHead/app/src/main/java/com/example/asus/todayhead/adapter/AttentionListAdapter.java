package com.example.asus.todayhead.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.bean.Attention;
import com.example.asus.todayhead.bean.ListAttention;
import com.example.asus.todayhead.utils.MyImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/3/27.
 */
public class AttentionListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ListAttention> list;
    private DbManager dbManager= new MyImageLoader().getDb();


    public  AttentionListAdapter(Context context, ArrayList<ListAttention> list) {
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
        final ViewHolder v;
        if (convertView==null){
           v=new ViewHolder();
            convertView=View.inflate(context, R.layout.attention_listviewadapter,null);
            v.textView= (TextView) convertView.findViewById(R.id.textView_attention);
            v.radioButton= (RadioButton) convertView.findViewById(R.id.button_attention);
            convertView.setTag(v);
        }else{
            v= (ViewHolder) convertView.getTag();
        }

          try {
                List<Attention> attentionList=dbManager.selector(Attention.class).findAll();
                if (attentionList!=null) {
                    for (int i = 0; i < attentionList.size(); i++) {
                        if (list.get(position).getName().equals(attentionList.get(i).getName())) {
//                        Toast.makeText(context, state+"", Toast.LENGTH_SHORT).show();
                            v.radioButton.setChecked(true);
                            notifyDataSetChanged();
                        }
                    }
                }

            } catch (DbException e) {
                e.printStackTrace();
            }


        v.textView.setText(list.get(position).getName());
        if (!v.radioButton.isChecked()){
            v.radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        Toast.makeText(context, "点击增加", Toast.LENGTH_SHORT).show();
                         v.radioButton.setChecked(true);
                        Attention attention=new Attention();
                        attention.setName(list.get(position).getName());
                        attention.setUrl(list.get(position).getUrl());
                        attention.setState(true);
                        dbManager.save(attention);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }else{
            v.radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        Toast.makeText(context, "点击删除", Toast.LENGTH_SHORT).show();
                        v.radioButton.setChecked(false);
                        dbManager.deleteById(Attention.class,list.get(position).getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        return convertView;
    }

    class ViewHolder{
        private TextView textView;
        private RadioButton radioButton;
    }
}
