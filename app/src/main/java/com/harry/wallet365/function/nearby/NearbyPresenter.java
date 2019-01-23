package com.harry.wallet365.function.nearby;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.NearbyBannerEntity;
import com.harry.wallet365.network.entity.NearbyCategoryEntity;
import com.harry.wallet365.network.entity.NearbyShopListEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/21.
 */
public class NearbyPresenter extends BasePresenter<NearbyFragment> {

    private final NearbyModel model;

    public NearbyPresenter() {
        model = new NearbyModel();
    }

    public void getBanner(String location) {
        model.getBanner(location, new Observer<NearbyBannerEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.NEARBY_FRAGMENT_GET_BANNER, d);
            }

            @Override
            public void onNext(NearbyBannerEntity nearbyBannerEntity) {
                if (nearbyBannerEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.setBanner(nearbyBannerEntity.data);
                } else {
                    ToastUtils.showShort(nearbyBannerEntity.msg);
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

    public void getCategory() {
        model.getCategory(new Observer<NearbyCategoryEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.NEARBY_FRAGMENT_GET_CATEGORY, d);
            }

            @Override
            public void onNext(NearbyCategoryEntity nearbyCategoryEntity) {
                if (nearbyCategoryEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.setCategory(nearbyCategoryEntity.data);
                } else {
                    ToastUtils.showShort(nearbyCategoryEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取行业分类失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getShopList(String location, int pageNum) {
        model.getShopList(location, pageNum, new Observer<NearbyShopListEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.NEARBY_FRAGMENT_GET_SHOP_LIST, d);
            }

            @Override
            public void onNext(NearbyShopListEntity nearbyShopListEntity) {
                if (nearbyShopListEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.setShopList(nearbyShopListEntity.data);
                } else {
                    ToastUtils.showShort(nearbyShopListEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取商家列表失败");
                view.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                view.setRefreshing(false);
            }
        });
    }
}
