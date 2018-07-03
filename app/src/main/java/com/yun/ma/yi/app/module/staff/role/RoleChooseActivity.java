package com.yun.ma.yi.app.module.staff.role;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.RoleInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/7/26
 * 名称：角色权限
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RoleChooseActivity extends BaseActivity implements View.OnClickListener, StaffRoleContract.View, OnItemClickListener {
    /**
     * 角色列表
     */
    @BindView(R.id.rv_role)
    RecyclerView rvRole;
    /**
     * 适配器
     */
    private RoleChooseAdapter adapter;
    /**
     *数据处理类
     */
    private StaffRolePresenter presenter;
    private List<RoleInfo> roleInfoList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_role_choose;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.role_permissions);
        setSubTitleText("新增");
        setSubtitleClickListener(this);
        roleInfoList = new ArrayList<>();
         adapter  = new RoleChooseAdapter(this,roleInfoList,R.layout.item_role_choose);
        rvRole.setLayoutManager(new LinearLayoutManager(this));
        rvRole.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter = new StaffRolePresenter(this,this);
        presenter.getRoleList();
    }

    @Override
    public void onClick(View v) {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_STAFF_ROLE_ADD)){
            Intent intent =new Intent(this,RoleEditActivity.class);
            intent.putExtra("operation", Constants.TYPR_ROLE_ADD);
            startActivityForResult(intent,Constants.TYPR_ROLE_ADD);
        }else {
            showMessage("你没有新增角色的权限哦！");
        }

    }
    @Override
    public void onClick(View view, int position) {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_STAFF_ROLE_EDIT)){
            Intent intent =new Intent(this,RoleEditActivity.class);
            intent.putExtra("operation", Constants.TYPR_ROLE_EDIT);
            intent.putExtra("roleInfo",roleInfoList.get(position));
            startActivityForResult(intent,Constants.TYPR_ROLE_EDIT);
        }else {
            showMessage("你没有编辑角色的权限哦！");
        }
    }



    @Override
    public void setRoleList(List<RoleInfo> roleInfoList) {
     this.roleInfoList.clear();
        this.roleInfoList.addAll(roleInfoList);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            presenter.getRoleList();
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
