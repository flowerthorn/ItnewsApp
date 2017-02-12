package com.items.code.Activity;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.items.code.Utils.ThemeUtils;


/**
 * Created by lihongxin on 2017/2/12.
 */

public class BaseActivity extends AppCompatActivity
{
 @Override
 protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     ThemeUtils.setTheme(this);
 }


}
