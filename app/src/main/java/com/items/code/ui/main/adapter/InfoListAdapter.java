package com.items.code.ui.main.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.items.code.R;
import com.items.code.model.bean.data.dataInfo;


import java.util.ArrayList;

/**
 * Created by lihongxin on 2016/11/30.
 */

public class InfoListAdapter extends BaseAdapter{
    //传入实体对象
    private Context c;
    private ArrayList<dataInfo> list;
    public InfoListAdapter(Context context, ArrayList<dataInfo> infos){
        this.c=context;
        this.list=infos;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //Log.i("First positon",""+position);
        //Log.i("First count",""+list.size());
        //Log.i("First getItem",""+list.get(position));
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(c).inflate(R.layout.lastest_itemlist,null);

            holder.imageview= (ImageView) convertView.findViewById(R.id.image);
            holder.tv_authorname= (TextView) convertView.findViewById(R.id.tv_authorname);
            holder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_date=(TextView)convertView.findViewById(R.id.tv_date);
            convertView.setTag(holder);

        }else {

            holder= (ViewHolder) convertView.getTag();
        }

        dataInfo data=list.get(position);

        //图片
        Glide.with(c).load(data.getThumbnail_pic_s()).placeholder(R.drawable.hongxin).into(holder.imageview);
        //标题
        holder.tv_title.setText(data.getTitle());
        //时间
        holder.tv_date.setText(data.getDate());
        //来源
        holder.tv_authorname.setText("来自："+data.getAuthor_name());

        return convertView;
    }



    class ViewHolder{
        TextView tv_title;
        TextView tv_authorname;
        TextView tv_date;
        ImageView imageview;
    }
}