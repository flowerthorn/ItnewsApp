package com.items.code.ui.main.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.items.code.Activity.BaseActivity;
import com.items.code.R;

import static java.security.AccessController.getContext;

/**
 * Created by lihongxin on 2016/12/11.
 */

//显示有趣新闻的webview的Avtivity
public class InterestWebActivity extends BaseActivity  {
    private WebView wv;
    private Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_content);
        wv= (WebView) findViewById(R.id.webview);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WebSettings webSettings=wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);//屏幕适配
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);//添加对js功能的支持
        Intent intent=getIntent();
        String url= (String) intent.getSerializableExtra("obj");
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {    @Override
        public void onClick(View v) {
          finish();
        }
        }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
        /*    case R.id.backup:
                finish();
                break;*/
            case R.id.collect:
                item.setIcon(R.drawable.ic_favorite_black_24dp);
                //toolbar.setLogo(R.drawable.ic_about);
                break;
            case R.id.share:
                Toast.makeText(this,"share",Toast.LENGTH_SHORT).show();
                break;

            default:
        }
        return true;
    }


}
