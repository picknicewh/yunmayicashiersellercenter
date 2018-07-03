package com.yun.ma.yi.app.module.Login;

import com.yun.ma.yi.app.module.Login.LoginContract.ILoginView;
import com.yun.ma.yi.app.utils.TextUtils;
import com.yunmayi.app.manage.R;

import static com.yun.ma.yi.app.utils.CommonUtil.getString;

/**
 * Created by ys on 2017/6/10.
 */

public class LoginCheckText {

    public static boolean check(ILoginView loginView){
        if (TextUtils.isEmpty(loginView.getUserName())){
            loginView.showMessage(getString(R.string.username_error));
            return false;
        }else if (TextUtils.isEmpty(loginView.getPassword())){
            loginView.showMessage(getString(R.string.password_error));
            return false;
        }
        return true;
    }

}
