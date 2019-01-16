package com.harry.wallet365.function.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.BaseFragment;
import com.harry.wallet365.function.modify_location.ModifyLocationActivity;
import com.harry.wallet365.network.entity.HomeBannerEntity;
import com.harry.wallet365.network.entity.HomeCouponEntity;
import com.harry.wallet365.utils.LocationUtil;
import com.harry.wallet365.utils.SwipeRefreshLayoutRefreshingUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Harry on 2019/1/10.
 * 首页
 */
public class HomeFragment extends BaseFragment<HomePresenter> {

    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    private HomeAdapter adapter;

    private int pageNum = 1;
    private boolean isLoadMore;

    @Override
    protected int setupView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        initRecyclerView();

        mPresenter.getBanner();
        mPresenter.getBottomList(pageNum);

        initLocation();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.HOME_FRAGMENT_GET_BANNER);
        tags.add(DisposableFinal.HOME_FRAGMENT_GET_BOTTOM_LIST);
        tags.add(DisposableFinal.HOME_FRAGMENT_GET_COUPON);
        return tags;
    }

    @Override
    protected HomePresenter bindPresenter() {
        return new HomePresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_location, R.id.iv_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_location:
                startActivity(new Intent(mActivity, ModifyLocationActivity.class));
                break;
            case R.id.iv_scan:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocationUtil.destroyLocation();
    }

    private void initLocation() {
        LocationUtil.startLocation(mActivity, new LocationListener());
    }

    private boolean isLocation = false;
    private boolean isGoToSettingLocation = false;

    private class LocationListener implements AMapLocationListener {

        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功, 发送经纬度到服务器
                tvLocation.setText(aMapLocation.getCity());
                mPresenter.getCoupon(aMapLocation.getLongitude() + "," + aMapLocation.getLatitude());
                isLocation = true;
            } else if (aMapLocation.getErrorCode() == 13) {
                //当前网络无法定位, 提示用户开启GPS
                if (!isGoToSettingLocation) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                    builder.setMessage("当前移动网/wifi无法定位, 为了确保功能正确使用, 是否开启GPS功能");
                    builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //跳转GPS设置界面
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            ToastUtils.showShort("您可能无法正确使用附近优惠券");
                        }
                    });
                    builder.show();
                    isGoToSettingLocation = true;
                }
            }
            if (isLocation) {
                LocationUtil.stopLocation();
            }
        }
    }

    private void initRecyclerView() {
        // 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.setEnableLoadMore(false);
                pageNum = 1;
                isLoadMore = false;
                mPresenter.getBottomList(pageNum);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        List<HomeMultiItem> mList = new ArrayList<>();
        mList.add(new HomeMultiItem(HomeMultiItem.TOP));
        mList.add(new HomeMultiItem(HomeMultiItem.BOTTOM));
        adapter = new HomeAdapter(mList);
        recyclerView.setAdapter(adapter);

        adapter.setPreLoadNumber(1);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                isLoadMore = true;
                mPresenter.getBottomList(pageNum);

            }
        }, recyclerView);
    }

    public void getBanner(HomeBannerEntity.DataBean data) {
        adapter.getBanner(data);
    }

    /**
     * @param data 底部促销活动数据
     */
    public void getBottomList(HomeBannerEntity.DataBean data) {
        if (data.list.size() != 0) {
            if (isLoadMore) {
                adapter.addBottomListData(data.list);
                adapter.loadMoreComplete();
            } else {
                adapter.setBottomListData(data.list);
            }
            adapter.setEnableLoadMore(true);
        } else {
            adapter.loadMoreEnd();
        }
    }

    /**
     * @param data 附近优惠券数据
     */
    public void getCoupon(HomeCouponEntity.DataBean data) {
        adapter.setCouponList(data.list);
    }

    public void setRefreshing(boolean refreshing) {
        if (swipeRefreshLayout != null) {
            if (refreshing) {
                SwipeRefreshLayoutRefreshingUtil.setRefreshing(swipeRefreshLayout);
            } else {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    /**
     * 检测GPS是否打开
     *
     * @return
     */
    private boolean checkGPSIsOpen() {
        boolean isOpen;
        LocationManager locationManager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);
        isOpen = locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
        return isOpen;
    }
}
