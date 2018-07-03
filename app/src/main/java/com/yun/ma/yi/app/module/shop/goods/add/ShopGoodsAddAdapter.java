package com.yun.ma.yi.app.module.shop.goods.add;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yunmayi.app.manage.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： wh
 * 时间：  2017/8/30
 * 名称：蚂蚁小店新增商品选择列表适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopGoodsAddAdapter extends RecyclerView.Adapter<ShopGoodsAddAdapter.ViewHolder> {
    private Map<Integer, Boolean> mapList;
    private List<GoodsDetailInfo> goodsDetailInfoList;

    public ShopGoodsAddAdapter(List<GoodsDetailInfo> goodsDetailInfoList) {
        this.goodsDetailInfoList = goodsDetailInfoList;
    }

    public void initData(boolean isAllSelect) {
        mapList = new HashMap<>();
        for (int i = 0; i < goodsDetailInfoList.size(); i++) {
            mapList.put(i, isAllSelect);
        }
        notifyDataSetChanged();
    }

    public void initData(Map<Integer, Boolean> mapList) {
        this.mapList = mapList;

        notifyDataSetChanged();
    }

    public Map<Integer, Boolean> getMapList() {
        return mapList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_add_choose_single, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        GoodsDetailInfo goodsDetailInfo = goodsDetailInfoList.get(position);
        holder.tvCode.setText(goodsDetailInfo.getBar_code());
        holder.tvName.setText(goodsDetailInfo.getTitle());
        holder.rbCheck.setChecked(mapList.get(position));
        holder.rbCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapList.get(position)) {
                    mapList.put(position, false);
                } else {
                    mapList.put(position, true);
                }
                notifyDataSetChanged();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapList.get(position)) {
                    mapList.put(position, false);
                } else {
                    mapList.put(position, true);
                }
                notifyDataSetChanged();
            }
        });
    }

    public String getGoodsIds() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < goodsDetailInfoList.size(); i++) {
            GoodsDetailInfo goodsDetailInfo = goodsDetailInfoList.get(i);
            String goodsIds = goodsDetailInfo.getId();
            if (mapList.get(i)) {
                buffer.append(goodsIds).append(",");
            }
        }
        if (buffer.length() > 0) {
            buffer.deleteCharAt(buffer.length() - 1);
        }
        return buffer.toString();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvCode;
        RadioButton rbCheck;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code);
            rbCheck = (RadioButton) itemView.findViewById(R.id.rb_check);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return goodsDetailInfoList.size();
    }
}
