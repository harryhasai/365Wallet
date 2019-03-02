package com.harry.wallet365.function.my_coupon.already_used;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.wallet365.R;
import com.harry.wallet365.base.BaseFragment;
import com.harry.wallet365.network.entity.MyCouponEntity;
import com.harry.wallet365.utils.SwipeRefreshLayoutRefreshingUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Harry on 2019/2/25.
 * 已使用的优惠券
 */
public class AlreadyUsedFragment extends BaseFragment<AlreadyUsedPresenter> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    private int pageNum = 1;
    private boolean isLoadMore;
    private AlreadyUsedAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.fragment_already_used;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        initRecyclerView();

        mPresenter.getCouponList(pageNum);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected AlreadyUsedPresenter bindPresenter() {
        return new AlreadyUsedPresenter();
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
                mPresenter.getCouponList(pageNum);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new AlreadyUsedAdapter(R.layout.item_already_used);
        recyclerView.setAdapter(adapter);

        adapter.setPreLoadNumber(1);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                isLoadMore = true;
                mPresenter.getCouponList(pageNum);

            }
        }, recyclerView);

    }

    public void getCouponList(MyCouponEntity.DataBean data) {
        if (data.list.size() != 0) {
            if (isLoadMore) {
                adapter.addData(data.list);
                adapter.loadMoreComplete();
            } else {
                adapter.setNewData(data.list);
            }
            adapter.setEnableLoadMore(true);
        } else {
            adapter.loadMoreEnd();
        }
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
