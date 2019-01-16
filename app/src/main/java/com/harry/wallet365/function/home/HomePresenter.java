package com.harry.wallet365.function.home;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.HomeBannerEntity;
import com.harry.wallet365.network.entity.HomeCouponEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/12.
 */
public class HomePresenter extends BasePresenter<HomeFragment> {

    private final HomeModel model;

    public HomePresenter() {
        model = new HomeModel();
    }

    public void getBanner() {
        model.getBanner(new Observer<HomeBannerEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.HOME_FRAGMENT_GET_BANNER, d);
            }

            @Override
            public void onNext(HomeBannerEntity homeBannerEntity) {
                if (homeBannerEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getBanner(homeBannerEntity.data);
                } else {
                    ToastUtils.showShort(homeBannerEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取轮播图失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getBottomList(int pageNum) {
        model.getBottomList(pageNum, new Observer<HomeBannerEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.HOME_FRAGMENT_GET_BOTTOM_LIST, d);
            }

            @Override
            public void onNext(HomeBannerEntity homeBannerEntity) {
                if (homeBannerEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getBottomList(homeBannerEntity.data);
                } else {
                    ToastUtils.showShort(homeBannerEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取促销活动失败");
                view.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                view.setRefreshing(false);
            }
        });
    }

    public void getCoupon(String location) {
        model.getCoupon(location, new Observer<HomeCouponEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.HOME_FRAGMENT_GET_COUPON, d);
            }

            @Override
            public void onNext(HomeCouponEntity homeCouponEntity) {
                if (homeCouponEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getCoupon(homeCouponEntity.data);
                } else {
                    ToastUtils.showShort(homeCouponEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取优惠券失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
