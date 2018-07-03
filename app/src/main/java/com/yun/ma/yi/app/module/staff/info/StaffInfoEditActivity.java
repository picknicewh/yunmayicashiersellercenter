package com.yun.ma.yi.app.module.staff.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.RoleInfo;
import com.yun.ma.yi.app.bean.SubUserInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.view.ConformDeleteDialog;
import com.yun.ma.yi.app.module.common.view.ItemEditText2;
import com.yun.ma.yi.app.module.common.view.ItemTextView;
import com.yun.ma.yi.app.utils.DateUtil;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yunmayi.app.manage.R.id.tv_staff_id;

/**
 * 作者： wh
 * 时间：  2017/7/27
 * 名称：新增/修改员工
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StaffInfoEditActivity extends BaseActivity implements OptionsPickerView.OnOptionsSelectListener, StaffInfoEditContract.ViewEdit, ConformDeleteDialog.DeleteCallBack {
    /**
     * 员工号
     */
    @BindView(tv_staff_id)
    ItemEditText2 tvStaffId;
    /**
     * 员工密码
     */
    @BindView(R.id.tv_staff_password)
    ItemEditText2 tvStaffPassword;
    /**
     * 员工名字
     */
    @BindView(R.id.tv_staff_name)
    ItemEditText2 tvStaffName;
    /**
     * 员工号码
     */
    @BindView(R.id.tv_staff_number)
    ItemEditText2 tvStaffNumber;
    /**
     * 员工权限列表
     */
    @BindView(R.id.tv_staff_permission)
    ItemTextView tvStaffPermission;
    /**
     * 删除
     */
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    /**
     * 操作/（编辑+新增）
     */
    private int operation;

    /**
     * 选择权限位置
     */
    private int position=0;
    /**
     * 权限选择
     */
    private OptionsPickerView optionsPickerView;

    /**
     * 数据处理类
     */
    private StaffInfoEditPresenter presenter;
    private SubUserInfo subUserInfo;
    /**
     * 角色权限选择列表
     */
    private List<RoleInfo> roleInfoList;
    /**
     * 选择角色id
     */
    private int roleId=-1;
    /**
     * 选择角色列表
     */
    private List<String> roleList;
    /**
     * 确认删除对话框
     */
    private ConformDeleteDialog deleteDialog;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_staff_info_edit;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        initData();
        deleteDialog = new ConformDeleteDialog(this);
        deleteDialog.setDeleteCallBack(this);
        presenter = new StaffInfoEditPresenter(this,this);
        presenter.getRoleList();
        roleInfoList  = new ArrayList<>();
        roleList = new ArrayList<>();

    }
    private void initData() {
        operation = getIntent().getIntExtra("operation", Constants.TYPR_ROLE_ADD);
        subUserInfo = (SubUserInfo) getIntent().getSerializableExtra("subUserInfo");
        switch (operation) {
            case Constants.TYPR_ROLE_ADD:
                setTitleTextId(R.string.staff_add);
                tvDelete.setVisibility(View.GONE);
                break;
            case Constants.TYPR_ROLE_EDIT:
                setTitleTextId(R.string.staff_edit);
                tvDelete.setVisibility(View.VISIBLE);
                tvStaffName.setText(subUserInfo.getName());
                tvStaffId.setText(subUserInfo.getUsername());
                tvStaffId.setEnabled(false);
                tvStaffPassword.setHintText("选填");
                tvStaffNumber.setText(subUserInfo.getMobile());
                tvStaffPermission.setText(subUserInfo.getRole_name());
                roleId= subUserInfo.getRole_id();

                break;
        }
    }

    @OnClick(R.id.tv_conform)
    public void conform() {
        switch (operation) {
            case Constants.TYPR_ROLE_ADD:
                presenter.addSubUser();
                break;
            case Constants.TYPR_ROLE_EDIT:
                presenter.editSubUser();
                break;
        }
    }
    @OnClick(R.id.tv_delete)
    public void delete() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_STAFF_INFO_DEL)){
            deleteDialog.show();
            deleteDialog.setContent(subUserInfo.getName()+"员工的信息吗",-1);
        }else {
            showMessage("你没有删除员工信息权限哦！");
        }
    }
    @Override
    public void callBack(int position) {
        presenter.delSubUser();
    }
    /**
     * 角色选择
     */
    @OnClick(R.id.ll_staff_permission)
    public void staffPermission() {
        optionsPickerView.show();
    }

    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View v) {
        position = options1;
        roleId  = roleInfoList.get(position).getId();
        tvStaffPermission.setText(roleInfoList.get(position).getName());
    }



    @Override
    public String getUserName() {
        return tvStaffId.getText().toString();
    }

    @Override
    public String getPassword() {
        return tvStaffPassword.getText().toString();
    }

    @Override
    public String getName() {
        return tvStaffName.getText().toString();
    }

    @Override
    public String getMobile() {
        return tvStaffNumber.getText().toString();
    }

    @Override
    public int getRoleId() {
        return roleId;
    }

    @Override
    public int getSubUserId() {
        return subUserInfo.getId();
    }

    @Override
    public void setRoleList(List<RoleInfo> roleInfoList) {
        this.roleInfoList.clear();
        this.roleInfoList.addAll(roleInfoList);
        for (int i = 0 ;i<roleInfoList.size();i++){
            roleList.add(roleInfoList.get(i).getName());
        }
        position = getRolePosition();
        tvStaffPermission.setText(roleInfoList.get(position).getName());
        roleId = roleInfoList.get(position).getId();
        optionsPickerView  = DateUtil.getOptionPickerView("选择权限",roleList,position,this,this);
    }
    private int getRolePosition(){
        for (int i=0;i<roleInfoList.size();i++){
            RoleInfo roleInfo = roleInfoList.get(i);
            if (roleInfo.getId()==roleId){
                return i;
            }
        }
        return 0;
    }

    @Override
    public void back() {
        Intent intent = new Intent(this,StaffInfoActivity.class);
        setResult(RESULT_OK,intent);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( presenter!= null) {
            presenter.unSubscribe();
        }
    }

}
