package com.example.asus.todayhead.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.adapter.ChannelAdapter;
import com.example.asus.todayhead.bean.ChannelEntity;
import com.example.asus.todayhead.helper.ItemDragHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class Activity_channel_manager extends AppCompatActivity {

    private RecyclerView mRecy;
    private String[] mystr=new String[]{"推荐","热点","阳光宽频","北京","社会","娱乐","问答","图片","科技","科技","汽车","体育","财经","军事","国际","段子","趣图","美女","健康","正能量","特卖","房产","手机"};
    private String[] channelstr=new String[]{"小说","订阅","时尚","历史","育儿","搞笑","数码","美食","养生","电影","旅游","宠物","情感","家居","教育","三农","孕产","文化","游戏","股票","科学","动漫","故事","收藏","精选"," 语录","星座","美图","政务","辟谣","中国新唱将","彩票"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        mRecy = (RecyclerView) findViewById(R.id.recy);
        init();
    }

    private void init() {
        final List<ChannelEntity> items = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            ChannelEntity entity = new ChannelEntity();
            entity.setName(mystr[i]);
            items.add(entity);
        }
        final List<ChannelEntity> otherItems = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            ChannelEntity entity = new ChannelEntity();
            entity.setName(channelstr[i]);
            otherItems.add(entity);
        }

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecy.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecy);

        final ChannelAdapter adapter = new ChannelAdapter(this, helper, items, otherItems);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == ChannelAdapter.TYPE_MY || viewType == ChannelAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        mRecy.setAdapter(adapter);

        adapter.setOnMyChannelItemClickListener(new ChannelAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(Activity_channel_manager.this, items.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
