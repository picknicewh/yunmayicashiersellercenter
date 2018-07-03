package com.yun.ma.yi.app.module.report.goods.sales;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.GoodsSalesInfo;
import com.yun.ma.yi.app.module.common.CommonRecyclerAdapter;
import com.yun.ma.yi.app.module.common.CommonRecyclerHolder;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yun.ma.yi.app.utils.StringUtils;
import com.yunmayi.app.manage.R;

import java.text.DecimalFormat;
import java.util.List;

import static com.yun.ma.yi.app.utils.CommonUtil.getString;

/**
 * Created by ys on 2017/6/20.
 * 商品销售报表适配
 */

public class GoodsSalesReportAdapter extends CommonRecyclerAdapter<GoodsSalesInfo> {

    private String groupType;
   private DecimalFormat decimalFormat = new DecimalFormat("######0.0");
    public GoodsSalesReportAdapter(Context context, List<GoodsSalesInfo> data, int layoutId, String groupType) {
        super(context, data, layoutId);
        this.groupType = groupType;
    }

    @Override
    public void convert(CommonRecyclerHolder holder, GoodsSalesInfo goodsSalesInfo) {
        if (holder != null) {
            if (getString(R.string.goods_statistics).equals(groupType)) {
                ((TextView) holder.getView(R.id.bar_code)).setText(goodsSalesInfo.getBar_code());
                ((TextView) holder.getView(R.id.title)).setText(goodsSalesInfo.getTitle());
            } else if (getString(R.string.category_statistics).equals(groupType)) {
                if (StringUtils.isEmpty(goodsSalesInfo.getCategory_name())) {
                    ((TextView) holder.getView(R.id.title)).setText("默认分类");
                } else {
                    ((TextView) holder.getView(R.id.title)).setText(goodsSalesInfo.getCategory_name());
                }
                holder.getView(R.id.goods_code_layout).setVisibility(View.GONE);
            }
            if (goodsSalesInfo.getIs_weigh() == 1) {
                ((TextView) holder.getView(R.id.tv_quantity)).setText("销售商品重量：");
                ((TextView) holder.getView(R.id.total_quantity)).setText(String.valueOf(goodsSalesInfo.getTotal_weight()));
            } else {
                ((TextView) holder.getView(R.id.tv_quantity)).setText("销售商品数：");
                ((TextView) holder.getView(R.id.total_quantity)).setText(goodsSalesInfo.getTotal_quantity());
            }
            ((TextView) holder.getView(R.id.total_fee)).setText(" ¥ " + PriceTransfer.chageMoneyToString(goodsSalesInfo.getTotal_fee()));
            float rate =0.0f;
            if (goodsSalesInfo.getTotal_fee()!=0){
                rate = (float) ((goodsSalesInfo.getTotal_fee() - goodsSalesInfo.getReal_cost_fee()) / goodsSalesInfo.getTotal_fee());
            }
            ((TextView) holder.getView(R.id.tv_interest_rate)).setText(decimalFormat.format(rate*100) + "%");
        }

    }
}

