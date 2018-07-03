package com.yun.ma.yi.app.module.marketing.bargain;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.BargainGoodsInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/29
 * 名称：余额明细/积分明细适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class BargainGoodsAdapter extends RecyclerView.Adapter<BargainGoodsAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
      private List<BargainGoodsInfo> fullCutInfoList;
    public BargainGoodsAdapter(List<BargainGoodsInfo> fullCutInfoList){
       this.fullCutInfoList = fullCutInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bargain_goods_list, null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        holder.tv_bargain.setText(fullCutInfoList.get(position).getName());
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
        TextView tv_bargain;
        ViewHolder(View itemView) {
            super(itemView);
            tv_bargain = (TextView) itemView.findViewById(R.id.tv_bargain);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return fullCutInfoList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
