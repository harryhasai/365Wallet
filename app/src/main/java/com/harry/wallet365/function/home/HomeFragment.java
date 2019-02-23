package com.harry.wallet365.function.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
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
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.BaseFragment;
import com.harry.wallet365.function.modify_location.ModifyLocationActivity;
import com.harry.wallet365.function.shop_detail.ShopDetailActivity;
import com.harry.wallet365.network.entity.HomeBannerEntity;
import com.harry.wallet365.network.entity.HomeCouponEntity;
import com.harry.wallet365.network.entity.HomeUseCouponEntity;
import com.harry.wallet365.utils.LocationUtil;
import com.harry.wallet365.utils.SPUtils;
import com.harry.wallet365.utils.SwipeRefreshLayoutRefreshingUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import gdut.bsx.share2.FileUtil;
import gdut.bsx.share2.Share2;
import gdut.bsx.share2.ShareContentType;

/**
 * Created by Harry on 2019/1/10.
 * 首页
 */
public class HomeFragment extends BaseFragment<HomePresenter> {

    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    private HomeAdapter adapter;

    private int pageNum = 1;
    private boolean isLoadMore;
    private String city;
    private AlertDialog useCouponDialog;
    private String location;

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

        //优先显示默认信息
        mPresenter.getCoupon(SPUtils.getString(UserInfo.CURRENT_LOCATION.name(), "0,0"));
        tvLocation.setText(SPUtils.getString(UserInfo.CITY.name(), ""));
        initLocation();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.HOME_FRAGMENT_GET_BANNER);
        tags.add(DisposableFinal.HOME_FRAGMENT_GET_BOTTOM_LIST);
        tags.add(DisposableFinal.HOME_FRAGMENT_GET_COUPON);
        tags.add(DisposableFinal.HOME_FRAGMENT_USE_COUPON);
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

    @OnClick({R.id.ll_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_location:
                Intent intent = new Intent(mActivity, ModifyLocationActivity.class);
                intent.putExtra("city", city);
                startActivityForResult(intent, CodeFinal.COMMON_REQUEST_CODE);
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
                city = aMapLocation.getCity();
                tvLocation.setText(city);
                location = aMapLocation.getLongitude() + "," + aMapLocation.getLatitude();
                mPresenter.getCoupon(location);
                isLocation = true;
                SPUtils.putString(UserInfo.CITY.name(), city);
                SPUtils.putString(UserInfo.CURRENT_LOCATION.name(), location);
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
        adapter.setOnCouponItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeCouponEntity.DataBean.ListBean item = (HomeCouponEntity.DataBean.ListBean) adapter.getData().get(position);
                switch (item.source) {
                    case 1://平台
                        setupDialog(item);
                        break;
                    case 2://商家
                        Intent intent = new Intent(mActivity, ShopDetailActivity.class);
                        intent.putExtra("sellerId", item.sellerId);//商家ID
                        intent.putExtra("location", location);
                        intent.putExtra("isShowCoupon", 1);
                        startActivity(intent);
                        break;
                }
            }
        });
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeFinal.COMMON_REQUEST_CODE && resultCode == CodeFinal.COMMON_RESULT_CODE) {
            String cityName = data.getStringExtra("cityName");
            city = cityName;
            tvLocation.setText(city);
            SPUtils.putString(UserInfo.CITY.name(), city);
            LocationUtil.getLocationFromCityName(mActivity, cityName, new GeocodeSearch.OnGeocodeSearchListener() {
                @Override
                public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

                }

                @Override
                public void onGeocodeSearched(GeocodeResult geocodeResult, int resultCode) {
                    if (resultCode == 1000) {
                        if (geocodeResult != null && geocodeResult.getGeocodeAddressList() != null &&
                                geocodeResult.getGeocodeAddressList().size() > 0) {
                            GeocodeAddress geocodeAddress = geocodeResult.getGeocodeAddressList().get(0);
                            double latitude = geocodeAddress.getLatLonPoint().getLatitude();//纬度
                            double longitude = geocodeAddress.getLatLonPoint().getLongitude();//经度
                            String adcode = geocodeAddress.getAdcode();//区域编码
                            location = longitude + "," + latitude;
                            mPresenter.getCoupon(location);
                            SPUtils.putString(UserInfo.CURRENT_LOCATION.name(), location);
                        } else {
                            ToastUtils.showShort("地址名出错");
                        }
                    }

                }
            });
        }
    }

    /**
     * @param item 配置附近可用优惠券的点击弹出
     */
    private void setupDialog(final HomeCouponEntity.DataBean.ListBean item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        useCouponDialog = builder.create();
        View view = View.inflate(mActivity, R.layout.dialog_coupon, null);
        useCouponDialog.setView(view);
        useCouponDialog.setCancelable(true);
        useCouponDialog.show();

        ImageView ivCancel = view.findViewById(R.id.iv_cancel);
        TextView tvPrice = view.findViewById(R.id.tv_price);
        TextView tvType = view.findViewById(R.id.tv_type);
        TextView tvBusinessName = view.findViewById(R.id.tv_business_name);
        TextView tvConditionsOfUse = view.findViewById(R.id.tv_conditions_of_use);
        TextView tvShare = view.findViewById(R.id.tv_share);
        TextView tvToUse = view.findViewById(R.id.tv_to_use);

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useCouponDialog.dismiss();
            }
        });
        if (item.type == 1) {
            tvPrice.setText(item.amount + "折");
            tvType.setText("折扣券");
        } else if (item.type == 2) {
            tvPrice.setText(item.amount + "元");
            tvType.setText("现金优惠券");
        }
        tvBusinessName.setText("可用商家：" + item.sellerName);
        tvConditionsOfUse.setText("使用条件：满" + item.price + "元可用");

        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Share2.Builder(mActivity)
                        .setContentType(ShareContentType.TEXT)
                        // 设置要分享的文本内容
                        .setTextContent("This is a test message.")
                        .setTitle("Share Text")
                        .build()
                        .shareBySystem();
            }
        });
        tvToUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.useCoupon(String.valueOf(item.id));
            }
        });
    }

    public void useCoupon(HomeUseCouponEntity.DataBean data) {
        //领取优惠券
        ToastUtils.showShort("领取成功");
        if (useCouponDialog.isShowing()) {
            useCouponDialog.dismiss();
        }
    }

}
