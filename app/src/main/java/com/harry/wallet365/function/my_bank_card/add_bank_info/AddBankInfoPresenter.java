package com.harry.wallet365.function.my_bank_card.add_bank_info;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/2/23.
 */
public class AddBankInfoPresenter extends BasePresenter<AddBankInfoActivity> {

    private final AddBankInfoModel model;

    public AddBankInfoPresenter() {
        model = new AddBankInfoModel();
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
                ToastUtils.showShort("获取验证码失败");
                view.finishCountDown();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void addBankInfo(String realName, String bankName, String cardNum, String phone, String code) {
        model.addBankInfo(realName, bankName, cardNum, phone, code, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.REGISTER_ACTIVITY_GET_VERIFICATION_CODE, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    ToastUtils.showShort("新增成功");
                    view.finish();
                } else if (commonEntity.code == CodeFinal.RESPONSE_NEED_LOGIN) {
                    view.showLoginDialog(view);
                } else {
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("新增银行卡信息失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void updateBankInfo(String realName, String bankName, String cardNum, String phone, String code) {
        model.updateBankInfo(realName, bankName, cardNum, phone, code, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.REGISTER_ACTIVITY_GET_VERIFICATION_CODE, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    ToastUtils.showShort("修改成功");
                    view.finish();
                } else if (commonEntity.code == CodeFinal.RESPONSE_NEED_LOGIN) {
                    view.showLoginDialog(view);
                } else {
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("修改银行卡信息失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
