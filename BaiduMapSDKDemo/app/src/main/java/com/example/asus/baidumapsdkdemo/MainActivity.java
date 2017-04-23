package com.example.asus.baidumapsdkdemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private MapView mapView;

    // 初始化全局 bitmap 信息，不用时及时 recycle
    BitmapDescriptor bdA = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_marka);
    BitmapDescriptor bdB = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_markb);
    BitmapDescriptor bdC = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_markc);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        //  SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.mapview);
        //获取地图
        final BaiduMap mBaiduMap = mapView.getMap();
        //普通地图
        //  mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //卫星地图
        //  mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        //开启交通图
        mBaiduMap.setTrafficEnabled(true);
        //开启热力图
        // mBaiduMap.setBaiduHeatMapEnabled(true);

        //地图标注
        //定义Maker坐标点
        LatLng latLng = new LatLng(39.963175, 116.400244);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);

        //-------------------------------------------------------------------------------------------

        //第一种方式直接显示地图标注 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(latLng)
                .icon(bitmap).draggable(true);  //设置手势拖拽;
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
//-------------------------------------------------------------------------------------------
        LatLng latLngA = new LatLng(39.963175, 116.300244);
        BitmapDescriptor bdAgrop = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
        //第二种方式添加掉下动画效果
        final MarkerOptions ooA = new MarkerOptions().position(latLngA).icon(bdAgrop)
                .zIndex(9).draggable(true);

        // 掉下动画
        // ooA.animateType(MarkerOptions.MarkerAnimateType.drop);
        ooA.animateType(MarkerOptions.MarkerAnimateType.grow);
        //在地图上添加Marker，并显示
        // mBaiduMap.addOverlay(ooA);
        final Marker mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));
//-------------------------------------------------------------------------------------------

        //帖动画效果
        // 通过marker的icons设置一组图片，再通过period设置多少帧刷新一次图片资源
        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
        giflist.add(bdA);
        giflist.add(bdB);
        giflist.add(bdC);
        //定义Maker坐标点
        LatLng latLngD = new LatLng(39.763175, 116.400244);
        final OverlayOptions ooD = new MarkerOptions().position(latLngD).icons(giflist)
                .zIndex(0).period(10);
        mBaiduMap.addOverlay(ooD);
        // Marker   mMarkerD = (Marker) (mBaiduMap.addOverlay(ooD));

        //实现Marker的点击
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //判断对应Marker
                if (mMarkerA == marker) {
                    Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                    startActivity(intent);
                }

                return true;
            }
        });
//-------------------------------------------------------------------------------------------
        //调用BaiduMap对象的setOnMarkerDragListener方法设置marker拖拽的监听
        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {
                //拖拽中
            }

            public void onMarkerDragEnd(Marker marker) {
                //拖拽结束
                LatLng latLng = marker.getPosition();
                double longitude = latLng.longitude;
                double latitude = latLng.latitude;
                Log.i("xxx", longitude + "," + latitude);


            }

            public void onMarkerDragStart(Marker marker) {
                //开始拖拽
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }


}