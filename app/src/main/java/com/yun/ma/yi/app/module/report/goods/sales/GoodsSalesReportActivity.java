package com.yun.ma.yi.app.module.report.goods.sales;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.GoodsSalesInfo;
import com.yun.ma.yi.app.bean.GoodsSalesInfoBo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.module.common.spiner.AbstractSpinnerAdapter;
import com.yun.ma.yi.app.module.common.spiner.SpinnerPopWindow;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.CommonUtil;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yun.ma.yi.app.zxing.activity.CaptureActivity;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yun.ma.yi.app.module.report.goods.sales.SalesConfig.PROFIT_STR;
import static com.yun.ma.yi.app.module.report.goods.sales.SalesConfig.TOTAL_FEE_STR;
import static com.yun.ma.yi.app.module.report.goods.sales.SalesConfig.TOTAL_QUANTITY;
import static com.yun.ma.yi.app.module.report.goods.sales.SalesConfig.TOTAL_QUANTITY_STR;

/**
 * Created by ys on 2017/6/13.
 * 商品销售报表
 */

public class GoodsSalesReportActivity extends BaseActivity implements GoodsSalesReportContract.View ,PullToRefreshBase.OnRefreshListener2<RecyclerView> ,AbstractSpinnerAdapter.IOnItemSelectListener{

    /**
     * 销售数量
     */
    @BindView(R.id.number)
    TextView number;
    /**
     * 总销售额
     */
    @BindView(R.id.total_sales)
    TextView totalSales;
    /**
     * 扫描
     */
    @BindView(R.id.scan_layout)
    LinearLayout scanLayout;
    /**
     * 编辑框
     */
    @BindView(R.id.input)
    ClearEditText input;
    /**
     * 销售列表
     */
    @BindView(R.id.goods_sales_list)
    PullToRefreshRecyclerView goodsSalesList;
    /**
     * 搜索
     */
    @BindView(R.id.search)
    TextView search;
    /**
     * 排序类型
     */
    @BindView(R.id.stype)
    LinearLayout stype;
    /**
     * 搜索标题
     */
    @BindView(R.id.seach_title)
    RelativeLayout seachTitle;
    /**
     * 没有数据
     */
    @BindView(R.id.tv_nodata)
    TextView tvNOdata;
    /**
     * 数据处理类
     */
    private GoodsSalesReportPresenter presenter;
    /**
     * 页码
     */
    private int page = 1;
    /**
     * 页数
     */
    private int size=10;
    /**
     * 默认的排序方式按销售数量
     */
    private String OrderType = TOTAL_QUANTITY;
    /**
     * 分类类型
     */
    private String startTime, endTime, source, groupType;
    /**
     * 适配器
     */
    private GoodsSalesReportAdapter goodsSalesReportAdapter;
    /**
     * 商品销售列表
     */
    private List<GoodsSalesInfo> goodsSalesInfos;
    /**
     * 排序类型列表
     */
    private List<String> saleTypeList;
    /**
     * 下拉选择排序类型
     */
    private SpinnerPopWindow mSpinnerPopWindow;
    /**
     * 列表
     */
    private RecyclerView recyclerView;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_goods_sales;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.goods_sales_report);
        goodsSalesInfos = new ArrayList<>();
        saleTypeList = new ArrayList<>();
        groupType = getIntent().getStringExtra("groupType");
        seachTitle.setVisibility(groupType.equals("按分类统计")?View.GONE:View.VISIBLE);
        presenter = new GoodsSalesReportPresenter(this, this);
        //设置排序
        String[] saleType = getResources().getStringArray(R.array.goods_sales_type);
        for(int i = 0; i < saleType.length; i++){
            saleTypeList.add(saleType[i]);
        }
        mSpinnerPopWindow = new SpinnerPopWindow(this);
        mSpinnerPopWindow.refreshData(saleTypeList, 0);
        mSpinnerPopWindow.setItemListener(this);

        goodsSalesList.setMode(PullToRefreshBase.Mode.BOTH);
        recyclerView = goodsSalesList.getRefreshableView();
        goodsSalesList.setOnRefreshListener(this);
        goodsSalesReportAdapter = new GoodsSalesReportAdapter(this, goodsSalesInfos, R.layout.goods_sales_item ,groupType);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(goodsSalesReportAdapter);
        presenter.getGoodsSalesReportInfo();
        //监听输入框
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                page = 1;
                presenter.getGoodsSalesReportInfo();
                goodsSalesList.setMode(PullToRefreshBase.Mode.BOTH);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        input.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    goodsSalesList.setRefreshing(false);
                    return true;
                }
                return false;
            }

        });
    }

    @OnClick(R.id.scan_layout)
    public void onScanLayout(){
        //打开二维码扫描界面
        if(CommonUtil.isCameraCanUse()){
            Intent intent = new Intent(GoodsSalesReportActivity.this, CaptureActivity.class);
            startActivityForResult(intent, Constants.REQUEST_REPORE_SALES);
        }else{
            Toast.makeText(this,"请打开此应用的摄像头权限！",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String getStartTime() {
        return  getIntent().getStringExtra("startTime");
    }

    @Override
    public String getEndTime() {
        return  getIntent().getStringExtra("endTime");
    }

    @Override
    public String getSource() {
        return getIntent().getStringExtra("source");
    }

    @Override
    public int getPageNo() {
        return page;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public int getUid() {
        return UserMessage.getInstance().getUId();
    }

    //获取统计方式
    @Override
    public String getGroupType() {
        if (getResources().getString(R.string.goods_statistics).equals(groupType)) {
            groupType = SalesConfig.ITEM_ID;
        } else if (getResources().getString(R.string.category_statistics).equals(groupType)) {
            groupType = SalesConfig.CATEGORY_ID;
        }
        return groupType;
    }

    //获取排序方式
    @Override
    public String getOrderType() {
        String orderTypeStr = TOTAL_QUANTITY;
        if (TOTAL_QUANTITY_STR.equals(OrderType)) {
            orderTypeStr = SalesConfig.TOTAL_QUANTITY;
        } else if (TOTAL_FEE_STR.equals(OrderType)) {
            orderTypeStr = SalesConfig.TOTAL_FEE;
        } else if (PROFIT_STR.equals(OrderType)) {
            orderTypeStr = SalesConfig.PROFIT;
        }
        return orderTypeStr;
    }

    //获取输入框code
    @Override
    public String getKeyword() {
        return input.getText().toString();
    }

    @Override
    public void setGoodsSalesReportInfo(GoodsSalesInfoBo result) {
        if (result != null) {
            List<GoodsSalesInfo> infos = result.getList();
            number.setText(String.valueOf(result.getTotal_quantity()));
            totalSales.setText(PriceTransfer.chageMoneyToString(result.getTotal_fee()));
            if (infos != null){
                if (infos.size() == 0){
                    if (page==1){
                        tvNOdata.setVisibility(infos.size()==0?View.VISIBLE:View.GONE);
                    }
                    goodsSalesList.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                }else{
                    if (page == 1){
                        goodsSalesInfos.clear();
                    }
                    goodsSalesInfos.addAll(infos);
                    goodsSalesReportAdapter.notifyDataSetChanged();
                }
            }
            for (int i = 0 ;i <infos.size();i++){
                G.log("--xxxxxxxx"+infos.get(i).getTitle());
            }

        }
        goodsSalesList.onRefreshComplete();
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page = 1;
        presenter.getGoodsSalesReportInfo();
        goodsSalesList.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.getGoodsSalesReportInfo();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(Constants.INTENT_EXTRA_KEY_QR_SCAN);
            //将扫描出的信息显示出来
            input.setText(scanResult);
            goodsSalesList.setRefreshing(false);
        }
    }

    @OnClick(R.id.stype)
    public void onStype(View view){
        mSpinnerPopWindow.setWidth(view.getWidth());
        mSpinnerPopWindow.showAsDropDown(view);

    }

    /**
     * 获取选中的type
     * @param  pos 选中的位置
     */
    @Override
    public void onItemClick(int pos) {
        if (pos >= 0 && pos <= saleTypeList.size()){
            String value = saleTypeList.get(pos);
            search.setText(value);
            OrderType = value;
            goodsSalesList.setRefreshing(false);
        }
    }

}
