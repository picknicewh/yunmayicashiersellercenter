package com.yun.ma.yi.app.module.stock.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yun.ma.yi.app.api.Config;
import com.yun.ma.yi.app.api.util.GlideUtils;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/29
 * 名称：库存查询适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StockSearchAdapter   extends RecyclerView.Adapter<StockSearchAdapter.ViewHolder>{
    private List<GoodsDetailInfo> goodsDetailInfoList;
    private Context context;
    public StockSearchAdapter(Context context,List<GoodsDetailInfo> goodsDetailInfoList){
        this.context = context;
        this.goodsDetailInfoList  = goodsDetailInfoList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock_search_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoodsDetailInfo goodsDetailInfo = goodsDetailInfoList.get(position);
        holder.iv_name.setText(goodsDetailInfo.getTitle());
        holder.tv_code.setText(goodsDetailInfo.getBar_code());
        holder.tv_price.setText("成本价：￥"+PriceTransfer.chageMoneyToString(goodsDetailInfo.getCost()));
        holder.tv_stock.setText("库存数："+goodsDetailInfo.getStock());
        double totalMoney = goodsDetailInfo.getCost() * goodsDetailInfo.getStock();
        holder.tv_total_money.setText("库存金额：￥"+PriceTransfer.chageMoneyToString(totalMoney));
        GlideUtils.loadImageView(context,goodsDetailInfo.getImage_url(),holder.iv_image);
    }
    class  ViewHolder  extends RecyclerView.ViewHolder{
        TextView tv_code ;
        TextView tv_price;
        ImageView iv_image ;
        TextView iv_name ;
        TextView tv_total_money;
        TextView tv_stock;
        ViewHolder(View itemView) {
            super(itemView);
            tv_code = (TextView)itemView.findViewById(R.id.tv_code);
            tv_price = (TextView)itemView.findViewById(R.id.tv_price);
            iv_image = (ImageView)itemView.findViewById(R.id.iv_image);
            iv_name = (TextView)itemView.findViewById(R.id.tv_name);
            tv_total_money = (TextView) itemView.findViewById(R.id.tv_total_money);
            tv_stock = (TextView)itemView.findViewById(R.id.tv_stock);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return goodsDetailInfoList.size();
    }
}
