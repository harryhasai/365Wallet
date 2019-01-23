package com.harry.wallet365.function.shop_detail.goods_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.BaseFragment;
import com.harry.wallet365.function.shop_detail.goods_detail.GoodsDetailActivity;
import com.harry.wallet365.network.entity.GoodsListEntity;
import com.harry.wallet365.utils.SwipeRefreshLayoutRefreshingUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Harry on 2019/1/18.
 * 商品列表
 */
public class GoodsListFragment extends BaseFragment<GoodsListPresenter> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    private int pageNum = 1;
    private boolean isLoadMore;
    private String sellerId;
    private GoodsListAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.fragment_goods_list;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        sellerId = bundle.getString("sellerId");

        initRecyclerView();

        mPresenter.getGoodsList(sellerId, pageNum);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.GOODS_LIST_FRAGMENT_GET_GOODS_LIST);
        return tags;
    }

    @Override
    protected GoodsListPresenter bindPresenter() {
        return new GoodsListPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
                mPresenter.getGoodsList(sellerId, pageNum);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new GoodsListAdapter(R.layout.item_goods_list);
        recyclerView.setAdapter(adapter);

        adapter.setPreLoadNumber(1);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                isLoadMore = true;
                mPresenter.getGoodsList(sellerId, pageNum);

            }
        }, recyclerView);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsListEntity.DataBean.ListBean bean = (GoodsListEntity.DataBean.ListBean) adapter.getData().get(position);
                Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
                intent.putExtra("goodsId", String.valueOf(bean.id));
                startActivity(intent);
            }
        });
    }

    public void getGoodsList(GoodsListEntity.DataBean data) {
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

}
