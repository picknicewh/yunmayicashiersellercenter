package com.yun.ma.yi.app.module.staff.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.SubUserInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StaffInfoActivity extends BaseActivity implements View.OnClickListener, OnItemClickListener, StaffInfoEditContract.View {
    @BindView(R.id.rv_staff)
    RecyclerView rvStaff;
    /**
     * 适配器
     */
    private StaffInfoAdapter adapter;
    /**
     * 信息列表
     */
    private List<SubUserInfo> subUserList;
    private StaffInfoEditPresenter presenter;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_staff_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.staff_info);
        setSubTitleText("新增");
        setSubtitleClickListener(this);
        subUserList = new ArrayList<>();
        adapter  = new StaffInfoAdapter(this,subUserList,R.layout.item_role_choose);
        rvStaff.setLayoutManager(new LinearLayoutManager(this));
        rvStaff.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter = new StaffInfoEditPresenter(this,this);
        presenter.getSubUserList();
    }

    @Override
    public void onClick(View v) {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_STAFF_INFO_ADD)){
            Intent intent = new Intent(this, StaffInfoEditActivity.class);
            intent.putExtra("operation", Constants.TYPR_ROLE_ADD);
            startActivityForResult(intent,Constants.TYPR_ROLE_ADD);
        }else {
            showMessage("你没有新增员工信息的权限！");
        }
    }

    @Override
    public void onClick(View view, int position) {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_STAFF_INFO_EDIT)){
            Intent intent = new Intent(this, StaffInfoEditActivity.class);
            intent.putExtra("operation", Constants.TYPR_ROLE_EDIT);
            intent.putExtra("subUserInfo",subUserList.get(position));
            startActivityForResult(intent,Constants.TYPR_ROLE_EDIT);
        }else {
            showMessage("你没有编辑员工信息的权限！");
        }

    }
 /*   @Override
    public int getUserId() {
        return UserMessage.getInstance().getUId();
    }*/

    @Override
    public void setSubUserList(List<SubUserInfo> subUserList) {
        this.subUserList.clear();
        this.subUserList.addAll(subUserList);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            presenter.getSubUserList();
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
