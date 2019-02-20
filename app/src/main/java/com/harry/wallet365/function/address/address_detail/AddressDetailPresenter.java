package com.harry.wallet365.function.address.address_detail;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.AddressDetailEntity;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/2/19.
 */
public class AddressDetailPresenter extends BasePresenter<AddressDetailActivity> {

    private final AddressDetailModel model;

    public AddressDetailPresenter() {
        model = new AddressDetailModel();
    }

    public void getAddress(int addressId) {
        model.getAddress(addressId, new Observer<AddressDetailEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ADDRESS_DETAIL_ACTIVITY_GET_ADDRESS, d);
            }

            @Override
            public void onNext(AddressDetailEntity addressDetailEntity) {
                if (addressDetailEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getAddress(addressDetailEntity.data);
                } else {
                    ToastUtils.showShort(addressDetailEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取收货地址失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void AddAddress(String realName, String phone, String address, int status) {
        model.AddAddress(realName, phone, address, status, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ADDRESS_DETAIL_ACTIVITY_ADD_ADDRESS, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.setResult(CodeFinal.COMMON_RESULT_CODE);
                    view.finish();
                } else {
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("添加收货地址失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void updateAddress(int addressId, String realName, String phone, String address, int status) {
        model.updateAddress(addressId, realName, phone, address, status, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ADDRESS_DETAIL_ACTIVITY_UPDATE_ADDRESS, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.setResult(CodeFinal.COMMON_RESULT_CODE);
                    view.finish();
                } else {
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("更新收货地址失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
