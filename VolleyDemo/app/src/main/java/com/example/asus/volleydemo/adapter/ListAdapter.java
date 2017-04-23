package com.example.asus.volleydemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.volleydemo.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/10 20:31
 */
public class ListAdapter extends BaseAdapter {

    private Context context;
    private JSONArray jsonArray;
    private Map<String,Boolean> map=new HashMap<String,Boolean>();
    public OnCheckListener listener;
    private CheckBox checkBox;

    public ListAdapter(Context context, JSONArray jsonArray,OnCheckListener listener) {
        this.context = context;
        this.jsonArray = jsonArray;
        this.listener = listener;
        setCheck(false);
    }

    public interface  OnCheckListener{
        void onCheck(boolean isFlag);
    }

    public void setData(JSONArray jsonArray){
        this.jsonArray=jsonArray;
        notifyDataSetChanged();
    }

    public void setnotifyDataSetChanged(boolean check){
        setCheck(check);
        notifyDataSetChanged();
    }

    public void setCheck(boolean b){
        map.clear();
        for (int i=0;i<jsonArray.length();i++){
            try {
                String id = jsonArray.getJSONObject(i).getString("ID");
                map.put(id,b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.listadapter,null);
        checkBox= (CheckBox) view.findViewById(R.id.list_checkBox);
        ImageView imageView= (ImageView) view.findViewById(R.id.list_iamgeView);
        TextView textView= (TextView) view.findViewById(R.id.list_textView);
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            String title= (String) jsonObject.get("TITLE");
            String imageUrl= (String) jsonObject.get("IMAGEURL");
            final String id= (String) jsonObject.get("ID");
            textView.setText(title);

            Picasso.with(context).load(imageUrl).into(imageView);

            checkBox.setChecked(map.get(id));
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked=checkBox.isChecked();
                    map.put(id,checked);
                    boolean isChecked=true;
                    for (String key:map.keySet()){
                        Boolean value = map.get(key);
                        if (!value){
                              isChecked=false;
                              listener.onCheck(false);
                        }
                    }
                    if (isChecked){
                        listener.onCheck(true);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
