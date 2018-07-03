package com.yun.ma.yi.app.module.marketing.choose.single;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yun.ma.yi.app.api.util.GlideUtils;
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
public class SingleGoodsEditChooseAdapter extends RecyclerView.Adapter<SingleGoodsEditChooseAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<GoodsDetailInfo> goodsDetailInfoList;
    private Context context;
    public SingleGoodsEditChooseAdapter(List<GoodsDetailInfo>goodsDetailInfoList, Context context){
      this.goodsDetailInfoList = goodsDetailInfoList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_edit_choose, null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final GoodsDetailInfo goodsDetailInfo = goodsDetailInfoList.get(position);
        GlideUtils.loadImageView(context,goodsDetailInfo.getImage_url(),holder.ivGoodsImage);
        holder.tvName.setText(goodsDetailInfo.getTitle());
        holder.tvCode.setText(goodsDetailInfo.getBar_code());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onClick(v,position);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGoodsImage;
        TextView tvName;
        TextView tvCode;
        ImageView ivDelete;
        ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code);
            ivDelete = (ImageView) itemView.findViewById(R.id.iv_delete) ;
            ivGoodsImage = (ImageView) itemView.findViewById(R.id.iv_goods_image) ;
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
}
