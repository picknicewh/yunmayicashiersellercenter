package com.yun.ma.yi.app.module.member.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.MemberInfoChangeInfo;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/7/11
 * 名称：余额明细/积分明细
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberInfoDetailActivity extends BaseActivity implements OnItemClickListener, MemberInfoDetailContract.View ,PullToRefreshBase.OnRefreshListener2<RecyclerView>{
    /**
     * recycleView下拉刷新
     */
    @BindView(R.id.pullRecyclerView)
    PullToRefreshRecyclerView pullRecyclerView;
    /**
     * 会员信息名字
     */
    @BindView(R.id.tv_info_name)
    TextView tvInfoName;
    /**
     * 会员信息修改后名字
     */
    @BindView(R.id.tv_after_name)
    TextView tvAfterName;
    /**
     * 适配器
     */
    private MemberInfoDetailAdapter adapter;
    /**
     * 列表控件
     */
    private RecyclerView recyclerView;
    /**
     * 来源
     */
    private int source;
    /**
     * 数据处理类
     */
   private MemberInfoDetailPresenter presenter;
    /**
     * 页码
     */
    private int page=1;
    /**
     *页数
     */
    private int size=15;
    /**
     * 是否还有更多数据
     */
    private boolean hasMoreData = true;
    /**
     * 数据列表
     */
    private List<MemberInfoChangeInfo> memberInfoChangeInfoList;
    /**
     * 类型
     */
    private String type;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_member_info_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        source = getIntent().getIntExtra("source", Constants.BALANCE_HAPPEND_DETAIL);
        memberInfoChangeInfoList = new ArrayList<>();
        initSource();
        adapter = new MemberInfoDetailAdapter(memberInfoChangeInfoList,source);
        recyclerView = pullRecyclerView.getRefreshableView();
        pullRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        pullRecyclerView.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        recyclerView.setBackgroundResource(R.drawable.rect_border_bg);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter = new MemberInfoDetailPresenter(this,this);
        presenter.getMoneyOrIntegralList();
    }

    private void initSource() {
        if (source == Constants.BALANCE_HAPPEND_DETAIL) {
            setTitleTextId(R.string.balance_detail);
            tvInfoName.setText("金额");
            tvAfterName.setText("发生后金额");
            type= Constants.TYPR_MEMBER_BALANCE;
        } else if (source == Constants.INTEGRAL_HAPPEND_DETAIL) {
            setTitleTextId(R.string.integral_detail);
            tvInfoName.setText("积分");
            tvAfterName.setText("发生后积分");
            type= Constants.TYPE_MEMBER_INTEGRAL;
        }
    }
    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, MemberInfoHappenDetailActivity.class);
        intent.putExtra("memberInfoChangeInfo",memberInfoChangeInfoList.get(position));
        intent.putExtra("source", source);
        startActivity(intent);
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getSellerId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public int getUserId() {
        return getIntent().getIntExtra("user_id",-1);
    }

    @Override
    public   String getType() {
        return type;
    }

    @Override
    public void setMemberInfo(List<MemberInfoChangeInfo> memberInfoChangeInfoList) {
        if (page==1){
            this.memberInfoChangeInfoList.clear();
        }
        hasMoreData = memberInfoChangeInfoList.size()==size;
        pullRecyclerView.setMode(hasMoreData?PullToRefreshBase.Mode.PULL_FROM_END:PullToRefreshBase.Mode.DISABLED);
        this.memberInfoChangeInfoList.addAll(memberInfoChangeInfoList);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page=1;
        presenter.getMoneyOrIntegralList();
        refreshView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.getMoneyOrIntegralList();
        refreshView.onRefreshComplete();
        if (!hasMoreData){
            G.showToast(this,"没有更多数据了！");
           // pullRecyclerView.setMode(PullToRefreshBase.Mode.DISABLED);
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
