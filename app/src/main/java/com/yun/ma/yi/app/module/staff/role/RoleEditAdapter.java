package com.yun.ma.yi.app.module.staff.role;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.yun.ma.yi.app.bean.RoleRuleInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yunmayi.app.manage.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： wh
 * 时间：  2017/7/26
 * 名称:角色权限适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RoleEditAdapter extends RecyclerView.Adapter<RoleEditAdapter.ViewHolder> {
    private onChildItemClickListener onChildItemClickListener;
    private OnParentCheckedChangeListener onParentCheckedChangeListener;
    private Map<Integer,Boolean> mapList;
    private List<RoleRuleInfo> roleRuleInfoList;
    private Context context;
    public RoleEditAdapter(Context context,List<RoleRuleInfo> roleRuleInfoList){
        this.roleRuleInfoList = roleRuleInfoList;
        this.context = context;
    }
    public void initData(int operation){
        mapList = new HashMap<>();
        for (int i = 0  ;i<roleRuleInfoList.size();i++){
            if (operation== Constants.TYPR_ROLE_ADD){
                mapList.put(i,false);
            }else {
                RoleRuleInfo roleRuleInfo = roleRuleInfoList.get(i);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_role_edit, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final RoleRuleInfo rolePermissionInfo = roleRuleInfoList.get(position);
        holder.tv_name.setText(rolePermissionInfo.getName());
        holder.tg_switch.setChecked(mapList.get(position));
        holder.rv_child_rule.setVisibility(mapList.get(position)?View.VISIBLE: View.GONE);
        holder.rv_child_rule.setNestedScrollingEnabled(false);
        RoleChildRuleAdapter adapter =new RoleChildRuleAdapter(context,rolePermissionInfo.getList(),R.layout.item_role_choose);
        holder.rv_child_rule.setLayoutManager(new LinearLayoutManager(context));
        holder.rv_child_rule.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                if (onChildItemClickListener!=null){
                    onChildItemClickListener.onChildClick(view,position,pos);
                }
            }
        });
        holder.tg_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mapList.put(position,isChecked);
                holder.rv_child_rule.setVisibility(mapList.get(position)?View.VISIBLE: View.GONE);
                if (onParentCheckedChangeListener!=null){
                    onParentCheckedChangeListener.onCheckedChange(isChecked,position);
                }
            }
        });
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ToggleButton tg_switch;
        RecyclerView rv_child_rule;
        ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tg_switch = (ToggleButton) itemView.findViewById(R.id.tg_switch);
            rv_child_rule = (RecyclerView)itemView.findViewById(R.id.rv_child_rule);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return roleRuleInfoList.size();
    }

    public void setOnChildItemClickListener(RoleEditAdapter.onChildItemClickListener onChildItemClickListener) {
        this.onChildItemClickListener = onChildItemClickListener;
    }

    public void setOnParentCheckedChangeListener(OnParentCheckedChangeListener onParentCheckedChangeListener) {
        this.onParentCheckedChangeListener = onParentCheckedChangeListener;
    }

    public interface  OnParentCheckedChangeListener{
       void onCheckedChange(boolean isCheck,int position);
    }
    public interface onChildItemClickListener{
        void onChildClick(View view,int groupPosition,int childPosition);
    }
}
