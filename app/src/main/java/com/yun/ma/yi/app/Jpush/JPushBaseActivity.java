package com.yun.ma.yi.app.Jpush;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;

import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 作者： wh
 * 时间： 2017-9-13
 * 名称： 极光推送的基本设置
 * 版本说明：代码规范整改
 * 附加注释：setAliasAndTags方法中设置自己想要自定义的值
 * 主要接口：暂无
 */
public class JPushBaseActivity extends AppCompatActivity {

    public void initJPushConfiguration(String alias, String tag) {
        //设置设备别名或者tag值
        setAliasAndTags(setAlias(alias), setTag(tag));
        //设置基本设计
        setStyleBasic();
        //设置推送的日期
        setPushTime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    /**
     * 设置将获取的tag字符串，转换成 Set<String>,以逗号分隔
     */
    private Set<String> setTag(String tag) {
        // 检查 tag 的有效性
        if (JPushUtil.isEmpty(tag)) {
            G.log("tag不能为空！");
            // Toast.makeText(getApplicationContext(), R.string.error_tag_empty, Toast.LENGTH_SHORT).show();
            return null;
        }
        // ","隔开的多个 转换成 Set
        String[] sArray = tag.split(",");
        Set<String> tagSet = new LinkedHashSet<String>();
        for (String sTagItme : sArray) {
            if (!JPushUtil.isValidTagAndAlias(sTagItme)) {
                G.log("设置tag格式不对！");
                //     Toast.makeText(getApplicationContext(),R.string.error_tag_gs_empty, Toast.LENGTH_SHORT).show();
                return null;
            }
            tagSet.add(sTagItme);
        }
        return tagSet;
    }

    /**
     * 设置将获取的alias字符串,进行过滤判断
     */
    private String setAlias(String alias) {
        if (JPushUtil.isEmpty(alias)) {
            G.log("alias不能为空！");
            return null;
        }
        if (!JPushUtil.isValidTagAndAlias(alias)) {
            G.log("设置alias格式不对！");
            return null;
        } else {
            G.log("设置极光设备别名成功！");
        }

        return alias;
    }

    /**
     * 设置别名
     */
    public void setAliasAndTags(String alias, Set<String> tags) {
        JPushInterface.setAliasAndTags(getApplicationContext(), alias, tags, new TagAliasCallback() {
            @Override
            public void gotResult(int code, String s, Set<String> set) {
                switch (code) {
                    case 0:
                        G.log("设置成功！");
                        break;
                    case 6002:
                        G.log("设置超时！");
                        break;
                }
            }
        });
    }

    /**
     * 设置推送日期,周一至周日的每天0-24小时
     */
    public void setPushTime() {
        Set<Integer> days = new HashSet<>();
        for (int i = 0; i < 7; i++) {
            days.add(i);
        }
        JPushInterface.setPushTime(getApplicationContext(), days, 0, 24);
        //设置静音时间段晚上10点半到早上八点半
        JPushInterface.setSilenceTime(getApplicationContext(), 22, 30, 8, 30);
    }

    /**
     * 设置通知提示方式 - 基础属性
     */
    public void setStyleBasic() {
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(getApplicationContext());
        builder.statusBarDrawable = R.mipmap.ic_launcher;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为点击后自动消失
        builder.notificationDefaults = Notification.DEFAULT_SOUND;  //设置为铃声（ Notification.DEFAULT_SOUND）或者震动（ Notification.DEFAULT_VIBRATE）
        JPushInterface.setPushNotificationBuilder(1, builder);
    }

}
