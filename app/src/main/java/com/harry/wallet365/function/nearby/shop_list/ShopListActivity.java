package com.harry.wallet365.function.nearby.shop_list;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.network.entity.NearbyShopListEntity;
import com.harry.wallet365.utils.SPUtils;
import com.harry.wallet365.utils.SwipeRefreshLayoutRefreshingUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/1/23.
 * 商家列表页面
 */
public class ShopListActivity extends BaseActivity<ShopListPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Integer> ids;
    private ArrayList<String> names;
    private int pageNum = 1;
    private boolean isLoadMore;
    private ShopListAdapter adapter;
    private int currentPosition;

    @Override
    protected int setupView() {
        return R.layout.activity_shop_list;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        tvTitle.setText("商家列表");

        ids = getIntent().getIntegerArrayListExtra("ids");
        names = getIntent().getStringArrayListExtra("names");
        currentPosition = getIntent().getIntExtra("position", 0);

        initTabLayout();
        initRecyclerView();
        mPresenter.getShopList(SPUtils.getString(UserInfo.CURRENT_LOCATION.name(), "0,0"),
                String.valueOf(ids.get(0)), pageNum);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.SHOP_LIST_ACTIVITY_GET_SHOP_LIST);
        return tags;
    }

    @Override
    protected ShopListPresenter bindPresenter() {
        return new ShopListPresenter();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
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
                mPresenter.getShopList(SPUtils.getString(UserInfo.CURRENT_LOCATION.name(), "0,0"),
                        String.valueOf(ids.get(currentPosition)), pageNum);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShopListAdapter(R.layout.item_nearby_bottom_item);
        recyclerView.setAdapter(adapter);

        adapter.setPreLoadNumber(1);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                isLoadMore = true;
                mPresenter.getShopList(SPUtils.getString(UserInfo.CURRENT_LOCATION.name(), "0,0"),
                        String.valueOf(ids.get(currentPosition)), pageNum);

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

    boolean isFirstIn = true;//用来判断当前Tab是否是首次进入, 如果是则清空列表 在加载数据, 如果不是, 比如下拉刷新则不清空列表

    public void setShopList(NearbyShopListEntity.DataBean data) {
        if (isFirstIn) {
            adapter.setNewData(null);
        }
        if (data.list.size() != 0) {
            if (isLoadMore) {
                adapter.addData(data.list);
                adapter.loadMoreComplete();
            } else {
                adapter.setNewData(data.list);
            }
            adapter.setEnableLoadMore(true);
            isFirstIn = false;
        } else {
            adapter.loadMoreEnd();
        }
    }

    private void initTabLayout() {
        ArrayList<CustomTabEntity> tabList = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            tabList.add(new TabEntity(names.get(i)));
        }
        tabLayout.setTabData(tabList);

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                ToastUtils.showShort("onTabSelect" + position);
                currentPosition = position;
                mPresenter.getShopList(SPUtils.getString(UserInfo.CURRENT_LOCATION.name(), "0,0"),
                        String.valueOf(ids.get(position)), pageNum);
                isFirstIn = true;
                pageNum = 1;
            }

            @Override
            public void onTabReselect(int position) {
//                ToastUtils.showShort("onTabReselect" + position);
            }
        });
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
