package com.yun.ma.yi.app.module.member.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.MemberInfo;
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
 * 名称：会员详情
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberTotalListActivity extends BaseActivity  implements OnItemClickListener, MemberTotalListContract.View ,PullToRefreshBase.OnRefreshListener2<RecyclerView>{
    /**
     * recycleView下拉刷新
     */
    @BindView(R.id.pullRecyclerView)
    PullToRefreshRecyclerView pullRecyclerView;
    /**
     * 适配器
     */
    private CommonTabAdapter adapter;
    /**
     * 列表控件
     */
    private RecyclerView recyclerView;
    /**
     * 数据处理类
     */
    private MemberTotalListPresenter presenter;
    /**
     * 页码
     */
    private int page=1;
    /**
     * 页数
     */
    private int size=15;
    /**
     * 数据处理
     */
    private List<MemberInfo> memberInfoList;
    /**
     * 是否还有列表
     */
    private boolean hasMoreData=true;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_member_total_account;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.member_detail);
        memberInfoList = new ArrayList<>();
        recyclerView = pullRecyclerView.getRefreshableView();
        pullRecyclerView.setOnRefreshListener(this);
        adapter = new CommonTabAdapter(memberInfoList, Constants.TYPR_MEMBER_LIST);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        recyclerView.setBackgroundResource(R.drawable.rect_border_bg);
        adapter.setOnItemClickListener(this);
        presenter  = new MemberTotalListPresenter(MemberTotalListActivity.this,this);
        presenter.getTotalMemberList();
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this,MemberInfoActivity.class);
        intent.putExtra("memberInfo",memberInfoList.get(position));
        startActivityForResult(intent,Constants.REQUEST_GOOODS_EDIT);
       // startActivity(intent);
    }

    @Override
    public int getSellerId() {
        return UserMessage.getInstance().getUId();
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
    public void setMemberInfoList(List<MemberInfo> memberInfoList) {
        if (page==1){
            this.memberInfoList.clear();
        }
        hasMoreData = memberInfoList.size()==size?true:false;
        pullRecyclerView.setMode(hasMoreData?PullToRefreshBase.Mode.PULL_FROM_END:PullToRefreshBase.Mode.DISABLED);
        this.memberInfoList.addAll(memberInfoList);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
       // page=1;
    }
    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.getTotalMemberList();
        refreshView.onRefreshComplete();
        if (!hasMoreData){
            G.showToast(this,"没有更多数据了！");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK==resultCode){
            presenter.getTotalMemberList();
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
