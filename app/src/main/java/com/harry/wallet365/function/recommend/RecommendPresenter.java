package com.harry.wallet365.function.recommend;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.RecommendEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/2/20.
 */
public class RecommendPresenter extends BasePresenter<RecommendActivity> {

    private final RecommendModel model;

    public RecommendPresenter() {
        model = new RecommendModel();
    }

    public void getRecommendList(int page) {
        model.getRecommendList(page, new Observer<RecommendEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.RECOMMEND_ACTIVITY_GET_RECOMMEND_LIST, d);
            }

            @Override
            public void onNext(RecommendEntity recommendEntity) {
                if (recommendEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getRecommendList(recommendEntity.data);
                } else {
                    ToastUtils.showShort(recommendEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取收货地址列表失败");
                view.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                view.setRefreshing(false);
            }
        });
    }
}
