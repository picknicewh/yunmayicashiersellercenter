package com.yun.ma.yi.app.module.shop.code;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.utils.FilePath;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PermissionsChecker;
import com.yun.ma.yi.app.utils.QRCodeUtil;
import com.yunmayi.app.manage.R;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yun.ma.yi.app.utils.G.showToast;

/**
 * 作者： wh
 * 时间：  2017/11/20
 * 名称：店铺二维码
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopEwCodeActivity extends BaseActivity {

    /**
     * 二维码
     */
    @BindView(R.id.iv_ewcode)
    ImageView ivEwcode;
    /**
     * 微信api接口
     */
    private IWXAPI api;
    /**
     * 发送微信位置、朋友圈/消息列表
     */
    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;
    private  String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    /**
     * 生成的二维码图片
     */
      private Bitmap mBitmap;
    /**
     * 登陆接口链接
     */
    private String shareUrl = "http://dd.v2.yunmayi.com/ms/#/shop/index/";
    /**
     * 店铺名称
     */
    private String shopName;

     @Override
     protected int getContentViewLayoutID() {
        return R.layout.activity_shop_ewcode;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.shop_ewcode);
        api = WXAPIFactory.createWXAPI(this, Constants.WECHAR_APP_ID);
        api.registerApp(Constants.WECHAR_APP_ID);
        //动态申请权限
        PermissionsChecker checker = PermissionsChecker.getInstance(this);
        if (checker.lacksPermissions(PERMISSIONS)) {
            checker.getPerMissions(PERMISSIONS);
            return;
        }
        shopName = getIntent().getStringExtra("shopName");
        shareUrl = shareUrl+ getIntent().getIntExtra("shopId",0)+"/";
        Bitmap log = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_log);
        mBitmap  = QRCodeUtil.getEncodeBitmap(log,shareUrl);
        ivEwcode.setImageBitmap(mBitmap);
    }

    @OnClick({R.id.tv_wechar_friend, R.id.tv_wechar_circel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wechar_friend:
                mTargetScene = SendMessageToWX.Req.WXSceneSession;
                break;
            case R.id.tv_wechar_circel:
                mTargetScene = SendMessageToWX.Req.WXSceneTimeline;
                break;
        }
        shareToWeChatWithWebpage(shareUrl,mTargetScene);
    }

    /**
     * 微信分享：分享网页
     * @param url
     * @param scene
     */
    public  void shareToWeChatWithWebpage(String url, int scene){
        if (!api.isWXAppInstalled()){
          G.showToast(this,"您尚未安装微信客户端");
            return;
        }
        WXWebpageObject wxWebpageObject = new WXWebpageObject();
        wxWebpageObject.webpageUrl = url;
        WXMediaMessage wxMediaMessage = new WXMediaMessage(wxWebpageObject);
        wxMediaMessage.mediaObject = wxWebpageObject;
        if (scene==0){
            wxMediaMessage.title = shopName;
            wxMediaMessage.description =getString(R.string.share_content);
        }else {
            wxMediaMessage.title = getString(R.string.share_content);
        }
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechar_share_icon);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] datas = baos.toByteArray();
        wxMediaMessage.thumbData =datas;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = wxMediaMessage;
        req.scene = scene;
        boolean isSuccess = api.sendReq(req);
        G.log("xxxxxx"+isSuccess);

    }
    @OnClick(R.id.tv_download)
    public void downLoad() {
        if (mBitmap!=null){
            String path= FilePath.saveImage(getApplicationContext(),mBitmap,"二维码");
            //这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(new File(path));
            intent.setData(uri);
            sendBroadcast(intent);
            showToast(this,"已保存到"+path+"中");
        }
    }
}
