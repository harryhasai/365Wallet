package com.harry.wallet365.function.feedback;

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
public class FeedbackPresenter extends BasePresenter<FeedbackActivity> {

    private final FeedbackModel model;

    public FeedbackPresenter() {
        model = new FeedbackModel();
    }

    public void feedback(String content, String phone) {
        model.feedback(content, phone, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.FEEDBACK_ACTIVITY_FEEDBACK, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.feedback();
                } else if (commonEntity.code == CodeFinal.RESPONSE_NEED_LOGIN) {
                    view.needLogin();
                } else {
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("提交意见反馈失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
