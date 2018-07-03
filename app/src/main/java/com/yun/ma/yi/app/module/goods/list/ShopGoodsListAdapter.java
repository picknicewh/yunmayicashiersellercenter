package com.yun.ma.yi.app.module.goods.list;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yun.ma.yi.app.api.util.GlideUtils;
import com.yun.ma.yi.app.bean.ShopGoodsInfoDetail;
import com.yun.ma.yi.app.module.common.CommonRecyclerAdapter;
import com.yun.ma.yi.app.module.common.CommonRecyclerHolder;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/24
 * 名称：蚂蚁小店商品子列表适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopGoodsListAdapter extends CommonRecyclerAdapter<ShopGoodsInfoDetail>{
    public ShopGoodsListAdapter(Context context, List<ShopGoodsInfoDetail> goodsInfo, int layoutId) {
        super(context, goodsInfo, layoutId);
    }

    @Override
    public void convert(CommonRecyclerHolder holder,ShopGoodsInfoDetail goodsInfo) {
        TextView tv_code = holder.getView(R.id.tv_item_code);
        TextView tv_price = holder.getView(R.id.tv_item_price);
        ImageView iv_image = holder.getView(R.id.iv_item_image);
        TextView tv_name = holder.getView(R.id.tv_item_name);
        ImageView iv_off_shelf = holder.getView(R.id.iv_off_shelf);
        tv_code.setText(goodsInfo.getBar_code());
        tv_price.setText("￥"+ PriceTransfer.chageMoneyToString(goodsInfo.getSell_price()));
        tv_name.setText(goodsInfo.getTitle());
        GlideUtils.loadImageView(mContext, goodsInfo.getPic_url(),iv_image);
        iv_off_shelf.setVisibility(goodsInfo.getItem_status()==4? View.GONE:View.VISIBLE);
        G.log("xxxxxxxxxxxxxxxx"+goodsInfo.getItem_status());
    }
}
