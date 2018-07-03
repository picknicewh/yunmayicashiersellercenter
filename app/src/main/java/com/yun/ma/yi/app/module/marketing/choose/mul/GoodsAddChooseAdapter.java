package com.yun.ma.yi.app.module.marketing.choose.mul;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/30
 * 名称：商品选择列表适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsAddChooseAdapter extends RecyclerView.Adapter<GoodsAddChooseAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<GoodsDetailInfo> goodsDetailInfoList;
    public GoodsAddChooseAdapter(List<GoodsDetailInfo> goodsDetailInfoList){
          this.goodsDetailInfoList = goodsDetailInfoList;
    }
    public String getGoodsIds(boolean check){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;i<goodsDetailInfoList.size();i++){
            if (goodsDetailInfoList.get(i).isCheck()==check){
             GoodsDetailInfo goodsDetailInfo = goodsDetailInfoList.get(i);
                String goodsId = goodsDetailInfo.getId();
                stringBuffer.append(goodsId).append(",");
            }
        }
        if (stringBuffer.length()>0){
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
        }
        return stringBuffer.toString();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_add_choose2, null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final GoodsDetailInfo goodsDetailInfo = goodsDetailInfoList.get(position);
        holder.tvCode.setText(goodsDetailInfo.getBar_code());
        holder.tvName.setText(goodsDetailInfo.getTitle());
        holder.tvCheck.setBackgroundResource(goodsDetailInfo.isCheck()?R.mipmap.ic_goods_checked:R.mipmap.ic_goods_unchecked);
        holder.tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = goodsDetailInfo.isCheck()?false:true;
                if (onCheckChangeListener!=null){
                    onCheckChangeListener.onCheck(position,isChecked);
                }
            }
        });

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
        TextView tvName;
        TextView tvCode;
        TextView tvCheck;
        ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code);
            tvCheck = (TextView) itemView.findViewById(R.id.tv_check) ;
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return goodsDetailInfoList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    private OnCheckChangeListener onCheckChangeListener;

    public void setOnCheckChangeListener(OnCheckChangeListener onCheckChangeListener) {
        this.onCheckChangeListener = onCheckChangeListener;
    }

    public interface  OnCheckChangeListener{
        void onCheck(int position,boolean isCheck);
    }
}
