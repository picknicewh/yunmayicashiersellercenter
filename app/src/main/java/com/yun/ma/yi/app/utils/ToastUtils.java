package com.yun.ma.yi.app.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ys on 17/5/27.
 */
public class ToastUtils {

    public static void makeText(Context context, String msg) {
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }
}
