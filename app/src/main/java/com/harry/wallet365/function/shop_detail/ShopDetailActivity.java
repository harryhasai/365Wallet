package com.harry.wallet365.function.shop_detail;

import android.app.AlertDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.function.shop_detail.goods_list.GoodsListFragment;
import com.harry.wallet365.function.shop_detail.shop_introduction.ShopIntroductionFragment;
import com.harry.wallet365.function.shop_detail.user_comment.UserCommentFragment;
import com.harry.wallet365.network.entity.HomeUseCouponEntity;
import com.harry.wallet365.network.entity.ShopDetailCouponEntity;
import com.harry.wallet365.network.entity.ShopDetailEntity;
import com.harry.wallet365.utils.StarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/1/18.
 * 店铺详情
 */
public class ShopDetailActivity extends BaseActivity<ShopDetailPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_cover_img)
    ImageView ivCoverImg;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.star_view)
    StarView starView;
    @BindView(R.id.tv_distance_and_time)
    TextView tvDistanceAndTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.rv_coupon)
    RecyclerView rvCoupon;
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    private ShopDetailCouponAdapter adapter;
    private int isShowCoupon;
    private AlertDialog couponDialog;
    private int sellerId;
    private String location;

    @Override
    protected int setupView() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("商家详情");

        location = getIntent().getStringExtra("location");
        sellerId = getIntent().getIntExtra("sellerId", 0);
        isShowCoupon = getIntent().getIntExtra("isShowCoupon", 0);

        initRecyclerView();
        initTabLayout();

        mPresenter.getShopDetail(String.valueOf(sellerId), location);
        mPresenter.getCoupon(String.valueOf(sellerId));
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.SHOP_DETAIL_ACTIVITY_GET_SHOP_DETAIL);
        tags.add(DisposableFinal.SHOP_DETAIL_ACTIVITY_GET_COUPON);
        return tags;
    }

    @Override
    protected ShopDetailPresenter bindPresenter() {
        return new ShopDetailPresenter();
    }

    /**
     * @param data 网络获取商家详情
     */
    public void getShopDetail(ShopDetailEntity.DataBean data) {
        Glide.with(this).load(data.headImg).into(ivCoverImg);
        tvShopName.setText(data.name);
        starView.setCheckCount(data.score);
        String time = data.workTime.substring(0, 5) + "-" + data.workTime.substring(5);
        tvDistanceAndTime.setText("|\t\t" + data.distance + "m\t\t|\t\t" + time);
        tvAddress.setText(data.address);
        tvTel.setText(data.tel);
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void initRecyclerView() {
        rvCoupon.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ShopDetailCouponAdapter(R.layout.item_shop_detail_coupon);
        rvCoupon.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (couponDialog != null) {
                    couponDialog.show();
                    setDialogSize();
                }

            }
        });
    }

    public void getCoupon(ShopDetailCouponEntity.DataBean data) {
        adapter.setNewData(data.list);

        initCouponDialog(data.list);
    }

    private void initCouponDialog(List<ShopDetailCouponEntity.DataBean.ListBean> list) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        couponDialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_shop_detail, null);
        couponDialog.setView(view);
        couponDialog.setCancelable(true);

        ImageView ivCancel = view.findViewById(R.id.iv_cancel);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couponDialog.dismiss();
            }
        });
        RecyclerView rvDialogCoupon = view.findViewById(R.id.rv_dialog_coupon);
        rvDialogCoupon.setLayoutManager(new LinearLayoutManager(this));
        DialogCouponAdapter adapter = new DialogCouponAdapter(R.layout.item_dialog_coupon);
        rvDialogCoupon.setAdapter(adapter);
        adapter.setNewData(list);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ShopDetailCouponEntity.DataBean.ListBean bean = (ShopDetailCouponEntity.DataBean.ListBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.btn_get:
                        mPresenter.useCoupon(String.valueOf(bean.id));
                        break;
                }
            }
        });

        if (isShowCoupon > 0) {
            //弹出优惠券弹窗
            couponDialog.show();
            setDialogSize();
        }
    }

    private void initTabLayout() {
        ArrayList<CustomTabEntity> tabList = new ArrayList<>();
        tabList.add(new TabEntity("商品列表"));
        tabList.add(new TabEntity("用户评价"));
        tabList.add(new TabEntity("商家介绍"));
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        GoodsListFragment goodsListFragment = new GoodsListFragment();
        Bundle goodsBundle = new Bundle();
        goodsBundle.putString("sellerId", String.valueOf(sellerId));
        goodsBundle.putString("location", location);
        goodsListFragment.setArguments(goodsBundle);
        fragmentList.add(goodsListFragment);
        UserCommentFragment userCommentFragment = new UserCommentFragment();
        userCommentFragment.setArguments(goodsBundle);
        fragmentList.add(userCommentFragment);
        ShopIntroductionFragment shopIntroductionFragment = new ShopIntroductionFragment();
        shopIntroductionFragment.setArguments(goodsBundle);
        fragmentList.add(shopIntroductionFragment);
        tabLayout.setTabData(tabList, this, R.id.fl_container, fragmentList);

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {
                    llContainer.setVisibility(View.VISIBLE);
                } else {
                    llContainer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabReselect(int position) {

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

    /**
     * 设置Dialog的尺寸
     */
    private void setDialogSize() {
        WindowManager.LayoutParams params = couponDialog.getWindow().getAttributes();
//        params.width = ConvertUtils.dp2px(310);
        params.height = ConvertUtils.dp2px(400);
        couponDialog.getWindow().setAttributes(params);
    }
}
