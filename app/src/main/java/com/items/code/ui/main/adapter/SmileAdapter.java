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
import com.items.code.model.bean.data.Smile;
import com.items.code.ui.main.activity.HotWebActivity;
import com.items.code.ui.main.activity.SmileWebActivity;
import com.items.code.ui.main.fragment.MyApplication;

import java.util.List;

/**
 * Created by lihongxin on 2017-02-24.
 */

public class SmileAdapter extends RecyclerView.Adapter<SmileAdapter.ViewHolder> {
   private List<Smile> list;
    public SmileAdapter(List<Smile> listt){
        list=listt;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.image);
            title= (TextView) itemView.findViewById(R.id.title);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_smile,parent,false);//子项
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Smile smile=list.get(holder.getAdapterPosition());
            Intent intent=new Intent(v.getContext(),SmileWebActivity.class);
                intent.putExtra("url",smile.getSmile_url());
                intent.putExtra("title",smile.getSmile_title());
                intent.putExtra("imageurl",smile.getSmile_imageurl());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Smile smiledata=list.get(position);
        holder.title.setText(smiledata.getSmile_title());
        Glide.with(MyApplication.getContext()).load(smiledata.getSmile_imageurl()).placeholder(R.drawable.hongxin).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
