package com.harry.wallet365.function.setting;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.harry.wallet365.R;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.application.WalletApplication;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.function.about.AboutActivity;
import com.harry.wallet365.function.feedback.FeedbackActivity;
import com.harry.wallet365.function.help_center.HelpCenterActivity;
import com.harry.wallet365.function.login.LoginActivity;
import com.harry.wallet365.function.main.MainActivity;
import com.harry.wallet365.function.modify_password.ModifyPasswordActivity;
import com.harry.wallet365.utils.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/1/24.
 * 设置页面
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.fl_modify_password)
    FrameLayout flModifyPassword;
    @BindView(R.id.fl_about)
    FrameLayout flAbout;
    @BindView(R.id.fl_help_center)
    FrameLayout flHelpCenter;
    @BindView(R.id.fl_feedback)
    FrameLayout flFeedback;
    @BindView(R.id.fl_logout)
    FrameLayout flLogout;

    @Override
    protected int setupView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("设置");
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @OnClick({R.id.iv_back, R.id.fl_modify_password, R.id.fl_about, R.id.fl_help_center, R.id.fl_feedback, R.id.fl_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.fl_modify_password://修改密码
                startActivity(new Intent(this, ModifyPasswordActivity.class));
                break;
            case R.id.fl_about://关于
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.fl_help_center://帮助中心
                startActivity(new Intent(this, HelpCenterActivity.class));
                break;
            case R.id.fl_feedback://意见反馈
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case R.id.fl_logout://退出登录
                logout();
                break;
        }
    }

    /**
     * 退出登录
     */
    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(this, R.layout.layout_logout, null);
        dialog.setView(dialogView);
        dialog.show();

        ImageView ivCancel = dialog.findViewById(R.id.iv_cancel);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
        TextView tvConfirm = dialog.findViewById(R.id.tv_confirm);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                SPUtils.putBoolean(UserInfo.IS_LOGIN.name(), false);
                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                WalletApplication.getAppContext().finishActivity(MainActivity.class);
                finish();
            }
        });
    }
}
