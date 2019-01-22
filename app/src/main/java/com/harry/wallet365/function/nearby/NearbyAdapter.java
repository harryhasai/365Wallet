package com.harry.wallet365.function.nearby;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.function.home.BannerImageLoader;
import com.harry.wallet365.function.home.HomeMultiItem;
import com.harry.wallet365.function.shop_detail.ShopDetailActivity;
import com.harry.wallet365.function.web_page.WebViewPageActivity;
import com.harry.wallet365.network.entity.NearbyBannerEntity;
import com.harry.wallet365.network.entity.NearbyCategoryEntity;
import com.harry.wallet365.network.entity.NearbyShopListEntity;
import com.harry.wallet365.utils.SPUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 2019/1/21.
 */
public class NearbyAdapter extends BaseMultiItemQuickAdapter<NearbyMultiItem, BaseViewHolder> {

    private Banner banner;
    private NearbyCategoryAdapter categoryAdapter;
    private NearbyShopListAdapter shopListAdapter;
    private TextView tvShopCount;
    private TextView tvShopDistance;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public NearbyAdapter(List<NearbyMultiItem> data) {
        super(data);
        addItemType(HomeMultiItem.TOP, R.layout.item_nearby_top);
        addItemType(HomeMultiItem.BOTTOM, R.layout.item_nearby_bottom);
    }

    @Override
    protected void convert(BaseViewHolder helper, NearbyMultiItem item) {
        switch (helper.getItemViewType()) {
            case NearbyMultiItem.TOP:
                banner = helper.getView(R.id.banner);
                setupBanner();
                RecyclerView rvCategory = helper.getView(R.id.rv_category);
                setupRecyclerView(rvCategory);
                break;
            case NearbyMultiItem.BOTTOM:
                RecyclerView rvShopList = helper.getView(R.id.rv_shop_list);
                tvShopCount = helper.getView(R.id.tv_shop_count);
                tvShopDistance = helper.getView(R.id.tv_shop_distance);
                rvShopList.setLayoutManager(new LinearLayoutManager(mContext));
                shopListAdapter = new NearbyShopListAdapter(R.layout.item_nearby_bottom_item);
                rvShopList.setAdapter(shopListAdapter);

                shopListAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        NearbyShopListEntity.DataBean.ListBean bean = (NearbyShopListEntity.DataBean.ListBean) adapter.getData().get(position);
                        Intent intent = new Intent(mContext, ShopDetailActivity.class);
                        intent.putExtra("location", SPUtils.getString(UserInfo.CURRENT_LOCATION.name(), ""));
                        intent.putExtra("sellerId", bean.id);
                        mContext.startActivity(intent);
                    }
                });
                break;
        }
    }

    public void addShopListData(NearbyShopListEntity.DataBean data) {
        shopListAdapter.addData(data.list);
    }

    public void setShopListData(NearbyShopListEntity.DataBean data) {
        tvShopCount.setText("附近有" + data.totalRow + "家商家");
        for (int i = 0; i < data.list.size(); i++) {
            NearbyShopListEntity.DataBean.ListBean bean = data.list.get(i);
            if (bean.status == 1) {//最近商家
                tvShopDistance.setText("最近距你" + bean.distance + "m");
            }
        }
        shopListAdapter.setNewData(data.list);
    }

    /**
     * 配置轮播图
     */
    private void setupBanner() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new BannerImageLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
        //banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
    }

    /**
     * @param data 轮播图数据
     */
    public void setBannerData(final List<NearbyBannerEntity.DataBean> data) {
        if (data.size() != 0) {
            List<String> images = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                images.add(data.get(i).img);
            }
            if (banner != null) {
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(mContext, WebViewPageActivity.class);
                        intent.putExtra("url", data.get(position).url);
                        mContext.startActivity(intent);
                    }
                });
                //设置图片集合
                banner.setImages(images);
                //banner设置方法全部调用完毕时最后调用
                banner.start();
            }
        }
    }

    /**
     * @param recyclerView 配置列表
     */
    private void setupRecyclerView(RecyclerView recyclerView) {
        categoryAdapter = new NearbyCategoryAdapter(R.layout.item_nearby_top_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(categoryAdapter);
    }


    public void setCategoryData(NearbyCategoryEntity.DataBean data) {
        categoryAdapter.setNewData(data.list);
    }

}
