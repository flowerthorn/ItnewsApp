package com.items.code.ui.other;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.BaseAdapter;

import com.items.code.Activity.BaseActivity;
import com.items.code.R;

/**
 * Created by lihongxin on 2017/2/12.
 */

public class AboutActivity extends BaseActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
/*        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("关于");
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
                 {    @Override
                 public void onClick(View v) {
                     finish();
                 }
                 }
        );
        setSupportActionBar(toolbar);*/
    }
}
