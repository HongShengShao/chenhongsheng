package com.example.asus.chenhongsheng1502l20170313.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.asus.chenhongsheng1502l20170313.R;
import com.example.asus.chenhongsheng1502l20170313.bean.ListData;

import java.util.ArrayList;

/**
 * Created by asus on 2017/3/13.
 */
public class ListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ListData> list;

    public ListAdapter(Context context, ArrayList<ListData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().size();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView=View.inflate(context, R.layout.activity_group,null);
        TextView textView= (TextView) convertView.findViewById(R.id.text_group);
        textView.setText(list.get(groupPosition).getCity());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView=View.inflate(context, R.layout.activity_group,null);
        TextView textView= (TextView) convertView.findViewById(R.id.text_group);
        textView.setText(list.get(groupPosition).getList().get(childPosition).getCity());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
