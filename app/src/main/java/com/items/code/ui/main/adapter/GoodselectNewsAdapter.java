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
import com.items.code.model.bean.data.GoodselectNews;
import com.items.code.ui.main.activity.GoodselectWebActivity;
import com.items.code.ui.main.activity.HotWebActivity;
import com.items.code.ui.main.fragment.MyApplication;

import java.util.List;

/**
 * Created by lihongxin on 2017-02-24.
 */

public class GoodselectNewsAdapter  extends RecyclerView.Adapter<GoodselectNewsAdapter.ViewHolder> {
    private List<GoodselectNews> list;
    public  GoodselectNewsAdapter(List<GoodselectNews> newslist){
        list=newslist;

    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        TextView time;
        TextView from;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.image);
            time= (TextView) itemView.findViewById(R.id.time);
            title= (TextView) itemView.findViewById(R.id.title);
            from= (TextView) itemView.findViewById(R.id.from);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goodselectnews,parent,false);//子项
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodselectNews goodselectNews=list.get(holder.getAdapterPosition());
                Intent intent=new Intent(v.getContext(),HotWebActivity.class);
                intent.putExtra("url",goodselectNews.getGoodselectnews_url());
                intent.putExtra("title",goodselectNews.getGoodselectnews_title());
                intent.putExtra("imageurl",goodselectNews.getGoodselectnews_imageurl());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoodselectNews goodselectNews=list.get(position);
        holder.time.setText(goodselectNews.getGoodselectnews_time());
        holder.from.setText("来自:"+goodselectNews.getGoodselectnews_from());
        holder.title.setText(goodselectNews.getGoodselectnews_title());
        Glide.with(MyApplication.getContext()).load(goodselectNews.getGoodselectnews_imageurl()).placeholder(R.drawable.hongxin).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
