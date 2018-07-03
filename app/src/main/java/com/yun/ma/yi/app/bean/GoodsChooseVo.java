package com.yun.ma.yi.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者： wh
 * 时间：  2017/10/26
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsChooseVo implements Parcelable {
    private String goodsIds;
    private boolean isSelect;
    private  int categoryId;
     public GoodsChooseVo(){

     }
    public GoodsChooseVo(Parcel in) {
        goodsIds = in.readString();
        isSelect = in.readByte() != 0;
        categoryId = in.readInt();
    }

    public static final Creator<GoodsChooseVo> CREATOR = new Creator<GoodsChooseVo>() {
        @Override
        public GoodsChooseVo createFromParcel(Parcel in) {
            return new GoodsChooseVo(in);
        }

        @Override
        public GoodsChooseVo[] newArray(int size) {
            return new GoodsChooseVo[size];
        }
    };

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(goodsIds);
        dest.writeByte((byte) (isSelect ? 1 : 0));
        dest.writeInt(categoryId);
    }
}
