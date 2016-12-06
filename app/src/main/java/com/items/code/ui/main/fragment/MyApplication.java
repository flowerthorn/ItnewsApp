package com.items.code.ui.main.fragment;


import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by lihongxin on 2016/11/30.
 */

public class MyApplication extends Application{
    private  static RequestQueue queue;

    @Override
    public void onCreate() {
        queue= Volley.newRequestQueue(getApplicationContext());
        super.onCreate();
    }
    public static RequestQueue getRequsetquene(){
        return queue;

    }


}
