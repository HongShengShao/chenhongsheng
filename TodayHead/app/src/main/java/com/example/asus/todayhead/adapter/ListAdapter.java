package com.example.asus.todayhead.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.activity.ImageBrowseActivity;
import com.example.asus.todayhead.bean.Data;
import com.example.asus.todayhead.utils.MySQLiteOpenHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by asus on 2017/3/14.
 */
public class ListAdapter extends BaseAdapter {

    private MySQLiteOpenHelper my;
    private SQLiteDatabase s;
    private Context context;
    private ArrayList<Data> list;
    final int TEXT=0;
    final int RIGHT_IMAGE=1;
    final int THREE_IMAGE=2;
    private ArrayList<String> strlist=new ArrayList<>();

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        my=new MySQLiteOpenHelper(context);
        s=my.getWritableDatabase();
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
                viewHolder_text.imageView= (ImageView) convertView.findViewById(R.id.listViewMoreItem_text_collect);
                convertView.setTag(viewHolder_text);
                break;
            case RIGHT_IMAGE:
                viewHolder_right_image=new ViewHolder_Right_Image();
                convertView=View.inflate(context,R.layout.listviewmoreitem_right_image,null);
                viewHolder_right_image.right_image_title= (TextView) convertView.findViewById(R.id.listViewMoreItem_right_image_title);
                viewHolder_right_image.right_image= (ImageView) convertView.findViewById(R.id.listViewMoreItem_right_image);
                viewHolder_right_image.right_image_author= (TextView) convertView.findViewById(R.id.listViewMoreItem_right_author);
                viewHolder_right_image.right_collect= (ImageView) convertView.findViewById(R.id.listViewMoreItem_right_collect);
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
                viewHolder_three_image.three_image_collect= (ImageView) convertView.findViewById(R.id.listViewMoreItem_three_image_collect);
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
                viewHolder_text.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                        s.execSQL("insert into collect(title,url,media_name)values('"+list.get(position).getTitle()+"','"+list.get(position).getUrl()+"','"+list.get(position).getMedia_name()+"')");

                    }
                });
                 break;
            case RIGHT_IMAGE:
                viewHolder_right_image.right_image_title.setText(list.get(position).getTitle());
                ImageLoader.getInstance().displayImage(list.get(position).getLarge_image_list().get(0).getUrl(),viewHolder_right_image.right_image);
                viewHolder_right_image.right_image_author.setText(list.get(position).getMedia_name());
                viewHolder_right_image.right_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context, ImageBrowseActivity.class);
                        if (list!=null&&list.size()!=0){
                            strlist.clear();
                            strlist.add(list.get(position).getLarge_image_list().get(0).getUrl());
                            intent.putStringArrayListExtra("url",strlist);
                            context.startActivity(intent);
                        }else{
                            strlist.add(list.get(position).getLarge_image_list().get(0).getUrl());
                            intent.putStringArrayListExtra("url",strlist);
                            context.startActivity(intent);
                        }

                    }
                });
                viewHolder_right_image.right_collect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                        s.execSQL("insert into collect(title,url,media_name)values('"+list.get(position).getTitle()+"','"+list.get(position).getUrl()+"','"+list.get(position).getMedia_name()+"')");
                    }
                });
                break;
            case THREE_IMAGE:
                viewHolder_three_image.three_image_title.setText(list.get(position).getTitle());
                ImageLoader.getInstance().displayImage(list.get(position).getImage_list().get(0).getUrl(),viewHolder_three_image.three_image_1);
                ImageLoader.getInstance().displayImage(list.get(position).getImage_list().get(1).getUrl(),viewHolder_three_image.three_image_2);
                ImageLoader.getInstance().displayImage(list.get(position).getImage_list().get(2).getUrl(),viewHolder_three_image.three_image_3);
                viewHolder_three_image.three_image_author.setText(list.get(position).getMedia_name());
                viewHolder_three_image.three_image_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addListData(position);
                }
            });
                viewHolder_three_image.three_image_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addListData(position);
                    }
                });
                viewHolder_three_image.three_image_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addListData(position);
                    }
                });
                viewHolder_three_image.three_image_collect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                        s.execSQL("insert into collect(title,url,media_name)values('"+list.get(position).getTitle()+"','"+list.get(position).getUrl()+"','"+list.get(position).getMedia_name()+"')");
                    }
                });
                break;
        }
        return convertView;
    }

    private void addListData(int position) {
        if (strlist!=null&&strlist.size()!=0){
            Intent intent=new Intent(context, ImageBrowseActivity.class);
            strlist.clear();
            strlist.add(list.get(position).getImage_list().get(0).getUrl());
            strlist.add(list.get(position).getImage_list().get(1).getUrl());
            strlist.add(list.get(position).getImage_list().get(2).getUrl());
            intent.putStringArrayListExtra("url",strlist);
            context.startActivity(intent);
        }else{
            Intent intent=new Intent(context, ImageBrowseActivity.class);
            strlist.add(list.get(position).getImage_list().get(0).getUrl());
            strlist.add(list.get(position).getImage_list().get(1).getUrl());
            strlist.add(list.get(position).getImage_list().get(2).getUrl());
            intent.putStringArrayListExtra("url",strlist);
            context.startActivity(intent);
        }
    }


    class ViewHolder_Text{
           private TextView title;
           private TextView author;
           private ImageView imageView;
    }
    class ViewHolder_Right_Image{
        private TextView right_image_title,right_image_author;
        private ImageView right_image,right_collect;
    }
    class ViewHolder_Three_Image{
        private TextView three_image_title,three_image_author;
        private ImageView three_image_1,three_image_2,three_image_3,three_image_collect;

    }

}
