package com.harry.wallet365.function.login;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.harry.wallet365.R;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.base.presenter.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Harry on 2019/1/10.
 * 登录页面
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;

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
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
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

                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private class MyTabEntity implements CustomTabEntity {

        private String title;

        public MyTabEntity(String title) {
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
