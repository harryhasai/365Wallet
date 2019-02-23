package com.harry.wallet365.function.x_pub;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.network.entity.XPubEntity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/2/23.
 */
public class XPubPresenter extends BasePresenter<XPubActivity> {

    private final XPubModel model;

    public XPubPresenter() {
        model = new XPubModel();
    }

    public void getXPubInfo() {
        model.getXPubInfo(new Observer<XPubEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(XPubEntity xPubEntity) {
                if (xPubEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getXPubInfo(xPubEntity.data);
                } else if (xPubEntity.code == CodeFinal.RESPONSE_ERROR) {
                    ToastUtils.showShort(xPubEntity.msg);
                } else if (xPubEntity.code == CodeFinal.RESPONSE_NEED_LOGIN) {
                    view.showLoginDialog(view);
                } else {
                    ToastUtils.showShort(xPubEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取XPub信息失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void updateXPubInfo(String account, String password) {
        model.updateXPubInfo(account, password, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.updateXPubInfo();
                } else if (commonEntity.code == CodeFinal.RESPONSE_NEED_LOGIN) {
                    view.showLoginDialog(view);
                } else {
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("修改XPub信息失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
