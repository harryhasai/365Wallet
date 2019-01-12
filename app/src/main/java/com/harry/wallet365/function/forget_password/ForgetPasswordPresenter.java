package com.harry.wallet365.function.forget_password;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/10.
 */
public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordActivity> {

    private final ForgetPasswordModel model;

    public ForgetPasswordPresenter() {
        model = new ForgetPasswordModel();
    }

    public void forgetPassword(String phone, String password, String code) {
        model.forgetPassword(phone, password, code, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.FORGET_PASSWORD_ACTIVITY_FORGET_PASSWORD, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    ToastUtils.showShort("修改成功");
                    view.finish();
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

    public void getVerificationCode(String phone) {
        model.getVerificationCode(phone, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.FORGET_PASSWORD_ACTIVITY_GET_VERIFICATION_CODE, d);
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
}
