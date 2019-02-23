package com.harry.wallet365.function.my_bank_card.add_bank_info;

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
 * Created by Harry on 2019/2/23.
 * 新增银行卡信息或者修改
 */
public class AddBankInfoActivity extends BaseActivity<AddBankInfoPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_bank_of_user)
    EditText etBankOfUser;
    @BindView(R.id.et_bank_of_name)
    EditText etBankOfName;
    @BindView(R.id.et_bank_of_number)
    EditText etBankOfNumber;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_verification_code)
    TextView tvVerificationCode;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private CountDownTimer countDownTimer;
    private boolean isUpdate;

    @Override
    protected int setupView() {
        return R.layout.activity_add_bank_info;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("我的银行卡");

        String bankName = getIntent().getStringExtra("bankName");
        String cardNumber = getIntent().getStringExtra("cardNumber");
        String username = getIntent().getStringExtra("username");
        if (!TextUtils.isEmpty(bankName) && !TextUtils.isEmpty(cardNumber) && !TextUtils.isEmpty(username)) {
            etBankOfName.setText(bankName);
            etBankOfNumber.setText(cardNumber);
            etBankOfUser.setText(username);
            isUpdate = true;
        }
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.REGISTER_ACTIVITY_GET_VERIFICATION_CODE);
        tags.add(DisposableFinal.REGISTER_ACTIVITY_REGISTER);
        return tags;
    }

    @Override
    protected AddBankInfoPresenter bindPresenter() {
        return new AddBankInfoPresenter();
    }

    @OnClick({R.id.iv_back, R.id.tv_verification_code, R.id.btn_confirm})
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
        }
    }

    private void confirm() {
        String bankOfUser = etBankOfUser.getText().toString().trim();
        String bankOfNumber = etBankOfNumber.getText().toString().trim();
        String bankOfName = etBankOfName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String verificationCode = etVerificationCode.getText().toString().trim();
        if (TextUtils.isEmpty(bankOfUser)) {
            ToastUtils.showShort("请填写持卡人姓名");
            return;
        } else if (TextUtils.isEmpty(bankOfNumber)) {
            ToastUtils.showShort("请填写银行卡号");
            return;
        } else if (TextUtils.isEmpty(bankOfName)) {
            ToastUtils.showShort("请填写银行名称");
            return;
        } else if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("请填写银行卡预留手机号");
            return;
        } else if (TextUtils.isEmpty(verificationCode)) {
            ToastUtils.showShort("请填写验证码");
            return;
        }
        if (isUpdate) {
            mPresenter.updateBankInfo(bankOfUser, bankOfName, bankOfNumber, phone, verificationCode);
        } else {
            mPresenter.addBankInfo(bankOfUser, bankOfName, bankOfNumber, phone, verificationCode);
        }
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

}
