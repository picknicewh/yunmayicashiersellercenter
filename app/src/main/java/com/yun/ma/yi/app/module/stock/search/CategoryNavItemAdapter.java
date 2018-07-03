package com.yun.ma.yi.app.module.stock.search;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by ys on 2017/7/4.
 * 侧滑分类
 */

public class CategoryNavItemAdapter extends RecyclerView.Adapter<CategoryNavItemAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private Map<Integer,Boolean> selectList;
    private List<GoodsListInfo> data;
    private Context context;
    public CategoryNavItemAdapter( Context context,List<GoodsListInfo> data) {
        this.data  =  data;
        this.context = context;
    }
   public void initData(int position){
       selectList = new HashMap<>();
       if (data!=null){
           for (int i = 0 ; i <data.size();i++){
               if (i==position){
                   selectList.put(i,true);
               }else {
                   selectList.put(i,false);
               }
           }
       }
       notifyDataSetChanged();
   }
    @Override
    public CategoryNavItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryNavItemAdapter.ViewHolder holder, final int position) {
        GoodsListInfo categoryInfo = data.get(position);
        if (G.isEmteny(categoryInfo.getCategory_name())) holder.category_name.setText("未知分类");
        else holder.category_name.setText(categoryInfo.getCategory_name());
        if (selectList.get(position)){
            holder.category_name.setTextColor(ContextCompat.getColor(context,R.color.red_btn));
        }else {
            holder.category_name.setTextColor(ContextCompat.getColor(context,R.color.main_text_color));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class  ViewHolder  extends RecyclerView.ViewHolder{
          TextView category_name ;
        ViewHolder(View itemView) {
            super(itemView);
            category_name = (TextView)itemView.findViewById(R.id.category_name);
            itemView.setTag(this);
        }
    }
}