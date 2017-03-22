package com.example.asus.todayhead.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.bean.Data;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by asus on 2017/3/14.
 */
public class ListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Data> list;
    final int TEXT=0;
    final int RIGHT_IMAGE=1;
    final int THREE_IMAGE=2;

    public ListAdapter(Context context, ArrayList<Data> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getImage_list()!=null&&list.get(position).getImage_list().size()>0){
             return THREE_IMAGE;
        }else if (list.get(position).getLarge_image_list()!=null&&list.get(position).getLarge_image_list().size()>0){
              return RIGHT_IMAGE;
        }else{
            return TEXT;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
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
        int type = getItemViewType(position);
        ViewHolder_Text viewHolder_text=null;
        ViewHolder_Right_Image viewHolder_right_image=null;
        ViewHolder_Three_Image viewHolder_three_image=null;
        if (convertView==null){
        switch (type) {
            case TEXT:
                viewHolder_text = new ViewHolder_Text();
                convertView = View.inflate(context, R.layout.listviewmoreitem_text, null);
                viewHolder_text.title = (TextView) convertView.findViewById(R.id.listViewMoreItem_text_title);
                viewHolder_text.author = (TextView) convertView.findViewById(R.id.listViewMoreItem_text_author);
                convertView.setTag(viewHolder_text);
                break;
            case RIGHT_IMAGE:
                viewHolder_right_image=new ViewHolder_Right_Image();
                convertView=View.inflate(context,R.layout.listviewmoreitem_right_image,null);
                viewHolder_right_image.right_image_title= (TextView) convertView.findViewById(R.id.listViewMoreItem_right_image_title);
                viewHolder_right_image.right_image= (ImageView) convertView.findViewById(R.id.listViewMoreItem_right_image);
                viewHolder_right_image.right_image_author= (TextView) convertView.findViewById(R.id.listViewMoreItem_right_author);
                convertView.setTag(viewHolder_right_image);
                break;
            case THREE_IMAGE:
                viewHolder_three_image=new ViewHolder_Three_Image();
                convertView=View.inflate(context,R.layout.listviewmoreitem_three_image,null);
                viewHolder_three_image.three_image_title= (TextView) convertView.findViewById(R.id.listViewMoreItem_three_image_title);
                viewHolder_three_image.three_image_1= (ImageView) convertView.findViewById(R.id.listViewMoreItem_three_image_1);
                viewHolder_three_image.three_image_2= (ImageView) convertView.findViewById(R.id.listViewMoreItem_three_image_2);
                viewHolder_three_image.three_image_3= (ImageView) convertView.findViewById(R.id.listViewMoreItem_three_image_3);
                viewHolder_three_image.three_image_author= (TextView) convertView.findViewById(R.id.listViewMoreItem_three_image_author);
                convertView.setTag(viewHolder_three_image);
                break;
           }  }else{
            switch (type) {
                case TEXT:
                    viewHolder_text = (ViewHolder_Text) convertView.getTag();
                    break;
                case RIGHT_IMAGE:
                    viewHolder_right_image= (ViewHolder_Right_Image) convertView.getTag();
                    break;
                case THREE_IMAGE:
                    viewHolder_three_image= (ViewHolder_Three_Image) convertView.getTag();
                    break;
             }
        }
        switch (type) {
            case TEXT:
                viewHolder_text.title.setText(list.get(position).getTitle());
                viewHolder_text.author.setText(list.get(position).getMedia_name());
                 break;
            case RIGHT_IMAGE:
                viewHolder_right_image.right_image_title.setText(list.get(position).getTitle());
                ImageLoader.getInstance().displayImage(list.get(position).getLarge_image_list().get(0).getUrl(),viewHolder_right_image.right_image);
                viewHolder_right_image.right_image_author.setText(list.get(position).getMedia_name());
                break;
            case THREE_IMAGE:
                viewHolder_three_image.three_image_title.setText(list.get(position).getTitle());
                ImageLoader.getInstance().displayImage(list.get(position).getImage_list().get(0).getUrl(),viewHolder_three_image.three_image_1);
                ImageLoader.getInstance().displayImage(list.get(position).getImage_list().get(1).getUrl(),viewHolder_three_image.three_image_2);
                ImageLoader.getInstance().displayImage(list.get(position).getImage_list().get(2).getUrl(),viewHolder_three_image.three_image_3);
                viewHolder_three_image.three_image_author.setText(list.get(position).getMedia_name());
                break;
        }
        return convertView;
    }

    class ViewHolder_Text{
           private TextView title;
           private TextView author;
    }
    class ViewHolder_Right_Image{
        private TextView right_image_title,right_image_author;
        private ImageView right_image;
    }
    class ViewHolder_Three_Image{
        private TextView three_image_title,three_image_author;
        private ImageView three_image_1,three_image_2,three_image_3;

    }

}
