package com.bawie.resist_paste_lane_project.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.resist_paste_lane_project.R;
import com.bawie.resist_paste_lane_project.bean.Particulars;
import com.bawie.resist_paste_lane_project.utils.ImageLoaderPicture;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/20 14:12
 */
public class ParticularsActivity extends Activity implements View.OnClickListener {


    private Button particulars_fan;
    private Button particulars_gouwuche,particulars_but_gouwuche;
    private Button particulars_fenxiang;
    private LinearLayout particulars_layout_shouchang;
    private ImageView particulars_image_shouchang;
    private TextView particulars_text_shouchang;
    private TextView particulars_text_1;
    private TextView particulars_text_2;
    private TextView particulars_text_3;
    private TextView particulars_name;
    private TextView particulars_qian1;
    private TextView particulars_qian2;
    private TextView particulars_chanpin_1;
    private TextView particulars_chanpin_2;
    private TextView particulars_chanpin_3;
    private TextView particulars_sales_volume,particulars_stock_number,particulars_shipping_fee;
    private LinearLayout chanqin_1;
    private LinearLayout chanqin_2;
    private LinearLayout chanqin_3;
    private XBanner xBanner;
    private ArrayList<String> urllist = new ArrayList<>();
    private ArrayList<String> imageList=new ArrayList<>();
    private String id;
    private PopupWindow popupWindow;
   private  View contentView;
    private int count=0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Particulars particulars = (Particulars) msg.obj;
                    Particulars.DataBean particularsData = particulars.getData();
                    List<Particulars.DataBean.ActivityBean> particularsDataActivity = particularsData.getActivity();
                    Particulars.DataBean.GoodsBean particularsDataGoods = particularsData.getGoods();
                    List<Particulars.DataBean.GoodsBean.GalleryBean> gallery = particularsDataGoods.getGallery();
                    List<Particulars.DataBean.CommentsBean> comments = particularsData.getComments();
                    commentsMethod(comments);
                    List<Particulars.DataBean.GoodsBean.AttributesBean> attributes = particularsDataGoods.getAttributes();
                    attributesMethod(attributes);
                    String goodsDesc = particularsDataGoods.getGoods_desc();
                    goodsDescMethod(goodsDesc);
                    String goodsName = particularsDataGoods.getGoods_name();
                    final double shopPrice = particularsDataGoods.getShop_price();
                    double marketPrice = particularsDataGoods.getMarket_price();
                    int salesVolume = particularsDataGoods.getSales_volume();
                    int stockNumber = particularsDataGoods.getStock_number();
                    double shippingFee = particularsDataGoods.getShipping_fee();
                    for (int i=0;i<gallery.size();i++){
                        imageList.add(gallery.get(i).getNormal_url());
                    }
                    xBanner.setData(imageList,null);
                    xBanner.setmAdapter(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, View view, int position) {
                            Glide.with(ParticularsActivity.this).load(imageList.get(position)).into((ImageView) view);
                        }
                    });
                    ImageView imageView= (ImageView) contentView.findViewById(R.id.popup_imageView);
                    final TextView textView_price= (TextView) contentView.findViewById(R.id.popup_textView_price);
                    TextView textView_repertory= (TextView) contentView.findViewById(R.id.popup_textView_repertory);
                    TextView textView_number= (TextView) contentView.findViewById(R.id.popup_textView_number);
                    final TextView shopping_number= (TextView) contentView.findViewById(R.id.shopping_number);
                    Button button_add= (Button) contentView.findViewById(R.id.but_add);
                    Button button_subtract= (Button) contentView.findViewById(R.id.but_subtract);
                    Button button_confirm= (Button) contentView.findViewById(R.id.but_confirm);
                    button_add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                          if (count<5){
                              count++;
                              shopping_number.setText(count+"");
                              textView_price.setText("￥"+shopPrice*count);
                          }else{
                              Toast.makeText(ParticularsActivity.this, "此商品限购5件", Toast.LENGTH_SHORT).show();
                          }
                        }
                    });
                    button_subtract.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (count>1){
                                  count--;
                                shopping_number.setText(count+"");
                                textView_price.setText("￥"+shopPrice*count);
                            }else{
                                Toast.makeText(ParticularsActivity.this, "商品数量不能低于1件哦", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    button_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                          Intent intent=new Intent(ParticularsActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    textView_price.setText("￥"+shopPrice);
                    textView_repertory.setText("库存"+stockNumber+"件");
                    textView_number.setText("限购5件");
                    shopping_number.setText("1");
                    Glide.with(ParticularsActivity.this).load(imageList.get(0)).into(imageView);

                    particulars_text_1.setText(particularsDataActivity.get(0).getTitle());
                    particulars_text_2.setText(particularsDataActivity.get(1).getTitle());
                    particulars_text_3.setText(particularsDataActivity.get(2).getTitle());
                    particulars_name.setText(goodsName);
                    particulars_qian1.setText(shopPrice+"");
                    particulars_qian2.setText(marketPrice+"");
                    particulars_qian2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    particulars_sales_volume.setText(salesVolume+"");
                    particulars_stock_number.setText(stockNumber+"");
                    particulars_shipping_fee.setText(shippingFee+"");
                    break;
            }
        }
    };

    private void commentsMethod(List<Particulars.DataBean.CommentsBean> comments) {
            for (Particulars.DataBean.CommentsBean c : comments) {
                View view = View.inflate(this,
                        R.layout.particulars_layout_itme, null);
                ImageView imageView = (ImageView) view.findViewById
                        (R.id.layou_itme_image);
                TextView name = (TextView) view.findViewById
                        (R.id.layou_itme_text_name);
                TextView pinglun = (TextView) view.findViewById
                        (R.id.layou_itme_text_pinglun);
                TextView shijian = (TextView) view.findViewById
                        (R.id.layou_itme_text_shijian);
                if (c.getContent().length()>15){
                    String substring = c.getContent().substring(0, 15);
                    pinglun.setText(substring+"......");
                }else {
                    pinglun.setText(c.getContent());
                }

                shijian.setText(c.getCreatetime());
                name.setText(c.getUser().getNick_name());
                //图片改成圆形
                ImageOptions options = new ImageOptions.Builder
                        ().setCircular(true).setCrop(true).setSize(100,
                        100).setLoadingDrawableId(R.mipmap.ic_launcher).build();
                x.image().bind(imageView,c.getUser().getIcon(), options);

                chanqin_3.addView(view);
            }
        }



    private void attributesMethod(List<Particulars.DataBean.GoodsBean.AttributesBean> attributes) {
        for (int i=0;i<attributes.size();i++) {
            TextView textView = new TextView(this);
            textView.setTextSize(15);
            textView.setPadding(10, 10, 10, 10);
            textView.setText(attributes.get(i).getAttr_name() + "  " + attributes.get(i).getAttr_value());
            chanqin_2.addView(textView);
        }
    }

    private void goodsDescMethod(String goodsDesc) {
        try {
            JSONArray jsonArray = new JSONArray(goodsDesc);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String url = (String) jsonObject.get("url");
                urllist.add(url);
            }
            for (String s : urllist) {
                ImageView imageView = new ImageView(this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                ImageLoader.getInstance().displayImage(s, imageView,
                        new ImageLoaderPicture(this).getOptions(), new SimpleImageLoadingListener());
                chanqin_1.addView(imageView);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particulars);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        initView();
        showPopupWindow();
    }

private void initView() {
    //轮播
    xBanner= (XBanner) findViewById(R.id.particulars_xBanner);
    particulars_fan = (Button) findViewById(R.id.particulars_fan);
    particulars_gouwuche = (Button) findViewById(R.id.particulars_gouwuche);
    particulars_fenxiang = (Button) findViewById(R.id.particulars_fenxiang);
    //收藏
    particulars_layout_shouchang = (LinearLayout) findViewById(R.id.particulars_layout_shouchang);
    particulars_image_shouchang = (ImageView) findViewById(R.id.particulars_image_shouchang);
    particulars_text_shouchang = (TextView) findViewById(R.id.particulars_text_shouchang);
    //打折信息
    particulars_text_1 = (TextView) findViewById(R.id.particulars_text_1);
    particulars_text_2 = (TextView) findViewById(R.id.particulars_text_2);
    particulars_text_3 = (TextView) findViewById(R.id.particulars_text_3);
    //商品名称+钱
    particulars_name = (TextView) findViewById(R.id.particulars_name);
    particulars_qian1 = (TextView) findViewById(R.id.particulars_qian1);
    particulars_qian2 = (TextView) findViewById(R.id.particulars_qian2);
    particulars_sales_volume= (TextView) findViewById(R.id.particulars_sales_volume);
    particulars_stock_number= (TextView) findViewById(R.id.particulars_stock_number);
    particulars_shipping_fee= (TextView) findViewById(R.id.particulars_shipping_fee);
    //产品信息详情
    particulars_chanpin_1 = (TextView) findViewById(R.id.particulars_chanpin_1);
    particulars_chanpin_2 = (TextView) findViewById(R.id.particulars_chanpin_2);
    particulars_chanpin_3 = (TextView) findViewById(R.id.particulars_chanpin_3);
    chanqin_1 = (LinearLayout) findViewById(R.id.chanqin_1);
    chanqin_2 = (LinearLayout) findViewById(R.id.chanqin_2);
    chanqin_3 = (LinearLayout) findViewById(R.id.chanqin_3);
    particulars_but_gouwuche= (Button) findViewById(R.id.particulars_but_gouwuche);

    particulars_but_gouwuche.setOnClickListener(this);
    particulars_gouwuche.setOnClickListener(this);
    particulars_chanpin_1.setOnClickListener(this);
    particulars_chanpin_2.setOnClickListener(this);
    particulars_chanpin_3.setOnClickListener(this);
     
    getData();
    }

    private void getData() {
        String url = "http://m.yunifang.com/yunifang/mobile/goods/detail?random=46389&encode=70ed2ed2facd7a812ef46717b37148d6&id="+id;
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                Particulars particulars = gson.fromJson(string, Particulars.class);
                Message message = handler.obtainMessage(0, particulars);
                message.sendToTarget();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.particulars_chanpin_1:
                chanqin_1.setVisibility(View.VISIBLE);
                chanqin_2.setVisibility(View.GONE);
                chanqin_3.setVisibility(View.GONE);
                break;
            case R.id.particulars_chanpin_2:
                chanqin_2.setVisibility(View.VISIBLE);
                chanqin_1.setVisibility(View.GONE);
                chanqin_3.setVisibility(View.GONE);
                break;
            case R.id.particulars_chanpin_3:
                chanqin_3.setVisibility(View.VISIBLE);
                chanqin_2.setVisibility(View.GONE);
                chanqin_1.setVisibility(View.GONE);
                break;
            case R.id.particulars_but_gouwuche:
                if (popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else{
                    popupWindow.showAtLocation(particulars_but_gouwuche,Gravity.BOTTOM,0,0);
                }
                break;
            case R.id.particulars_but_goumai:
                break;
        }
    }

    private void showPopupWindow() {
       contentView= LayoutInflater.from(this).inflate(
                R.layout.popupwindow_shopping_cart, null);
        popupWindow = new PopupWindow(contentView,
                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

    }
}
