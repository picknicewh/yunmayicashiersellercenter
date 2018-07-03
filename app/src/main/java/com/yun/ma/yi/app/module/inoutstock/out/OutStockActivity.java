package com.yun.ma.yi.app.module.inoutstock.out;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.OrderInfoDetail;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.zxing.activity.CaptureActivity;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yun.ma.yi.app.module.Constants.REQUEST_OUT_STOCK;

/**
 * 作者： wh
 * 时间：  2017/7/29
 * 名称：退货入库
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class OutStockActivity extends BaseActivity implements OnItemClickListener, OutStockContract.View {
    /**
     * 扫描
     */
    @BindView(R.id.iv_scan)
    TextView ivScan;
    /**
     * 批发订单号
     */
    @BindView(R.id.tv_search_content)
    ClearEditText tvSearchContent;
    /**
     * 订单号列表
     */
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    /**
     * 没有订单信息
     */
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    /**
     * 适配器
     */
    private OutStockAdapter adapter;
    /**
     * 数据处理类
     */
    private OutStockPresenter presenter;
    /**
     * 出库列表
     */
    private List<OrderInfoDetail> orderInfoDetailList;
    /**
     * 编写
     */
    private String keyWord = "";

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_out_stock;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.out_stock);
        orderInfoDetailList = new ArrayList<>();
        adapter = new OutStockAdapter(orderInfoDetailList);
        rvGoods.setLayoutManager(new LinearLayoutManager(this));
        rvGoods.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter = new OutStockPresenter(this, this);
      //  presenter.conformOutStock();
    }

    @OnClick(R.id.iv_scan)
    public void scan() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_OUT_STOCK);
    }

    @OnClick(R.id.tv_search)
    public void search() {
        keyWord = tvSearchContent.getText().toString();
        presenter.conformOutStock();
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, OutStockDetailActivity.class);
        intent.putExtra("orderInfoDetail", orderInfoDetailList.get(position));
        startActivity(intent);
    }

    @Override
    public int getUserId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getKeyWord() {
        return keyWord;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constants.REQUEST_OUT_STOCK) {
            Bundle bundle = data.getExtras();
            keyWord = bundle.getString(Constants.INTENT_EXTRA_KEY_QR_SCAN);
            presenter.conformOutStock();
        }
    }

    @Override
    public void setOrderInfoDetailList(List<OrderInfoDetail> orderInfoDetailList) {
        this.orderInfoDetailList.clear();
        this.orderInfoDetailList.addAll(orderInfoDetailList);
        G.log("xxxxxxxxxx"+orderInfoDetailList.size()+"-----------------");
        tvNodata.setVisibility(orderInfoDetailList.size()==0?View.VISIBLE: View.GONE);
        rvGoods.setVisibility(orderInfoDetailList.size()!=0?View.VISIBLE: View.GONE);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }

}
