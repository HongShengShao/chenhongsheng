package com.example.asus.todayhead.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.webkit.WebView;

import com.example.asus.todayhead.R;

/**
 * Created by asus on 2017/3/17.
 */
public class ConnectActivity  extends Activity{

    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connet);
        webView= (WebView) findViewById(R.id.webView);
        Intent intent=getIntent();
        String str=intent.getStringExtra("url");
        webView.loadUrl(str);
    }
}
