package com.items.code.ui.collect;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.items.code.Activity.BaseActivity;
import com.items.code.R;
import com.items.code.Utils.LogUtils;
import com.items.code.model.bean.data.LiteTable.CollectNews;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by lihongxin on 2017/2/5.
 */

public class CollectActivity extends BaseActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("收藏");
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
                                             {    @Override
                                             public void onClick(View v) {
                                                 finish();
                                             }
                                             }
        );
        setSupportActionBar(toolbar);
        List<CollectNews> collectNewses = DataSupport.findAll(CollectNews.class);
        for (CollectNews collectNews : collectNewses) {
            LogUtils.Logli("newstitile is", collectNews.getNews_title());
            LogUtils.Logli("newurl is", collectNews.getNews_url());
            LogUtils.Logli("newid is ", collectNews.getId() + "");
    /*    CollectNews collectNews=new CollectNews();
        collectNews.deleteAll(CollectNews.class,null,null);*/

        }
    }

}