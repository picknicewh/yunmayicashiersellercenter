package com.yun.ma.yi.app.module.member.cardsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.MemberCardInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/7/11
 * 名称：会员卡查询
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberCardSearchActivity extends BaseActivity implements OnItemClickListener ,View.OnClickListener, MemberCardSearchContract.View {
    /**
     * 适配器
     */
    private MemberCardSearchAdapter adapter;
    /**
     * 列表控件
     */
    @BindView(R.id.recyclerView)
     RecyclerView recyclerView;
    /**
     * 数据处理类
     */
    private MemberCardSearchPresenter presenter;
    private List<MemberCardInfo> memberCardInfoList;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_member_card_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleText("会员卡类型查询");
        setSubTitleText("新增");
        memberCardInfoList = new ArrayList<>();
        adapter = new MemberCardSearchAdapter(memberCardInfoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        setSubtitleClickListener(this);
        presenter = new MemberCardSearchPresenter(this,this);
        presenter.memberGradeList();
    }


    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this,MemberCardEditActivity.class);
        intent.putExtra("source", Constants.EDIT_MEMBER_CARD);
        intent.putExtra("memberCardInfo", memberCardInfoList.get(position));
        startActivityForResult(intent,1);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MemberCardEditActivity.class);
        intent.putExtra("source", Constants.ADD_MEMBER_CARD);
        startActivityForResult(intent,1);
    }

    @Override
    public int getSellerId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public void setMemberInfo(List<MemberCardInfo> memberCardInfoList) {
     this.memberCardInfoList.clear();
     this.memberCardInfoList.addAll(memberCardInfoList);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void NoMemberInfo() {
        G.showToast(this,"还没有任何卡信息哦！");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            presenter.memberGradeList();
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
