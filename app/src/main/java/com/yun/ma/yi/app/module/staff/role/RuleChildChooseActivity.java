package com.yun.ma.yi.app.module.staff.role;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yunmayi.app.manage.R;

import butterknife.BindView;

public class RuleChildChooseActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tg_switch)
    ToggleButton tgSwitch;
    private int isCheck;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_rule_child_choose;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.staff_choose_rule);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        isCheck = intent.getIntExtra("check", 0);
        tvName.setText(name);
        tgSwitch.setChecked(isCheck == 1);
        setBackClickListener(this);
    }


    @Override
    public void onClick(View v) {
        isCheck = tgSwitch.isChecked() ? 1 : 0;
        Intent intent = new Intent();
        intent.putExtra("check", isCheck);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isCheck = tgSwitch.isChecked() ? 1 : 0;
            Intent intent = new Intent();
            intent.putExtra("check", isCheck);
            setResult(RESULT_OK, intent);
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
