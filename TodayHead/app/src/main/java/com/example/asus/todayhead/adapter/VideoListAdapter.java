package com.example.asus.todayhead.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.todayhead.R;
import com.example.asus.todayhead.bean.VideoData;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by asus on 2017/3/17.
 */
public class VideoListAdapter extends BaseAdapter {

    private Context context;
    private List<VideoData>  list;

    public VideoListAdapter(Context context, List<VideoData> list) {
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
        if (convertView == null) {
            v = new ViewHolder();
            convertView = View.inflate(context,R.layout.activity_video,null);
            v.jcVideoPlayerSimple = (JCVideoPlayerStandard) convertView.findViewById(R.id.jcVideoPlayer);
            v.imageView= (ImageView) convertView.findViewById(R.id.video_image);
            v.textView= (TextView) convertView.findViewById(R.id.video_author);
            v.textView2= (TextView) convertView.findViewById(R.id.video_date);
            convertView.setTag(v);
        } else {
            v = (ViewHolder) convertView.getTag();
        }

        boolean setUp = v.jcVideoPlayerSimple.setUp(list.get(position).getMp4_url(), JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, list.get(position).getTitle());
        if (setUp) {

            Glide.with(context).load(list.get(position).getCover()).into(v.jcVideoPlayerSimple.thumbImageView);
            ImageOptions imageOptions=new ImageOptions.Builder().setCircular(true).build();
            x.image().bind(v.imageView,list.get(position).getTopicImg(),imageOptions);
//            ImageLoader.getInstance().displayImage(list.get(position).getTopicImg(),v.imageView);
            v.textView.setText(list.get(position).getTopicName());
            v.textView2.setText(list.get(position).getPtime());
        }
        return convertView;
    }

    class ViewHolder{
        private JCVideoPlayerStandard jcVideoPlayerSimple;
        private ImageView imageView;
        private TextView textView,textView2;
    }

}
