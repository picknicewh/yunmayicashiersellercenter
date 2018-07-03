package com.yun.ma.yi.app.module.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.NoticeInfo;
import com.yun.ma.yi.app.module.common.OnItemObjClickListener;
import com.yun.ma.yi.app.module.setting.SettingContract.INoticeiView;
import com.yun.ma.yi.app.module.setting.SettingContract.ISettingPresenter;
import com.yun.ma.yi.app.module.setting.adapter.NoticeInfoAdapter;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ys on 2017/6/30.
 * 帮助 通知中心
 */

public class NoticeCenterActivity extends BaseActivity implements INoticeiView ,OnItemObjClickListener,PullToRefreshBase.OnRefreshListener2<RecyclerView>{

    @BindView(R.id.help_list)
    PullToRefreshRecyclerView helpList;
    private int page = 1;
    private int type = 1;
    private ISettingPresenter settingPresenter;
    private NoticeInfoAdapter noticeInfoAdapter;
    private List<NoticeInfo> noticeInfos;
    private RecyclerView recyclerView;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_help_center;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        type = getIntent().getIntExtra("type",1);
        if (type == 1){
            setTitleTextId(R.string.help);
        }else {
            setTitleTextId(R.string.notice);
        }
        settingPresenter = new SettingPersenter(this,this);
        noticeInfos = new ArrayList<>();
        recyclerView = helpList.getRefreshableView();
        helpList.setOnRefreshListener(this);
        noticeInfoAdapter = new NoticeInfoAdapter(this, noticeInfos, R.layout.notice_item );
        noticeInfoAdapter.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(noticeInfoAdapter);
        helpList.setRefreshing(false);

    }

    @Override
    public int gettType() {
        return type;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getSize() {
        return 15;
    }

    @Override
    public void getNoticeList(List<NoticeInfo> infos) {
        if (infos != null){
                if (infos.size() == 0){
                    helpList.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                }else{
                    if (page == 1){
                        noticeInfos.clear();
                    }
                    noticeInfos.addAll(infos);
                    noticeInfoAdapter.notifyDataSetChanged();
                }
        }
        helpList.onRefreshComplete();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page = 1;
        settingPresenter.getNoticeList();
        helpList.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        settingPresenter.getNoticeList();
    }

    @Override
    public void onClick(View view, Object oj) {
        NoticeInfo noticeInfo = (NoticeInfo)oj;
        Intent intent = new Intent(this,NoticeDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("noticeInfo",noticeInfo);
        intent.putExtras(bundle);
        intent.putExtra("type",type);
        startActivity(intent);
    }
}
