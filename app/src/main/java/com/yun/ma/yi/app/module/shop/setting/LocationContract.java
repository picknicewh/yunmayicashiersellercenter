package com.yun.ma.yi.app.module.shop.setting;

import com.amap.api.location.AMapLocation;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.CityInfo;
import com.yun.ma.yi.app.bean.DistrictInfo;
import com.yun.ma.yi.app.bean.ProvinceInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/11/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class LocationContract {
    public interface View extends BaseView {
        String getKeyWord();
        String getCity();
        void setPoiItem(PoiItem poiItem);
        void setAMapLocation(AMapLocation location);
    }
    public interface ViewEdit extends BaseView {

        double getLatitude();
        double getLongitude();
        int getProvId();
        int getCityId();
        int getDistrictId();
        String getShopName();
        String getNotice();
        int getRange();
        double getAccount();
        double getFree();
        String getDayOpenTime();
        String getDayCloseTime();
        String getNightOpenTime();
        String getNightCloseTime();
        String getName();
        String getMobile();
        int getShopStatus();//店铺状态（1没营业，2营业）
        int getCod();//是否货到付款（1不支持，2支持）
        int getOnline();//是否支持线上支付（1.不支持，2支持）
        String getLng();
        String getLat();
        String getAddress();
        void setProvinceInfoList(List<ProvinceInfo> provinceInfo);
        void setCityInfoList(List<CityInfo> cityInfoList);
        void setDistrictInfoList(List<DistrictInfo> districtInfoList);

       // void setAddress(String address);
        void setRegeocodeResult(RegeocodeResult regeocodeResult);
        void setSuccessBack();
    }

    public interface Presenter extends BasePresenter {
        void doSearchByKeyWord();
        void doSearchByLocation();
       void location();
        void destroy();
        void getProvinces();
        void getCities();
        void getDistricts();

        void updateShopInfo();

    }
}
