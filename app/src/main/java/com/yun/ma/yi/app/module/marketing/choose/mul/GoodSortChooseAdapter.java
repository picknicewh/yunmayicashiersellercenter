package com.yun.ma.yi.app.module.marketing.choose.mul;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： wh
 * 时间：  2017/6/27
 * 名称：分类选择
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodSortChooseAdapter extends RecyclerView.Adapter<GoodSortChooseAdapter.ViewHolder>{

    private List<GoodsListInfo> goodsInfoList;
    private Activity context;
    private Map<Integer,Boolean>  selectedList;
    private OnItemClickListener onItemClickListener;
    public int currentPosition=0;
    public GoodSortChooseAdapter(List<GoodsListInfo> goodsInfoList, Activity context){
        this.goodsInfoList = goodsInfoList;
        this.context = context;
    }
    public void initData(boolean check){
        selectedList = new HashMap<>();
        for (int i=0;i<goodsInfoList.size();i++){
            selectedList.put(i,check);
        }
        notifyDataSetChanged();
    }
    public void initData(int position,boolean isCheck){
        selectedList.put(position,isCheck);
        notifyDataSetChanged();
    }
    public Map<Integer,Boolean> getSelectedList(){
        return selectedList;
    }

    public void initData(Map<Integer,Boolean> selectedList){
        this.selectedList = new HashMap<>();
        for (int i=0;i<selectedList.size();i++){
            this.selectedList.put(i,selectedList.get(i));
        }
        notifyDataSetChanged();
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (goodsInfoList.size()>0){
            GoodsListInfo info = goodsInfoList.get(position);
            holder.tv_sort_count.setText(String.valueOf(info.getCount()));
            holder.tv_name.setText(info.getCategory_name());
                if (selectedList.get(position)){
                    if (position==currentPosition){
                        holder.tv_name.setTextColor(ContextCompat.getColor(context,R.color.goods_add_save_color));
                        holder.tv_sort_count.setTextColor(ContextCompat.getColor(context,R.color.goods_add_save_color));
                    }else {
                        holder.tv_name.setTextColor(ContextCompat.getColor(context,R.color.red_btn));
                        holder.tv_sort_count.setTextColor(ContextCompat.getColor(context,R.color.red_btn));
                    }
                }else {
                    if (position==currentPosition){
                        holder.tv_name.setTextColor(ContextCompat.getColor(context,R.color.dark_green));
                        holder.tv_sort_count.setTextColor(ContextCompat.getColor(context,R.color.dark_green));
                    }else {
                        holder.tv_name.setTextColor(ContextCompat.getColor(context,R.color.goods_text_color));
                        holder.tv_sort_count.setTextColor(ContextCompat.getColor(context,R.color.goods_text_color));
                    }

            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null){
                        onItemClickListener.onClick(v,position);
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return goodsInfoList.size();
    }
    class  ViewHolder extends RecyclerView.ViewHolder {
    TextView tv_sort_count;
    TextView tv_name;

    ViewHolder(View itemView) {
        super(itemView);
        tv_sort_count = (TextView) itemView.findViewById(R.id.tv_sort_count);
        tv_name = (TextView) itemView.findViewById(R.id.tv_sort);
        G.initDisplaySize(context);
        int width   =G.size.W/4;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(lp);
        itemView.setTag(this);
    }
  }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
