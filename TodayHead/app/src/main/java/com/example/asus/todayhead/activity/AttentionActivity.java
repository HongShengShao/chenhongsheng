package com.example.asus.todayhead.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.adapter.AttentionListAdapter;
import com.example.asus.todayhead.bean.ListAttention;

import java.util.ArrayList;

/**
 * Created by asus on 2017/3/27.
 */
public class AttentionActivity extends Activity {

    private ListView listView;
    private ArrayList<ListAttention> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        listView= (ListView) findViewById(R.id.listView_attention);
        addList();

    }

    private void addList() {
        list.add(new ListAttention(1,"推荐","http://ic.snssdk.com/2/article/v25/stream/?count=20&min_behot_time=1457659690&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1457672153&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6"));
        list.add(new ListAttention(2,"财经","http://ic.snssdk.com/2/article/v25/stream/?category=news_finance&count=20&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1458025150&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6"));
        list.add(new ListAttention(3,"社会","http://a3.pstatp.com/2/article/content/8/1/6254089566836719873/?iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6"));
        list.add(new ListAttention(4,"娱乐","http://ic.snssdk.com/2/article/v25/stream/?category=news_entertainment&count=20&min_behot_time=1457659532&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1457659698&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6"));
        list.add(new ListAttention(5,"体育","http://ic.snssdk.com/2/article/v25/stream/?category=news_sports&count=20&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1458025150&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6"));
        list.add(new ListAttention(6,"汽车","http://ic.snssdk.com/2/article/v25/stream/?category=news_car&count=20&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1458025150&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6"));
        listView.setAdapter(new AttentionListAdapter(this,list));
    }
}
