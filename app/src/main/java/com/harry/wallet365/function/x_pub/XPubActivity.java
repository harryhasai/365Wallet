package com.harry.wallet365.function.x_pub;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.R;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.network.entity.XPubEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/2/23.
 * 我的XPub账号
 */
public class XPubActivity extends BaseActivity<XPubPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    private boolean isUpdate;
    private boolean isEdit;

    @Override
    protected int setupView() {
        return R.layout.activity_x_pub;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("我的XPUB账号");

        mPresenter.getXPubInfo();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected XPubPresenter bindPresenter() {
        return new XPubPresenter();
    }

    @OnClick({R.id.iv_back, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_confirm:
                String account = etAccount.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String text = btnConfirm.getText().toString().trim();
                if (text.equals("新增")) {
                    if (TextUtils.isEmpty(account)) {
                        ToastUtils.showShort("账号不能为空");
                        return;
                    } else if (TextUtils.isEmpty(password)) {
                        ToastUtils.showShort("密码不能为空");
                        return;
                    }
                    mPresenter.updateXPubInfo(account, password);
                    isUpdate = false;
                } else if (text.equals("修改")) {
                    if (!isEdit) {
                        etPassword.setEnabled(true);
                        ToastUtils.showShort("请修改您的密码");
                        isEdit = true;
                    } else {
                        if (TextUtils.isEmpty(password)) {
                            ToastUtils.showShort("密码不能为空");
                            return;
                        }
                        mPresenter.updateXPubInfo(account, password);
                        isUpdate = true;
                    }
                }
                break;
        }
    }

    public void getXPubInfo(XPubEntity.DataBean data) {
        etAccount.setText(data.account);
        etPassword.setText("******");
        etAccount.setEnabled(false);
        etPassword.setEnabled(false);
        btnConfirm.setText("修改");
        isUpdate = false;
    }

    public void updateXPubInfo() {
        etAccount.setEnabled(false);
        etPassword.setEnabled(false);
        isEdit = false;
        if (isUpdate) {
            ToastUtils.showShort("修改成功");
        } else {
            ToastUtils.showShort("新增成功");
        }
    }
}
