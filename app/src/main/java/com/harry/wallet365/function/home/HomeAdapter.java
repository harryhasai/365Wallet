package com.harry.wallet365.function.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.wallet365.R;
import com.harry.wallet365.function.web_page.WebViewPageActivity;
import com.harry.wallet365.network.entity.HomeBannerEntity;
import com.harry.wallet365.network.entity.HomeCouponEntity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 2019/1/12.
 */
public class HomeAdapter extends BaseMultiItemQuickAdapter<HomeMultiItem, BaseViewHolder> {

    private Banner banner;
    private HomeBottomListAdapter bottomListAdapter;
    private HomeCouponAdapter couponAdapter;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeAdapter(List<HomeMultiItem> data) {
        super(data);
        addItemType(HomeMultiItem.TOP, R.layout.item_home_top);
        addItemType(HomeMultiItem.BOTTOM, R.layout.item_home_bottom);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMultiItem item) {
        switch (helper.getItemViewType()) {
            case HomeMultiItem.TOP:
                setupBanner(helper);
                setupCoupon(helper);
                break;
            case HomeMultiItem.BOTTOM:
                initBottomList(helper);
                break;
        }
    }

    private void setupCoupon(BaseViewHolder helper) {
        RecyclerView rvCoupon = helper.getView(R.id.rv_coupon);
        rvCoupon.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        couponAdapter = new HomeCouponAdapter(R.layout.item_coupon);
        rvCoupon.setAdapter(couponAdapter);
    }

    public void setOnCouponItemClickListener(OnItemClickListener listener) {
        couponAdapter.setOnItemClickListener(listener);
    }

    private void initBottomList(BaseViewHolder helper) {
        RecyclerView rvActivityList = helper.getView(R.id.rv_activity_list);
        rvActivityList.setLayoutManager(new LinearLayoutManager(mContext));
        bottomListAdapter = new HomeBottomListAdapter(R.layout.item_activity);
        rvActivityList.setAdapter(bottomListAdapter);
    }

    /**
     * @param data 设置底部列表数据
     */
    public void setBottomListData(List<HomeBannerEntity.DataBean.ListBean> data) {
        bottomListAdapter.setNewData(data);
    }

    /**
     * @param data 添加底部列表数据(用于分页)
     */
    public void addBottomListData(List<HomeBannerEntity.DataBean.ListBean> data) {
        bottomListAdapter.addData(data);
    }

    public void getBanner(final HomeBannerEntity.DataBean data) {
        if (data.list.size() != 0) {
            List<String> images = new ArrayList<>();
            for (int i = 0; i < data.list.size(); i++) {
                images.add(data.list.get(i).img);
            }
            if (banner != null) {
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(mContext, WebViewPageActivity.class);
                        intent.putExtra("url", data.list.get(position).url);
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

    private void setupBanner(BaseViewHolder helper) {
        banner = helper.getView(R.id.banner);
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

    public void setCouponList(List<HomeCouponEntity.DataBean.ListBean> list) {
        couponAdapter.setNewData(list);
    }
}
