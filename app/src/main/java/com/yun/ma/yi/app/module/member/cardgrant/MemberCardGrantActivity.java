package com.yun.ma.yi.app.module.member.cardgrant;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.module.common.view.ItemEditText2;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/7/11
 * 名称：会员卡发放
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberCardGrantActivity extends BaseActivity implements MemberCardGrantContract.View {
    /**
     * 手机号码
     */
    @BindView(R.id.tv_phone_number)
    ItemEditText2 tvPhoneNumber;
    /**
     * 验证码
     */
    @BindView(R.id.et_check_number)
    ItemEditText2 etCheckNumber;
    /**
     * 获取验证码
     */
    @BindView(R.id.tv_check_number)
    TextView tvCheckNumber;
    /**
     * 验证码
     */
    private String dynamic="";
    /**
     * 数据处理类
     */
    private   MemberCardGrantPresenter presenter;
    /**
     * 计时器
     */
    private Timer timer;
    /**
     * 是否获取验证码
     */
    private boolean isCheckNumber;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_member_card_grant;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.member_card_grant);
        presenter = new MemberCardGrantPresenter(this,this);
        etCheckNumber.setEnabled(false);
        isCheckNumber  = false;
    }
    @OnClick(R.id.et_check_number)
    public void CheckNumber() {
        if (!isCheckNumber){
            G.showToast(this,"请先获取验证码先！");
        }
    }
    @OnClick(R.id.tv_next)
    public void next() {
        if (G.isEmteny(tvPhoneNumber.getText().toString())){
            G.showToast(this,"手机号不能为空！");
            return;
        }
        presenter.findMemberCard();


    }
    private int count =  0;

    @OnClick(R.id.tv_check_number)
    public void getCheckNumber() {
        isCheckNumber = true;
        etCheckNumber.setEnabled(true);
           dynamic = String.valueOf((int) (Math.random()*100000+100000));
           if (G.isEmteny(getMobile())){
            G.showToast(this,"手机号码不能为空");
            return;
        }
        presenter.findMemberCard();
        G.log("xxxxxxxxx"+hasCard);

        presenter.sendSms();
        timer = new Timer();
        count = 0;
        timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putInt("rest",60-(count++));
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
            },0,1000);

    }
   private Handler handler = new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           Bundle bundle =  msg.getData();
           int rest = bundle.getInt("rest");
           if (rest==0){
               count=0;
               isCheckNumber = false;
               tvCheckNumber.setEnabled(true);
               tvCheckNumber.setText("获取验证码");
               timer.cancel();
               timer=null;
           }else {
               tvCheckNumber.setText(rest+"秒后重新获取");
               tvCheckNumber.setEnabled(false);
           }

       }
   };
    @Override
    protected void onStop() {
        super.onStop();
        if (timer!=null){
            tvCheckNumber.setEnabled(true);
            tvCheckNumber.setText("获取验证码");
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler!=null){
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        if (presenter!= null) {
            presenter.unSubscribe();
        }
    }

    @Override
    public int getSellerId() {
        return UserMessage.getInstance().getUser_id();
    }

    @Override
    public String getMobile() {
        return tvPhoneNumber.getText().toString();
    }

    @Override
    public String getDynamic() {
        return dynamic;
    }

    @Override
    public String getKeyWord() {
        return tvPhoneNumber.getText().toString();
    }

    private boolean hasCard;
    @Override
    public void setHasCard(boolean hasCard) {
        this.hasCard = hasCard;
        if (hasCard){
            G.showToast(this,"该手机号已经是会员了!");
            return;
        }
        Intent intent = new Intent(this,MemberEditCardInfoActivity.class);
        intent.putExtra("number",tvPhoneNumber.getText().toString());
        startActivity(intent);
    }
}
