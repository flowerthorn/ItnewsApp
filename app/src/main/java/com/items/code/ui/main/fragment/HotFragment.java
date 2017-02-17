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
    private String url_regEx="(?<=<a class=\"question_link\" href=\").*?(?=\" target)";
    private String title_regEx="(?<=target=\"_blank\">).*?(?=</a></span>)";
    //private String image_regEx="http://.*?.jpg";
    private String image_regEx="(http://h.chuansong.me/).*?(.jpg)";
    private String regEx="<div class=\"pagedlist_item\" tabindex=\"-1\" >(.*?)</span></span></div></div></div>";
    private String time_regEx="(?<=<span class=\"timestamp\"><span>).*?(?=</span>)";
    private String from_regEx="(?<=alt=\").*?(?=\" height)";
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hot,container,false);
        initHotNews();
        RecyclerView recycleview= (RecyclerView) view.findViewById(R.id.hot_recycleview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recycleview.setLayoutManager(layoutManager);
        HotNewsAdapter hotnewsAdapter=new HotNewsAdapter(hotnewsList);
        recycleview.setAdapter(hotnewsAdapter);
        return view;
    }

    private void initHotNews() {
        StringRequest request=new StringRequest(originurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                // Log.i("获取到的数据:",s);
                //对传入的源代码数据进行正则匹配
                //logdata(s);
                analyseHTML(s);
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
        "http://h.chuansong.me/freesvc.jpg"
        LogUtils.Logli("一共有多少项:",stringlist.size()+"");*/
        //String image_regEx="http://.*?.jpg";
//        Pattern imagePattern=Pattern.compile(image_regEx);
//        Matcher imageMatcher=imagePattern.matcher(data);
//        while (imageMatcher.find()){
//            imagelist.add(imageMatcher.group());
//        }
//        LogUtils.Logli("image几个",""+imagelist.size());//输出1
        for (String str:stringlist){
         Pattern imagePattern=Pattern.compile(image_regEx);
          Matcher imageMatcher=imagePattern.matcher(str);
            while (imageMatcher.find()){
                image=imageMatcher.group(0);
            }
            LogUtils.Logli("image每项具体内容：",image);
          /*  /* Pattern timePattern=Pattern.compile(time_regEx);
            Matcher timeMatcher=timePattern.matcher(str);
            while (timeMatcher.find()){
                time=timeMatcher.group();
            }
            LogUtils.Logli("time每项具体内容：",time);*/
         /*   Pattern fromPattern=Pattern.compile(from_regEx);
            Matcher fromMatcher=fromPattern.matcher(str);
            while (fromMatcher.find()){
                from=fromMatcher.group();
            }*/
/*            Pattern titlePattern=Pattern.compile(title_regEx);
            Matcher titleMatcher=titlePattern.matcher(str);
            while (titleMatcher.find()){
                title=titleMatcher.group();
            }
            LogUtils.Logli("title每项具体内容：",title);*/
/*        Pattern urlPattern=Pattern.compile(url_regEx);
            Matcher urlMattern=urlPattern.matcher(str);
            while (urlMattern.find()){
                newsurl="http://chuansong.me/"+urlMattern.group();
            }
            LogUtils.Logli("newsurl每项具体内容：",newsurl);
*/
        }



    }


    private void analyseHTML(String s) {
       List<String> stringlist=new ArrayList<>();
        String data=s.replace("\n","");
        //正则处理得到list
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(data);
        while(matcher.find()){
            stringlist.add(matcher.group());
        }

        for (String itemstring:stringlist){
            //通过正则得到from image time title
           Pattern imagePattern=Pattern.compile(image_regEx);
           Matcher imageMatcher=imagePattern.matcher(itemstring);
            while (imageMatcher.find()){
                image=imageMatcher.group(0);
            }
            Pattern timePattern=Pattern.compile(time_regEx);
            Matcher timeMatcher=timePattern.matcher(itemstring);
            while (timeMatcher.find()){
                time=timeMatcher.group();
            }
            Pattern fromPattern=Pattern.compile(from_regEx);
            Matcher fromMatcher=fromPattern.matcher(itemstring);
            while (fromMatcher.find()){
                from=fromMatcher.group();
            }
            Pattern urlPattern=Pattern.compile(url_regEx);
            Matcher urlMattern=urlPattern.matcher(itemstring);
            while (urlMattern.find()){
                newsurl="http://chuansong.me"+urlMattern.group();
            }
            Pattern titlePattern=Pattern.compile(title_regEx);
            Matcher titleMatcher=titlePattern.matcher(itemstring);
            while (titleMatcher.find()){
                title=titleMatcher.group();
            }

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

