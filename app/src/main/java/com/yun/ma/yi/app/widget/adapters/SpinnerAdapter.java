package com.yun.ma.yi.app.widget.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yunmayi.app.manage.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： wh
 * 时间：  2017/11/22
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class SpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<String> dataList;
    private Map<Integer,Boolean> isSelectList;
    public SpinnerAdapter(Context context, List<String> dataList){
        this.context = context;
        this.dataList = dataList;
        isSelectList = new HashMap<>();
    }

    public void initData(int position){
        for (int i = 0 ;i<dataList.size();i++){
            if (i ==position){
                isSelectList.put(i,true);
            }else {
                isSelectList.put(i,false);
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView =LayoutInflater.from(context).inflate(R.layout.spiner_item,null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String item =dataList.get(position);
        if (isSelectList.get(position)){
            viewHolder.mTextView.setTextColor(ContextCompat.getColor(context,R.color.red_btn));
        }else {
            viewHolder.mTextView.setTextColor(ContextCompat.getColor(context,R.color.hit_color));
        }
        viewHolder.mTextView.setText(item);
        return convertView;
    }
    public static class ViewHolder {
        public TextView mTextView;
    }
}
