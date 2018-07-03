package com.yun.ma.yi.app.module.goods.edit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.api.util.GlideUtils;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.CategoryInfo;
import com.yun.ma.yi.app.bean.EditGoodsDetailInfo;
import com.yun.ma.yi.app.bean.EditItemInfo;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsItemCodeInfo;
import com.yun.ma.yi.app.bean.OrderInfoDetail;
import com.yun.ma.yi.app.bean.SecondCategoryInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.ConformPriceDialog;
import com.yun.ma.yi.app.module.common.PermissionsActivity;
import com.yun.ma.yi.app.module.common.PictureChooseDialog;
import com.yun.ma.yi.app.module.common.spiner.AbstractSpinnerAdapter;
import com.yun.ma.yi.app.module.common.spiner.SpinnerPopWindow;
import com.yun.ma.yi.app.module.common.view.ConformDeleteDialog;
import com.yun.ma.yi.app.module.common.view.ItemMidEditText;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.CommonUtil;
import com.yun.ma.yi.app.utils.FileUtils;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yun.ma.yi.app.zxing.activity.CaptureActivity;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yun.ma.yi.app.utils.PermissionsChecker.REQUEST_CODE;
import static java.lang.Integer.parseInt;

/**
 * 作者： wh
 * 时间：  2017/6/22
 * 名称： 添加/编辑/删除商品界面
 * 版本说明：
 * 附加注释：
 * 主要接口:添加/编辑商品，根据id获取商品信息，删除商品，上传图片
 */
public class GoodsEditActivity extends BaseActivity implements AbstractSpinnerAdapter.IOnItemSelectListener, GoodsEditContract.View, ConformPriceDialog.OnConformClickListener, ConformDeleteDialog.DeleteCallBack {
    /**
     * 商品编码
     */
    @BindView(R.id.et_goods_code)
    ItemMidEditText etGoodsCode;
    /**
     * 一级项目
     */
    @BindView(R.id.tv_first_cat)
    TextView tvFirstCat;
    /**
     * 二级项目
     */
    @BindView(R.id.tv_second_cat)
    TextView tvSecondCat;
    /**
     * 三级项目
     */
    @BindView(R.id.tv_third_cat)
    TextView tvThirdCat;
    /**
     * 商品名字
     */
    @BindView(R.id.et_goods_name)
    ItemMidEditText etGoodsName;
    /**
     * 商品售价
     */
    @BindView(R.id.et_goods_sale_price)
    ItemMidEditText etGoodsSalePrice;
    /**
     * 商品进价
     */
    @BindView(R.id.et_goods_pur_price)
    ItemMidEditText etGoodsPurPrice;
    /**
     * 商品库存
     */
    @BindView(R.id.et_goods_stock)
    ItemMidEditText etGoodsStock;
    /**
     * 商品速记码
     */
    @BindView(R.id.et_goods_sim_code)
    ItemMidEditText etGoodsSimCode;
    /**
     * 库存预警值
     */
    @BindView(R.id.et_warn_value_high)
    ItemMidEditText etWarnValueHigh;
    /**
     * 库存预警值
     */
    @BindView(R.id.et_warn_value_low)
    ItemMidEditText etWarnValueLow;
    /**
     * 商品规格
     */
    @BindView(R.id.et_goods_format)
    ItemMidEditText etGoodsFormat;
    /**
     * 商品单位
     */
    @BindView(R.id.et_goods_unit)
    ItemMidEditText etGoodsUnit;
    /**
     * 商品保质期
     */
    @BindView(R.id.et_goods_deadline)
    ItemMidEditText etGoodsDeadline;
    /**
     * 商品不称重
     */
    @BindView(R.id.rb_not_weight)
    RadioButton rbNotWeight;
    /**
     * 商品称重
     */
    @BindView(R.id.rb_weight)
    RadioButton rbWeight;
    /**
     * 添加图片
     */
    @BindView(R.id.tv_add_image)
    TextView tvAddImage;
    /**
     * 选择的图片
     */
    @BindView(R.id.iv_picture)
    ImageView ivPicture;
    /**
     * 删除
     */
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    /**
     * 继续添加
     */
    @BindView(R.id.tv_save_continue)
    TextView tvSaveContinue;
    /**
     * 会员售价
     */
    @BindView(R.id.et_member_price)
    ItemMidEditText etMemberPrice;
    /**
     * 商品图片上架
     */
    @BindView(R.id.rb_up)
    RadioButton rbUp;
    /**
     * 商品图片下架
     */
    @BindView(R.id.rb_down)
    RadioButton rbDown;
    /**
     * 图片选择对话框
     */
    private PictureChooseDialog dialog;
    /**
     * 分类选择框
     */
    private SpinnerPopWindow mSpinerPopWindow;
    /**
     * 分类；列表
     */
    private List<String> dataList;
    /**
     * 分类
     */
    private String sort;
    /**
     * 图片路径
     */
    private String imagePath;
    /**
     * 数据处理类
     */
    private GoodsEditPresenter presenter;
    /**
     * 一级类目数据列表
     */
    private List<CategoryInfo> categoryInfos;
    /**
     * 一级类目选择的位置
     */
    private int topCatId = -1;
    /**
     * 二级类目数据列表
     */
    private List<SecondCategoryInfo> secondCategoryInfos;
    /**
     * 二级类目选择的位置
     */
    private int secondCatId = -1;
    /**
     * 三级类目数据列表
     */
    private List<SecondCategoryInfo> thildCategoryInfos;
    /**
     * 三级类目选择位置
     */
    private int thirdCatId = -1;
    /**
     * 商品id 如果是修改时商品id不为空，如果新增时商品id为空
     */
    private String goodsId;
    /**
     * 是否继续添加
     */
    private boolean isContinue = false;
    /**
     * 编辑时的图片地址
     */
    private String imageUrl;
    /**
     * 扫描的商品条形码
     */
    private String bar_code;
    /**
     * 商品价格确认对话框
     */
    private ConformPriceDialog conformPriceDialog;
    /**
     * 判断销售价格是否小于进价
     */
    private boolean isSalePriceLower = false;
    /**
     * 确认删除对话框
     */
    private ConformDeleteDialog deleteDialog;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_add_goods;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        dialog = new PictureChooseDialog(this);
        conformPriceDialog = new ConformPriceDialog(this);
        conformPriceDialog.setOnConformClickListener(this);
        deleteDialog = new ConformDeleteDialog(this);
        deleteDialog.setDeleteCallBack(this);
        presenter = new GoodsEditPresenter(this, this);
        initData();
        scanAddGoods();
    }

    /**
     * 正常的新增或者编辑商品
     * 如果goodsId为空则新增商品
     */
    private void initData() {
        initPopWindowData();
        setInStockEditView();
        //在商品列表点击点单个商品编商品
        goodsId = getIntent().getStringExtra("goodsId");
        tvSaveContinue.setVisibility(G.isEmteny(goodsId) ? View.VISIBLE : View.GONE);
        tvDelete.setVisibility(G.isEmteny(goodsId) ? View.GONE : View.VISIBLE);
        setTitleTextId(G.isEmteny(goodsId) ? R.string.goods_add : R.string.goods_edit);
        if (!G.isEmteny(goodsId)) {
            //通过商品id查询相对的商品信息
            presenter.getGoodInfoById();
        }

    }

    /**
     * 扫描添加商品
     */
    private void scanAddGoods() {
        //在商品列表页点扫描查询商品，如果商品不存在则,进入新增页面
        bar_code = getIntent().getStringExtra("scanCode");
        if (!G.isEmteny(bar_code)) {
            //根据扫描的二维码获取商品详情以便新增商品
            presenter.getGoodsInfoByCode();
        }
    }

    /**
     * 商品入库时没有商品对相应的商品新增
     * 到对应的收银机
     */
    private void setInStockEditView() {
        OrderInfoDetail goodsDetailInfo = (OrderInfoDetail) getIntent().getSerializableExtra("orderInfoDetail");
        if (goodsDetailInfo != null) {
            etGoodsCode.setText(goodsDetailInfo.getProduct_bar_code());
            etGoodsFormat.setText(goodsDetailInfo.getProduct_spec());
            etGoodsName.setText(goodsDetailInfo.getProduct_title());
            etGoodsPurPrice.setText(PriceTransfer.chageMoneyToString(goodsDetailInfo.getProduct_sell_price()));
            etGoodsSalePrice.setText(PriceTransfer.chageMoneyToString(goodsDetailInfo.getProduct_sell_price()));
            etMemberPrice.setText(PriceTransfer.chageMoneyToString(goodsDetailInfo.getProduct_sell_price()));
            etGoodsUnit.setText(goodsDetailInfo.getProduct_unit());
            etGoodsSimCode.setText(goodsDetailInfo.getProduct_number());
            imageUrl = goodsDetailInfo.getProduct_image();
            boolean hasPicture = !G.isEmteny(imageUrl);
            ivPicture.setVisibility(hasPicture ? View.VISIBLE : View.GONE);
            tvAddImage.setVisibility(hasPicture ? View.GONE : View.VISIBLE);
            if (hasPicture) {
                GlideUtils.loadImageView(this, imageUrl, ivPicture);
            }
        }

    }

    /**
     * 初始化类目选择窗口
     */
    private void initPopWindowData() {
        mSpinerPopWindow = new SpinnerPopWindow(this);
        categoryInfos = new ArrayList<>();
        thildCategoryInfos = new ArrayList<>();
        secondCategoryInfos = new ArrayList<>();
        presenter.getSortList();
        mSpinerPopWindow.setItemListener(this);
    }

    @OnClick(R.id.iv_scan)
    public void scan() {
        //打开二维码扫描界面
        if (CommonUtil.isCameraCanUse()) {
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent, Constants.REQUEST_GOOODS_ADD);
        } else {
            G.showToast(this, "请打开此应用的摄像头权限！");
        }

    }

    /**
     * 类目选择
     */
    @OnClick({R.id.tv_first_cat, R.id.tv_second_cat, R.id.tv_third_cat})
    public void goodsCode(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_rec_arrow_down);
        int width = view.getMeasuredWidth() - drawable.getIntrinsicWidth() - G.dp2px(this, 5);
        int minWidth = G.dp2px(this, 60);
        if (width < minWidth) {
            width = minWidth;
        }
        mSpinerPopWindow.setWidth(width);
        switch (view.getId()) {
            case R.id.tv_first_cat:
                this.dataList = presenter.getCategoryTitle(categoryInfos);
                sort = Constants.GOODS_FIRST_SORT;
                break;
            case R.id.tv_second_cat:
                this.dataList = presenter.getSecondCategoryTitle(secondCategoryInfos);
                sort = Constants.GOODS_SECOND_SORT;
                break;
            case R.id.tv_third_cat:
                this.dataList = presenter.getSecondCategoryTitle(thildCategoryInfos);
                sort = Constants.GOODS_THIRD_SORT;
                break;
        }
        mSpinerPopWindow.refreshData(dataList, 0);
        mSpinerPopWindow.showAsDropDown(view, -G.dp2px(this, 5), 0);
    }

    @OnClick(R.id.tv_save)
    public void save() {
        if (!isConformHasShow) {
            //如果确认价格的对话框没有显示，那么则根据价格来判断对话框是否显示，否则不再判断对话框已经显示
            isSalePriceLower = getCost() > getPrice();
        }
        isContinue = false;
        //添加商品
        if (G.isEmteny(goodsId)) {
            if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_GOODS_ADD)) {
                if (isSalePriceLower) {
                    conformPriceDialog.show();
                } else if (!G.isEmteny(imagePath)) {
                    presenter.upLoadImage(imagePath);
                } else {
                    presenter.addItemInfo();
                }
            } else {
                showMessage("你没有添加商品的权限哦！");
            }
        } else {
            if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_CARD_EDIT)) {
                if (isSalePriceLower) {
                    conformPriceDialog.show();
                } else if (!G.isEmteny(imagePath)) {
                    presenter.upLoadImage(imagePath);
                } else {
                    presenter.addItemInfo();
                }
            } else {
                showMessage("你没有编辑商品的权限哦！");
            }
        }
    }

    @OnClick(R.id.tv_save_continue)
    public void saveContinue() {
        if (!isConformHasShow) {
            //如果确认价格的对话框没有显示，那么则根据价格来判断对话框是否显示，否则不再判断对话框已经显示
            isSalePriceLower = getCost() > getPrice();
        }
        isContinue = true;
        //添加商品
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_GOODS_ADD)) {
            if (isSalePriceLower) {
                conformPriceDialog.show();
            } else if (!G.isEmteny(imagePath)) {
                presenter.upLoadImage(imagePath);
            } else {
                presenter.addItemInfo();
            }
        } else {
            showMessage("你没有添加商品的权限哦！");
        }
    }

    @OnClick(R.id.tv_delete)
    public void delete() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_GOODS_DELETE)) {
            deleteDialog.show();
            deleteDialog.setContent(etGoodsName.getText() + "商品吗？", -1);
        } else {
            showMessage("你没有删除商品的权限哦！");
        }
    }

    @Override
    public void callBack(int position) {
        presenter.deleteItemInfo();
    }

    @OnClick({R.id.tv_add_image, R.id.iv_picture})
    public void addImage() {
        //添加图片对话框
        dialog.show();
    }

    /**
     * 项目选择监听
     */
    @Override
    public void onItemClick(int pos) {
        switch (sort) {
            case Constants.GOODS_FIRST_SORT:
                if (categoryInfos != null && categoryInfos.size() > 0) {
                    tvFirstCat.setText(categoryInfos.get(pos).getTitle());
                    tvSecondCat.setText("二级类目");
                    tvThirdCat.setText("三级类目");
                    presenter.getSecondSortList(categoryInfos.get(pos).getId(), sort);
                    topCatId = categoryInfos.get(pos).getId();
                    secondCatId = -1;
                    thirdCatId = -1;
                }
                break;
            case Constants.GOODS_SECOND_SORT:
                if (secondCategoryInfos != null && secondCategoryInfos.size() > 0) {
                    tvSecondCat.setText(secondCategoryInfos.get(pos).getName());
                    presenter.getSecondSortList(secondCategoryInfos.get(pos).getId(), sort);
                    secondCatId = secondCategoryInfos.get(pos).getId();
                    thirdCatId = -1;
                }
                break;
            case Constants.GOODS_THIRD_SORT:
                tvThirdCat.setText(dataList.get(pos));
                thirdCatId = thildCategoryInfos.get(pos).getId();
                break;
        }
    }

    @Override
    public void setSortList(List<CategoryInfo> categoryInfoList) {
        this.categoryInfos.clear();
        this.categoryInfos.addAll(categoryInfoList);
    }

    @Override
    public void setSecondSortList(List<SecondCategoryInfo> secondCategoryInfoList) {
        this.secondCategoryInfos.clear();
        this.secondCategoryInfos.addAll(secondCategoryInfoList);
    }

    @Override
    public void setThirdSortList(List<SecondCategoryInfo> secondCategoryInfoList) {
        this.thildCategoryInfos.clear();
        this.thildCategoryInfos.addAll(secondCategoryInfoList);
    }

    @Override
    public void setItemInfo(EditGoodsDetailInfo editGoodsDetailInfo) {
        GoodsDetailInfo goodsDetailInfo = editGoodsDetailInfo.getItem();
        setGoodsDetailInfo(goodsDetailInfo);
        EditItemInfo itemInfo = editGoodsDetailInfo.getInfo();
        topCatId = itemInfo.getAid();
        secondCatId = itemInfo.getBid();
        thirdCatId = itemInfo.getCid();
        secondCategoryInfos = itemInfo.getMid_category_list();
        thildCategoryInfos = itemInfo.getLeaf_category_list();
        List<String> categoryNameList = presenter.getCategoryCodeList(goodsDetailInfo.getCategory_names());
        if (categoryNameList != null && categoryNameList.size() > 0) {
            if (categoryNameList.size() == 3) {
                tvFirstCat.setText(categoryNameList.get(0));
                tvSecondCat.setText(categoryNameList.get(1));
                tvThirdCat.setText(categoryNameList.get(2));
            } else if (categoryNameList.size() == 2) {
                tvFirstCat.setText(categoryNameList.get(0));
                tvSecondCat.setText(categoryNameList.get(1));
                tvThirdCat.setText("三级类目");
            } else if (categoryNameList.size() == 1) {
                if (!G.isEmteny(categoryNameList.get(0))) {
                    tvFirstCat.setText(categoryNameList.get(0));
                } else {
                    tvFirstCat.setText("一级类目");
                }
                tvSecondCat.setText("二级类目");
                tvThirdCat.setText("三级类目");
            }
        }
    }

    @Override
    public void setGoodsDetailInfo(GoodsDetailInfo goodsDetailInfo) {
        etGoodsDeadline.setText(String.valueOf(goodsDetailInfo.getShelf_life()));
        etGoodsCode.setText(goodsDetailInfo.getBar_code());
        etGoodsFormat.setText(goodsDetailInfo.getSpec());
        etGoodsName.setText(goodsDetailInfo.getTitle());
        etGoodsPurPrice.setText(PriceTransfer.chageMoneyToString(goodsDetailInfo.getCost()));
        etMemberPrice.setText(PriceTransfer.chageMoneyToString(goodsDetailInfo.getVip_price()));
        etGoodsSalePrice.setText(PriceTransfer.chageMoneyToString(goodsDetailInfo.getPrice()));
        etGoodsUnit.setText(goodsDetailInfo.getUnit());
        etGoodsSimCode.setText(goodsDetailInfo.getNumber());
        etGoodsStock.setText(String.valueOf(goodsDetailInfo.getStock()));
        etWarnValueHigh.setText(String.valueOf(goodsDetailInfo.getStock_warning_high()));
        etWarnValueLow.setText(String.valueOf(goodsDetailInfo.getStock_warning_low()));
        rbNotWeight.setChecked(goodsDetailInfo.getIs_weigh() == 0);
        rbWeight.setChecked(goodsDetailInfo.getIs_weigh() == 1);
        imageUrl = goodsDetailInfo.getImage_url();
        boolean hasPicture = !G.isEmteny(imageUrl);
        ivPicture.setVisibility(hasPicture ? View.VISIBLE : View.GONE);
        tvAddImage.setVisibility(hasPicture ? View.GONE : View.VISIBLE);
        rbUp.setChecked(goodsDetailInfo.getItem_status() == 1);
        rbDown.setChecked(goodsDetailInfo.getItem_status() == 0);
        if (hasPicture) {
            GlideUtils.loadImageView(this, goodsDetailInfo.getImage_url(), ivPicture);
        }

    }

    /**
     * 扫描添加商品
     *
     * @param goodsItemCodeInfoList 更加扫描的商品编码获取商品列表
     *                              默认选择第一条商品信息
     */
    @Override
    public void setGoodsItemCodeInfoList(List<GoodsItemCodeInfo> goodsItemCodeInfoList) {
        if (goodsItemCodeInfoList.size() > 0) {
            GoodsItemCodeInfo goodsDetailInfo = goodsItemCodeInfoList.get(0);
            etGoodsCode.setText(goodsDetailInfo.getBar_code());
            etGoodsFormat.setText(goodsDetailInfo.getSpec());
            etGoodsName.setText(goodsDetailInfo.getTitle());
            etGoodsPurPrice.setText(PriceTransfer.chageMoneyToString(goodsDetailInfo.getCost_price()));
            etGoodsSalePrice.setText(PriceTransfer.chageMoneyToString(goodsDetailInfo.getSell_price()));
            etMemberPrice.setText(PriceTransfer.chageMoneyToString(goodsDetailInfo.getVip_price()));
            etGoodsUnit.setText(goodsDetailInfo.getUnit());
            etGoodsSimCode.setText(goodsDetailInfo.getNumber());
            etGoodsStock.setText(String.valueOf(goodsDetailInfo.getStock_num()));
            imageUrl = goodsDetailInfo.getPic_url();
            boolean hasPicture = !G.isEmteny(imageUrl);
            ivPicture.setVisibility(hasPicture ? View.VISIBLE : View.GONE);
            tvAddImage.setVisibility(hasPicture ? View.GONE : View.VISIBLE);
            if (hasPicture) {
                GlideUtils.loadImageView(this, goodsDetailInfo.getPic_url(), ivPicture);
            }
        }

    }

    @Override
    public int getUid() {
        return UserMessage.getInstance().getUId();
    }


    @Override
    public String getName() {
        return etGoodsName.getText();
    }

    @Override
    public int getTopCatId() {
        return topCatId;
    }

    @Override
    public int getMidCatId() {
        return secondCatId;
    }

    @Override
    public int getLeafCatId() {
        return thirdCatId;
    }


    @Override
    public float getPrice() {
        return Float.parseFloat(G.isEmteny(etGoodsSalePrice.getText()) ? "0" : etGoodsSalePrice.getText());
    }

    @Override
    public float getCost() {
        return Float.parseFloat(G.isEmteny(etGoodsPurPrice.getText()) ? "0" : etGoodsPurPrice.getText());
    }

    @Override
    public float getVipPrice() {
        return Float.parseFloat(G.isEmteny(etMemberPrice.getText()) ? "0" : etMemberPrice.getText());
    }

    @Override
    public String getNumber() {
        return etGoodsSimCode.getText();
    }

    @Override
    public int getStock() {
        return parseInt(G.isEmteny(etGoodsStock.getText()) ? "0" : etGoodsStock.getText());
    }

    @Override
    public String getBarCode() {
        return etGoodsCode.getText();
    }

    @Override
    public int getStockWaringHigh() {
        return parseInt(G.isEmteny(etWarnValueHigh.getText()) ? "0" : etWarnValueHigh.getText());
    }

    @Override
    public int getStockWaringLow() {
        return parseInt(G.isEmteny(etWarnValueLow.getText()) ? "0" : etWarnValueLow.getText());
    }

    @Override
    public String getSpec() {
        return etGoodsFormat.getText();
    }

    @Override
    public String getUnit() {
        return etGoodsUnit.getText();
    }

    @Override
    public int getShelfLife() {
        return Integer.parseInt(G.isEmteny(etGoodsDeadline.getText()) ? "0" : etGoodsDeadline.getText());
    }

    @Override
    public int getIsWeight() {
        if (rbWeight.isChecked()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String getId() {
        return goodsId;
    }

    @Override
    public String getCode() {
        return bar_code;
    }

    @Override
    public int getItemStatus() {
        int status = rbUp.isChecked() ? 1 : 0;
        return status;
    }

    @Override
    public void cleanInfo() {
        etGoodsDeadline.setText("");
        etGoodsCode.setText("");
        etGoodsFormat.setText("");
        etGoodsName.setText("");
        etGoodsPurPrice.setText("");
        etGoodsSalePrice.setText("");
        etGoodsUnit.setText("");
        etGoodsSimCode.setText("");
        etGoodsStock.setText("");
        etWarnValueHigh.setText("");
        etWarnValueLow.setText("");
        ivPicture.setBackgroundColor(Color.WHITE);
        topCatId = -1;
        secondCatId = -1;
        thirdCatId = -1;
        tvFirstCat.setText("一级类目");
        tvSecondCat.setText("二级类目");
        tvThirdCat.setText("三级类目");

    }

    @Override
    public boolean getIsContinue() {
        return isContinue;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
                G.showToast(this, "权限没有授取，本次操作取消，请到权限中心授权");
            }
        }
        if (resultCode == Activity.RESULT_OK) {
            ivPicture.setVisibility(View.VISIBLE);
            tvAddImage.setVisibility(View.GONE);
            //获取图片路径
            if (requestCode == Constants.RESULT_IMAG) {
                imagePath = FileUtils.getChoosePicture(this, data.getData());
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                ivPicture.setImageBitmap(bitmap);
            } else if (requestCode == Constants.RESULT_CAMERA) {
                if (Build.VERSION.SDK_INT >= 24) {
                    imagePath = dialog.getImagePath();
                    FileUtils.savePhoto(imagePath, null);
                    ivPicture.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                } else {
                    imagePath = FileUtils.getUploadPhotoFile(this);
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    FileUtils.savePhoto(imagePath, bitmap);
                    ivPicture.setImageBitmap(bitmap);
                }
            } else if (requestCode == Constants.REQUEST_GOOODS_ADD) {
                Bundle bundle = data.getExtras();
                bar_code = bundle.getString(Constants.INTENT_EXTRA_KEY_QR_SCAN);
                etGoodsCode.setText(bar_code);
                presenter.getGoodsInfoByCode();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }
    /**
     * 确认价格的对话框是否已经显示对话框是否显示
     */
    private boolean isConformHasShow = false;

    @Override
    public void onConform() {
        isConformHasShow = true;
        isSalePriceLower = false;
        if (isContinue) {
            saveContinue();
        } else {
            save();
        }
    }
}
