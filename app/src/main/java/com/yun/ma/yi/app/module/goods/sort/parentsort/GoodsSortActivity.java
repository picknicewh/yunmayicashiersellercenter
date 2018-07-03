package com.yun.ma.yi.app.module.goods.sort.parentsort;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.CategoryInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.module.goods.sort.childsort.SecondGoodsSortActivity;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/6/19
 * 名称：商品分类界面
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsSortActivity extends BaseActivity implements OnItemClickListener, GoodsSortContract.View {
    @BindView(R.id.rv_goods_sort)
    RecyclerView rvGoodsSort;
    /**
     * 适配器
     */
    private GoodsSortAdapter adapter;
    /**
     * 数据处理类
     */
    private GoodsSortPresenter presenter;
    /**
     * 数据列表
     */
    private List<CategoryInfo>  categoryInfos;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_goods_sort;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.goods_sort);
        categoryInfos = new ArrayList<>();
        adapter   = new GoodsSortAdapter(this,categoryInfos,R.layout.item_goods_sort);
        rvGoodsSort.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvGoodsSort.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter  = new GoodsSortPresenter(GoodsSortActivity.this,this);
        presenter.getSortList();
    }


    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, SecondGoodsSortActivity.class);
        intent.putExtra("title",categoryInfos.get(position).getTitle());
        intent.putExtra("parentId",categoryInfos.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void setSortList(List<CategoryInfo> categoryInfos) {
        this.categoryInfos.clear();
        this.categoryInfos.addAll(categoryInfos);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
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
