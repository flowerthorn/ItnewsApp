package com.items.code.Activity;

import android.app.Application;

/**
 * Created by charmingsoft on 2016/3/14.
 */
public class App extends Application {
    private static App app;
    public static App getInstance(){
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
