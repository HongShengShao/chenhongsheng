package com.example.asus.todayhead.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.asus.todayhead.R;
import com.example.asus.todayhead.activity.AttentionActivity;
import com.example.asus.todayhead.adapter.AttentionListAdapter;
import com.example.asus.todayhead.adapter.ListAdapterAttention;
import com.example.asus.todayhead.bean.Attention;
import com.example.asus.todayhead.bean.ListAttention;
import com.example.asus.todayhead.utils.MyImageLoader;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/3/21.
 */
public class Fragment3 extends Fragment {

    private ImageView image_attention;
    private ListView listView;
    private DbManager dbManager=new MyImageLoader().getDb();
    private RelativeLayout relativeLayout_attention,relativeLayout_list;
    private ArrayList<ListAttention> list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment3_attention,null);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        image_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AttentionActivity.class);
                startActivity(intent);
            }
        });
        getListData();
    }

    private void initView(View view) {
        image_attention= (ImageView) view.findViewById(R.id.image_attention);
        relativeLayout_attention= (RelativeLayout) view.findViewById(R.id.relativeLayout_attention);
        relativeLayout_list= (RelativeLayout) view.findViewById(R.id.relativeLayout_list);
        listView= (ListView) view.findViewById(R.id.list_attention);
    }

    private void getListData() {
        try {
            List<Attention> attentionList=dbManager.selector(Attention.class).findAll();
            if (attentionList!=null&&attentionList.size()!=0){
                relativeLayout_attention.setVisibility(View.GONE);
                relativeLayout_list.setVisibility(View.VISIBLE);
                for (int i=0;i<attentionList.size();i++){
                    Toast.makeText(getActivity(), attentionList.get(i).getName()+"", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), attentionList.get(i).getUrl()+"", Toast.LENGTH_SHORT).show();
                    list.add(new ListAttention(attentionList.get(i).getId(),attentionList.get(i).getName(),attentionList.get(i).getUrl()));
                }
                ListAdapterAttention listAdapterAttention=new ListAdapterAttention(getActivity(),list);
                listView.setAdapter(listAdapterAttention);
            }else{
                relativeLayout_attention.setVisibility(View.VISIBLE);
                relativeLayout_list.setVisibility(View.GONE);
            }

        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
