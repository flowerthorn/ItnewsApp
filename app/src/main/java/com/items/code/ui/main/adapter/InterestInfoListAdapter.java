package com.items.code.ui.main.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.items.code.R;
import com.items.code.model.bean.data.InterestdataInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihongxin on 2016/12/11.
 */

public class InterestInfoListAdapter extends BaseAdapter {
    private Context c;
    private ArrayList<InterestdataInfo> lists=new ArrayList<>();
    private InterestdataInfo interestdataInfo;
    public InterestInfoListAdapter(Context context,InterestdataInfo interestdataInfo,ArrayList<InterestdataInfo> lists){
       this.c=context;
       this.lists=lists;
       this.interestdataInfo=interestdataInfo;
    }
   public int getCount() {

       return interestdataInfo.getUrllist().size();

   }
    //获得相应数据集合中特定位置的数据项
    //getItem()和getItemId()则在需要处理和取得Adapter中的数据时调用。
   public Object getItem(int position) {

       //return position;lists.positon
      //return interestdataInfo.getUrllist().get(position);
       return null;
   }
    //它返回的是该postion对应item的id
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        Log.i("Second getItem", "" + interestdataInfo.getUrllist().get(position));
        Log.i("Second count", "" + interestdataInfo.getUrllist().size());
        Log.i("Second position", "" + position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(c, R.layout.interest_itemlist, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textview);
            convertView.setTag(viewHolder);
        }
        else {

            viewHolder= (ViewHolder) convertView.getTag();
        }
        Glide.with(c).load(interestdataInfo.getSrclist().get(position)).placeholder(R.drawable.hongxin).into(viewHolder.imageView);
        viewHolder.textView.setText(interestdataInfo.getTitlelist().get(position));
        return convertView;
    }
    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }

}
