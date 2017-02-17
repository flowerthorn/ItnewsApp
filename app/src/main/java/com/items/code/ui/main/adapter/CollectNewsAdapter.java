package com.items.code.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.items.code.R;
import com.items.code.model.bean.data.LiteTable.CollectNews;
import com.items.code.ui.main.activity.HotWebActivity;
import com.items.code.ui.main.activity.InterestWebActivity;
import com.items.code.ui.main.activity.LastestWebActivity;
import com.items.code.ui.main.fragment.MyApplication;

import java.util.List;

/**
 * Created by lihongxin on 2017/2/15.
 */

public class CollectNewsAdapter extends RecyclerView.Adapter<CollectNewsAdapter.ViewHolder> {
    private List<CollectNews> collectNewsList;
    private  String flag=null;
    private Context context;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_collect,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectNews collectNews=collectNewsList.get(holder.getAdapterPosition());
                String where=whereNews(collectNews.getNews_url());
                if (where=="interest"){
                    Intent intent=new Intent(view.getContext(),InterestWebActivity.class);
                    intent.putExtra("url",collectNews.getNews_url());
                    intent.putExtra("title",collectNews.getNews_title());
                    intent.putExtra("imageurl",collectNews.getNews_image());
                    view.getContext().startActivity(intent);
                }
                else if (where=="lastest"){
                    Intent intent=new Intent(view.getContext(),LastestWebActivity.class);
                    intent.putExtra("url",collectNews.getNews_url());
                    intent.putExtra("title",collectNews.getNews_title());
                    intent.putExtra("imageurl",collectNews.getNews_image());
                    view.getContext().startActivity(intent);
                }
                else if (where=="hot"){
                    Intent intent=new Intent(view.getContext(),HotWebActivity.class);
                    intent.putExtra("url",collectNews.getNews_url());
                    intent.putExtra("title",collectNews.getNews_title());
                    intent.putExtra("imageurl",collectNews.getNews_image());
                    view.getContext().startActivity(intent);
                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CollectNews collectNews=collectNewsList.get(position);
        holder.newsTitle.setText(collectNews.getNews_title());
        Glide.with(MyApplication.getContext()).load(collectNews.getNews_image()).placeholder(R.drawable.hongxin).into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return collectNewsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView newsImage;
        TextView newsTitle;
        public ViewHolder(View view){
            super(view);
            newsImage= (ImageView) view.findViewById(R.id.image);
            newsTitle= (TextView) view.findViewById(R.id.title);
        }
    }
    public CollectNewsAdapter(List<CollectNews> collectNewslist){
        collectNewsList=collectNewslist;
    }
    //判断url来自哪个fragment,决定打开的方式
    public String whereNews(String url){

        Boolean contain=true;
        if (url.contains("http://www.myexception.cn")){
           //contain=true;
            flag="interest";
        }
        else if (url.contains("http://mini.eastday.com/mobile"))
        {
            flag="lastest";
        }
        else if (url.contains("http://chuansong.me")){
            flag="hot";
        }

        return flag;
    }
}
