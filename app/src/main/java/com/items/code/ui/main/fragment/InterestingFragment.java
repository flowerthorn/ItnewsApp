package com.items.code.ui.main.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.items.code.R;
import com.items.code.model.bean.data.InterestdataInfo;
import com.items.code.ui.main.activity.InterestWebActivity;
import com.items.code.ui.main.adapter.InterestInfoListAdapter;
import com.items.code.ui.main.adapter.MainFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InterestingFragment extends Fragment implements AdapterView.OnItemClickListener{
    private ListView listView;
    private InterestInfoListAdapter adapter;
    private ArrayList<InterestdataInfo> dataInfolist=new ArrayList<>();
    private InterestdataInfo interInfo=new InterestdataInfo();
    private List<String> srcList;
    private List<String> urlList;
    private List<String> titleList;
    private String url="http://www.myexception.cn/other/";
    private Toolbar toolbar;
    public InterestingFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_interesting, container, false);
        listView= (ListView) view.findViewById(R.id.interest_listview);
        //得到网页源代码
        getHTML();
        return view;
    }

    private void getHTML() {
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
               // Log.i("获取到的数据:",s);
                //对传入的源代码数据进行正则匹配
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
        //定义一个存放<dd></dd> 的stringlist 57个
        List<String> stringlist=new ArrayList<>();
        String regEx="<dd>(.*?)</dd>";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(s);
        while(matcher.find()){
            stringlist.add(matcher.group());
        }
        //得到stringlist后进行二次正则//分析<dd></dd>得到单独的String // 提取数据
        interInfo.setTitlelist(gettitleList(stringlist));
        interInfo.setSrclist(getsrcList(stringlist));
        interInfo.setUrllist(geturlList(stringlist));
        dataInfolist.add(interInfo);
        adapter=new InterestInfoListAdapter(getActivity(),interInfo,dataInfolist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private List<String> getsrcList(List<String> stringlist) {
        srcList = new ArrayList<>();
        for (String data:stringlist){
            String regEX="(/u/cms/www/).*?(.jpg|png|gif|jpeg)";//图片
            Pattern pattern=Pattern.compile(regEX);
            Matcher matcher=pattern.matcher(data);
            while(matcher.find()){
                Log.i("Src:","http://www.myexception.cn/"+matcher.group());
                srcList.add("http://www.myexception.cn/"+matcher.group());
            }
        }
        return srcList;
    }

    private List<String> gettitleList(List<String> stringlist) {
        titleList=new ArrayList<>();
        for (String data:stringlist){
            String regEX="(?<=alt=\").*?(?=\"></a>)";//标题
            Pattern pattern=Pattern.compile(regEX);
            Matcher matcher=pattern.matcher(data);
            while(matcher.find()){
                titleList.add(matcher.group());
                Log.i("Title:",matcher.group());
            }

        }
        return titleList;
    }

    private List<String> geturlList(List<String> stringlist) {
        urlList=new ArrayList<>();
        //title src url
        for(String data:stringlist){
            String regEX="(/other/).*?(.html)";//url
            Pattern pattern=Pattern.compile(regEX);
            Matcher matcher=pattern.matcher(data);
            while(matcher.find()){
                Log.i("Url:","http://www.myexception.cn/"+matcher.group());
                urlList.add("http://www.myexception.cn/"+matcher.group());
            }

        }
        return  urlList;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String url = interInfo.getUrllist().get(position);
        String title=interInfo.getTitlelist().get(position);
        String imageurl=interInfo.getSrclist().get(position);
        Intent intent=new Intent(getActivity(),InterestWebActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        intent.putExtra("imageurl",imageurl);
        startActivity(intent);
    }

}
