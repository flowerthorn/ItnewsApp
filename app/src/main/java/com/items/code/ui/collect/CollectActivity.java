package com.items.code.ui.collect;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.items.code.Activity.BaseActivity;
import com.items.code.R;
import com.items.code.Utils.DbUtils;
import com.items.code.Utils.LogUtils;
import com.items.code.model.bean.data.LiteTable.CollectNews;
import com.items.code.ui.main.adapter.CollectNewsAdapter;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by lihongxin on 2017/2/5.
 */

public class CollectActivity extends BaseActivity {
    private Toolbar toolbar;
    private RecyclerView reclcleview;
    private  List<CollectNews> collectNewslist ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("收藏");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
             {    @Override
             public void onClick(View v) {
                 finish();
             }
             }
        );
        initCollectNews();
        reclcleview= (RecyclerView) findViewById(R.id.recycleview_collect_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        reclcleview.setLayoutManager(layoutManager);
        CollectNewsAdapter newsadapter=new CollectNewsAdapter(collectNewslist);
        reclcleview.setAdapter(newsadapter);

      /* Button delete= (Button) findViewById(R.id.button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(CollectNews.class);

            }
        });
        Button view= (Button) findViewById(R.id.button2);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取得所有新闻的列表
                List<CollectNews> collectNewses = DbUtils.getCollectNewsList();
                for (CollectNews collectNews : collectNewses) {
                    LogUtils.Logli("newstitile is", collectNews.getNews_title());
                    LogUtils.Logli("newurl is", collectNews.getNews_url());
                    LogUtils.Logli("newid is ", collectNews.getId() + "");


                }
            }
        });
*/
        }

    private void initCollectNews() {
        collectNewslist = DbUtils.getCollectNewsList();
    }



}