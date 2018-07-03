package com.yun.ma.yi.app.module.goods.list;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.ShopCatInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： wh
 * 时间：  2017/6/27
 * 名称:商品分类适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodCateListAdapter extends RecyclerView.Adapter<GoodCateListAdapter.ViewHolder>{

    private List<GoodsListInfo> goodsInfoList;
    private List<ShopCatInfo> shopCatInfoList;
    private Activity context;
    private Map<Integer,Boolean>  selectedList;
    private OnItemClickListener onItemClickListener;
    public GoodCateListAdapter(List<GoodsListInfo> goodsInfoList, Activity context){
        this.goodsInfoList = goodsInfoList;
        this.context = context;
        selectedList = new HashMap<>();
    }
    public GoodCateListAdapter(Activity context, List<ShopCatInfo> shopCatInfoList){
        this.shopCatInfoList = shopCatInfoList;
        this.context = context;
        selectedList = new HashMap<>();
    }
    public void initData(int position){
        for (int i=0;i<getItemCount();i++){
            if (i==position){
                selectedList.put(i,true);
            }else {
                selectedList.put(i,false);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (goodsInfoList!=null){
            GoodsListInfo info = goodsInfoList.get(position);
            holder.tv_sort_count.setText(String.valueOf(info.getCount()));
            holder.tv_name.setText(info.getCategory_name());
        }else if (shopCatInfoList!=null){
            ShopCatInfo info = shopCatInfoList.get(position);
            holder.tv_sort_count.setText(String.valueOf(info.getCount()));
            holder.tv_name.setText(info.getCategory_title());
        }
        if (selectedList.get(position)){
            holder.tv_name.setTextColor(ContextCompat.getColor(context,R.color.red_btn));
            holder.tv_sort_count.setTextColor(ContextCompat.getColor(context,R.color.red_btn));
        }else {
            holder.tv_name.setTextColor(ContextCompat.getColor(context,R.color.goods_text_color));
            holder.tv_sort_count.setTextColor(ContextCompat.getColor(context,R.color.goods_text_color));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onClick(v,position);
                }
                initData(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        int size = 0;
        if (goodsInfoList!=null){
            size = goodsInfoList.size();
        }else if (shopCatInfoList!=null){
            size = shopCatInfoList.size();
        }
        return size;
    }
    class  ViewHolder extends RecyclerView.ViewHolder {
    TextView tv_sort_count;
    TextView tv_name;

    ViewHolder(View itemView) {
        super(itemView);
        G.initDisplaySize(context);
        tv_sort_count = (TextView) itemView.findViewById(R.id.tv_sort_count);
        tv_name = (TextView) itemView.findViewById(R.id.tv_sort);
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
