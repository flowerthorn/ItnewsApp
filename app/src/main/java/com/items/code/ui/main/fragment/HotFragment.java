package com.items.code.ui.main.fragment;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.items.code.R;

public class HotFragment extends Fragment {
    private WebView webView;
    private String url="https://www.baidu.com";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hot, container, false);
        webView= (WebView) view.findViewById(R.id.hot_webview);
        dealwebview();
        return view;
    }

    private void dealwebview() {
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);//屏幕适配
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);//添加对js功能的支持
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            //这个方法会在传进url时处理 决定下一步行动
            //true 自己去处理url
            //false 让webview去加载这个url
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //返回值是true的时候控制去WebView打开，
                //为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

    }
    //改写物理按键——返回的逻辑
    //如果希望浏览的网页后退而不是退出浏览器，
    // 需要WebView覆盖URL加载，让它自动生成历史访问记录，
    // 那样就可以通过前进或后退访问已访问过的站点。


}

