package com.yun.ma.yi.app.module.inoutstock.in;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.OrderInfoDetail;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yunmayi.app.manage.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： wh
 * 时间：  2017/7/29
 * 名称：入库详情适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InStockDetailAdapter extends RecyclerView.Adapter<InStockDetailAdapter.ViewHolder> {

    /**
     * 库存集合
     */
    private Map<Integer,Integer> stockList;
    /**
     * 是否已经入库集合
     */
    private Map<Integer,Boolean> checkList;
    /**
     * 数据列表
     */
    private List<OrderInfoDetail> orderInfoDetailList;
    /**
     * 回调监听
     */
    private OnItemClickListener onItemClickListener;
    public InStockDetailAdapter(List<OrderInfoDetail> orderInfoDetailList) {
       this.orderInfoDetailList = orderInfoDetailList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_in_stock_goods, null);
        return new ViewHolder(view);
    }
    public void initData(){
        stockList = new HashMap<>();
        checkList = new HashMap<>();
        for (int i  = 0 ;i<orderInfoDetailList.size();i++){
            OrderInfoDetail orderInfoDetail = orderInfoDetailList.get(i);
            stockList.put(i,orderInfoDetail.getQuantity());
            checkList.put(i,orderInfoDetail.getIs_enter()==1);
        }
        notifyDataSetChanged();
    }
    @Override
      public void onBindViewHolder(final ViewHolder holder,final int position) {
        final OrderInfoDetail orderInfoDetail = orderInfoDetailList.get(position);
         holder.iv_name.setText(orderInfoDetail.getProduct_title());
         holder.tv_no.setText(String.valueOf((position+1)));
         holder.tv_adjust_count.setText(String.valueOf(stockList.get(position)));
        if (checkList.get(position)) {
            holder.tv_check.setText("已入库");
            holder.tv_check.setBackgroundResource(R.drawable.round_grey_btn);
        }  else{
            holder.tv_check.setText("确定");
            holder.tv_check.setBackgroundResource(R.drawable.round_red_btn);
         }
         holder.tv_check.setEnabled(!checkList.get(position));
          holder.iv_add.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  setAfterChange(position,stockList.get(position) + 1);

              }
          });
         holder.iv_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setAfterChange(position,stockList.get(position) - 1);
            }
        });
        holder.tv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onClick(v,position);
                }
                notifyDataSetChanged();
            }
        });
    }
    /**
     * 改变数量后刷新数据
     * @param  position 第几条数据
     *@param  count 改变数量
     */
    public void setAfterChange(int position,int count){
        OrderInfoDetail orderInfoDetail  = orderInfoDetailList.get(position);
        if (stockList.get(position)<0){
            stockList.put(position, 0);
        }else if (count>orderInfoDetail.getQuantity()){
            stockList.put(position, orderInfoDetail.getQuantity());
        }else {
            stockList.put(position, count);
        }
        notifyDataSetChanged();
    }
    /**
     * 批量处理的时候获取的全部data
     */
    public Map<Integer,Integer> getOrderInfoDetailList(){
        Map<Integer,Integer> map = new HashMap<>();
        if (stockList!=null&& orderInfoDetailList!=null){
            for (int i = 0 ;i<stockList.size();i++){
                OrderInfoDetail orderInfoDetail = orderInfoDetailList.get(i);
                if (!checkList.get(i)){
                    map.put(orderInfoDetail.getId(),stockList.get(i));
                }
            }
            return map;
        }
       return  map;
    }
    /**
     * 设置对于position是否已经入库
     * @param position 对应的位置
     * @param  isInStock 是否入库
     */
    public void setPositionCheck(int position,boolean isInStock){
        checkList.put(position,isInStock);
        notifyDataSetChanged();
    }
    /**
     * 设置对于position是否已经入库
     * @param  isInStock 是否入库
     */
    public void setCheck(boolean isInStock){
        if (orderInfoDetailList!=null&&orderInfoDetailList.size()>0){
            for (int i  = 0 ;i<orderInfoDetailList.size();i++){
                checkList.put(i,isInStock);
            }
        }
        notifyDataSetChanged();
    }
    /**
     * 获取当前点击位置的库存数量
     * @param  position 当前位置
     */
    public int getQuantity(int position){
        return  stockList.get(position);
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView iv_name;
        ImageView iv_minus;
        TextView tv_adjust_count;
        ImageView iv_add;
        TextView tv_check;
        TextView tv_no;

        ViewHolder(View itemView) {
            super(itemView);
            iv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_add =  (ImageView) itemView.findViewById(R.id.iv_add);
            iv_minus = (ImageView) itemView.findViewById(R.id.iv_minus);
            tv_adjust_count = (TextView) itemView.findViewById(R.id.tv_adjust_count);
            tv_check = (TextView) itemView.findViewById(R.id.tv_check);
            tv_no =  (TextView) itemView.findViewById(R.id.tv_no);
            itemView.setTag(this);
        }
    }
    @Override
    public int getItemCount() {
        return orderInfoDetailList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
