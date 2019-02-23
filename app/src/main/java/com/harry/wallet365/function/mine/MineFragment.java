package com.harry.wallet365.function.mine;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.BaseFragment;
import com.harry.wallet365.function.address.AddressActivity;
import com.harry.wallet365.function.main.MainActivity;
import com.harry.wallet365.function.merchant_entry.MerchantEntryActivity;
import com.harry.wallet365.function.my_bank_card.MyBankCardActivity;
import com.harry.wallet365.function.recommend.RecommendActivity;
import com.harry.wallet365.function.setting.SettingActivity;
import com.harry.wallet365.function.skin.SkinActivity;
import com.harry.wallet365.function.x_pub.XPubActivity;
import com.harry.wallet365.network.entity.UserInfoEntity;
import com.harry.wallet365.utils.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Harry on 2019/1/10.
 * 我的
 */
public class MineFragment extends BaseFragment<MinePresenter> {

    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_user_type)
    TextView tvUserType;
    @BindView(R.id.tv_user_ID)
    TextView tvUserID;
    @BindView(R.id.ll_user_info)
    LinearLayout llUserInfo;
    @BindView(R.id.fl_message_push)
    FrameLayout flMessagePush;
    @BindView(R.id.fl_my_order)
    FrameLayout flMyOrder;
    @BindView(R.id.fl_my_wallet)
    FrameLayout flMyWallet;
    @BindView(R.id.fl_my_coupon)
    FrameLayout flMyCoupon;
    @BindView(R.id.fl_my_account)
    FrameLayout flMyAccount;
    @BindView(R.id.fl_my_bank_card)
    FrameLayout flMyBankCard;
    @BindView(R.id.fl_merchant_entry)
    FrameLayout flMerchantEntry;
    @BindView(R.id.fl_receiving_address)
    FrameLayout flReceivingAddress;
    @BindView(R.id.fl_skin_setting)
    FrameLayout flSkinSetting;
    @BindView(R.id.fl_setting)
    FrameLayout flSetting;
    @BindView(R.id.tv_merchant_entry)
    TextView tvMerchantEntry;
    Unbinder unbinder;

    private boolean isDisplayed;
    /**
     * 商家登录(1)或者普通会员登录(2)
     */
    private int userType;

    @Override
    protected int setupView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        userType = SPUtils.getInt(UserInfo.LOGIN_TYPE.name(), 0);
        if (userType == 1) {
            tvUserType.setVisibility(View.GONE);
            tvMerchantEntry.setText("推荐人列表");
        } else if (userType == 2) {
            tvUserType.setVisibility(View.VISIBLE);
            tvMerchantEntry.setText("商家入驻");
        }


    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.MINE_FRAGMENT_GET_USER_INFO);
        return tags;
    }

    @Override
    protected MinePresenter bindPresenter() {
        return new MinePresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_user_info, R.id.fl_message_push, R.id.fl_my_order, R.id.fl_my_wallet, R.id.fl_my_coupon, R.id.fl_my_account, R.id.fl_my_bank_card, R.id.fl_merchant_entry, R.id.fl_receiving_address, R.id.fl_skin_setting, R.id.fl_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_user_info: //用户信息
                break;
            case R.id.fl_message_push://消息推送
                break;
            case R.id.fl_my_order://我的订单
                break;
            case R.id.fl_my_wallet://我的钱包
                break;
            case R.id.fl_my_coupon://我的优惠券
                break;
            case R.id.fl_my_account://我的xPUB账号
                startActivity(new Intent(mActivity, XPubActivity.class));
                break;
            case R.id.fl_my_bank_card://我的银行卡
                startActivity(new Intent(mActivity, MyBankCardActivity.class));
                break;
            case R.id.fl_merchant_entry://商家入驻 或者 推荐人列表
                if (userType == 1) {
                    startActivity(new Intent(mActivity, RecommendActivity.class));
                } else if (userType == 2) {
                    startActivity(new Intent(mActivity, MerchantEntryActivity.class));
                }
                break;
            case R.id.fl_receiving_address://收货地址
                startActivity(new Intent(mActivity, AddressActivity.class));
                break;
            case R.id.fl_skin_setting://皮肤设置
                startActivityForResult(new Intent(mActivity, SkinActivity.class), CodeFinal.COMMON_REQUEST_CODE);
                break;
            case R.id.fl_setting://设置
                startActivity(new Intent(mActivity, SettingActivity.class));
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {//显示
            if (!isDisplayed) {
                mPresenter.getUserInfo();
            }
        }
    }

    public void showLoginDialog() {
        showLoginDialog(mActivity);
    }

    public void getUserInfo(UserInfoEntity.DataBean data) {
        isDisplayed = true;
        RequestOptions requestOptions = new RequestOptions()
                .error(R.drawable.ic_default_user)
                .placeholder(R.drawable.ic_place_hold)
                .centerCrop()
                .circleCrop(); //圆形图片
//                .transform(new RoundedCorners(ConvertUtils.dp2px(5)));//圆角矩形
//                .override(ConvertUtils.dp2px(80), ConvertUtils.dp2px(80));
        Glide.with(mActivity)
                .load(data.headImg)
                .apply(requestOptions)
                .into(ivHeader);

        tvUsername.setText(data.nickname);
        tvUserID.setText(data.idCard);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeFinal.COMMON_REQUEST_CODE && resultCode == CodeFinal.COMMON_RESULT_CODE) {
            MainActivity mainActivity = (MainActivity) mActivity;
            mainActivity.setBottomNavigationIconColor();
        }
    }
}
