package com.harry.wallet365.function.recommend;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.network.entity.RecommendEntity;
import com.harry.wallet365.utils.SwipeRefreshLayoutRefreshingUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/2/20.
 * 推荐人列表
 */
public class RecommendActivity extends BaseActivity<RecommendPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private int pageNum = 1;
    private boolean isLoadMore;
    private RecommendAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.activity_recommend;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("推荐人列表");

        initRecyclerView();

        mPresenter.getRecommendList(pageNum);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.RECOMMEND_ACTIVITY_GET_RECOMMEND_LIST);
        return tags;
    }

    @Override
    protected RecommendPresenter bindPresenter() {
        return new RecommendPresenter();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    public void getRecommendList(RecommendEntity.DataBean data) {
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
                mPresenter.getRecommendList(pageNum);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecommendAdapter(R.layout.item_recommend);
        recyclerView.setAdapter(adapter);

        adapter.setPreLoadNumber(1);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                isLoadMore = true;
                mPresenter.getRecommendList(pageNum);

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
}
