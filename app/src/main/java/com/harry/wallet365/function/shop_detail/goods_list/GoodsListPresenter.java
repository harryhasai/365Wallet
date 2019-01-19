package com.harry.wallet365.function.shop_detail.goods_list;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.GoodsListEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/18.
 */
public class GoodsListPresenter extends BasePresenter<GoodsListFragment> {

    private final GoodsListModel model;

    public GoodsListPresenter() {
        model = new GoodsListModel();
    }

    public void getGoodsList(String sellerId, int page) {
        model.getGoodsList(sellerId, page, new Observer<GoodsListEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.GOODS_LIST_FRAGMENT_GET_GOODS_LIST, d);
            }

            @Override
            public void onNext(GoodsListEntity goodsListEntity) {
                if (goodsListEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getGoodsList(goodsListEntity.data);
                } else {
                    ToastUtils.showShort(goodsListEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取商品列表失败");
                view.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                view.setRefreshing(false);
            }
        });
    }
}
