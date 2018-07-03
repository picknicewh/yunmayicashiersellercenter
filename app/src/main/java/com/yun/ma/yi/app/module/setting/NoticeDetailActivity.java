package com.yun.ma.yi.app.module.setting;

import android.os.Bundle;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.NoticeInfo;
import com.yunmayi.app.manage.R;

import butterknife.BindView;

/**
 * Created by ys on 2017/7/3.
 * 帮助 公告详情
 */

public class NoticeDetailActivity extends BaseActivity {
    @BindView(R.id.notice_title)
    TextView noticeTitle;
    @BindView(R.id.notice_detail)
    TextView noticeDetail;
    private NoticeInfo noticeInfo;
    private int type;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_notice_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        type = getIntent().getIntExtra("type",1);
        if (type == 1){
            setTitleTextId(R.string.help);
        }else {
            setTitleTextId(R.string.notice);
        }
        noticeInfo = (NoticeInfo) getIntent().getSerializableExtra("noticeInfo");
        if (noticeInfo != null){
            noticeTitle.setText(noticeInfo.getTitle());
            noticeDetail.setText(noticeInfo.getDetail());
        }
    }
}
