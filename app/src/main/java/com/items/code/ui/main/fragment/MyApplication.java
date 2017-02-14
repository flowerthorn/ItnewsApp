package com.items.code.ui.main.fragment;


import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.litepal.LitePalApplication;

/**
 * Created by lihongxin on 2016/11/30.
 */

public class MyApplication extends LitePalApplication{
    private  static RequestQueue queue;
    private static Context context;

    @Override
    public void onCreate() {
        queue= Volley.newRequestQueue(getApplicationContext());
        context=getApplicationContext();
        LitePalApplication.initialize(context);
        super.onCreate();
    }
    public static RequestQueue getRequsetquene(){
        return queue;

    }
    public static Context getContext(){
        return context;
    }


}
