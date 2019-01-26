package com.harry.wallet365.function.help_center;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.AboutEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/25.
 */
public class HelpCenterPresenter extends BasePresenter<HelpCenterActivity> {

    private final HelpCenterModel model;

    public HelpCenterPresenter() {
        model = new HelpCenterModel();
    }

    public void about() {
        model.about(new Observer<AboutEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ABOUT_ACTIVITY_ABOUT, d);
            }

            @Override
            public void onNext(AboutEntity aboutEntity) {
                if (aboutEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.about(aboutEntity.data);
                } else {
                    ToastUtils.showShort(aboutEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
