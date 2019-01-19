package com.harry.wallet365.function.shop_detail.goods_detail;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.GoodsDetailEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/19.
 */
public class GoodsDetailPresenter extends BasePresenter<GoodsDetailActivity> {

    private final GoodsDetailModel model;

    public GoodsDetailPresenter() {
        model = new GoodsDetailModel();
    }

    public void getGoodsDetail(String goodsId) {
        model.getGoodsDetail(goodsId, new Observer<GoodsDetailEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.GOODS_DETAIL_ACTIVITY_GET_GOODS_DETAIL, d);
            }

            @Override
            public void onNext(GoodsDetailEntity goodsDetailEntity) {
                if (goodsDetailEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getGoodsDetail(goodsDetailEntity.data);
                } else {
                    ToastUtils.showShort(goodsDetailEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取商品详情失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
