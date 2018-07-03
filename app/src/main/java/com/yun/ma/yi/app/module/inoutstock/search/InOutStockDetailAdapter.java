package com.yun.ma.yi.app.module.inoutstock.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yun.ma.yi.app.api.Config;
import com.yun.ma.yi.app.api.util.GlideUtils;
import com.yun.ma.yi.app.bean.InOutSearchInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/29
 * 名称：出库详情适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InOutStockDetailAdapter extends RecyclerView.Adapter<InOutStockDetailAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<InOutSearchInfo.InOutSearchInfoDetail> infoDetailList;
    private Context context;
    public InOutStockDetailAdapter(Context context,List<InOutSearchInfo.InOutSearchInfoDetail> infoDetailList) {
        this.infoDetailList = infoDetailList;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inout_stock_list, null);
        return new ViewHolder(view);
    }
    @Override
      public void onBindViewHolder(final ViewHolder holder,final int position) {
        InOutSearchInfo.InOutSearchInfoDetail searchInfoDetail = infoDetailList.get(position);
        holder.tv_goods_name.setText(searchInfoDetail.getTile());
        holder.tv_order_count.setText("数量："+searchInfoDetail.getChange_total());
        GlideUtils.loadImageView(context, searchInfoDetail.getImage_url(),holder.iv_goods_image);
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (onItemClickListener!=null){
                     onItemClickListener.onClick(v,position);
                 }
             }
         });
       }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_goods_name;
        TextView tv_order_count;
        ImageView iv_goods_image;
        ViewHolder(View itemView) {
            super(itemView);
            tv_goods_name = (TextView) itemView.findViewById(R.id.tv_goods_name);
            tv_order_count = (TextView) itemView.findViewById(R.id.tv_order_count);
            iv_goods_image= (ImageView) itemView.findViewById(R.id.iv_goods_image);
            itemView.setTag(this);
        }
    }
    @Override
    public int getItemCount() {
        return infoDetailList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
