package com.harry.wallet365.function.main;

import android.Manifest;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.harry.wallet365.R;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.function.cash.CashFragment;
import com.harry.wallet365.function.discount.DiscountFragment;
import com.harry.wallet365.function.home.HomeFragment;
import com.harry.wallet365.function.mine.MineFragment;
import com.harry.wallet365.function.nearby.NearbyFragment;
import com.harry.wallet365.function.shopping.ShoppingFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;

    private String[] mCustomerTitles = {"首页", "附近", "优惠广场", /*"积分商城",*/ "我的"};
    private String[] mShopTitles = {"首页", "附近", "收银", /*"积分商城",*/ "我的"};

    private int[] mCustomerIconUnSelectIds = {
            R.drawable.ic_main1_unselect, R.drawable.ic_main2_unselect,
            R.drawable.ic_main3_unselect_customer, /*R.drawable.ic_main4_unselect,*/
            R.drawable.ic_main5_unselect};
    private int[] mCustomerIconSelectIds = {
            R.drawable.ic_main1_red, R.drawable.ic_main2_red,
            R.drawable.ic_main3_red_customer, /*R.drawable.ic_main4_red,*/
            R.drawable.ic_main5_red};
    private int[] mShopIconUnSelectIds = {
            R.drawable.ic_main1_unselect, R.drawable.ic_main2_unselect,
            R.drawable.ic_main3_unselect_shop, /*R.drawable.ic_main4_unselect,*/
            R.drawable.ic_main5_unselect};
    private int[] mShopIconSelectIds = {
            R.drawable.ic_main1_red, R.drawable.ic_main2_red,
            R.drawable.ic_main3_red_shop, /*R.drawable.ic_main4_red,*/
            R.drawable.ic_main5_red};

    @Override
    protected int setupView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        registerPermission();
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
        String type = getIntent().getStringExtra("type");

        ArrayList<CustomTabEntity> tabList = new ArrayList<>();
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new NearbyFragment());
        if (type.equals("shop")) {
            for (int i = 0; i < mCustomerTitles.length; i++) {
                tabList.add(new TabEntity(mShopTitles[i], mShopIconSelectIds[i], mShopIconUnSelectIds[i]));
            }
            fragmentList.add(new CashFragment());
        } else {
            for (int i = 0; i < mCustomerTitles.length; i++) {
                tabList.add(new TabEntity(mCustomerTitles[i], mCustomerIconSelectIds[i], mCustomerIconUnSelectIds[i]));
            }
            fragmentList.add(new DiscountFragment());
        }
//        fragmentList.add(new ShoppingFragment());
        fragmentList.add(new MineFragment());
        tabLayout.setTabData(tabList, this, R.id.fl_container, fragmentList);
    }

    private class TabEntity implements CustomTabEntity {

        private String title;
        private int selectedIcon;
        private int unSelectedIcon;

        private TabEntity(String title, int selectedIcon, int unSelectedIcon) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }
    }

    private Disposable mDisposable;
    private void registerPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        mDisposable = rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            //当所有权限都允许之后，返回true
                        } else {
                            //只要有一个权限禁止，返回false，
                            //下一次申请只申请没通过申请的权限
                            finish();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

}
