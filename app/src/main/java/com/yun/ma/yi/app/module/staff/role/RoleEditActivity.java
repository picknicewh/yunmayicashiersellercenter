package com.yun.ma.yi.app.module.staff.role;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.RoleInfo;
import com.yun.ma.yi.app.bean.RoleRuleInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.view.ItemEditText2;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/7/26
 * 名称：新增/修改角色
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RoleEditActivity extends BaseActivity implements StaffRoleContract.ViewEdit, RoleEditAdapter.onChildItemClickListener, RoleEditAdapter.OnParentCheckedChangeListener {
    /**
     * 员工姓名
     */
    @BindView(R.id.tv_staff_name)
    ItemEditText2 tvStaffName;
    /**
     * 权限
     */
    @BindView(R.id.rv_permission)
    RecyclerView rvPermission;
    /**
     * 操作/（编辑+新增）
     */
    private int operation;
    /**
     *适配器
     */
    private RoleEditAdapter adapter;
    /**
     * 数据处理类
     */
    private StaffRolePresenter presenter;
    /**
     * id
     */
    private int id = 0;
    private RoleInfo roleInfo;
    /**
     * 权限列表
     */
    private    List<RoleRuleInfo> roleRuleInfoList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_role_edit;
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        initData();
        roleRuleInfoList  = new ArrayList<>();
        adapter = new RoleEditAdapter(this,roleRuleInfoList);
        rvPermission.setAdapter(adapter);
        rvPermission.setNestedScrollingEnabled(false);
        rvPermission.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnChildItemClickListener(this);
        adapter.setOnParentCheckedChangeListener(this);
    }
    private void initData(){
        presenter  = new StaffRolePresenter(this,this);
        operation  = getIntent().getIntExtra("operation", Constants.TYPR_ROLE_ADD);
        roleInfo = (RoleInfo) getIntent().getSerializableExtra("roleInfo");
        switch (operation){
            case Constants.TYPR_ROLE_ADD:
                setTitleTextId(R.string.staff_role_add);
                presenter.getRuleList();
                break;
            case  Constants.TYPR_ROLE_EDIT:
                id = roleInfo.getId();
                tvStaffName.setText(roleInfo.getName());
                setTitleTextId(R.string.staff_role_edit);
                presenter.getRuleByRole();
                break;
        }
    }
    @OnClick(R.id.tv_conform)
    public void conform() {
        switch (operation){
            case Constants.TYPR_ROLE_ADD:
                presenter.addRole();
                break;
            case  Constants.TYPR_ROLE_EDIT:
                presenter.editRole();
                break;
        }
    }
    @Override
    public RoleInfo getData() {
        setRoleRuleInfoList(adapter.getMapList());
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setId(id);
        roleInfo.setUser_id(UserMessage.getInstance().getUId());
        String  staffName = tvStaffName.getText().toString();
        roleInfo.setName(staffName);
        roleInfo.setRule_ids(getRuleIds());
        return  roleInfo;
    }
    @Override
    public int getRoleId() {
        return id;
    }


    @Override
    public void setRolePermissionInfoList(List<RoleRuleInfo> roleRuleInfoList) {
        this.roleRuleInfoList.clear();
        this.roleRuleInfoList.addAll(roleRuleInfoList);
        if (adapter!=null){
            adapter.initData(operation);
        }
    }
    @Override
    public void back() {
        Intent intent = new Intent(this,RoleEditActivity.class);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            int isCheck =data.getIntExtra("check",0);
             roleRuleInfoList.get(groupPosition).getList().get(childPosition).setChecked(isCheck);
        }
    }
    public  void setRoleRuleInfoList(Map<Integer,Boolean> mapList){
        if (roleRuleInfoList.size()>0){
            for (int i = 0  ;i<roleRuleInfoList.size();i++){
                if (mapList.get(i)){
                    roleRuleInfoList.get(i).setChecked(1);
                }else {
                    roleRuleInfoList.get(i).setChecked(0);
                }
            }
        }
    }
    private String getRuleIds(){
        StringBuffer buffer  = new StringBuffer();
        if (roleRuleInfoList.size()>0){
            for (int i = 0  ;i<roleRuleInfoList.size();i++){
                RoleRuleInfo roleRuleInfo = roleRuleInfoList.get(i);
                if (roleRuleInfo.getChecked()==1){
                    buffer.append(roleRuleInfo.getId()).append(",");
                   for (int j = 0 ;j<roleRuleInfo.getList().size();j++){
                       if (roleRuleInfo.getList().get(j).getChecked()==1){
                           buffer.append(roleRuleInfo.getList().get(j).getId()).append(",");
                       }
                   }
                }
            }
            if (buffer.length()>0){
                buffer.deleteCharAt(buffer.length()-1);
            }
            return  buffer.toString();
        }
        return "0";
    }
    private  int groupPosition = 0;
    private  int childPosition = 0;
    @Override
    public void onChildClick(View view, int groupPosition, int childPosition) {
        this.groupPosition = groupPosition;
        this.childPosition  = childPosition;
        Intent intent = new Intent(this,RuleChildChooseActivity.class);
        RoleRuleInfo.ChildRuleInfo ruleInfo = roleRuleInfoList.get(groupPosition).getList().get(childPosition);
        intent.putExtra("name",ruleInfo.getName());
        intent.putExtra("check",ruleInfo.getChecked());
        startActivityForResult(intent,1);
    }
    @Override
    public void onCheckedChange(boolean isCheck, int position) {
        for (int i = 0 ;i<roleRuleInfoList.get(position).getList().size();i++){
            roleRuleInfoList.get(position).getList().get(i).setChecked(isCheck?1:0);
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
