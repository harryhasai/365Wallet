package com.harry.wallet365.function.address;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.AddressEntity;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/2/19.
 */
public class AddressPresenter extends BasePresenter<AddressActivity> {

    private final AddressModel model;

    public AddressPresenter() {
        model = new AddressModel();
    }

    public void getAddress(int page) {
        model.getAddress(page, new Observer<AddressEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ADDRESS_ACTIVITY_GET_ADDRESS, d);
            }

            @Override
            public void onNext(AddressEntity addressEntity) {
                if (addressEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getAddress(addressEntity.data);
                } else {
                    ToastUtils.showShort(addressEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取收货地址列表失败");
                view.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                view.setRefreshing(false);
            }
        });
    }

    public void deleteAddress(int id) {
        model.deleteAddress(id, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ADDRESS_ACTIVITY_DELETE_ADDRESS, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                view.deleteAddress();
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("删除收货地址失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
