package com.example.asus.todayhead.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.adapter.VideoListAdapter;
import com.example.asus.todayhead.bean.VideoData;
import com.example.asus.todayhead.utils.StrUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * Created by asus on 2017/3/21.
 */
public class  Fragment_Video extends Fragment {

    private ListView listView;
    private int count;
    private  List<VideoData> videoList=new ArrayList<>();
    private  String strUrl;
    private JSONArray jsonArray;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count=getArguments().getInt("bun");
        Toast.makeText(getActivity(), count+"", Toast.LENGTH_SHORT).show();
    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        count=getArguments().getInt("bun");
//        Toast.makeText(getActivity(), count+"", Toast.LENGTH_SHORT).show();
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_video,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView= (ListView) view.findViewById(R.id.video_list);
        getData();
    }

    private void getData() {
        if (count==2){
            strUrl="http://c.3g.163.com/nc/video/list/V9LG4CHOR/n/10-10.html";
        }else if (count==3){
            strUrl="http://c.3g.163.com/nc/video/list/V9LG4E6VR/n/10-10.html";
        }else if (count==4){
            strUrl="http://c.3g.163.com/nc/video/list/00850FRB/n/10-10.html";
        }else{
            strUrl ="http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-10.html";
        }
        MyAsyncTask myAsyncTask=new MyAsyncTask();
        myAsyncTask.execute(strUrl);
    }

    public class MyAsyncTask extends AsyncTask<String, Integer,String> {

        @Override
        protected String  doInBackground(String... params) {
            String param = params[0];
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(param);
            try {

                HttpResponse response = httpClient.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    String str = StrUtil.str(inputStream);
                  return str;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute( String s) {
            super.onPostExecute(s);

            try {
                if (count==2){
                     jsonArray= new JSONObject(s).getJSONArray("V9LG4CHOR");
                }else if (count==3){
                     jsonArray=new JSONObject(s).getJSONArray("V9LG4E6VR");
                }else if (count==4){
                   jsonArray=new JSONObject(s).getJSONArray("00850FRB");
                }else{
                    jsonArray=new JSONObject(s).getJSONArray("V9LG4B3A0");
                }
                Type type=new TypeToken<List<VideoData>>(){}.getType();
                Gson gson = new Gson();
                videoList = gson.fromJson(jsonArray.toString(),type);
                listView.setAdapter(new VideoListAdapter(getActivity(),videoList));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
