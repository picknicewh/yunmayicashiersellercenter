package com.yun.ma.yi.app.module.stock.add;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yun.ma.yi.app.api.util.GlideUtils;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.StockChangeInfo;
import com.yun.ma.yi.app.utils.ClassChangeUtil;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： wh
 * 时间：  2017/6/29
 * 名称：新增盘点适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class AddStatisticAdapter extends RecyclerView.Adapter<AddStatisticAdapter.ViewHolder> {
    private List<GoodsDetailInfo> goodsDetailInfoList;
    private Context context;
    private Map<Integer,Integer> stockList;
    private List<StockChangeInfo> stockChangeInfoList;
    private StockInfoChangeListener infoChangeListener;
    public AddStatisticAdapter(  Context context,List<GoodsDetailInfo> goodsDetailInfoList) {
        this.context =context;
        this.goodsDetailInfoList = goodsDetailInfoList;
        stockChangeInfoList = new ArrayList<>();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistic_add_list, null);
        return new ViewHolder(view);
    }
    public void initData(){
        stockList = new HashMap<>();
        stockChangeInfoList = new ArrayList<>();
        for (int i  = 0 ;i<goodsDetailInfoList.size();i++){
            stockList.put(i,0);
        }
        notifyDataSetChanged();
    }
    @Override
      public void onBindViewHolder(final ViewHolder holder,final int position) {
         final   GoodsDetailInfo goodsDetailInfo=  goodsDetailInfoList.get(position);
          holder.iv_name.setText(goodsDetailInfo.getTitle());
          holder.tv_code.setText(goodsDetailInfo.getBar_code());
          GlideUtils.loadImageView(context,goodsDetailInfo.getImage_url(),holder.iv_image);
          holder.tv_adjust_count.setText(""+stockList.get(position));
          holder.tv_before_adjust.setText("调整前："+goodsDetailInfo.getStock());
          String after = String.valueOf(goodsDetailInfo .getStock()+ stockList.get(position));
          holder.tv_after_adjust.setText("调整后："+after);
          holder.iv_add.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                     stockList.put(position, stockList.get(position) + 1);
                     setAfter_stock(position);
              }
          });
         holder.iv_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int stock = stockList.get(position)-1;
                if (goodsDetailInfo.getStock()+stock<0){
                    stock=(-goodsDetailInfo.getStock());
                }
                stockList.put(position, stock);
                setAfter_stock(position);
            }
        });
    }
    /**
     * 设置调整后的数据
     * @param  position
     */
    private void setAfter_stock(int position){
        GoodsDetailInfo goodsDetailInfo=  goodsDetailInfoList.get(position);
        int after = goodsDetailInfo .getStock()+ stockList.get(position);
        int changeCount =stockList.get(position);
        StockChangeInfo changeInfo =  ClassChangeUtil.setStockChangeInfoList(goodsDetailInfo) ;
        changeInfo.setAfter_stock(after);
        changeInfo.setChange_stock(changeCount);
        int index = getSameIndex(goodsDetailInfo.getId());
        if (index!=-1){
            if (changeCount==0) stockChangeInfoList.remove(index);
            else stockChangeInfoList.set(index,changeInfo);
            }else {
                stockChangeInfoList.add(changeInfo);
            }
            if (infoChangeListener!=null){
                infoChangeListener.onStockInfoChange(stockChangeInfoList);
        }
        notifyDataSetChanged();
    }
    /**
     *获取是否有重复项，并获取相应的坐标
     * @param id
     */
    private int  getSameIndex(String id){
        int index =-1;
        for (int i = 0 ; i<stockChangeInfoList.size();i++){
            if (id.equals(stockChangeInfoList.get(i).getId()) ){
                    index = i;
                break;
            }
        }
        return index;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_code;
        ImageView iv_image;
        TextView iv_name;
        ImageView iv_minus;
        TextView tv_adjust_count;
        ImageView iv_add;
        TextView tv_before_adjust;
        TextView tv_after_adjust;
        ViewHolder(View itemView) {
            super(itemView);
            iv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_code = (TextView) itemView.findViewById(R.id.tv_code);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            iv_add =  (ImageView) itemView.findViewById(R.id.iv_add);
            iv_minus = (ImageView) itemView.findViewById(R.id.iv_minus);
            tv_after_adjust = (TextView) itemView.findViewById(R.id.tv_after_adjust);
            tv_before_adjust = (TextView) itemView.findViewById(R.id.tv_before_adjust);
            tv_adjust_count = (TextView) itemView.findViewById(R.id.tv_adjust_count);
            itemView.setTag(this);
        }
    }
    @Override
    public int getItemCount() {
        return goodsDetailInfoList.size();
    }


    public void setInfoChangeListener(StockInfoChangeListener infoChangeListener) {
        this.infoChangeListener = infoChangeListener;
    }

    public interface StockInfoChangeListener{
        void  onStockInfoChange(List<StockChangeInfo> stockChangeInfoList);
    }
}
