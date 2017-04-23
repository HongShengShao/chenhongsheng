package com.example.asus.todayhead.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.adapter.CollectListAdapter;
import com.example.asus.todayhead.bean.CollectList;
import com.example.asus.todayhead.utils.MySQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by asus on 2017/3/23.
 */
public class CollectActivity extends Activity {

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private ListView listView;
    private String title,url,media_name;
    private ArrayList<CollectList> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
         listView= (ListView) findViewById(R.id.collect_listView);
        mySQLiteOpenHelper=new MySQLiteOpenHelper(this);
        sqLiteDatabase=mySQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from collect", null);
        while(cursor.moveToNext()){
            title= cursor.getString(cursor.getColumnIndex("title"));
            media_name=cursor.getString(cursor.getColumnIndex("media_name"));
            url = cursor.getString(cursor.getColumnIndex("url"));
            list.add(new CollectList(title,media_name,url));
        }
        CollectListAdapter collectListAdapter=new CollectListAdapter(CollectActivity.this,list);
        listView.setAdapter(collectListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(CollectActivity.this,ConnectActivity.class);
                intent.putExtra("url",list.get(position).getUrl());
                startActivity(intent);
            }
        });
    }
}
