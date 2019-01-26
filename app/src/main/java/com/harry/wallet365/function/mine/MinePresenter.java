package com.harry.wallet365.function.mine;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.UserInfoEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/24.
 */
public class MinePresenter extends BasePresenter<MineFragment> {

    private final MineModel model;//40001

    public MinePresenter() {
        model = new MineModel();
    }

    public void getUserInfo() {
        model.getUserInfo(new Observer<UserInfoEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.MINE_FRAGMENT_GET_USER_INFO, d);
            }

            @Override
            public void onNext(UserInfoEntity userInfoEntity) {
                if (userInfoEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getUserInfo(userInfoEntity.data);
                } else if (userInfoEntity.code == CodeFinal.RESPONSE_NEED_LOGIN) {
                    view.showLoginDialog();
                } else {
                    ToastUtils.showShort(userInfoEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取用户信息失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
