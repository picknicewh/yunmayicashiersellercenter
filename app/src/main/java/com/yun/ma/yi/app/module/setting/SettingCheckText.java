package com.yun.ma.yi.app.module.setting;

import com.yun.ma.yi.app.module.setting.SettingContract.IUpdatePasswordView;
import com.yun.ma.yi.app.utils.TextUtils;
import com.yunmayi.app.manage.R;

import static com.yun.ma.yi.app.utils.CommonUtil.getString;

/**
 * Created by ys on 2017/6/23.
 * 验证
 */

public class SettingCheckText {

    public static boolean check(IUpdatePasswordView updatePasswordView){

        if (TextUtils.isEmpty(updatePasswordView.getUser().getNoPassword())){
            updatePasswordView.showMessage(getString(R.string.original_password_error));
            return false;
        } else if (TextUtils.isEmpty(updatePasswordView.getUser().getNewPassword())){
            updatePasswordView.showMessage(getString(R.string.new_password_error));
            return false;
        } else if (!updatePasswordView.getUser().getPasswrod().equals(updatePasswordView.getUser().getNewPassword())){
            updatePasswordView.showMessage(getString(R.string.two_password_error));
            return false;
        }
        return  true;
    }

}
