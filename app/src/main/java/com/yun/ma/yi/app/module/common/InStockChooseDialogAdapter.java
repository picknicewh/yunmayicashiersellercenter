package com.yun.ma.yi.app.module.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yunmayi.app.manage.R;

import java.util.List;


public class InStockChooseDialogAdapter extends RecyclerView.Adapter<InStockChooseDialogAdapter.ViewHolder> {
    private Context context;
    private  OnItemClickListener clickListener;
    private List<GoodsDetailInfo> goodsDetailInfoList;
    public InStockChooseDialogAdapter(Context context, List<GoodsDetailInfo> goodsDetailInfoList) {
        this.context = context;
        this.goodsDetailInfoList = goodsDetailInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_instock_choose, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_no.setText(String.valueOf(position+1));
        holder.tv_name.setText(goodsDetailInfoList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener!=null){
                    clickListener.onClick(v,position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return goodsDetailInfoList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_no;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_no = (TextView) itemView.findViewById(R.id.tv_no);
            itemView.setTag(this);
        }
    }
    /**
     * 设置item 事件监听
     * @param clickListener
     */
    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
