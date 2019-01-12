package com.harry.wallet365.function.register;

import android.content.Intent;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.function.main.MainActivity;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/10.
 */
public class RegisterPresenter extends BasePresenter<RegisterActivity> {

    private final RegisterModel model;

    public RegisterPresenter() {
        model = new RegisterModel();
    }

    public void getVerificationCode(String phone) {
        model.getVerificationCode(phone, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.REGISTER_ACTIVITY_GET_VERIFICATION_CODE, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.countDown();
                } else {
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
                view.finishCountDown();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void register(String phone, String password, String code, String inviteCode) {
        model.register(phone, password, code, inviteCode, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.REGISTER_ACTIVITY_REGISTER, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.registerSuccess();
                } else {
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
