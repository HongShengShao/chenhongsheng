package com.bawie.resist_paste_lane_project.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.resist_paste_lane_project.R;
import com.bawie.resist_paste_lane_project.activity.ParticularsActivity;
import com.bawie.resist_paste_lane_project.adapter.HomePageListViewAdapter;
import com.bawie.resist_paste_lane_project.adapter.HomePagerGridViewAdapter;
import com.bawie.resist_paste_lane_project.bean.HomePageBean;
import com.bawie.resist_paste_lane_project.bean.XbannerData;
import com.bawie.resist_paste_lane_project.view.MyWebView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/11 20:04
 */
public class Fragment_Home_Page extends Fragment {

    private String url="http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447";
    private ImageView everyDay_Sign_In_image,integral_Store_image,exchange_Prefecture_image,true_and_False_image,hot_spring_image;
    private TextView everyDay_Sign_In_text,integral_Store_text,exchange_Prefecture_text,true_and_False_text,textView_springtime;
    private XBanner xBanner;
    private ArrayList<XbannerData> list=new ArrayList<>();
    private LinearLayout mLinearLayout,mLinearLayout2,hot_spring_linear;
    private String name,image,secondImg;
    private ListView listView;
    private GridView gridView;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    final ArrayList<XbannerData> arrayList = (ArrayList<XbannerData>) msg.obj;
                    xBanner.setData(arrayList, null);
                    xBanner.setmAdapter(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, View view, int position) {
                            Glide.with(getActivity()).load(arrayList.get(position).getImageUrl()).into((ImageView) view);
                        }
                    });
                    xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                        @Override
                        public void onItemClick(XBanner banner, int position) {
                            Toast.makeText(getActivity(), "点击了第" + (position + 1) + "张图片" + arrayList.get(position).getUrl(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MyWebView.class);
                            intent.putExtra("url", arrayList.get(position).getUrl());
                            startActivity(intent);
                        }
                    });
                    xBanner.setPageTransformer(Transformer.Depth);
                    break;
                case 1:
                    List<HomePageBean.DataBean.Ad5Bean> ad5BeanList = (List<HomePageBean.DataBean.Ad5Bean>) msg.obj;
                    Glide.with(getActivity()).load(ad5BeanList.get(0).getImage()).into(everyDay_Sign_In_image);
                    Glide.with(getActivity()).load(ad5BeanList.get(1).getImage()).into(integral_Store_image);
                    Glide.with(getActivity()).load(ad5BeanList.get(2).getImage()).into(exchange_Prefecture_image);
                    Glide.with(getActivity()).load(ad5BeanList.get(3).getImage()).into(true_and_False_image);
                    everyDay_Sign_In_text.setText(ad5BeanList.get(0).getTitle());
                    integral_Store_text.setText(ad5BeanList.get(1).getTitle());
                    exchange_Prefecture_text.setText(ad5BeanList.get(2).getTitle());
                    true_and_False_text.setText(ad5BeanList.get(3).getTitle());
                    break;
                case 2:
                    final List<HomePageBean.DataBean.BestSellersBean.GoodsListBeanX> goodsListBeanList = (List<HomePageBean.DataBean.BestSellersBean.GoodsListBeanX>) msg.obj;
                    for (int x = 0; x < goodsListBeanList.size();x++) {
                        //寻找行布局，第一个参数为行布局ID，第二个参数为这个行布局需要放到那个容器上
                        View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_text, mLinearLayout, false);
                        //通过View寻找ID实例化控件
                        ImageView img = (ImageView) view.findViewById(R.id.imageView_commodity);
                        //实例化TextView控件
                        TextView tv = (TextView) view.findViewById(R.id.textView_name);
                        TextView tv_price = (TextView) view.findViewById(R.id.textView_price);
                        TextView tv_market_price = (TextView) view.findViewById(R.id.textView_market_price);
                        //将集合中的数据放到ImageView中
                        Glide.with(getActivity()).load(goodsListBeanList.get(x).getGoods_img()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).crossFade(2000).into(img);
                        //给TextView添加文字
                        tv.setText(goodsListBeanList.get(x).getGoods_name());
                        tv_price.setText("￥" + goodsListBeanList.get(x).getShop_price());
                        tv_market_price.setText("￥" + goodsListBeanList.get(x).getMarket_price());
                        tv_market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        textView_springtime.setText("---  " + name + "  ---");
                        //把行布局放到linear里
                        mLinearLayout.addView(view);
                        final int position = x;
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "点击", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), ParticularsActivity.class);
                                intent.putExtra("id", goodsListBeanList.get(position).getId());
                                startActivity(intent);
                            }
                        });
                    }
                    break;
                case 3:
                    List<HomePageBean.DataBean.SubjectsBean> subjectsBeanList= (List<HomePageBean.DataBean.SubjectsBean>) msg.obj;
                    listView.setAdapter(new HomePageListViewAdapter(getActivity(),subjectsBeanList));
                    break;
                case 4:
                    final List<HomePageBean.DataBean.DefaultGoodsListBean> defaultGoodsList = (List<HomePageBean.DataBean.DefaultGoodsListBean>) msg.obj;
                    gridView.setAdapter(new HomePagerGridViewAdapter(getActivity(),defaultGoodsList));
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(getActivity(),ParticularsActivity.class);
                            intent.putExtra("id",defaultGoodsList.get(position).getId());
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home_page,null);
        initView(view);

        return view;
    }

    private void initView(View view) {
        xBanner= (XBanner) view.findViewById(R.id.xBanner);
        everyDay_Sign_In_image= (ImageView) view.findViewById(R.id.EveryDay_Sign_In_image);
        integral_Store_image= (ImageView) view.findViewById(R.id.Integral_Store_image);
        exchange_Prefecture_image= (ImageView) view.findViewById(R.id.Exchange_Prefecture_image);
        true_and_False_image= (ImageView) view.findViewById(R.id.True_and_False_image);
        everyDay_Sign_In_text= (TextView) view.findViewById(R.id.EveryDay_Sign_In_text);
        integral_Store_text= (TextView) view.findViewById(R.id.Integral_Store_text);
        exchange_Prefecture_text= (TextView) view.findViewById(R.id.Exchange_Prefecture_text);
        true_and_False_text= (TextView) view.findViewById(R.id.True_and_False_text);
        textView_springtime= (TextView) view.findViewById(R.id.textView_springtime);
        mLinearLayout= (LinearLayout) view.findViewById(R.id.linear);
        mLinearLayout2= (LinearLayout) view.findViewById(R.id.lineaLayout);
        listView= (ListView) view.findViewById(R.id.Home_page_listView);
        gridView= (GridView) view.findViewById(R.id.gridView);
        getData();
    }

    private void getData() {
        OkHttpClient mokHttpClient=new OkHttpClient();
        Request mrequest=new Request.Builder().url(url).build();
        Call call=mokHttpClient.newCall(mrequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                HomePageBean homePageBean = gson.fromJson(string, HomePageBean.class);
                HomePageBean.DataBean data = homePageBean.getData();
                List<HomePageBean.DataBean.Ad1Bean> ad1 = data.getAd1();
                List<HomePageBean.DataBean.Ad5Bean> ad5 = data.getAd5();
                List<HomePageBean.DataBean.BestSellersBean> bestSellersBeanList = data.getBestSellers();
                HomePageBean.DataBean.BestSellersBean bestSellersBean = bestSellersBeanList.get(0);
                List<HomePageBean.DataBean.BestSellersBean.GoodsListBeanX> goodsDataList = bestSellersBean.getGoodsList();
                name = bestSellersBean.getName();
                List<HomePageBean.DataBean.SubjectsBean> subjectsBeanList = data.getSubjects();
                List<HomePageBean.DataBean.DefaultGoodsListBean> defaultGoodsList = data.getDefaultGoodsList();
                for(int i=0;i<ad1.size();i++){
                      list.add(new XbannerData(ad1.get(i).getImage(),ad1.get(i).getAd_type_dynamic_data()));
                }
                Message message = handler.obtainMessage(0, list);
                message.sendToTarget();
                Message message1 = handler.obtainMessage(1, ad5);
                message1.sendToTarget();
                Message message2 = handler.obtainMessage(2, goodsDataList);
                message2.sendToTarget();
                Message message3 = handler.obtainMessage(3, subjectsBeanList);
                message3.sendToTarget();
                Message message4 = handler.obtainMessage(4, defaultGoodsList);
                message4.sendToTarget();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        xBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xBanner.stopAutoPlay();
    }
}
