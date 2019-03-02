package com.harry.wallet365.function.my_coupon.already_used;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.MyCouponEntity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/2/25.
 */
public class AlreadyUsedPresenter extends BasePresenter<AlreadyUsedFragment> {

    private final AlreadyUsedModel model;

    public AlreadyUsedPresenter() {
        model = new AlreadyUsedModel();
    }

    public void getCouponList(int page) {
        model.getCouponList(page, new Observer<MyCouponEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyCouponEntity myCouponEntity) {
                if (myCouponEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getCouponList(myCouponEntity.data);
                } else {
                    ToastUtils.showShort(myCouponEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取优惠券失败");
                view.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                view.setRefreshing(false);
            }
        });
    }
}
