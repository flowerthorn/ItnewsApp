package com.items.code.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.items.code.Activity.BaseActivity;
import com.items.code.R;
import com.items.code.Utils.WebUtils;
import com.items.code.model.bean.data.dataInfo;
import com.items.code.ui.main.fragment.MyApplication;


/**
 * Created by lihongxin on 2016/12/6.
 */
//显示最新新闻的webview
public class ItemWebActivity extends BaseActivity {
    private WebView wv;
    private String data="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_content);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        wv= (WebView) findViewById(R.id.webview);
        WebSettings webSettings=wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);//屏幕适配
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);//添加对js功能的支持
        wv.setWebViewClient(new WebViewClient());
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
         {    @Override
         public void onClick(View v) {
             finish();
         }
         }
        );
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
        Intent intent=getIntent();
        dataInfo dataInfo= (dataInfo) intent.getSerializableExtra("obj");
        String url=dataInfo.getUrl();
        getLeatestUrlHtml(url);

}

    public void getLeatestUrlHtml(String url) {
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //对获取到的html代码处理
                //LogUtils.Logli("请求url返回的html(未处理):",s);
                dealHtml(s);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        MyApplication.getRequsetquene().add(request);
    }

        public void dealHtml(String s) {
        //字符串操作
          String todeletebottom=s.substring(s.indexOf("<div id=\"news_check\">"));
            data=s.replace(todeletebottom,"");
            String append="<script src=\"https://mini.eastday.com/toutiaoh5/js/photoswipe/photoswipe.min.js\"></script>\n" +
                    "<script src=\"https://mini.eastday.com/toutiaoh5/js/common.min.js\"></script>\n" +
                    "<script src=\"https://mini.eastday.com/toutiaoh5/js/gg_details_v2.min.js\"></script>\n" +
                    "</body>\n" +
                    "</html>";
            data=data+append;
            //LogUtils.Logli("data2",data);
            wv.loadDataWithBaseURL(WebUtils.BASE_URL,data,WebUtils.MIME_TYPE, WebUtils.ENCODING,"");
            //LogUtils.Logli("显示之后的html代码",data);
            //LogUtils.LogLi(data);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
   /*         case R.id.backup:
                finish();
                break;*/
            case R.id.collect:
                item.setIcon(R.drawable.ic_favorite_black_24dp);
                break;
            case R.id.share:
                Toast.makeText(this,"share",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
