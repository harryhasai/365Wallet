package com.harry.wallet365.function.register;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/1/10.
 * 注册
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_verification_code)
    TextView tvVerificationCode;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.et_recommendation_code)
    EditText etRecommendationCode;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.tv_return_to_login)
    TextView tvReturnToLogin;
    private CountDownTimer countDownTimer;

    @Override
    protected int setupView() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        tvTitle.setText("注册");
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.REGISTER_ACTIVITY_GET_VERIFICATION_CODE);
        tags.add(DisposableFinal.REGISTER_ACTIVITY_REGISTER);
        return tags;
    }

    @Override
    protected RegisterPresenter bindPresenter() {
        return new RegisterPresenter();
    }

    @OnClick({R.id.iv_back, R.id.tv_verification_code, R.id.btn_confirm, R.id.tv_return_to_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_verification_code:
                getVerificationCode();
                break;
            case R.id.btn_confirm:
                confirm();
                break;
            case R.id.tv_return_to_login:
                finish();
                break;
        }
    }

    private void confirm() {
        String phone = etPhone.getText().toString().trim();
        String verificationCode = etVerificationCode.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String recommendationCode = etRecommendationCode.getText().toString().trim();//邀请码

        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("电话号码不能为空");
            return;
        } else if (TextUtils.isEmpty(verificationCode)) {
            ToastUtils.showShort("验证码不能为空");
            return;
        } else if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort("密码不能为空");
            return;
        } else if (TextUtils.isEmpty(confirmPassword)) {
            ToastUtils.showShort("确认密码不能为空");
            return;
        }
        if (!password.equals(confirmPassword)) {
            ToastUtils.showShort("确认密码与原密码不一致");
            return;
        }
        mPresenter.register(phone, password, verificationCode, recommendationCode);
    }

    /**
     * 获取验证码
     */
    private void getVerificationCode() {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("电话号码不能为空");
            return;
        }
        mPresenter.getVerificationCode(phone);
    }

    /**
     * 倒计时
     */
    public void countDown() {
        countDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvVerificationCode.setText(millisUntilFinished / 1000 + "秒后重新获取");
                tvVerificationCode.setClickable(false);
                tvVerificationCode.setFocusable(false);
            }

            @Override
            public void onFinish() {
                tvVerificationCode.setText("获取验证码");
                tvVerificationCode.setClickable(true);
                tvVerificationCode.setFocusable(true);
            }
        };
        countDownTimer.start();
    }

    /**
     * 如果出现异常, 则直接完成倒计时
     */
    public void finishCountDown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer.onFinish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void registerSuccess() {
        ToastUtils.showShort("注册成功");
        finish();
    }
}
