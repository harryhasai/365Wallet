package com.harry.wallet365.function.modify_password;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/1/25.
 * 修改密码
 */
public class ModifyPasswordActivity extends BaseActivity<ModifyPasswordPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    @Override
    protected int setupView() {
        return R.layout.activity_modify_password;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("修改密码");
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.MODIFY_PASSWORD_ACTIVITY_MODIFY);
        return tags;
    }

    @Override
    protected ModifyPasswordPresenter bindPresenter() {
        return new ModifyPasswordPresenter();
    }

    @OnClick({R.id.iv_back, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                String password = etPassword.getText().toString().trim();
                String newPassword = etNewPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showShort("原密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(newPassword)) {
                    ToastUtils.showShort("新密码不能为空");
                    return;
                }
                mPresenter.modify(password, newPassword);
                break;
        }
    }
}
