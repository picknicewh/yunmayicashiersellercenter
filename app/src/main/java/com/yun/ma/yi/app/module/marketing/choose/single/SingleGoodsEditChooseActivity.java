package com.yun.ma.yi.app.module.marketing.choose.single;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.module.common.view.ConformDeleteDialog;
import com.yun.ma.yi.app.module.marketing.choose.GoodsChooseContract;
import com.yun.ma.yi.app.module.marketing.choose.GoodsChoosePresenter;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SingleGoodsEditChooseActivity extends BaseActivity implements View.OnClickListener, GoodsChooseContract.ViewSingleEdit, OnItemClickListener, ConformDeleteDialog.DeleteCallBack {
    /**
     * 商品列表
     */
    @BindView(R.id.rv_edit)
    RecyclerView rv_edit;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    /**
     * 商品选择适配器
     */
    private SingleGoodsEditChooseAdapter adapter;
    /**
     * 赠送id
     */
    private String delivery_product_id;
    /**
     * 数据处理类
     */
    private GoodsChoosePresenter presenter;
    /**
     * 商品数据列表
     */
    private List<GoodsDetailInfo> goodsDetailInfoList;

    /**
     * 确认删除对话框
     */
    private ConformDeleteDialog deleteDialog;
    /**
     * 活动id
     */
    private int activityId;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_goods_edit_choose;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.goods_choose);
        setSubTitleText("确定");
        setSubtitleClickListener(this);
        deleteDialog = new ConformDeleteDialog(this);
        deleteDialog.setDeleteCallBack(this);
        goodsDetailInfoList = new ArrayList<>();
        //详情
        adapter = new SingleGoodsEditChooseAdapter(goodsDetailInfoList, this);
        adapter.setOnItemClickListener(this);
        rv_edit.setLayoutManager(new LinearLayoutManager(this));
        rv_edit.setAdapter(adapter);
        initSource();
    }

    private void initSource() {
        activityId = getIntent().getIntExtra("activityId", -1);
        presenter = new GoodsChoosePresenter(this, this);
        tvAdd.setText("修改");
        delivery_product_id = getIntent().getStringExtra("delivery_product_id");
        presenter.getGoodItemsByIds();
    }
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.subtitle_text:
                Intent intent = new Intent();
                intent.putExtra("delivery_product_id", delivery_product_id);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }


    @OnClick(R.id.tv_add)
    public void add() {
        Intent intent =new Intent(this, SingleGoodsAddChooseActivity.class);
        startActivityForResult(intent, getIntent().getIntExtra("source",Constants.DELIVERY_ONE_GOODS_CHOOSE));
    }

    @Override
    public int getUserId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getIds() {
        return delivery_product_id;
    }



    @Override
    public void setGoodsDetailInfoList(List<GoodsDetailInfo> detailInfoList) {
        this.goodsDetailInfoList.clear();
        this.goodsDetailInfoList.addAll(detailInfoList);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onClick(View view, int position) {
        deleteDialog.show();
        deleteDialog.setContent(goodsDetailInfoList.get(position).getTitle() + "?", position);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//新增商品/替换选中的赠送商品
            delivery_product_id = data.getStringExtra("delivery_product_id");
            presenter.getGoodItemsByIds();
        }
    }
    @Override
    public void callBack(int position) {
        goodsDetailInfoList.remove(position);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }
}
