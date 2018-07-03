package com.yun.ma.yi.app.module.report.statistics.receivables;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.ReceivablesInfo;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.DISABLED;

/**
 * Created by ys on 2017/6/12.
 * 收款统计报表
 */

public class ReceivablesReportActivity extends BaseActivity implements ReceivablesReportContract.View{
    /**
     * 收款统计报表列表
     */
    @BindView(R.id.receivables_list)
    PullToRefreshRecyclerView receivablesList;
    private RecyclerView recyclerView;
    /**
     * 没有数据
     */
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    /**
     * 适配器
     */
    private ReceivablesReportAdapter cashierReportAdapter;
    /**
     *  收款统计报表列表
     */
    private List<ReceivablesInfo> receivablesInfos;
    private String startTime, endTime, source;
    /**
     * 数据处理类
     */
    private ReceivablesReportPresenter presenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_receivables;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.receivables_report);
        startTime = getIntent().getStringExtra("startTime");
        endTime = getIntent().getStringExtra("endTime");
        source = getIntent().getStringExtra("source");
        receivablesInfos = new ArrayList<>();
        presenter = new ReceivablesReportPresenter(this, this);

        recyclerView = receivablesList.getRefreshableView();
        cashierReportAdapter = new ReceivablesReportAdapter(this, receivablesInfos, R.layout.receivables_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(cashierReportAdapter);
        receivablesList.setMode(DISABLED);
        presenter.getReceivablesReportInfo();

    }

    @Override
    public String getStartTime() {
        return startTime;
    }

    @Override
    public String getEndTime() {
        return endTime;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public int getUid() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public void getReceivablesReportInfo(List<ReceivablesInfo> infos) {
        if (infos != null) {
            receivablesInfos.addAll(infos);
            receivablesList.setVisibility(receivablesInfos.size()!=0? View.VISIBLE: View.GONE);
            tvNodata.setVisibility(receivablesInfos.size()==0?View.VISIBLE: View.GONE);
            if (cashierReportAdapter!=null){
                cashierReportAdapter.notifyDataSetChanged();
            }
        }
        receivablesList.onRefreshComplete();
    }

}
