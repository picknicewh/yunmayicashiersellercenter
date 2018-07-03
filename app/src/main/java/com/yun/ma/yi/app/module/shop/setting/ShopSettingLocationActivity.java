package com.yun.ma.yi.app.module.shop.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.StatusBarUtil;
import com.yunmayi.app.manage.R;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yunmayi.app.manage.R.id.mapView;

public class ShopSettingLocationActivity extends BaseActivity implements AMap.OnCameraChangeListener, LocationContract.View {
    @BindView(mapView)
    MapView mMapView;
    private AMap aMap;
    /**
     * 纬度
     */
    private double latitude;
    /**
     * 经度
     */
    private double longitude;

    /**
     * 城市
     */
    private String city;
    /**
     * 地址
     */
    private String address;

    private int range;
    /**
     * 标志的配置信息
     */
    private MarkerOptions markerOptions;
    /**
     * 数据处理
     */
    private LocationPresenter presenter;

    private int currentZoom = 17;

    @Override
    protected void beforeInit() {
        super.beforeInit();
        hideTitleLayout(true);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.half_transparent));
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_location_setting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        aMap.setOnCameraChangeListener(this);
        aMap.getUiSettings().setRotateGesturesEnabled(false);
        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("latitude", -1);
        longitude = intent.getDoubleExtra("longitude", -1);
        range = intent.getIntExtra("range", 150);
        city = intent.getStringExtra("city");
        address = intent.getStringExtra("address");
        //设置缩放级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(currentZoom));
        //将地图移动到定位点
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(latitude, longitude)));
        setMarker();
        presenter = new LocationPresenter(this, this);
        if (latitude == -1 && longitude == -1) {
            presenter.doSearchByKeyWord();
        }
    }

    /*private void addCircle() {
        circleOptions = new CircleOptions();
        circleOptions.center(new LatLng(longitude, latitude));
        circleOptions.radius(range);
        circleOptions.strokeWidth(0);
        circleOptions.fillColor(0xaabbccdd);//设置颜色，可以在此设置透明度
        aMap.addCircle(circleOptions);
    }*/

    /**
     * 设置marker
     */
    private void setMarker() {
        markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(latitude, longitude));
        markerOptions.title(city).snippet(address);
        markerOptions.draggable(true);//设置Marker可拖动
        markerOptions.visible(true);
        markerOptions.setFlat(true);//设置marker平贴地图效果
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.location_marker);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromView(imageView);
      //  BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.location_marker));
        markerOptions.icon(bitmapDescriptor);
        aMap.addMarker(markerOptions);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        presenter.destroy();
    }

    @OnClick({R.id.iv_delete, R.id.tv_conform})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_delete:
                finish();
                break;
            case R.id.tv_conform:
                longitude = markerOptions.getPosition().longitude;
                latitude = markerOptions.getPosition().latitude;
                Intent intent = new Intent();
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        //G.log("xxxxxxxxxx"+cameraPosition.zoom);
        aMap.clear();
        markerOptions.position(cameraPosition.target);
        //  circleOptions.center(cameraPosition.target);
        aMap.addMarker(markerOptions);
        //aMap.addCircle(circleOptions);
        G.log("xxxxxxxxxx" + cameraPosition.target);

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {

    }

    @Override
    public String getKeyWord() {
        return address;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setPoiItem(PoiItem poiItem) {
        LatLonPoint llp = poiItem.getLatLonPoint();
        longitude = llp.getLongitude();
        latitude = llp.getLatitude();
        city = poiItem.getCityName();
        // address = poiItem.getAdName();
        //将地图移动到定位点
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(llp.getLatitude(), llp.getLongitude())));
        markerOptions.position(new LatLng(llp.getLatitude(), llp.getLongitude()));
        markerOptions.title(poiItem.getCityName()).snippet(poiItem.getAdCode());
        aMap.addMarker(markerOptions);
        //     circleOptions.center(markerOptions.getPosition());
        //    aMap.addCircle(circleOptions);
    }


    @Override
    public void setAMapLocation(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                G.log("xxxxxxxxxxxxxxxxxxx" + "定位成功！");
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude())));    //将地图移动到定位点
                markerOptions.position(new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude()));
                longitude = amapLocation.getLongitude();
                latitude = amapLocation.getLatitude();
                G.log("xxxxxxxxxxxxxxxxxxx" + amapLocation.getAddress());
                aMap.addMarker(markerOptions);
                //   circleOptions.center(markerOptions.getPosition());
                // aMap.addCircle(circleOptions);
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                G.log("AmapError" + "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }


    @OnClick(R.id.tv_reset)
    public void reset() {
       /* //设置缩放级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        //将地图移动到定位点
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(mLatitude, mLongitude)));
        circleOptions.center(new LatLng(mLatitude, mLongitude));
        aMap.addCircle(circleOptions);
        markerOptions.position(new LatLng(mLatitude,mLongitude));
        aMap.addMarker(markerOptions);*/
        presenter.doSearchByKeyWord();
    }


}
