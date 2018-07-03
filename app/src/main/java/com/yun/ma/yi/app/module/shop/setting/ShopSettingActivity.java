package com.yun.ma.yi.app.module.shop.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.CityInfo;
import com.yun.ma.yi.app.bean.DistrictInfo;
import com.yun.ma.yi.app.bean.ProvinceInfo;
import com.yun.ma.yi.app.bean.ShopInfo;
import com.yun.ma.yi.app.module.common.view.ItemMidEditText;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yun.ma.yi.app.utils.TextUtils;
import com.yun.ma.yi.app.widget.MySpinner;
import com.yunmayi.app.manage.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yunmayi.app.manage.R.id.tv_distribution_scope;
import static com.yunmayi.app.manage.R.id.tv_province;

/**
 * 作者： wh
 * 时间：  2017/11/20
 * 名称：店铺设置
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopSettingActivity extends BaseActivity implements MySpinner.OnItemSelectListener, LocationContract.ViewEdit {
    /**
     * 店铺名称
     */
    @BindView(R.id.tv_shop_name)
    ItemMidEditText tvShopName;
    /**
     * 店铺公告
     */
    @BindView(R.id.et_shop_notice)
    EditText etShopNotice;
    /**
     * 配送范围
     */
    @BindView(tv_distribution_scope)
    ItemMidEditText tvDistributionScope;
    /**
     * 起送金额
     */
    @BindView(R.id.tv_delivery_amount)
    ItemMidEditText tvDeliveryAmount;
    /**
     * 配送金额
     */
    @BindView(R.id.tv_distribution_amount)
    ItemMidEditText tvDistributionAmount;
    /**
     * 白天营业开始时间
     */
    @BindView(R.id.tv_day_open_time)
    TextView tvDayOpenTime;
    /**
     * 白天营业结束时间
     */
    @BindView(R.id.tv_day_close_time)
    TextView tvDayCloseTime;
    /**
     * 凌晨营业开始时间
     */
    @BindView(R.id.tv_night_open_time)
    TextView tvNightOpenTime;
    /**
     * 凌晨营业结束时间
     */
    @BindView(R.id.tv_night_close_time)
    TextView tvNightCloseTime;
    /**
     * 小店店主
     */
    @BindView(R.id.tv_shop_owner)
    ItemMidEditText tvShopOwner;
    /**
     * 联系方式
     */
    @BindView(R.id.tv_shop_number)
    ItemMidEditText tvShopNumber;
    /**
     * 省
     */
    @BindView(tv_province)
    TextView tvProvince;
    /**
     * 市
     */
    @BindView(R.id.tv_city)
    TextView tvCity;
    /**
     * 区
     */
    @BindView(R.id.tv_area)
    TextView tvArea;
    /**
     * 详细地址
     */
    @BindView(R.id.et_address)
    EditText etAddress;
    /**
     * 定位
     */
    @BindView(R.id.tv_location)
    TextView tvLocation;
    /**
     * 是否营业
     */
    @BindView(R.id.tg_switch)
    ToggleButton tgSwitch;
    /**
     * 是否货到付款
     */
    @BindView(R.id.tg_array_pay)
    ToggleButton tgArrayPay;
    /**
     * 是否线上支付
     */
    @BindView(R.id.tg_online_pay)
    ToggleButton tgOnlinePay;
    /**
     * 下拉匡
     */
    private MySpinner mSpinner;
    /**
     * 当前选中的位置
     */
    private int position = 0;
    /**
     * 下拉数据列表
     */
    private List<String> dataList;
    /**
     * 下拉区域数据列表
     */
    private List<String> areaList;
    /**
     * 纬度
     */
    private double latitude=-1;
    /**
     * 经度
     */
    private double longitude=-1;
    private LocationPresenter presenter;
    /**
     * 城市id
     */
    private int cityId;
    /**
     * 省份id
     */
    private int provId;
    /**
     * 区域id
     */
    private int disId;
    /**
     * 省列表
     */
    private List<ProvinceInfo> provinceInfoList;
    /**
     * 市列表
     */
    private List<CityInfo> cityInfoList;
    /**
     * 区列表
     */
    private List<DistrictInfo> districtInfoList;
    private DecimalFormat decimalFormat;
    /**
     * 配送范围
     */
    private int range ;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_shop_setting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.shop_settings);
        dataList = new ArrayList<>();
        areaList = new ArrayList<>();
        provinceInfoList = new ArrayList<>();
        cityInfoList = new ArrayList<>();
        districtInfoList =new ArrayList<>();
        mSpinner = new MySpinner(this);
        mSpinner.setOnItemSelectListener(this);
        presenter = new LocationPresenter(this,this);
        decimalFormat = new DecimalFormat("#.000000");
        setShopInfo();


    }
    private void setShopInfo(){
        ShopInfo shopInfo = (ShopInfo) getIntent().getSerializableExtra("shopInfo");
        if (shopInfo!=null){
            tvShopName.setText(shopInfo.getTitle());
            etShopNotice.setText(shopInfo.getNotice());
            tvDistributionScope.setText(String.valueOf(shopInfo.getDelivery_range()));//
            tvDistributionAmount.setText(PriceTransfer.chageMoneyToString(shopInfo.getDelivery_fee()));//配送金额
            tvDeliveryAmount .setText(PriceTransfer.chageMoneyToString(shopInfo.getMinimum_amount()));//起送金额
            tvDayOpenTime.setText(shopInfo.getDay_open_time());
            tvDayCloseTime.setText(shopInfo.getDay_close_time());
            tvNightOpenTime.setText(shopInfo.getNight_open_time());
            tvNightCloseTime.setText(shopInfo.getNight_close_time());
            tvProvince.setText(shopInfo.getProv_name());
            tvArea.setText(shopInfo.getDistrict_name());
            tvCity.setText(shopInfo.getCity_name());
            etAddress.setText(shopInfo.getAddress());
            cityId = shopInfo.getCity_id();
            disId = shopInfo.getDistrict_id();
            provId = shopInfo.getProv_id();
            tvShopOwner.setText(shopInfo.getName());
            tvShopNumber.setText(shopInfo.getMobile());
            tgSwitch.setChecked(shopInfo.getShop_status()==2);
            tgArrayPay.setChecked(shopInfo.getSupport_cod()==2);
            tgOnlinePay.setChecked(shopInfo.getSupport_online()==2);
            longitude =shopInfo.getLongitude()/1000000;
            latitude = shopInfo.getLatitude()/1000000;
            range = shopInfo.getDelivery_range();
             tvArea.setEnabled(false);
             tvCity.setEnabled(false);
             tvProvince.setEnabled(false);

        }else {
            UserMessage userMessage = UserMessage.getInstance();
            tvShopName.setText(userMessage.getShopName());
            tvShopNumber.setText(userMessage.getMobile());
            etAddress.setText(userMessage.getShopAddress());
            tvProvince.setText(userMessage.getProvName());
            tvArea.setText(userMessage.getAreaName());
            tvCity.setText(userMessage.getCityName());
            cityId = userMessage.geCityId();
            disId = userMessage.getAreaId();
            provId = userMessage.getProvId();
        }
    }

    /**
     * 保存
     */
    @OnClick(R.id.tv_save)
    public void save() {
        presenter.updateShopInfo();
    }

    /**
     * 定位
     */
    @OnClick(R.id.tv_location)
    public void location() {
        Intent intent = new Intent(this, ShopSettingLocationActivity.class);
        intent.putExtra("address", etAddress.getText().toString());
        intent.putExtra("city",tvCity.getText().toString());
        intent.putExtra("latitude",latitude);
        intent.putExtra("longitude",longitude);
        intent.putExtra("range",range);
        startActivityForResult(intent,1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK==resultCode){
            G.log("xxxxxxxxxxxxxx---before"+latitude);
            G.log("xxxxxxxxxxxxxx---before"+longitude);
             latitude = data.getDoubleExtra("latitude",latitude);
             longitude = data.getDoubleExtra("longitude",longitude);
             G.log("xxxxxxxxxxxxxx---after"+latitude);
             G.log("xxxxxxxxxxxxxx---after"+longitude);
             presenter.doSearchByLocation();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.tv_day_open_time, R.id.tv_day_close_time, R.id.tv_night_open_time, R.id.tv_night_close_time})
    public void time(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        mSpinner.setWidth(view.getWidth());
        mSpinner.setHeight(G.dp2px(this, 200));
        String data = "";
        switch (view.getId()) {
            case R.id.tv_day_open_time:
                dataList = TextUtils.getTimeList();
                data = tvDayOpenTime.getText().toString();
                break;
            case R.id.tv_day_close_time:
                dataList = TextUtils.getTimeList();
                data = tvDayCloseTime.getText().toString();
                break;
            case R.id.tv_night_open_time:
                dataList = TextUtils.getTimeList();
                data = tvNightOpenTime.getText().toString();
                break;
            case R.id.tv_night_close_time:
                dataList = TextUtils.getTimeList();
                data = tvNightCloseTime.getText().toString();
                break;

        }
        position = TextUtils.getPosition(dataList, data);
        mSpinner.setDataList(view.getId(), dataList, position);
        mSpinner.showAsDropDown(view);
    }
    @OnClick({R.id.tv_night_close_time, tv_province, R.id.tv_city, R.id.tv_area})
    public void area(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        mSpinner.setWidth(view.getWidth());
        mSpinner.setHeight(G.dp2px(this, 200));
        switch (view.getId()) {
            case tv_province:
                presenter.getProvinces();
                break;
            case R.id.tv_city:
                presenter.getCities();
                break;
            case R.id.tv_area:
                presenter.getDistricts();
                break;
        }
    }
    @Override
    public void onItemSelect(int viewId, int position) {
        switch (viewId) {
            case R.id.tv_day_open_time:
                tvDayOpenTime.setText(dataList.get(position));
                break;
            case R.id.tv_day_close_time:
                tvDayCloseTime.setText(dataList.get(position));
                break;
            case R.id.tv_night_open_time:
                tvNightOpenTime.setText(dataList.get(position));
                break;
            case R.id.tv_night_close_time:
                tvNightCloseTime.setText(dataList.get(position));
                break;
            case tv_province:
                tvProvince.setText(areaList.get(position));
                provId = provinceInfoList.get(position).getNumber();
                tvCity.setText("城市");
                tvArea.setText("区域");
                break;
            case R.id.tv_city:
                tvCity.setText(areaList.get(position));
                cityId = cityInfoList.get(position).getNumber();
                break;
            case R.id.tv_area:
                tvArea.setText(areaList.get(position));
                break;
        }
    }


    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public int getProvId() {
        return provId;
    }

    @Override
    public int getCityId() {
        return cityId;
    }

    @Override
    public int getDistrictId() {
        return disId;
    }

    @Override
    public String getShopName() {
        return tvShopName.getText();
    }

    @Override
    public String getNotice() {
        return etShopNotice.getText().toString();
    }

    @Override
    public int getRange() {
        String range = tvDistributionScope.getText();
        return  Integer.parseInt(G.isEmteny(range)?"1500":range);
    }

    @Override
    public double getAccount() {//配送金额
        String account = tvDeliveryAmount.getText();
        return Double.parseDouble(G.isEmteny(account)?"0":account);
    }

    @Override
    public double getFree() {//起送金额
        String free = tvDistributionAmount.getText();
        return  Double.parseDouble(G.isEmteny(free)?"0":free);
    }

    @Override
    public String getDayOpenTime() {
        return tvDayOpenTime.getText().toString();
    }

    @Override
    public String getDayCloseTime() {
        return tvDayCloseTime.getText().toString();
    }

    @Override
    public String getNightOpenTime() {
        return tvNightOpenTime.getText().toString();
    }

    @Override
    public String getNightCloseTime() {
        return tvNightCloseTime.getText().toString();
    }

    @Override
    public String getName() {
        return tvShopOwner.getText();
    }

    @Override
    public String getMobile() {
        return tvShopNumber.getText();
    }

    @Override
    public int getShopStatus() {
        return tgSwitch.isChecked()?2:1;
    }

    @Override
    public int getCod() {
        return tgArrayPay.isChecked()?2:1;
    }

    @Override
    public int getOnline() {
        return tgOnlinePay.isChecked()?2:1;
    }

    @Override
    public String getLng() {
        return decimalFormat.format(longitude);
    }

    @Override
    public String getLat() {
        return decimalFormat.format(latitude);
    }

    @Override
    public String getAddress() {
        return etAddress.getText().toString();
    }

    @Override
    public void setProvinceInfoList(List<ProvinceInfo> provinceInfoList) {
        this.provinceInfoList.clear();
        this.provinceInfoList.addAll(provinceInfoList);
        areaList.clear();
        for (ProvinceInfo provinceInfo:provinceInfoList){
            areaList.add(provinceInfo.getName());
        }
        position = TextUtils.getPosition(areaList, tvProvince.getText().toString());
        provId = provinceInfoList.get(position).getNumber();
        mSpinner.setDataList(R.id.tv_province, areaList, position);
        mSpinner.showAsDropDown(tvProvince);
    }

    @Override
    public void setCityInfoList(List<CityInfo> cityInfoList) {
        this.cityInfoList.clear();
        this.cityInfoList.addAll(cityInfoList);
        areaList.clear();
        for (CityInfo cityInfo:cityInfoList){
            areaList.add(cityInfo.getName());
        }
        position = TextUtils.getPosition(areaList, tvCity.getText().toString());
        cityId = cityInfoList.get(position).getNumber();
        mSpinner.setDataList(R.id.tv_city, areaList, position);
        mSpinner.showAsDropDown(tvCity);
    }

    @Override
    public void setDistrictInfoList(List<DistrictInfo> districtInfoList) {
        this.districtInfoList.clear();
        this.districtInfoList.addAll(districtInfoList);
        areaList.clear();
        for (DistrictInfo districtInfo:districtInfoList){
            areaList.add(districtInfo.getName());
        }
        position = TextUtils.getPosition(areaList, tvArea.getText().toString());
        disId = districtInfoList.get(position).getNumber();
        mSpinner.setDataList(R.id.tv_area, areaList, position);
        mSpinner.showAsDropDown(tvArea);
    }


    @Override
    public void setRegeocodeResult(RegeocodeResult regeocodeResult) {
        RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
       // String formatAddress = regeocodeAddress.getFormatAddress();
        //etAddress.setText(formatAddress.substring(9));
     //   tvArea.setText(regeocodeAddress.getDistrict());
      //  tvCity.setText(regeocodeAddress.getCity());
      //  tvProvince.setText(regeocodeAddress.getProvince());
        G.log("wwxwxwxw"+etAddress.getText().toString());
    }

    @Override
    public void setSuccessBack() {
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}
