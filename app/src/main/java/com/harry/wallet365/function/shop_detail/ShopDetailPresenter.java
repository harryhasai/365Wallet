package com.harry.wallet365.function.shop_detail;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.HomeUseCouponEntity;
import com.harry.wallet365.network.entity.ShopDetailCouponEntity;
import com.harry.wallet365.network.entity.ShopDetailEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/18.
 */
public class ShopDetailPresenter extends BasePresenter<ShopDetailActivity> {

    private final ShopDetailModel model;

    public ShopDetailPresenter() {
        model = new ShopDetailModel();
    }

    public void getShopDetail(String sellerId, String location) {
        model.getShopDetail(sellerId, location, new Observer<ShopDetailEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SHOP_DETAIL_ACTIVITY_GET_SHOP_DETAIL, d);
            }

            @Override
            public void onNext(ShopDetailEntity shopDetailEntity) {
                if (shopDetailEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getShopDetail(shopDetailEntity.data);
                } else {
                    ToastUtils.showShort(shopDetailEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取商家详情失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getCoupon(String sellerId) {
        model.getCoupon(sellerId, new Observer<ShopDetailCouponEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SHOP_DETAIL_ACTIVITY_GET_COUPON, d);
            }

            @Override
            public void onNext(ShopDetailCouponEntity shopDetailCouponEntity) {
                if (shopDetailCouponEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getCoupon(shopDetailCouponEntity.data);
                } else {
                    ToastUtils.showShort(shopDetailCouponEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取商家优惠券失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void useCoupon(String voucherId) {
        model.useCoupon(voucherId, new Observer<HomeUseCouponEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.HOME_FRAGMENT_USE_COUPON, d);
            }

            @Override
            public void onNext(HomeUseCouponEntity homeUseCouponEntity) {
                if (homeUseCouponEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    ToastUtils.showShort("领取成功");
                } else {
                    ToastUtils.showShort(homeUseCouponEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("领取优惠券失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
