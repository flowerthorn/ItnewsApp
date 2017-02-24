package com.items.code.ui.smile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.items.code.Activity.BaseActivity;
import com.items.code.R;
import com.items.code.Utils.DecorationUtils;
import com.items.code.model.bean.data.Smile;
import com.items.code.ui.main.adapter.SmileAdapter;
import com.items.code.ui.main.fragment.MyApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lihongxin on 2017/2/12.
 */

public class SmileActivity extends BaseActivity {
    private Toolbar toolbar;
    private RecyclerView recycleview;
    private List<Smile> list=new ArrayList<>();
    private String url="http://www.runoob.com/w3cnote_genre/joke";
    private String smile_url2="http://www.runoob.com/w3cnote_genre/joke/page/2";
    private String smile_url3="http://www.runoob.com/w3cnote_genre/joke/page/3";
    private String regEx="(<div class=\"archive-list-item\">)(.*?)(</i></h2>)";
    private String url_regEx="(http://www.runoob.com/).*?(.html)";
    private String title_regEx="(?<=title=\").*?(?=\">)";
    private String image_regEx="(?<=src=\").*?(?=\" alt)";
    private String title=null;
    private String image=null;
    private String target_smile_url=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smile);
        initSmile();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
             {    @Override
             public void onClick(View v) {
                 finish();
             }
             }
        );

        recycleview= (RecyclerView) findViewById(R.id.recycleview_smile_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getApplicationContext());
        recycleview.setLayoutManager(layoutManager);

    }

    private void initSmile() {
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                analyseHTML(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError s) {

            }
        });

        MyApplication.getRequsetquene().add(request);


    }



    private void analyseHTML(String s) {
        List<String> stringlist=new ArrayList<>();
        String data=s.replace("\n","");
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(data);
        while(matcher.find()){
            stringlist.add(matcher.group());
        }
        for (String itemstring:stringlist){

            Pattern imagePattern=Pattern.compile(image_regEx);
            Matcher imageMatcher=imagePattern.matcher(itemstring);
            while (imageMatcher.find()){
                image=imageMatcher.group();
            }
            Pattern urlPattern=Pattern.compile(url_regEx);
            Matcher urlMattern=urlPattern.matcher(itemstring);
            while (urlMattern.find()){
                target_smile_url=urlMattern.group();
            }
            Pattern titlePattern=Pattern.compile(title_regEx);
            Matcher titleMatcher=titlePattern.matcher(itemstring);
            while (titleMatcher.find()){
                title=titleMatcher.group();
            }
            Log.i("title",stringlist.size()+"");

            Smile smile=new Smile();
            smile.setSmile_imageurl(image);
            smile.setSmile_title(title);
            smile.setSmile_url(target_smile_url);
            list.add(smile);

        }
       sendlistData(list);

    }

    private void sendlistData(List<Smile> list) {

        SmileAdapter newsadapter=new SmileAdapter(list);
        recycleview.setAdapter(newsadapter);
        recycleview.addItemDecoration(new DecorationUtils(this,DecorationUtils.VERTICAL_LIST));
    }
}
