package com.yun.ma.yi.app.module.setting.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.NoticeInfo;
import com.yun.ma.yi.app.module.common.CommonRecyclerAdapter;
import com.yun.ma.yi.app.module.common.CommonRecyclerHolder;
import com.yun.ma.yi.app.module.common.OnItemObjClickListener;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * Created by ys on 2017/7/1.
 */

public class NoticeInfoAdapter extends CommonRecyclerAdapter<NoticeInfo> {

    private static OnItemObjClickListener clickListener;

    public NoticeInfoAdapter(Context context, List<NoticeInfo> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonRecyclerHolder holder,final NoticeInfo noticeInfo) {
        if (holder != null){
            ((TextView)holder.getView(R.id.notice_title)).setText(noticeInfo.getTitle());

            holder.getView(R.id.notice_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null){
                        clickListener.onClick(view,noticeInfo);
                    }
                }
            });
        }
    }
    /**
     * 设置item 事件监听
     * @param clickListener
     */
    public void setClickListener(OnItemObjClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
