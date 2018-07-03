package com.yun.ma.yi.app.module.goods.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yun.ma.yi.app.api.util.GlideUtils;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.ShopGoodsInfoDetail;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/24
 * 名称：搜索适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
     private  enum  TYPE{
        TYPE_TITLE,
        TYPE_CONTENT
     }
     private List<GoodsDetailInfo> goodsInfoList;
     private List<ShopGoodsInfoDetail> shopGoodsInfoDetailList;
     private Context context;
     private int totleCount=0;
    private OnItemClickListener onItemClickListener;
     public GoodsSearchAdapter(List<GoodsDetailInfo> goodsInfoList, Context context){
         this.goodsInfoList = goodsInfoList;
         this.context = context;
     }
     public GoodsSearchAdapter(Context context,List<ShopGoodsInfoDetail> shopGoodsInfoDetailList){
         this.shopGoodsInfoDetailList = shopGoodsInfoDetailList;
         this.context =context;
     }
    @Override
    public int getItemViewType(int position) {
        if (position==0)return TYPE.TYPE_TITLE.ordinal();
        return TYPE.TYPE_CONTENT.ordinal();
    }
    public void setTotleCount(int totleCount){
        this.totleCount = totleCount;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE.TYPE_TITLE.ordinal()){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_seach_title,null);
            return new TitleViewHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_child_list,null);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
       if (position==0){
           TitleViewHolder viewHolder = (TitleViewHolder) holder;
           viewHolder.tv_search_count.setText(String.valueOf(totleCount));
       }else {
           ViewHolder viewHolder = (ViewHolder) holder;
           if(goodsInfoList!=null){
               GoodsDetailInfo info = goodsInfoList.get(position-1);
               viewHolder.tv_code.setText(info.getBar_code());
               viewHolder.iv_name.setText(info.getTitle());
               viewHolder.tv_price.setText("￥"+ PriceTransfer.chageMoneyToString(info.getPrice()));
               GlideUtils.loadImageView(context,info.getImage_url(),viewHolder.iv_image);
               viewHolder.iv_off_shelf.setVisibility(info.getItem_status()==1? View.GONE:View.VISIBLE);
           }
           if (shopGoodsInfoDetailList!=null){
               ShopGoodsInfoDetail infoDetail = shopGoodsInfoDetailList.get(position-1);
               viewHolder.tv_code.setText(infoDetail.getBar_code());
               viewHolder.iv_name.setText(infoDetail.getTitle());
               viewHolder.tv_price.setText(PriceTransfer.chageMoneyToString(infoDetail.getSell_price()));
               GlideUtils.loadImageView(context,infoDetail.getPic_url(),viewHolder.iv_image);
               viewHolder.iv_off_shelf.setVisibility(infoDetail.getItem_status()==4? View.GONE:View.VISIBLE);
           }
           viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (onItemClickListener!=null){
                       onItemClickListener.onClick(v,position-1);
                   }
               }
           });
       }
    }
    @Override
    public int getItemCount() {
        int size = 0;
        if(goodsInfoList!=null){
            size=goodsInfoList.size()+1;
        }
        if (shopGoodsInfoDetailList!=null){
            size=shopGoodsInfoDetailList.size()+1;
        }
        return size;
    }
     class  TitleViewHolder extends RecyclerView.ViewHolder{
         TextView tv_search_count;
        public TitleViewHolder(View itemView) {
            super(itemView);
            tv_search_count = (TextView)itemView.findViewById(R.id.tv_search_count);
            itemView.setTag(this);
        }
    }
     class  ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_code ;
        TextView tv_price;
        ImageView iv_image ;
        TextView iv_name ;
        ImageView iv_off_shelf;
         ViewHolder(View itemView) {
            super(itemView);
            tv_code = (TextView)itemView.findViewById(R.id.tv_item_code);
            tv_price = (TextView)itemView.findViewById(R.id.tv_item_price);
            iv_image = (ImageView)itemView.findViewById(R.id.iv_item_image);
            iv_name = (TextView)itemView.findViewById(R.id.tv_item_name);
              iv_off_shelf = (ImageView) itemView.findViewById(R.id.iv_off_shelf);
             itemView.setTag(this);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
