package com.items.code.ui.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.items.code.Activity.BaseActivity;
import com.items.code.R;
import com.items.code.Utils.DbUtils;
import com.items.code.Utils.LogUtils;
import com.items.code.Utils.WebUtils;
import com.items.code.ui.main.fragment.MyApplication;

/**
 * Created by lihongxin on 2016/12/11.
 */

//显示有趣新闻的webview的Avtivity
public class InterestWebActivity extends BaseActivity  {
    private WebView wv;
    private Toolbar toolbar;
    String title=null;
    String url=null;
    String imageurl=null;
    private Boolean flag=false;
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
        wv.setWebViewClient(new WebViewClient());
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {    @Override
        public void onClick(View v) {
            finish();
        }
        });
        Intent intent=getIntent();
        url= (String) intent.getSerializableExtra("url");
        title=(String)intent.getSerializableExtra("title");
        imageurl= (String) intent.getSerializableExtra("imageurl");
       // getInterestUrlHtml(url);//对url进行解析和处理
        wv.loadUrl(url);
    }

    private void getInterestUrlHtml(String url) {
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

    private void dealHtml(String s) {
        String result=null;
        String replace=null;
        replace=s.substring(s.indexOf("<link href=\"/r/cms/www/red/css/20150202/co.css\" rel=\"stylesheet\" type=\"text/css\" />"),s.indexOf("<link rel=\"shortcut icon\" href=\"/r/cms/www/red/img/favicon.ico\" type=\"image/x-icon\" />"));
        result=s.replace(replace,"<link href=\"http://www.myexception.cn/r/cms/www/red/css/20150202/co.css\" rel=\"stylesheet\" type=\"text/css\" />");
        //LogUtils.Logli("result",result);

        // result=s.replace(replace,"http://www.myexception.cn/r/cms/www/red/css/20150202/co.css");
       //String jieqqu=s.substring(s.indexOf("<link href=\"/r/cms/www/red/css/20150202/co.css\" rel=\"stylesheet\" type=\"text/css\">"),"<link href=\"/r/cms/www/red/css/20150202/co.css\" rel=\"stylesheet\" type=\"text/css\">".length());
        //s=s.replace(jieqqu,"<link href=\"http://www.myexception.cn/r/cms/www/red/css/20150202/co.css\" rel=\"stylesheet\" type=\"text/css\">");
        //LogUtils.Logli("截取的字符串",s);
        wv.loadDataWithBaseURL(WebUtils.BASE_URL,result,WebUtils.MIME_TYPE, WebUtils.ENCODING,"");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        MenuItem collectitem;
        collectitem=menu.getItem(0);
        flag=DbUtils.ifExitInCollect(url);
        if (flag==false){
            collectitem.setIcon(R.drawable.ic_favorite_border_black_24dp);
        }
        else{
            collectitem.setIcon(R.drawable.ic_favorite_black_24dp);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
        /*    case R.id.backup:
                finish();
                break;*/
            case R.id.collect:
                if (flag==false){
                    DbUtils.createDatabase();
                    DbUtils.addCollectNews(title,url,imageurl);
                    Toast.makeText(this,"收藏成功",Toast.LENGTH_SHORT).show();
                    flag=true;
                    item.setIcon(R.drawable.ic_favorite_black_24dp);
                }
                else {
                    DbUtils.deleteCollectNews(url);
                    Toast.makeText(this,"取消收藏",Toast.LENGTH_SHORT).show();
                    flag=false;
                    item.setIcon(R.drawable.ic_favorite_border_black_24dp);
                }
                break;
            case R.id.share:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "哈哈哈");
                intent.putExtra(Intent.EXTRA_TEXT, "标题："+title+url+"\n"+"---来自IT资讯");
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, "IT资讯分享"));
                break;

            default:
        }
        return true;
    }


}
