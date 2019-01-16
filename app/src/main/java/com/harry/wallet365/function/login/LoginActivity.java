package com.harry.wallet365.function.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.function.forget_password.ForgetPasswordActivity;
import com.harry.wallet365.function.register.RegisterActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/1/10.
 * 登录页面
 */
public class LoginActivity extends BaseActivity<LoginPresenter> {

    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;

    /**
     * 0 商家 <br>
     * 1 会员
     */
    private int tabPosition = -1;

    @Override
    protected int setupView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        initTabLayout();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.LOGIN_ACTIVITY_SHOP_LOGIN);
        tags.add(DisposableFinal.LOGIN_ACTIVITY_CUSTOMER_LOGIN);
        return tags;
    }

    @Override
    protected LoginPresenter bindPresenter() {
        return new LoginPresenter();
    }

    private void initTabLayout() {
        ArrayList<CustomTabEntity> tabEntity = new ArrayList<>();
        tabEntity.add(new MyTabEntity("我是商家"));
        tabEntity.add(new MyTabEntity("我是会员"));

        tabLayout.setTabData(tabEntity);

        tabLayout.setCurrentTab(0);

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                tabPosition = position;
                if (position == 1) {
                    llContainer.setVisibility(View.VISIBLE);
                } else {
                    llContainer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @OnClick({R.id.btn_login, R.id.tv_forget_password, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private void login() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showShort("账号不能为空");
            return;
        } else if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort("密码不能为空");
            return;
        }
        if (tabPosition == 1) {
            mPresenter.customerLogin(username, password);
        } else {
            mPresenter.shopLogin(username, password);
        }
    }

    private class MyTabEntity implements CustomTabEntity {

        private String title;

        private MyTabEntity(String title) {
            this.title = title;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return 0;
        }

        @Override
        public int getTabUnselectedIcon() {
            return 0;
        }
    }
}
