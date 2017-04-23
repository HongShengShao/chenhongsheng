package com.example.asus.volleydemo.activity;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.example.asus.volleydemo.R;
import com.example.asus.volleydemo.adapter.ListAdapter;


import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SwipyRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private CheckBox checkBox;
    private int startNum=0;
    private JSONArray jSONArray;
    private ListAdapter listAdapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout= (SwipyRefreshLayout) findViewById(R.id.srl);
        listView= (ListView) findViewById(R.id.listView);
        checkBox= (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnClickListener(this);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimaryDark,R.color.colorPrimary,R.color.colorAccent,android.R.color.holo_blue_bright);
        swipeRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        swipeRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                startNum=0;
                getData();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                     swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }

            @Override
            public void onLoad(int index) {
                startNum++;
                getData();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                         swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });
        getData();
    }

    private void getData() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url = "http://www.93.gov.cn/93app/data.do?" + "channelId=" + 0 + "&startNum=" + startNum;
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jSONObject=new JSONObject(s);
                    JSONArray data = jSONObject.optJSONArray("data");
                    if (startNum==0){
                        jSONArray=new JSONArray();
                    }
                    for (int i=0;i<data.length();i++){
                       jSONArray.put(data.get(i));
                    }
                    initListView(jSONArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void initListView(JSONArray jSONArray) {
             if (listAdapter==null){
                  listAdapter=new ListAdapter(this, jSONArray, new ListAdapter.OnCheckListener() {
                      @Override
                      public void onCheck(boolean isFlag) {
                        checkBox.setChecked(isFlag);
                      }
                  });
                 listAdapter.setData(jSONArray);
                 listView.setAdapter(listAdapter);
             }else{
                 listAdapter.setData(jSONArray);
             }
    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.checkBox:
                  boolean checked = checkBox.isChecked();
                  if (checked){
                    listAdapter.setnotifyDataSetChanged(checked);
                  }else{
                      listAdapter.setnotifyDataSetChanged(checked);
                  }
                  break;
          }
    }
}
