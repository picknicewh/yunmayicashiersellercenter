package com.yun.ma.yi.app.module.goods.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsInfo;
import com.yun.ma.yi.app.bean.ShopGoodsInfo;
import com.yun.ma.yi.app.bean.ShopGoodsInfoDetail;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.module.common.LoadingDialog;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.module.goods.edit.GoodsEditActivity;
import com.yun.ma.yi.app.module.shop.goods.ShopGoodsEditActivity;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.StatusBarUtil;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsSearchActivity extends AppCompatActivity implements GoodsSearchContract.View ,PullToRefreshBase.OnRefreshListener2<RecyclerView>,OnItemClickListener {
    /**
     * 搜索编辑
     */
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    /**
     * 搜索
     */
    @BindView(R.id.tv_search)
    TextView tvSearch;
    /**
     * 搜索列表
     */
    @BindView(R.id.rv_search)
    PullToRefreshRecyclerView refreshRecyclerView;
    RecyclerView rvSearch;
    /**
     * 搜索适配器
     */
    private GoodsSearchAdapter adapater;
    /**
     * 查询列表
     */
    private List<GoodsDetailInfo> goodsDetailInfoList;
    /**
     * 数据处理类
     */
    private GoodsSearchPresenter presenter;
    /**
     * 页码
     */
    private int page = 1;
    /**
     * 一页条目
     */
    private int size = 10;
    /**
     * 加载框
     */
    private LoadingDialog loadingDialog;
    /**
     * 扫描得到的二维码
     */
    private String scanCode;
    /**
     * 搜索条目
     */
    private int totleCount=0;
    /**
     * 是否还有更多数据
     */
    private boolean hasData=true;
    /**
     * 是否重新搜索
     */
    private boolean isSearch =true;
    /**
     * 是否是蚂蚁小店
     */
    private boolean isShop;
    private List<ShopGoodsInfoDetail> shopGoodsInfoDetailList;
    /**
     * 蚂蚁小店id
     */
    private int shopId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_search);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        StatusBarUtil.setColor(this,getResources().getColor(R.color.red_btn));
        scanCode = getIntent().getStringExtra("scanCode");
        isShop = getIntent().getBooleanExtra("isShop",false);
        shopId =    getIntent().getIntExtra("shopId",0);
        etSearch.setText(scanCode);
        loadingDialog = new LoadingDialog(this);
        rvSearch = refreshRecyclerView.getRefreshableView();
        refreshRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        refreshRecyclerView.setOnRefreshListener(this);
        goodsDetailInfoList = new ArrayList<>();
        shopGoodsInfoDetailList =new ArrayList<>();
        if (isShop){
            adapater = new GoodsSearchAdapter(this,shopGoodsInfoDetailList);
        }else {
            adapater = new GoodsSearchAdapter(goodsDetailInfoList, this);
        }
        rvSearch.setLayoutManager(new LinearLayoutManager(this));
        rvSearch.setAdapter(adapater);
        adapater.setOnItemClickListener(this);
        presenter = new GoodsSearchPresenter(this, this);
        if (!G.isEmteny(scanCode)){
            //如果扫码进来直接搜索
            if (isShop){
                presenter.getShopGoodInfoByCatId();
            }else {
                presenter.getGoodsDetailInfoList();
            }
            showProgress();
        }
    }


    @OnClick(R.id.tv_back)
    public void back() {
        finish();
    }

    @Override
    public void toast(String msg) {
        G.showToast(this, msg);
    }

    @Override
    public void showProgress() {
        loadingDialog.show();
    }

    @Override
    public void hideProgress() {
        loadingDialog.dismiss();
    }

    @Override
    public void showMessage(String message) {
        G.showToast(this, message);
    }

    @Override
    public int getUser_id() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getKeyWord() {
        return etSearch.getText().toString();
    }

    @Override
    public int getShopId() {
        return shopId;
    }


    @Override
    public String getToken() {
        return UserMessage.getInstance().getToken();
    }

    @Override
    public void setGoodsInfo(GoodsInfo goodsInfo) {
        hideProgress();
        if (isSearch){
            this.goodsDetailInfoList.clear();
        }
        hasData  = goodsInfo.getData().size()==10;
        this.goodsDetailInfoList.addAll(goodsInfo.getData());
        totleCount  =  goodsInfo.getCount();
        if (adapater != null) {
            adapater.setTotleCount(totleCount);
            adapater.notifyDataSetChanged();
        }
    }

    @Override
    public void setShopGoodsInfo(ShopGoodsInfo shopGoodsInfo) {
        hideProgress();
        if (isSearch){
            this.shopGoodsInfoDetailList.clear();
        }
        hasData  = shopGoodsInfo.getList().size()==10;
        this.shopGoodsInfoDetailList.addAll(shopGoodsInfo.getList());
        totleCount  = totleCount+shopGoodsInfo.getList().size();
        if (adapater != null) {
            adapater.setTotleCount(totleCount);
            adapater.notifyDataSetChanged();
        }
    }



    @OnClick(R.id.tv_search)
    public void search() {
        isSearch  = true;
        hasData = true;
        page=1;
        refreshRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        if (isShop){
            presenter.getShopGoodInfoByCatId();
        }else {
            presenter.getGoodsDetailInfoList();
        }
        showProgress();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {}

    @Override
    public void onPullUpToRefresh(final PullToRefreshBase<RecyclerView> refreshView) {
        isSearch = false;
        page++;
        if (isShop){
            presenter.getShopGoodInfoByCatId();
        }else {
            presenter.getGoodsDetailInfoList();
        }
        refreshView.onRefreshComplete();
        if (!hasData ){
           refreshRecyclerView.setMode(PullToRefreshBase.Mode.DISABLED);
            G.showToast(this,"没有更多数据了！");
        }
    }
    private int position;
    @Override
    public void onClick(View view, int position) {
        this.position = position;
        Intent intent = new Intent();
        if (isShop) {
            intent.setClass(this, ShopGoodsEditActivity.class);
            intent.putExtra("goodsId", shopGoodsInfoDetailList.get(position).getId());
            intent.putExtra("shopId",shopId);
        } else {
            intent.setClass(this, GoodsEditActivity.class);
            intent.putExtra("goodsId", goodsDetailInfoList.get(position).getId());
        }
        startActivityForResult(intent, Constants.REQUEST_GOOODS_EDIT);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Constants.REQUEST_GOOODS_EDIT){
            if (resultCode==Constants.RESULT_GOOODS_EDIT){
                if (isShop){
                    presenter.getShopGoodInfoByCatId();
                }else {
                    presenter.getGoodsDetailInfoList();
                }
            }else if (resultCode==Constants.RESULT_GOOODS_DELETE){
                if (isShop){
                    shopGoodsInfoDetailList.remove(position);
                }else {
                    goodsDetailInfoList.remove(position);
                }
                if (adapater!=null){
                    adapater.setTotleCount(totleCount-1);
                    adapater.notifyDataSetChanged();
                }
            }
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
