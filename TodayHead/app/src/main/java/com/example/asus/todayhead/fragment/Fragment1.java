package com.example.asus.todayhead.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.activity.ConnectActivity;
import com.example.asus.todayhead.adapter.ListAdapter;
import com.example.asus.todayhead.bean.Data;
import com.example.asus.todayhead.bean.NewsData;
import com.example.asus.todayhead.utils.StrUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import xlistview.bawei.com.xlistviewlibrary.XListView;

/**
 * Created by asus on 2017/3/11.
 */
public class Fragment1 extends Fragment {
    private int count=0;
    private XListView xListView;
    private ArrayList<Data> dataArrayList=new ArrayList<>();
    private int number=20;
    private String strUrl;
    private Handler handler=new Handler();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count=getArguments().getInt("bun");
        Log.i("TAG",count+"");
        Toast.makeText(getActivity(), count+"", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        xListView= (XListView) view.findViewById(R.id.xListView);
        xListView.setPullLoadEnable(true);
        xListView.setPullRefreshEnable(true);
        getData(20);
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
              handler.postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      getDate();
                  }
              },2000);
            }

            @Override
            public void onLoadMore() {
                  number++;
                getData(number);
                xListView.stopLoadMore();
            }
        });
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), ConnectActivity.class);
                intent.putExtra("url",dataArrayList.get(position-1).getUrl());
                startActivity(intent);
            }
        });
    }

    private void getDate() {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy_MM_dd hh:mm:ss");
        String format = simpleDateFormat.format(date);
        xListView.setRefreshTime(format);
        xListView.stopRefresh();

    }

    private void getData(int number) {

       if (count==2){
            strUrl="http://ic.snssdk.com/2/article/v25/stream/?category=news_hot&count="+number+"&min_behot_time=1457659116&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1457672153&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6";
       }else if (count==3){
           strUrl="http://ic.snssdk.com/2/article/v25/stream/?category=news_hot&count="+number+"&min_behot_time=1457659116&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1457672153&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6";
       }else if (count==4){
            strUrl="http://ic.snssdk.com/2/article/v25/stream/?category=video&count="+number+"&min_behot_time=1457684204&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1457672153&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6";
       }else if (count==5){
           strUrl="http://ic.snssdk.com/2/article/v25/stream/?category=news_tech&count="+number+"&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1458025150&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6";
       }else if (count==6){
            strUrl="http://ic.snssdk.com/2/article/v25/stream/?category=news_tech&count="+number+"&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1458025150&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6";
       }else if (count==7){
             strUrl="http://ic.snssdk.com/2/article/v25/stream/?category=news_car&count="+number+"&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1458025150&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6";
       }else if (count==8){
              strUrl="http://ic.snssdk.com/2/article/v25/stream/?category=news_sports&count="+number+"&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1458025150&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6";
       }else if (count==9){
              strUrl="http://ic.snssdk.com/2/article/v25/stream/?category=news_finance&count="+number+"&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1458025150&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6";
       }
       else{
           strUrl = "http://ic.snssdk.com/2/article/v25/stream/?count=" + number + "&min_behot_time=1457659690&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1457672153&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6";
       }
      MyAsyncTask myAsyncTask=new MyAsyncTask();
       myAsyncTask.execute(strUrl);
    }

    public class MyAsyncTask extends AsyncTask<String,Integer,ArrayList<Data>>{

        @Override
        protected ArrayList<Data> doInBackground(String... params) {
            String param = params[0];
            HttpClient httpClient=new DefaultHttpClient();
            HttpGet httpGet=new HttpGet(param);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                if (response.getStatusLine().getStatusCode()==200){
                    InputStream inputStream = response.getEntity().getContent();
                    String str = StrUtil.str(inputStream);
                    Gson gson=new Gson();
                    NewsData newsData = gson.fromJson(str, NewsData.class);
                    dataArrayList= newsData.getData();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return dataArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Data> s) {
            super.onPostExecute(s);
            xListView.setAdapter(new ListAdapter(getActivity(),dataArrayList));
        }
    }

}
