package com.harry.wallet365.function.search_shop;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.NearbyShopListEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/22.
 */
public class SearchShopPresenter extends BasePresenter<SearchShopActivity> {

    private final SearchShopModel model;

    public SearchShopPresenter() {
        model = new SearchShopModel();
    }

    public void getShopList(String location, String name) {
        model.getShopList(location, name, new Observer<NearbyShopListEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SEARCH_SHOP_ACTIVITY_GET_SHOP_LIST, d);
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
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
