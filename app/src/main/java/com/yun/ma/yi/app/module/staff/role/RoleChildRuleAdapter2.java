package com.yun.ma.yi.app.module.staff.role;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.yun.ma.yi.app.bean.RoleRuleInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yunmayi.app.manage.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： wh
 * 时间：  2017/7/26
 * 名称:角色子权限适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RoleChildRuleAdapter2 extends RecyclerView.Adapter<RoleChildRuleAdapter2.ViewHolder> {
    private Map<Integer,Boolean> mapList;
    private List<RoleRuleInfo.ChildRuleInfo> roleRuleInfoList;

    public RoleChildRuleAdapter2(List<RoleRuleInfo.ChildRuleInfo> roleRuleInfoList){
        this.roleRuleInfoList = roleRuleInfoList;
    }
    public void initData(int operation){
        mapList = new HashMap<>();
        for (int i = 0  ;i<roleRuleInfoList.size();i++){
            if (operation== Constants.TYPR_ROLE_ADD){
                mapList.put(i,true);
            }else {
                RoleRuleInfo.ChildRuleInfo roleRuleInfo = roleRuleInfoList.get(i);
                boolean check  = roleRuleInfo.getChecked()==0?false:true;
                mapList.put(i,check);
            }
        }
        notifyDataSetChanged();
    }
    public Map<Integer, Boolean> getMapList() {
        return mapList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_role_child_choose, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final RoleRuleInfo.ChildRuleInfo rolePermissionInfo = roleRuleInfoList.get(position);
        holder.tv_role.setText(rolePermissionInfo.getName());
        holder.tg_switch.setChecked(mapList.get(position));
        holder.tg_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mapList.put(position,isChecked);
            }
        });
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_role;
        ToggleButton tg_switch;

        ViewHolder(View itemView) {
            super(itemView);
            tv_role = (TextView) itemView.findViewById(R.id.tv_role);
            tg_switch = (ToggleButton) itemView.findViewById(R.id.tg_switch);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return roleRuleInfoList.size();
    }

}
