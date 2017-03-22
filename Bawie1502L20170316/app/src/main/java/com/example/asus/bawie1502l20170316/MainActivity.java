package com.example.asus.bawie1502l20170316;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySQLiteOpenHelper=new MySQLiteOpenHelper(this);
        sqLiteDatabase=mySQLiteOpenHelper.getWritableDatabase();
        getData();

  }

private void getData() {
    String strUrl="http://mock.eolinker.com/success/C9kimTQ9XQTqvLMNlXNLdyTWAkChKHh2";
    try {
        URL url=new URL(strUrl);
        HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        if (httpURLConnection.getResponseCode()==200){
            InputStream inputStream = httpURLConnection.getInputStream();
            String str = StrUtil.str(inputStream);
            Gson gson=new Gson();
            Data data = gson.fromJson(str, Data.class);
            MyResult result = data.getResult();
            ArrayList<NewsData> datas = result.getData();
            for (int i=0;i<datas.size();i++){
                String author_name = datas.get(i).getAuthor_name();
                String title = datas.get(i).getTitle();
                sqLiteDatabase.execSQL("insert into yidong(author,title)values('"+author_name+"','"+title+"')");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
