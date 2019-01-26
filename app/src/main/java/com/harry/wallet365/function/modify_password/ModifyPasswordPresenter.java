package com.harry.wallet365.function.modify_password;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/25.
 */
public class ModifyPasswordPresenter extends BasePresenter<ModifyPasswordActivity> {

    private final ModifyPasswordModel model;

    public ModifyPasswordPresenter() {
        model = new ModifyPasswordModel();
    }

    public void modify(String password, String newPassword) {
        model.modify(password, newPassword, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.MODIFY_PASSWORD_ACTIVITY_MODIFY, d);
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
                ToastUtils.showShort("修改密码失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
