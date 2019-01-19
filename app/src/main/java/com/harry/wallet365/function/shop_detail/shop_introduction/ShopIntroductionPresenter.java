package com.harry.wallet365.function.shop_detail.shop_introduction;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.ShopDetailEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/19.
 */
public class ShopIntroductionPresenter extends BasePresenter<ShopIntroductionFragment> {

    private final ShopIntroductionModel model;

    public ShopIntroductionPresenter() {
        model = new ShopIntroductionModel();
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
}
