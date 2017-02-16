package com.items.code.ui.main.fragment;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.items.code.R;
import com.items.code.Utils.LogUtils;
import com.items.code.model.bean.data.HotNews;
import com.items.code.ui.main.adapter.HotNewsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HotFragment extends Fragment {
    private List<HotNews> hotnewsList=new ArrayList<>();
    private String originurl="http://chuansong.me/ideatech";
    private String title=null;
    private String from=null;
    private String image=null;
    private String time=null;
    private String newsurl=null;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hot,container,false);
        initHotNews();
        RecyclerView recycleview= (RecyclerView) view.findViewById(R.id.hot_recycleview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(MyApplication.getContext());
        recycleview.setLayoutManager(layoutManager);
        HotNewsAdapter hotnewsAdapter=new HotNewsAdapter(hotnewsList);
        recycleview.setAdapter(hotnewsAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initHotNews() {
        StringRequest request=new StringRequest(originurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                // Log.i("获取到的数据:",s);
                //对传入的源代码数据进行正则匹配
                logdata(s);
                //analyseHTML(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError s) {

            }
        });

        MyApplication.getRequsetquene().add(request);

    }

    private void logdata(String s) {
        //用来测试的
        List<String> stringlist=new ArrayList<>();
        List<String> imagelist=new ArrayList<>();
        //LogUtils.Logli("获取的网页html",s);
        //</span>  </div>  </div> </div>
        String data=s.replace("\n","");
        LogUtils.Logli("替换后的html",data);
        String regEx="<div class=\"pagedlist_item\" tabindex=\"-1\" >(.*?)</span></span></div></div></div>";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(data);
        while(matcher.find()){
            stringlist.add(matcher.group());

        }
        LogUtils.Logli("一共多少项",""+stringlist.size());//输出25
  /*      for(String str:stringlist){
            LogUtils.Logli("每项的具体内容",str);
        }
        LogUtils.Logli("一共有多少项:",stringlist.size()+"");*/
        String image_regEx="http://h.chuansong.me/.*jpg";
        Pattern imagePattern=Pattern.compile(image_regEx);
        Matcher imageMatcher=imagePattern.matcher(data);
        while (imageMatcher.find()){
            imagelist.add(imageMatcher.group());
        }
        LogUtils.Logli("image几个",""+imagelist.size());//输出1

    }


    private void analyseHTML(String s) {
       List<String> stringlist=new ArrayList<>();
        String data=s.replace("\n","");
        //正则处理得到list
        String title_regEx="";
        String image_regEx="(http://h.chuansong.me/).*?(.jpg|png|gif|jpeg)";
        String from_regEx="";
        String time_regEx="";
        String regEx="<div class=\"pagedlist_item\" tabindex=\"-1\" >(.*?)</span></span></div></div></div>";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(data);
        while(matcher.find()){
            stringlist.add(matcher.group());
        }

        for (String itemstring:stringlist){
            //通过正则得到from image time title
           Pattern imagePattern=Pattern.compile(image_regEx);
           Matcher imageMatcher=imagePattern.matcher(itemstring);
           image=matcher.group();


            //将处理后得到的四个数据赋值给新闻
            HotNews hotnew=new HotNews();
            hotnew.setHotnew_from(from);
            hotnew.setHotnews_image(image);
            hotnew.setHotnews_time(time);
            hotnew.setHotnews_title(title);
            hotnew.setHotnews_url(newsurl);
            hotnewsList.add(hotnew);
        }
    }
}

