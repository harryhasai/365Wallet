package com.harry.wallet365.function.my_bank_card;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.MyBankInfoEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/2/23.
 */
public class MyBankCardPresenter extends BasePresenter<MyBankCardActivity> {

    private final MyBankCardModel model;

    public MyBankCardPresenter() {
        model = new MyBankCardModel();
    }

    public void getBankInfo() {
        model.getBankInfo(new Observer<MyBankInfoEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.MY_BANK_CARD_ACTIVITY_GET_BANK_INFO, d);
            }

            @Override
            public void onNext(MyBankInfoEntity myBankInfoEntity) {
                if (myBankInfoEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getBankInfo(myBankInfoEntity.data);
                } else if (myBankInfoEntity.code == 1001 || myBankInfoEntity.data == null) {
                    ToastUtils.showShort("暂无银行卡信息");
                } else {
                    ToastUtils.showShort(myBankInfoEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取银行卡信息失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
