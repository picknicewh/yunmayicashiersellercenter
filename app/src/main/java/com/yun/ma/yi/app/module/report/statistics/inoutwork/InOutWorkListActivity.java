package com.yun.ma.yi.app.module.report.statistics.inoutwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.InOutWorkInfo;
import com.yun.ma.yi.app.bean.InOutWorkInfoVos;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InOutWorkListActivity extends BaseActivity implements OnItemClickListener, InOutWorkContract.View {

    /**
     * 总收银金额
     */
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    /**
     * 收银记录
     */
    @BindView(R.id.rv_record)
    RecyclerView rvRecord;
    /**
     * 没有数据
     */
    @BindView(R.id.tv_nodata)
    TextView tvNOdata;
    /**
     * 适配器
     */
    private InOutWorkAdapter adapter;
    /**
     * 数据处理类
     */
    private InOutWorkPresenter presenter;
    /**
     * 员工信息列表
     */
    private List<InOutWorkInfo> inOutWorkInfoList;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_in_out_work_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.staff_in_out_work_search);
        inOutWorkInfoList = new ArrayList<>();
        adapter = new InOutWorkAdapter(inOutWorkInfoList);
        rvRecord.setLayoutManager(new LinearLayoutManager(this));
        rvRecord.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter = new InOutWorkPresenter(this,this);
        presenter.subscribe();
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this,InOutWorkDetailActivity.class);
        intent.putExtra("inOutWorkInfo",inOutWorkInfoList.get(position));
        startActivity(intent);
    }

    @Override
    public String getStartTime() {
        return getIntent().getStringExtra("startTime");
    }

    @Override
    public String getEndTime() {
        return getIntent().getStringExtra("endTime");
    }

    @Override
    public String getKeyWord() {
        return getIntent().getStringExtra("keyword");
    }

    @Override
    public int getUserId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public void setInOutWorkVos(InOutWorkInfoVos inOutWorkVos) {
        inOutWorkInfoList.clear();
        inOutWorkInfoList.addAll(inOutWorkVos.getList());
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
        tvNOdata.setVisibility(inOutWorkVos.getList().size()==0?View.VISIBLE:View.GONE);
        tvTotalMoney.setText("￥"+PriceTransfer.chageMoneyToString(inOutWorkVos.getTotalCash()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
         if (presenter!=null){
             presenter.unSubscribe();
         }
    }
}
