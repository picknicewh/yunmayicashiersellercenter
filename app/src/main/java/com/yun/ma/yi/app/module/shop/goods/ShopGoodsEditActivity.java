package com.yun.ma.yi.app.module.shop.goods;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yun.ma.yi.app.api.util.GlideUtils;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.ShopGoodsInfoDetail;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.PermissionsActivity;
import com.yun.ma.yi.app.module.common.PictureChooseDialog;
import com.yun.ma.yi.app.module.common.view.ConformDeleteDialog;
import com.yun.ma.yi.app.module.common.view.ItemMidEditText;
import com.yun.ma.yi.app.module.goods.search.GoodsSearchActivity;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.FileUtils;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yun.ma.yi.app.utils.PermissionsChecker.REQUEST_CODE;

/**
 * 作者： wh
 * 时间：  2017/11/24
 * 名称：蚂蚁小店商品编辑
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopGoodsEditActivity extends BaseActivity implements ShopGoodsEditContract.View, ConformDeleteDialog.DeleteCallBack {
    /**
     * 商品编码
     */
    @BindView(R.id.tv_goods_code)
    TextView tvGoodsCode;
    /**
     * 商品名字
     */
    @BindView(R.id.tv_goods_title)
    TextView tvGoodsTitle;
    /**
     * 商品参考价
     */
    @BindView(R.id.et_goods_advice_price)
    ItemMidEditText etGoodsAdvicePrice;
    /**
     * 商品售价
     */
    @BindView(R.id.et_goods_sale_price)
    ItemMidEditText etGoodsSalePrice;
    /**
     * 商品库存
     */
    @BindView(R.id.et_goods_stock)
    ItemMidEditText etGoodsStock;
    /**
     * 商品限购数
     */
    @BindView(R.id.et_goods_limit_count)
    ItemMidEditText etGoodsLimitCount;
    /**
     * 商品排序
     */
    @BindView(R.id.et_goods_sort)
    ItemMidEditText etGoodsSort;
    /**
     * 商品图片
     */
    @BindView(R.id.tv_add_image)
    TextView tvAddImage;
    /**
     * 商品图片
     */
    @BindView(R.id.iv_picture)
    ImageView ivPicture;
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
     * 图片路径
     */
    private String imagePath;
    /**
     * 编辑时的图片地址
     */
    private String imageUrl="";

    /**
     * 图片选择对话框
     */
    private PictureChooseDialog dialog;


    /**
     * 图片是否上架
     */
    private boolean isUp;
    /**
     * 数据处理类
     */
    private ShopGoodsEditPresenter presenter;
    /**
     * 商品id
     */
    private int itemId = -1;
    /**
     * 确认删除对话框
     */
    private ConformDeleteDialog deleteDialog;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_shop_goods_edit;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.goods_edit);
        deleteDialog = new ConformDeleteDialog(this);
        deleteDialog.setDeleteCallBack(this);
        dialog = new PictureChooseDialog(this);
        itemId = getIntent().getIntExtra("goodsId", 0);
        presenter = new ShopGoodsEditPresenter(this, this);
        presenter.getShopInfoById();

    }


    @OnClick(R.id.tv_delete)
    public void delete() {
        deleteDialog.show();
        deleteDialog.setContent(tvGoodsTitle.getText().toString(), -1);
    }
    @OnClick({R.id.tv_add_image, R.id.iv_picture})
    public void addImage() {
        //添加图片对话框
        dialog.show();
    }
    @OnClick(R.id.tv_save)
    public void save() {
        if (G.isEmteny(imagePath)) {
            presenter.editShopGoodInfo();

        } else {
            presenter.upLoadImage(imagePath);
         //   G.log("xxxxxxxxxxxxxxxxxx"+"imagePath");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
                G.showToast(this, "权限没有授取，本次操作取消，请到权限中心授权");
            }
        }
        if (resultCode == Activity.RESULT_OK ) {
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
            }
        }
    }

    @Override
    public int getItemId() {
        return itemId;
    }

    @Override
    public String getShopCatId() {
        return shopGoodsInfoDetail.getShop_cat_id();
    }

    @Override
    public String getPicUrl() {
        return imageUrl;
    }

    @Override
    public String getGoodsTitle() {
        return tvGoodsTitle.getText().toString();
    }

    @Override
    public String getSubTitle() {
        return shopGoodsInfoDetail.getSub_title();
    }

    @Override
    public double getSellPrice() {
        String sellText = etGoodsSalePrice.getText();
        double sellPrice = G.isEmteny(sellText) ? 0 : Double.parseDouble(sellText);
        return sellPrice;
    }

    @Override
    public double getMarketPrice() {
        String marketText = etGoodsAdvicePrice.getText();
        double marketPrice = G.isEmteny(marketText) ? 0 : Double.parseDouble(marketText);
        return marketPrice;
    }

    @Override
    public double getCostPrice() {
        return shopGoodsInfoDetail.getCost_price();
    }

    @Override
    public int getStockNum() {
        String stockText = etGoodsStock.getText();
        int stock = G.isEmteny(stockText) ? 0 : Integer.parseInt(stockText);
        return stock;
    }

    @Override
    public String getBarCode() {
        return tvGoodsCode.getText().toString();
    }

    @Override
    public int getLimitNum() {
        String limitCount = etGoodsLimitCount.getText();
        int limit = G.isEmteny(limitCount) ? 0 : Integer.parseInt(limitCount);
        return limit;
    }

    @Override
    public int getShopSortId() {
        String sortText = etGoodsSort.getText();
        int sort = G.isEmteny(sortText) ? 0 : Integer.parseInt(sortText);
        return sort;
    }

    @Override
    public String getToken() {
        return UserMessage.getInstance().getToken();
    }

    @Override
    public int getShopId() {
        return getIntent().getIntExtra("shopId", 0);
    }


    @Override
    public int getItemStatus() {
        int status  = rbUp.isChecked()?4:2;
        return status;
    }

    @Override
    public void setSuccessBack(int resultCode) {
        Intent intent = new Intent(this, GoodsSearchActivity.class);
        setResult(resultCode, intent);
        finish();
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
     private ShopGoodsInfoDetail shopGoodsInfoDetail;
    @Override
    public void setShopGoodsInfoDetail(ShopGoodsInfoDetail shopGoodsInfoDetail) {
        this.shopGoodsInfoDetail = shopGoodsInfoDetail;
        tvGoodsTitle.setText(shopGoodsInfoDetail.getTitle());
        tvGoodsCode.setText(shopGoodsInfoDetail.getBar_code());
        etGoodsAdvicePrice.setText(PriceTransfer.chageMoneyToString(shopGoodsInfoDetail.getMarket_price()));
        etGoodsSalePrice.setText(PriceTransfer.chageMoneyToString(shopGoodsInfoDetail.getSell_price()));
        etGoodsStock.setText(String.valueOf(shopGoodsInfoDetail.getStock_num()));
        etGoodsSort.setText(String.valueOf(shopGoodsInfoDetail.getShop_sort_id()));
        imageUrl = shopGoodsInfoDetail.getPic_url();
        boolean hasPicture = !G.isEmteny(imageUrl);
        ivPicture.setVisibility(hasPicture ? View.VISIBLE : View.GONE);
        tvAddImage.setVisibility(hasPicture ? View.GONE : View.VISIBLE);
        if (hasPicture) {
            GlideUtils.loadImageView(this, imageUrl, ivPicture);
        }
        rbUp.setChecked(shopGoodsInfoDetail.getItem_status()==4);
        rbDown.setChecked(shopGoodsInfoDetail.getItem_status()==2);
    }

    @Override
    public void callBack(int position) {
        presenter.deleteShopGoodInfo();
    }



}
