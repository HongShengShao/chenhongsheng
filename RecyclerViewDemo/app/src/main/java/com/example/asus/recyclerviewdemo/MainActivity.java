package com.example.asus.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Integer> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        recyclerView= (RecyclerView) findViewById(R.id.recycleView);
        //向集合中添加数据
        for (int i=0;i<50;i++){
            list.add(R.mipmap.a);
            list.add(R.mipmap.b);
            list.add(R.mipmap.c);
            list.add(R.mipmap.d);
            list.add(R.mipmap.e);
            list.add(R.mipmap.f);
        }
        //设置recyclerView的布局管理器
//       LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,GridLayoutManager.HORIZONTAL);
//        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL);
        //设置 recyclerView的布局
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setLayoutManager(gridLayoutManager);
         recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
          recyclerView.setAdapter(new Adapter(this,list));
//        recyclerView.addItemDecoration(new MyDecoration(this,MyDecoration.VERTICAL_LIST));

    }
}
