package com.harry.wallet365.function.my_coupon;

import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.harry.wallet365.R;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.function.my_coupon.already_used.AlreadyUsedFragment;
import com.harry.wallet365.function.my_coupon.not_used.NotUsedFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/2/25.
 * 我的优惠券
 */
public class MyCouponActivity extends BaseActivity {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected int setupView() {
        return R.layout.activity_my_coupon;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        tvTitle.setText("我的优惠券");
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
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new NotUsedFragment());
        fragmentList.add(new AlreadyUsedFragment());
        ArrayList<CustomTabEntity> tabList = new ArrayList<>();
        tabList.add(new TabEntity("未使用"));
        tabList.add(new TabEntity("已使用"));
        tabLayout.setTabData(tabList, this, R.id.fl_container, fragmentList);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    private class TabEntity implements CustomTabEntity {

        private String title;

        private TabEntity(String title) {
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
