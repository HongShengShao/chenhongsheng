package com.example.asus.chenhongsheng1502l20170313.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.asus.chenhongsheng1502l20170313.R;
import com.example.asus.chenhongsheng1502l20170313.adapter.ListAdapter;
import com.example.asus.chenhongsheng1502l20170313.bean.CityData;
import com.example.asus.chenhongsheng1502l20170313.bean.ExpandableData;
import com.example.asus.chenhongsheng1502l20170313.bean.ListData;
import com.example.asus.chenhongsheng1502l20170313.utils.NetWorkUtil;
import com.example.asus.chenhongsheng1502l20170313.utils.StrUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private String strUrl="http://api.jisuapi.com/weather/city?appkey=b4d06fdd59ed379f";
    private ArrayList<ExpandableData> list=new ArrayList<>();
    private ArrayList<ArrayList<ExpandableData>> listDatas=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView= (ExpandableListView) findViewById(R.id.expandableListView);
        boolean netWork = NetWorkUtil.isNetWork(this);
        if (netWork){
            getData();
        }else{
            Toast.makeText(MainActivity.this, "网络无连接", Toast.LENGTH_SHORT).show();
        }
    }

    private void getData() {

        MyAsyncTask myAsyncTask=new MyAsyncTask();
        myAsyncTask.execute(strUrl);

    }

    public class MyAsyncTask extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost(url);
            try {
                HttpResponse response = httpClient.execute(httpPost);
                if (response.getStatusLine().getStatusCode()==200){
                    InputStream inputStream = response.getEntity().getContent();
                    String str = StrUtils.str(inputStream);
                    return str;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String str=s.toString();
            Gson gson=new Gson();
            ListData listData = gson.fromJson(str, ListData.class);
            ArrayList<ExpandableData> datas = listData.resultix;
            for (ExpandableData city:datas){
                if (city.parentid.equals("0")){
                    list.add(city);
                }
            }
            for (ExpandableData expandable:list){
                ArrayList<ExpandableData> childList=new ArrayList<>();
                for (ExpandableData city:datas){
                    if (expandable.cityid.equals(city.parentid)){
                        childList.add(city);
                    }
                }
                listDatas.add(childList);
            }
            expandableListView.setAdapter(new ListAdapter(MainActivity.this,list,listDatas));
        }
    }
}
