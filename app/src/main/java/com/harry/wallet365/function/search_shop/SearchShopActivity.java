package com.harry.wallet365.function.search_shop;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.function.shop_detail.ShopDetailActivity;
import com.harry.wallet365.network.entity.NearbyShopListEntity;
import com.harry.wallet365.utils.SPUtils;
import com.harry.wallet365.utils.SwipeRefreshLayoutRefreshingUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/1/22.
 * 店家搜索
 */
public class SearchShopActivity extends BaseActivity<SearchShopPresenter> {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private SearchShopAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.activity_search_shop;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        initRecyclerView();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.SEARCH_SHOP_ACTIVITY_GET_SHOP_LIST);
        return tags;
    }

    @Override
    protected SearchShopPresenter bindPresenter() {
        return new SearchShopPresenter();
    }

    @OnClick({R.id.iv_back, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                String searchText = etSearch.getText().toString().trim();
                if (TextUtils.isEmpty(searchText)) {
                    ToastUtils.showShort("请输入您要搜索的内容");
                    return;
                }
                mPresenter.getShopList(SPUtils.getString(UserInfo.CURRENT_LOCATION.name(), "0,0"), searchText);
                break;
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchShopAdapter(R.layout.item_nearby_bottom_item);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NearbyShopListEntity.DataBean.ListBean bean = (NearbyShopListEntity.DataBean.ListBean) adapter.getData().get(position);
                Intent intent = new Intent(SearchShopActivity.this, ShopDetailActivity.class);
                intent.putExtra("location", SPUtils.getString(UserInfo.CURRENT_LOCATION.name(), "0,0"));
                intent.putExtra("sellerId", bean.id);
                startActivity(intent);
            }
        });
    }

    public void setShopList(NearbyShopListEntity.DataBean data) {
        adapter.setNewData(data.list);
    }

}
