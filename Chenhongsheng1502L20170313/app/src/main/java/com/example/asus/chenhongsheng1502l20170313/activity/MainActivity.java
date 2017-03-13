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
    private ArrayList<ListData> listDatas=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean netWork = NetWorkUtil.isNetWork(this);
        if (netWork){
            getData();
        }else{
            Toast.makeText(MainActivity.this, "网络无连接", Toast.LENGTH_SHORT).show();
        }
    }

    private void getData() {

        MyAsyncTask myAsyncTask=new MyAsyncTask();
        myAsyncTask.execute();

    }

    public class MyAsyncTask extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost(strUrl);
            try {
                HttpResponse response = httpClient.execute(httpPost);
                if (response.getStatusLine().getStatusCode()==200){
                    InputStream inputStream = response.getEntity().getContent();
                    String str = StrUtils.str(inputStream);
                    Gson gson=new Gson();
                    CityData cityData = gson.fromJson(str, CityData.class);
                    List<CityData.ResultBean> resultBeanList = cityData.getResult();
                    for (int i=0;i<resultBeanList.size();i++){
                        CityData.ResultBean resultBean = resultBeanList.get(i);
                        String parentid = resultBean.getParentid();
                        ListData listData=new ListData();
                        if (parentid.equals("0")){
                            listData.setCity(resultBean.getCity());
                            listData.setParentid(parentid);
                        }else{
                            ExpandableData expandableData=new ExpandableData();
                            expandableData.setCity(expandableData.getCity());
                            expandableData.setParentid(parentid);
                            list.add(expandableData);
                            listData.setList(list);
                        }
                        listDatas.add(listData);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            expandableListView.setAdapter(new ListAdapter(MainActivity.this,listDatas));
        }
    }
}
