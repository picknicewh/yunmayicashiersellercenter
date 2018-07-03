package com.yun.ma.yi.app.module.stock.statistic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yun.ma.yi.app.api.util.GlideUtils;
import com.yun.ma.yi.app.bean.StockStatisticInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/29
 * 名称：盘点记录适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RecordStatisticListAdapter extends RecyclerView.Adapter<RecordStatisticListAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<StockStatisticInfo> stockStatisticInfoList;
    private Context context;
    public RecordStatisticListAdapter(Context context, List<StockStatisticInfo> stockStatisticInfoList) {
        this.context = context;
      this.stockStatisticInfoList =stockStatisticInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistic_record_list, null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        StockStatisticInfo stockStatisticInfo = stockStatisticInfoList.get(position);
        GlideUtils.loadImageView(context, stockStatisticInfo.getImage_url(),holder.iv_image);
        holder.iv_name.setText(stockStatisticInfo.getTitle());
        holder.tv_code.setText(stockStatisticInfo.getBar_code());
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
        TextView tv_code;
        ImageView iv_image;
        TextView iv_name;
        ViewHolder(View itemView) {
            super(itemView);
            iv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_code = (TextView) itemView.findViewById(R.id.tv_code);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return stockStatisticInfoList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
