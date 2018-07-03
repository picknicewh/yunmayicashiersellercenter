package com.yun.ma.yi.app.module.shop.setting;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.CityInfo;
import com.yun.ma.yi.app.bean.DistrictInfo;
import com.yun.ma.yi.app.bean.ProvinceInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/11/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class LocationPresenter implements LocationContract.Presenter, PoiSearch.OnPoiSearchListener, GeocodeSearch.OnGeocodeSearchListener, AMapLocationListener {
    private LocationContract.View view;
    private LocationContract.ViewEdit viewEdit;
    private Context context;
    private Subscription mSubscription;
    /**
     * Poi查询条件类
     */
    private PoiSearch.Query query;
    /**
     * POI搜索
     */
    private PoiSearch poiSearch;

    private GeocodeSearch geocodeSearch;
    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    public LocationPresenter(LocationContract.View view, Context context) {
        this.view = view;
        this.context = context;
        initLoc();
    }

    public LocationPresenter(LocationContract.ViewEdit viewEdit, Context context) {
        this.viewEdit = viewEdit;
        this.context = context;
        geocodeSearch = new GeocodeSearch(context);
        geocodeSearch.setOnGeocodeSearchListener(this);
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }


    @Override
    public void doSearchByKeyWord() {
        query = new PoiSearch.Query(view.getKeyWord(), "", view.getCity());
        query.setPageSize(5);
        poiSearch = new PoiSearch(context, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    public void doSearchByLocation() {
        LatLonPoint latLonPoint = new LatLonPoint(viewEdit.getLatitude(), viewEdit.getLongitude());
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 500f, GeocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(query);     //异步查询
    }

    @java.lang.Override
    public void location() {
        mlocationClient.startLocation();
    }
    private void initLoc() {
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        mlocationClient =new AMapLocationClient(context);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.setLocationListener(this);
    }
    @java.lang.Override
    public void destroy() {
        mlocationClient.stopLocation();
        mlocationClient.onDestroy();
    }

    @Override
    public void getProvinces() {
        RequestParameter requestParameter = new RequestParameter();
        mSubscription = ApiManager.getApiManager()
                .getProvinces(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<ProvinceInfo>>>() {
                    @Override
                    public void beforeRequest() {
                        //  viewEdit.showProgress();

                    }

                    @Override
                    public void requestError(String msg) {
                        //  viewEdit.hideProgress();
                        viewEdit.showMessage(msg);
                    }

                    @Override
                    public void requestComplete() {
                        //   viewEdit.hideProgress();
                    }

                    @Override
                    public void requestSuccess(Result<List<ProvinceInfo>> data) {
                        if (data != null) {
                            viewEdit.setProvinceInfoList(data.getData());
                        }
                    }
                }, context));
    }

    @Override
    public void getCities() {
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setParam("prov_id", viewEdit.getProvId());
        mSubscription = ApiManager.getApiManager()
                .getCities(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<CityInfo>>>() {
                    @Override
                    public void beforeRequest() {
                        //    viewEdit.showProgress();
                    }

                    @Override
                    public void requestError(String msg) {
                        // viewEdit.hideProgress();
                        viewEdit.showMessage(msg);
                    }

                    @Override
                    public void requestComplete() {
                        // viewEdit.hideProgress();
                    }

                    @Override
                    public void requestSuccess(Result<List<CityInfo>> data) {
                        if (data != null) {
                            viewEdit.setCityInfoList(data.getData());
                        }
                    }
                }, context));
    }

    @Override
    public void getDistricts() {
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setParam("city_id", viewEdit.getCityId());
        mSubscription = ApiManager.getApiManager()
                .getDistricts(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<DistrictInfo>>>() {
                    @Override
                    public void beforeRequest() {
                        // viewEdit.showProgress();
                    }

                    @Override
                    public void requestError(String msg) {
                        //  viewEdit.hideProgress();
                        viewEdit.showMessage(msg);
                    }

                    @Override
                    public void requestComplete() {
                        // viewEdit.hideProgress();
                    }

                    @Override
                    public void requestSuccess(Result<List<DistrictInfo>> data) {
                        if (data != null) {
                            viewEdit.setDistrictInfoList(data.getData());
                        }
                    }
                }, context));
    }


    @Override
    public void updateShopInfo() {
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setParam("user_id", UserMessage.getInstance().getUId());
        requestParameter.setParam("title", viewEdit.getShopName());
        requestParameter.setParam("notice", viewEdit.getNotice());
        requestParameter.setParam("range", viewEdit.getRange());
        requestParameter.setParam("amount", viewEdit.getAccount());
        requestParameter.setParam("fee", viewEdit.getFree());
        requestParameter.setParam("dayOpenTime", viewEdit.getDayOpenTime());
        requestParameter.setParam("dayCloseTime", viewEdit.getDayCloseTime());
        requestParameter.setParam("nightOpenTime", viewEdit.getNightOpenTime());
        requestParameter.setParam("nightCloseTime", viewEdit.getNightCloseTime());
        requestParameter.setParam("name", viewEdit.getName());
        requestParameter.setParam("mobile", viewEdit.getMobile());
        requestParameter.setParam("shopStatus", viewEdit.getShopStatus());
        requestParameter.setParam("cod", viewEdit.getCod());
        requestParameter.setParam("online", viewEdit.getOnline());
        requestParameter.setParam("lng", viewEdit.getLng());
        requestParameter.setParam("lat", viewEdit.getLat());
        requestParameter.setParam("provId", viewEdit.getProvId());
        requestParameter.setParam("cityId", viewEdit.getCityId());
        requestParameter.setParam("districtId", viewEdit.getDistrictId());
        requestParameter.setParam("address", viewEdit.getAddress());
        if (G.isEmteny(viewEdit.getShopName()) || G.isEmteny(viewEdit.getDayOpenTime())
                || G.isEmteny(viewEdit.getDayCloseTime()) || G.isEmteny(viewEdit.getNightOpenTime()) || G.isEmteny(viewEdit.getNightCloseTime())
                || G.isEmteny(viewEdit.getName()) || G.isEmteny(viewEdit.getMobile()) || G.isEmteny(viewEdit.getLat())
                || G.isEmteny(viewEdit.getLng()) || G.isEmteny(viewEdit.getAddress())) {
            viewEdit.showMessage("必填项不能为空哦！");
            return;
        }
        if (viewEdit.getProvId() == 0 || viewEdit.getCityId() == 0 || viewEdit.getDistrictId() == 0) {
            viewEdit.showMessage("必填项不能为空哦！");
            return;
        }
        mSubscription = ApiManager.getApiManager()
                .updateShopInfo(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<String>>() {
                    @Override
                    public void beforeRequest() {
                        viewEdit.showProgress();
                    }

                    @Override
                    public void requestError(String msg) {
                        viewEdit.hideProgress();
                        viewEdit.showMessage(msg);
                    }

                    @Override
                    public void requestComplete() {
                        viewEdit.hideProgress();
                    }

                    @Override
                    public void requestSuccess(Result<String> data) {
                        if (data != null) {
                            viewEdit.showMessage(data.getData());
                            viewEdit.setSuccessBack();
                        }
                    }
                }, context));
    }

    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        boolean isSuccess = false;
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {  // 搜索poi的结果
                if (result.getQuery().equals(query)) {  // 是否是同一条
                    List<PoiItem> poiItems = result.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    if (poiItems.size() > 0) {
                        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
                            PoiItem poiItem = poiItems.get(0);
                            view.setPoiItem(poiItem);
                            isSuccess = true;
                        }
                    }
                }
            } else {
                view.showMessage("搜索失败");
            }
        }
        if (!isSuccess){
            G.log("xxxxxxxxxxxxxxxxxxx"+"搜索失败开启定位！");
            location();
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {}

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        viewEdit.setRegeocodeResult(regeocodeResult);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        G.log("xxxxxxxxxxxxxxxxxxx"+"定位回调成功！");
         view.setAMapLocation(aMapLocation);

    }
}
