package com.yun.ma.yi.app.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.zxing.activity.CaptureActivity;
import com.yunmayi.app.manage.R;

/**
 * 作者： wh
 * 时间：  2017/12/5
 * 名称：共用的扫描二维码
 * 扫描部分
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public abstract class BaseScanActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 搜索内容
     */
   private  ClearEditText etCodeSearch;
    /**
     * 二维码
     */
    private String scanCode;
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.tv_scan){
            scanCode();
        }
    }
    public void scanCode(){
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, Constants.REQUEST_GOOODS_SEARCH);
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        initScanData();
    }

    public abstract  void   initScanData();
    public void setEtCodeSearch(ClearEditText etCodeSearch) {
        this.etCodeSearch = etCodeSearch;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_GOOODS_SEARCH) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                scanCode = bundle.getString(Constants.INTENT_EXTRA_KEY_QR_SCAN);
                etCodeSearch.setText(scanCode);
                setScanCodeChange();
            }
        }
    }


    /**
     * 获取商品编码
    */
    public String getScanCode() {
        return scanCode;
    }
    public void  setScanCodeChange(){}

}
