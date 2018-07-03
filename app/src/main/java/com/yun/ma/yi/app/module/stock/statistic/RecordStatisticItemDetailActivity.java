package com.yun.ma.yi.app.module.stock.statistic;

import android.os.Bundle;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.StockStatisticItemInfo;
import com.yun.ma.yi.app.module.common.view.ItemTextView;
import com.yunmayi.app.manage.R;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/6/22
 * 名称： 商品盘点记录详情
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RecordStatisticItemDetailActivity extends BaseActivity implements RecordStatisticContract.RecordStatisticItemDetailView{

    /**
     * 商品名称
     */
    @BindView(R.id.tv_name)
    ItemTextView tvName;
    /**
     * 商品编码
     */
    @BindView(R.id.tv_code)
    ItemTextView tvCode;
    /**
     * 操作
     */
    @BindView(R.id.tv_operation)
    ItemTextView tvOperation;
    /**
     * 调整数量
     */
    @BindView(R.id.tv_adjust_count)
    ItemTextView tvAdjustCount;
    /**
     * 调整编码
     */
    @BindView(R.id.tv_adjust_report_code)
    ItemTextView tvAdjustReportCode;
    /**
     * 调整时间
     */
    @BindView(R.id.tv_adjust_time)
    ItemTextView tvAdjustTime;
    /**
     * 操作人
     */
    @BindView(R.id.tv_operator)
    ItemTextView tvOperator;
    private int id;
    private RecordStatisticContract.Presenter presenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_record_statistic_item_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.goods_statistics_record_adjust);
        presenter = new RecordStatisticPresenter(this, this);
        id = getIntent().getIntExtra("id",0);
        presenter.stockGetById();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void stockGetById(StockStatisticItemInfo info) {
        if (info != null){
            tvName.initData(info.getTitle());
            tvCode.initData(info.getBar_code());
            String type = "";
            switch (info.getType()){
                case 1:
                    type = "库存调整";
                    break;
                case 2:
                    type = "收货入库";
                    break;
                case 3:
                    type = "销售出库";
                    break;
            }
            tvOperation.initData(type);
            tvAdjustCount.initData(String.valueOf(info.getChange_stock()));
            tvAdjustReportCode.initData(info.getTrade_id());
            tvAdjustTime.initData(info.getUpdate_datetime());
            tvOperator.initData(info.getUser_name());
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( presenter!= null) {
            presenter.unSubscribe();
        }
    }
}
