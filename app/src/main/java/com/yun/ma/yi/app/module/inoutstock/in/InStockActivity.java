package com.yun.ma.yi.app.module.inoutstock.in;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.OrderInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.zxing.activity.CaptureActivity;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/7/29
 * 名称:收货入库
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InStockActivity extends BaseActivity implements OnItemClickListener, InStockContract.View {

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
    @BindView(R.id.rv_number)
    RecyclerView rvNumber;
    /**
     * 没有订单时显示
     */
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    /**
     * 适配器
     */
    private InStockAdapter adapter;

    private List<OrderInfo> orderInfoList;
    /**
     * 订单号
     */
    private String order_id = "";
    /**
     * 数据处理类
     */
    private InStockPresenter presenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_in_stock;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.in_stock);
        orderInfoList = new ArrayList<>();
        adapter = new InStockAdapter(this, orderInfoList, R.layout.item_in_stock);
        rvNumber.setLayoutManager(new LinearLayoutManager(this));
        rvNumber.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter = new InStockPresenter(this, this);
        presenter.getStockOrder();
    }

    @OnClick(R.id.iv_scan)
    public void scan() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, Constants.REQUEST_IN_STOCK);
    }

    @OnClick(R.id.tv_search)
    public void search() {
        order_id  = tvSearchContent.getText().toString();
        presenter.getStockOrder();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            order_id = bundle.getString(Constants.INTENT_EXTRA_KEY_QR_SCAN);
            presenter.getStockOrder();
        }
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, InStockDetailActivity.class);
        intent.putExtra("orderId", orderInfoList.get(position).getOrder_id());
        startActivity(intent);
    }

    @Override
    public int getUserId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getOrderId() {
        return order_id;
    }

    @Override
    public void setOrderInfoList(List<OrderInfo> orderInfoList) {
        this.orderInfoList.clear();
        this.orderInfoList.addAll(orderInfoList);
        tvNodata.setVisibility(orderInfoList.size()==0?View.VISIBLE: View.GONE);
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
