package com.bawie.resist_paste_lane_project.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.bawie.resist_paste_lane_project.R;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/12 20:03
 */
public class MyWebView extends Activity{

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webView= (WebView) findViewById(R.id.myWebView);
        Intent inten=getIntent();
        String url=inten.getStringExtra("url");
        webView.loadUrl(url);

    }
}
