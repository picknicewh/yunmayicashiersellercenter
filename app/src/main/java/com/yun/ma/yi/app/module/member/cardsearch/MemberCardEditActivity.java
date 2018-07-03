package com.yun.ma.yi.app.module.member.cardsearch;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.MemberCardInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.view.ItemEditText2;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.DateUtil;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static java.lang.Integer.parseInt;

/**
 * 作者： wh
 * 时间：  2017/7/11
 * 名称：新增/编辑会员卡
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberCardEditActivity extends BaseActivity implements OptionsPickerView.OnOptionsSelectListener, MemberCardEditContract.View {
    /**
     * 会员卡名称
     */
    @BindView(R.id.tv_card_name)
    ItemEditText2 tvCardName;
    /**
     * 优惠方式
     */
    @BindView(R.id.tv_discount_way)
    TextView tvDiscountWay;
    @BindView(R.id.tv_discount_way_text)
    TextView tvDiscountWayText;
    /**
     * 折扣率
     */
    @BindView(R.id.tv_discount_rate)
    ItemEditText2 tvDiscountRate;
    /**
     * 所需积分
     */
    @BindView(R.id.tv_integral)
    ItemEditText2 tvIntegral;
    /**
     * 消费多少元获取一个积分
     */
    @BindView(R.id.tv_integral_get)
    ItemEditText2 tvIntegralGet;
    /**
     * 取消
     */
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    /**
     * 编辑或者新增
     */
    private int source;
    /**
     * 优惠方式
     */
    private String discountWay;
    /**
     * 优惠方式数据列表
     */
    private List<String> wayList;
    /**
     * 选择优惠方式
     */
    private OptionsPickerView pickerView;
    /**
     * 数据处理类
     */
    private MemberCardEditPresenter presenter;
    /**
     * 编辑信息
     */
    private MemberCardInfo memberCardInfo;
    /**
     * 编辑id
     */
    private int id=-1;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_member_card_edit;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        source = getIntent().getIntExtra("source", Constants.ADD_MEMBER_CARD);
        initSource();
        presenter = new MemberCardEditPresenter(this, this);
    }
    private void initSource() {
        wayList = new ArrayList<>();
        wayList.add("会员价");
        wayList.add("折扣率");
        switch (source) {
            case Constants.ADD_MEMBER_CARD:
                setTitleTextId(R.string.add_member_card);
                tvCancel.setText("取消");
                break;
            case Constants.EDIT_MEMBER_CARD:
                setTitleTextId(R.string.edit_member_card);
                tvCancel.setText("删除");
                memberCardInfo = (MemberCardInfo) getIntent().getSerializableExtra("memberCardInfo");
                initText();
                break;
        }
        pickerView = DateUtil.getOptionPickerView("选择优惠方式", wayList, typePosition, this, this);
    }

    private void initText() {
        tvDiscountRate.setVisibility(memberCardInfo.getDiscount_type() == 1 ? View.VISIBLE : View.GONE);
        tvDiscountRate.setText(tvDiscountRate.getVisibility() == View.VISIBLE ? String.valueOf(memberCardInfo.getDiscount_rate()) : "0");
        tvDiscountWay.setText(wayList.get(memberCardInfo.getDiscount_type()));
        tvIntegralGet.setText(PriceTransfer.chageMoneyToString(memberCardInfo.getGet_integral_by_money()));
        tvIntegral.setText(String.valueOf(memberCardInfo.getCard_integral()));
        tvCardName.setText(memberCardInfo.getCard_name());
        id = memberCardInfo.getId();
        typePosition = memberCardInfo.getDiscount_type();
    }

    private int typePosition = 0;

    @OnClick(R.id.tv_discount_way)
    public void discountWay() {
        pickerView.show();
    }

    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View v) {
        typePosition = options1;
        discountWay = wayList.get(options1);
        tvDiscountWay.setText(discountWay);
        tvDiscountRate.setVisibility(discountWay.equals("折扣率") ? View.VISIBLE : View.GONE);
    }

    @Override
    public MemberCardInfo getData() {
             MemberCardInfo memberCardInfo = new MemberCardInfo();
             String rateString =  tvDiscountRate.getText().toString();
             String integralString = tvIntegral.getText().toString();
             String integralGetString = tvIntegralGet.getText().toString();
            int rate=-1;//不显示的时候默认为-1
            if (tvDiscountRate.getVisibility()==View.VISIBLE){
                rate = G.isEmteny(rateString) ? 0 : parseInt(rateString);
            }
           int integral =  G.isEmteny(integralString) ? 0 : parseInt(integralString);
           int integral_get;
            integral_get = G.isEmteny(integralGetString) ? 0: (int) (Float.parseFloat(integralGetString) * 100);
           if (source==Constants.EDIT_MEMBER_CARD){
              if (integral_get==this.memberCardInfo.getGet_integral_by_money()){
                  integral_get = (int) this.memberCardInfo.getGet_integral_by_money();
              }
           }
            memberCardInfo.setDiscount_rate(rate);
            memberCardInfo.setDiscount_type(typePosition);
            memberCardInfo.setGet_integral_by_money(integral_get);
            memberCardInfo.setSeller_id(UserMessage.getInstance().getUId());
            memberCardInfo.setCard_integral(integral);
            memberCardInfo.setCard_name(tvCardName.getText().toString());
            memberCardInfo.setCreate_name(UserMessage.getInstance().getUsername());
            memberCardInfo.setCreate_id(UserMessage.getInstance().getUId());
            memberCardInfo.setId(id);
            return memberCardInfo;
    }

    @Override
    public int getId() {
        return id;
    }

    @OnClick(R.id.tv_conform)
    public void conform() {
        switch (source) {
            case Constants.ADD_MEMBER_CARD:
                presenter.addMemberGrade();
                break;
            case Constants.EDIT_MEMBER_CARD:
                presenter.editMemberGrade();
                break;
        }
    }
    @OnClick(R.id.tv_cancel)
    public void cancel() {
        switch (source) {
            case Constants.ADD_MEMBER_CARD:
                typePosition =0;
                discountWay = wayList.get(typePosition);
                tvDiscountWay.setText(discountWay);
                tvIntegralGet.setText("");
                tvIntegral.setText("");
                tvCardName.setText("");
                break;
            case Constants.EDIT_MEMBER_CARD:
                //initText();
                presenter.deleteMemberGrade();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( presenter!= null) {
            presenter.unSubscribe();
        }
    }
}



