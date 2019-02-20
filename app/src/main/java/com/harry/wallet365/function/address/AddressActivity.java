package com.harry.wallet365.function.address;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.function.address.address_detail.AddressDetailActivity;
import com.harry.wallet365.network.entity.AddressEntity;
import com.harry.wallet365.utils.SwipeRefreshLayoutRefreshingUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/2/19.
 * 收货地址
 */
public class AddressActivity extends BaseActivity<AddressPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private int pageNum = 1;
    private boolean isLoadMore;
    private AddressAdapter adapter;
    /**
     * 确认删除的提示Dialog
     */
    private AlertDialog deleteDialog;

    @Override
    protected int setupView() {
        return R.layout.activity_address;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("收货地址");
        initRecyclerView();

        mPresenter.getAddress(pageNum);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.ADDRESS_ACTIVITY_GET_ADDRESS);
        tags.add(DisposableFinal.ADDRESS_ACTIVITY_DELETE_ADDRESS);
        return tags;
    }

    @Override
    protected AddressPresenter bindPresenter() {
        return new AddressPresenter();
    }

    public void getAddress(AddressEntity.DataBean data) {
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
                mPresenter.getAddress(pageNum);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddressAdapter(R.layout.item_address);
        recyclerView.setAdapter(adapter);

        adapter.setPreLoadNumber(1);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
                isLoadMore = true;
                mPresenter.getAddress(pageNum);

            }
        }, recyclerView);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AddressEntity.DataBean.ListBean bean = (AddressEntity.DataBean.ListBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.tv_edit:
                        Intent intent = new Intent(AddressActivity.this, AddressDetailActivity.class);
                        intent.putExtra("addressId", bean.id);
                        startActivityForResult(intent, CodeFinal.COMMON_REQUEST_CODE);
                        break;
                    case R.id.tv_delete:
                        showDeleteDialog(bean.id);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.btn_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_add_address:
                startActivityForResult(new Intent(this, AddressDetailActivity.class), CodeFinal.COMMON_REQUEST_CODE);
                break;
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

    private void showDeleteDialog(final int addressId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        deleteDialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_delete_address, null);
        deleteDialog.setView(view);
        deleteDialog.show();

        ImageView ivCancel = view.findViewById(R.id.iv_cancel);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.deleteAddress(addressId);
            }
        });
    }

    public void deleteAddress() {
        deleteDialog.dismiss();
        setRefreshing(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeFinal.COMMON_REQUEST_CODE && resultCode == CodeFinal.COMMON_RESULT_CODE) {
            setRefreshing(true);
        }
    }
}
