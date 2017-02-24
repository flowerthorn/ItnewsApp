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
import com.items.code.Utils.DecorationUtils;
import com.items.code.Utils.LogUtils;
import com.items.code.model.bean.data.GoodselectNews;
import com.items.code.ui.main.adapter.GoodselectNewsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GoodselectFragment extends Fragment {
    private RecyclerView recycleview;
    private List<GoodselectNews> newslist=new ArrayList<>();
    private GoodselectNewsAdapter adapter;
    private String title=null;
    private String image=null;
    private String time=null;
    private String newsurl=null;
    private String from=null;
    private String originurl="http://chuansong.me/";
    private String regEx="<div class=\"pagedlist_item\" tabindex=\"-1\" >(.*?)</span></span></div></div></div>";
    private String url_regEx="(?<=<a class=\"question_link\" href=\").*?(?=\" target)";
    private String title_regEx="(?<=target=\"_blank\">).*?(?=</a></span>)";
    //private String image_regEx="http://.*?.jpg";
    private String image_regEx="(http://h.chuansong.me/).*?(.jpg)";
    private String time_regEx="(?<=<span class=\"timestamp\"><span>).*?(?=</span>)";
    private String from_regEx="(?<=alt=\").*?(?=\" height)";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_goodselect, container, false);
        initGoodselectNews();
        recycleview= (RecyclerView) view.findViewById(R.id.goodselect_recycleview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recycleview.setLayoutManager(layoutManager);
        return view;
    }

    private void initGoodselectNews() {
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

    private void analyseHTML(String s) {
        List<String> stringlist=new ArrayList<>();
        String data=s.replace("\n","");
        //LogUtils.Logli("goodnewshtml:",data);
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
            GoodselectNews goodselectnew=new GoodselectNews();
            goodselectnew.setGoodselectnews_from(from);
            goodselectnew.setGoodselectnews_imageurl(image);
            goodselectnew.setGoodselectnews_time(time);
            goodselectnew.setGoodselectnews_title(title);
            goodselectnew.setGoodselectnews_url(newsurl);
            newslist.add(goodselectnew);
        }
        sendListData(newslist);

    }

    private void sendListData(List<GoodselectNews> newslist) {
        adapter=new GoodselectNewsAdapter(newslist);
        recycleview.setAdapter(adapter);
        recycleview.addItemDecoration(new DecorationUtils(getContext(),DecorationUtils.VERTICAL_LIST));
    }

}
