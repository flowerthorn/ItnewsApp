package com.items.code.ui.main.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.items.code.R;
import com.items.code.model.bean.data.HotNews;
import com.items.code.ui.main.activity.HotWebActivity;
import com.items.code.ui.main.fragment.MyApplication;

import java.util.List;

/**
 * Created by lihongxin on 2017/2/16.
 */

public class HotNewsAdapter extends RecyclerView.Adapter<HotNewsAdapter.ViewHolder> {
    private List<HotNews> hotNewsList;
    public HotNewsAdapter(List<HotNews> hotNewses){
        hotNewsList=hotNewses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hotnews,parent,false);//子项
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HotNews hotNews=hotNewsList.get(holder.getAdapterPosition());
                Intent intent=new Intent(v.getContext(),HotWebActivity.class);
                intent.putExtra("url",hotNews.getHotnews_url());
                intent.putExtra("title",hotNews.getHotnews_title());
                intent.putExtra("imageurl",hotNews.getHotnews_image());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HotNews hotNews=hotNewsList.get(position);
        holder.from.setText("来自:"+hotNews.getHotnew_from());
        holder.title.setText(hotNews.getHotnews_title());
        holder.time.setText(hotNews.getHotnews_time());
        Glide.with(MyApplication.getContext()).load(hotNews.getHotnews_image()).placeholder(R.drawable.hongxin).into(holder.imageView);
        }

    @Override
    public int getItemCount() {
        return hotNewsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        TextView from;
        TextView time;
        public ViewHolder(View itemView) {
        super(itemView);
        imageView= (ImageView) itemView.findViewById(R.id.image);
        time= (TextView) itemView.findViewById(R.id.time);
        title= (TextView) itemView.findViewById(R.id.title);
        from= (TextView) itemView.findViewById(R.id.from);
        }
    }

}
