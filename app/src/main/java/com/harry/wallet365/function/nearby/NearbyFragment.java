package com.harry.wallet365.function.nearby;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.harry.wallet365.function.search_shop.SearchShopActivity;
import com.harry.wallet365.network.entity.NearbyBannerEntity;
import com.harry.wallet365.network.entity.NearbyCategoryEntity;
import com.harry.wallet365.network.entity.NearbyShopListEntity;
import com.harry.wallet365.utils.LocationUtil;
import com.harry.wallet365.utils.SPUtils;
import com.harry.wallet365.utils.SwipeRefreshLayoutRefreshingUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Harry on 2019/1/10.
 * 附近
 */
public class NearbyFragment extends BaseFragment<NearbyPresenter> {

    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    private int pageNum = 1;
    private boolean isLoadMore;
    private NearbyAdapter adapter;
    /**
     * 是否获取到了商家列表
     */
    private boolean isShopList;
    /**
     * 是否获取到了行业分类
     */
    private boolean isCategory;
    /**
     * 是否获取到了轮播图
     */
    private boolean isBanner;

    @Override
    protected int setupView() {
        return R.layout.fragment_nearby;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        initRecyclerView();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.NEADBY_FRAGMENT_GET_BANNER);
        tags.add(DisposableFinal.NEADBY_FRAGMENT_GET_CATEGORY);
        tags.add(DisposableFinal.NEADBY_FRAGMENT_GET_SHOP_LIST);
        return tags;
    }

    @Override
    protected NearbyPresenter bindPresenter() {
        return new NearbyPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_location, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_location: //定位
                Intent intent = new Intent(mActivity, ModifyLocationActivity.class);
                intent.putExtra("city", SPUtils.getString(UserInfo.CITY.name(), ""));
                startActivityForResult(intent, CodeFinal.COMMON_REQUEST_CODE);
                break;
            case R.id.tv_search: //搜索栏
                startActivity(new Intent(mActivity, SearchShopActivity.class));
                break;
        }
    }

    /**
     * @param data 获取到的轮播图数据
     */
    public void setBanner(List<NearbyBannerEntity.DataBean> data) {
        adapter.setBannerData(data);
        isBanner = true;
    }

    /**
     * @param data 行业分类数据
     */
    public void setCategory(NearbyCategoryEntity.DataBean data) {
        adapter.setCategoryData(data);
        isCategory = true;
    }

    /**
     * @param data 商家列表数据
     */
    public void setShopList(NearbyShopListEntity.DataBean data) {
        if (data.list.size() != 0) {
            if (isLoadMore) {
                adapter.addShopListData(data);
                adapter.loadMoreComplete();
            } else {
                adapter.setShopListData(data);
            }
            adapter.setEnableLoadMore(true);
        } else {
            adapter.loadMoreEnd();
        }
        isShopList = true;
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
                String location = SPUtils.getString(UserInfo.CURRENT_LOCATION.name(), "");
                if (!TextUtils.isEmpty(location)) {
                    mPresenter.getShopList(location, pageNum);
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        List<NearbyMultiItem> multiItems = new ArrayList<>();
        multiItems.add(new NearbyMultiItem(NearbyMultiItem.TOP));
        multiItems.add(new NearbyMultiItem(NearbyMultiItem.BOTTOM));
        adapter = new NearbyAdapter(multiItems);
        recyclerView.setAdapter(adapter);

        adapter.setPreLoadNumber(1);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                isLoadMore = true;
                String location = SPUtils.getString(UserInfo.CURRENT_LOCATION.name(), "");
                if (!TextUtils.isEmpty(location)) {
                    mPresenter.getShopList(location, pageNum);
                }

            }
        }, recyclerView);

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
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) { //显示
            String city = SPUtils.getString(UserInfo.CITY.name(), "");
            if (!TextUtils.isEmpty(city)) {
                tvLocation.setText(city);
            }
            String location = SPUtils.getString(UserInfo.CURRENT_LOCATION.name(), "");
            if (!TextUtils.isEmpty(location)) {
                if (!isBanner) {
                    mPresenter.getBanner(location);
                }
                if (!isCategory) {
                    mPresenter.getCategory();
                }
                if (!isShopList) {
                    mPresenter.getShopList(location, pageNum);
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeFinal.COMMON_REQUEST_CODE && resultCode == CodeFinal.COMMON_RESULT_CODE) {
            String cityName = data.getStringExtra("cityName");
            tvLocation.setText(cityName);
            SPUtils.putString(UserInfo.CITY.name(), cityName);
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
                            String currentLocation = longitude + "," + latitude;
                            mPresenter.getShopList(currentLocation, pageNum);
                            mPresenter.getBanner(currentLocation);
                            SPUtils.putString(UserInfo.CURRENT_LOCATION.name(), currentLocation);
                        } else {
                            ToastUtils.showShort("地址名出错");
                        }
                    }

                }
            });
        }
    }
}
