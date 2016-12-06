package com.items.code.ui.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.items.code.R;
import com.items.code.model.bean.data.dataInfo;

import java.util.ArrayList;

/**
 * Created by lihongxin on 2016/12/6.
 */

public class ItemWebActivity extends Activity {
    private WebView wv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_content);
        wv= (WebView) findViewById(R.id.webview);
        WebSettings webSettings=wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);//屏幕适配
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);//添加对js功能的支持
        Intent intent=getIntent();
        dataInfo dataInfo= (dataInfo) intent.getSerializableExtra("obj");
        String url=dataInfo.getUrl();
        wv.loadUrl(url);
 /*       wv.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    ItemWebActivity.this.setTitle("加载完成");
                } else {
                    ItemWebActivity.this.setTitle("加载中.......");

                }
            }

        });*/
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;

            }
        });

    }


}
