package com.items.code.ui.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import com.items.code.R;
import com.items.code.model.bean.data.Info;
import com.items.code.model.bean.data.RESULT;
import com.items.code.model.bean.data.dataInfo;
import com.items.code.ui.main.activity.ItemWebActivity;
import com.items.code.ui.main.adapter.InfoListAdapter;

import java.io.Serializable;
import java.util.ArrayList;

public class LastestFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ArrayList<dataInfo> dataInfoslist;
    private ListView listView;
    private InfoListAdapter adapter;
    private Context context;
   // RequestQueue mQueue;
    private String dataurl="http://v.juhe.cn/toutiao/index?key=02524fb2d1344a1c5abf95d513169d44&type=keji";


    public LastestFragment()
    {
         super();
     }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_lastest, container, false);
        listView= (ListView) view.findViewById(R.id.listview);
        //mQueue= Volley.newRequestQueue(getActivity());
        getjsondata();
        return view;
    }

    private void getjsondata() {
        StringRequest request=new StringRequest(dataurl, new Response.Listener<String>() {
        @Override
        public void onResponse(String s) {
            Log.i("获取到的数据:",s);
            //对传入的json数据进行解析
            dealjsonData(s);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError s) {

        }
    });

      MyApplication.getRequsetquene().add(request);




    }

    private void dealjsonData(String s) {
        //解析出来生成一个集合
        Gson gson=new Gson();
        Info info=gson.fromJson(s,Info.class);
        RESULT result=info.getResult();
        dataInfoslist =result.getData();
        adapter=new InfoListAdapter(getActivity(), dataInfoslist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        dataInfo dataInfo= dataInfoslist.get(position);
        Intent intent=new Intent(getActivity(),ItemWebActivity.class);
        intent.putExtra("obj",dataInfo);
        startActivity(intent);
    }
}
