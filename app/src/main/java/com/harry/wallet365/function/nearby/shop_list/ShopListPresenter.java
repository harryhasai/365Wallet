package com.harry.wallet365.function.nearby.shop_list;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.NearbyShopListEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/23.
 */
public class ShopListPresenter extends BasePresenter<ShopListActivity> {

    private final ShopListModel model;

    public ShopListPresenter() {
        model = new ShopListModel();
    }

    public void getShopList(String location, String type, int pageNum) {
        model.getShopList(location, type, pageNum, new Observer<NearbyShopListEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SHOP_LIST_ACTIVITY_GET_SHOP_LIST, d);
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
