package com.yun.ma.yi.app.module.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.yun.ma.yi.app.api.download.DownUtils;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.UserInfo;
import com.yun.ma.yi.app.bean.Version;
import com.yun.ma.yi.app.module.Home.HomeActivity;
import com.yun.ma.yi.app.module.Login.LoginContract.ILoginPresenter;
import com.yun.ma.yi.app.module.Login.LoginContract.ILoginView;
import com.yun.ma.yi.app.module.common.AlertDialog;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.userdb.UserAction;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.userdb.UserRuleInfoDb;
import com.yun.ma.yi.app.userdb.UserRuleInfoDbHelper;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.ShareUtils;
import com.yun.ma.yi.app.utils.StringUtils;
import com.yunmayi.app.manage.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.username)
    ClearEditText username;
    @BindView(R.id.password)
    ClearEditText password;
    private ShareUtils shareUtils;

    private ILoginPresenter loginPersenter;
    /**
     * 权限数据库操作
     */
    private SQLiteDatabase db;
    /**
     * 用户信息
     */
    private UserMessage userMessage;

    @Override
    protected int getContentViewLayoutID() {
        hideTitleLayout(true);
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initJPushConfiguration("yumayi", "yunmayi");
        loginPersenter = new LoginPresenter(this, this);
        shareUtils = new ShareUtils(this);
        db = UserRuleInfoDb.getInstance().getWritableDatabase();
        userMessage = UserMessage.getInstance();
        // String userName = shareUtils.getObject(Configure.USERNAME, String.class);
        // String passWord = shareUtils.getObject(Configure.PASSWORD, String.class);
        String userName = userMessage.getUsername();
        String passWord = userMessage.getPassword();
        username.initData(userName);
        password.initData(passWord);
        //获取最新版本
        loginPersenter.getVersion();


    }

    @OnClick(R.id.login)
    public void login() {
        if (getUserName().contains(":")) loginPersenter.childLogin();
        else loginPersenter.login();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginPersenter != null) {
            loginPersenter.unSubscribe();
        }
    }

    @Override
    public String getUserName() {
        return username.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void loginSuccess(UserInfo userInfo) {
        G.log("xxxxxxx" + userInfo.getParent_id());
        /**记住密码保存本地*/
        UserAction.saveLoginMessage(getUserName(), getPassword());
        /**记住密码用户信息*/
        UserAction.saveUserInfo(userInfo, getUserName(), getPassword());
        /**记住密码保存本地*/
        //  shareUtils.setObject(Configure.USERNAME,getUserName());
        //shareUtils.setObject(Configure.PASSWORD,getPassword());
        //shareUtils.setObject(Configure.USERINFO,userInfo);
        //保存角色权限信息
        if (userInfo.getRule() != null && userInfo.getRule().size() > 0) {
            UserRuleInfoDbHelper.insertAll(userInfo, db);
        }
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void getVersion(Version version) {
        if (version != null) {
            String versionName = null;
            try {
                versionName = getPackageManager().getPackageInfo(
                        getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            int versionType = StringUtils.compareVersion(versionName, version.getVersion());
            if (versionType == -1) {
                this.version = version;
                showDown(version);
            }
        }
    }

    private Version version;

    /**
     * 弹出更新提示
     */
    private void showDown(final Version version) {
        final AlertDialog alertDialog = new AlertDialog(LoginActivity.this);
        alertDialog.setMessage(getResources().getString(R.string.version_release));
        alertDialog.updateVersion("新版本" + version.getVersion());
        alertDialog.setUpdateInfo(version.getIntroduce());
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setPositiveButton(getResources().getString(R.string.update_now), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                checkIsAndroidO();
            }
        });
        alertDialog.setNegativeButton(getResources().getString(R.string.remind_me_later), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    /**
     * 判断是否是8.0,8.0需要处理未知应用来源权限问题,否则直接安装
     */

    private void checkIsAndroidO() {
        if (Build.VERSION.SDK_INT >= 26) {
            boolean b = getPackageManager().canRequestPackageInstalls();
            if (b) {
                DownUtils.retrofitDownload(LoginActivity.this, version);//安装应用的逻辑(写自己的就可以)
            } else {
                Uri packageURI = Uri.parse("package:"+getPackageName());
                Intent intent =new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,packageURI);
                startActivityForResult(intent, 10086);
            }
        } else {
            DownUtils.retrofitDownload(LoginActivity.this, version);
        }

    }


    @Override
    @SuppressLint("InlinedApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10086) {
         //   checkIsAndroidO();
            DownUtils.retrofitDownload(LoginActivity.this, version);
        }
    }
}

