package com.harry.wallet365.function.main;

import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.function.cash.CashFragment;
import com.harry.wallet365.function.discount.DiscountFragment;
import com.harry.wallet365.function.home.HomeFragment;
import com.harry.wallet365.function.mine.MineFragment;
import com.harry.wallet365.function.nearby.NearbyFragment;
import com.harry.wallet365.utils.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private int[] mCustomerIconSelectIdsRed = {
            R.drawable.ic_main1_red, R.drawable.ic_main2_red,
            R.drawable.ic_main3_red_customer, /*R.drawable.ic_main4_red,*/
            R.drawable.ic_main5_red};
    private int[] mCustomerIconSelectIdsBlack = {
            R.drawable.ic_main1_black, R.drawable.ic_main2_black,
            R.drawable.ic_main3_black_customer, /*R.drawable.ic_main4_black,*/
            R.drawable.ic_main5_black};
    private int[] mCustomerIconSelectIdsYellow = {
            R.drawable.ic_main1_yellow, R.drawable.ic_main2_yellow,
            R.drawable.ic_main3_yellow_customer, /*R.drawable.ic_main4_yellow,*/
            R.drawable.ic_main5_yellow};
    private int[] mShopIconUnSelectIds = {
            R.drawable.ic_main1_unselect, R.drawable.ic_main2_unselect,
            R.drawable.ic_main3_unselect_shop, /*R.drawable.ic_main4_unselect,*/
            R.drawable.ic_main5_unselect};
    private int[] mShopIconSelectIdsRed = {
            R.drawable.ic_main1_red, R.drawable.ic_main2_red,
            R.drawable.ic_main3_red_shop, /*R.drawable.ic_main4_red,*/
            R.drawable.ic_main5_red};
    private int[] mShopIconSelectIdsBlack = {
            R.drawable.ic_main1_black, R.drawable.ic_main2_black,
            R.drawable.ic_main3_black_shop, /*R.drawable.ic_main4_black,*/
            R.drawable.ic_main5_black};
    private int[] mShopIconSelectIdsYellow = {
            R.drawable.ic_main1_yellow, R.drawable.ic_main2_yellow,
            R.drawable.ic_main3_yellow_shop, /*R.drawable.ic_main4_yellow,*/
            R.drawable.ic_main5_yellow};

    @Override
    protected int setupView() {
        return R.layout.activity_main;
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
        int type = SPUtils.getInt(UserInfo.LOGIN_TYPE.name(), 0);

        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new NearbyFragment());
        if (type == 1) {    //商家登录
            fragmentList.add(new CashFragment());
        } else if (type == 2) {  //普通用户登录
            fragmentList.add(new DiscountFragment());
        }
//        fragmentList.add(new ShoppingFragment());
        fragmentList.add(new MineFragment());
        tabLayout.setTabData(getTabList(), this, R.id.fl_container, fragmentList);
    }

    /**
     * 重新设置底部导航栏的图标
     */
    public void setBottomNavigationIconColor() {
        tabLayout.setTabData(getTabList());
        tabLayout.notifyDataSetChanged();
    }

    /**
     * 设置底部导航栏Icon
     */
    private ArrayList<CustomTabEntity> getTabList() {
        int type = SPUtils.getInt(UserInfo.LOGIN_TYPE.name(), 0);
        //1 - 红色 2 - 黑色 3 - 黄色
        int navigationColor = SPUtils.getInt(UserInfo.BOTTOM_NAVIGATION_ICON_COLOR.name(), 0);

        ArrayList<CustomTabEntity> tabList = new ArrayList<>();

        if (type == 1) {    //商家登录
            switch (navigationColor) {//1 - 红色 2 - 黑色 3 - 黄色
                case 1:
                    for (int i = 0; i < mCustomerTitles.length; i++) {
                        tabList.add(new TabEntity(mShopTitles[i], mShopIconSelectIdsRed[i], mShopIconUnSelectIds[i]));
                    }
                    tabLayout.setTextSelectColor(getResources().getColor(R.color.tab_select_red));
                    break;
                case 2:
                    for (int i = 0; i < mCustomerTitles.length; i++) {
                        tabList.add(new TabEntity(mShopTitles[i], mShopIconSelectIdsBlack[i], mShopIconUnSelectIds[i]));
                    }
                    tabLayout.setTextSelectColor(getResources().getColor(R.color.tab_select_black));
                    break;
                case 3:
                    for (int i = 0; i < mCustomerTitles.length; i++) {
                        tabList.add(new TabEntity(mShopTitles[i], mShopIconSelectIdsYellow[i], mShopIconUnSelectIds[i]));
                    }
                    tabLayout.setTextSelectColor(getResources().getColor(R.color.tab_select_yellow));
                    break;
                default:
                    for (int i = 0; i < mCustomerTitles.length; i++) {
                        tabList.add(new TabEntity(mShopTitles[i], mShopIconSelectIdsRed[i], mShopIconUnSelectIds[i]));
                    }
                    tabLayout.setTextSelectColor(getResources().getColor(R.color.tab_select_red));
                    break;
            }
        } else if (type == 2) {  //普通用户登录
            switch (navigationColor) {//1 - 红色 2 - 黑色 3 - 黄色
                case 1:
                    for (int i = 0; i < mCustomerTitles.length; i++) {
                        tabList.add(new TabEntity(mCustomerTitles[i], mCustomerIconSelectIdsRed[i], mCustomerIconUnSelectIds[i]));
                    }
                    tabLayout.setTextSelectColor(getResources().getColor(R.color.tab_select_red));
                    break;
                case 2:
                    for (int i = 0; i < mCustomerTitles.length; i++) {
                        tabList.add(new TabEntity(mCustomerTitles[i], mCustomerIconSelectIdsBlack[i], mCustomerIconUnSelectIds[i]));
                    }
                    tabLayout.setTextSelectColor(getResources().getColor(R.color.tab_select_black));
                    break;
                case 3:
                    for (int i = 0; i < mCustomerTitles.length; i++) {
                        tabList.add(new TabEntity(mCustomerTitles[i], mCustomerIconSelectIdsYellow[i], mCustomerIconUnSelectIds[i]));
                    }
                    tabLayout.setTextSelectColor(getResources().getColor(R.color.tab_select_yellow));
                    break;
                default:
                    for (int i = 0; i < mCustomerTitles.length; i++) {
                        tabList.add(new TabEntity(mCustomerTitles[i], mCustomerIconSelectIdsRed[i], mCustomerIconUnSelectIds[i]));
                    }
                    tabLayout.setTextSelectColor(getResources().getColor(R.color.tab_select_red));
                    break;
            }
        }
        return tabList;
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

}
