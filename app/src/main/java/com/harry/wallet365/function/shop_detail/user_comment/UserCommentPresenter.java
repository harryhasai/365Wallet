package com.harry.wallet365.function.shop_detail.user_comment;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.UserCommentEntity;
import com.harry.wallet365.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/19.
 */
public class UserCommentPresenter extends BasePresenter<UserCommentFragment> {

    private final UserCommentModel model;

    public UserCommentPresenter() {
        model = new UserCommentModel();
    }

    public void getCommentList(String sellerId, int page) {
        model.getCommentList(sellerId, page, new Observer<UserCommentEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.USER_COMMENT_FRAGMENT_GET_COMMENT_LIST, d);
            }

            @Override
            public void onNext(UserCommentEntity userCommentEntity) {
                if (userCommentEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getCommentList(userCommentEntity.data);
                } else {
                    ToastUtils.showShort(userCommentEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取评论列表失败");
                view.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                view.setRefreshing(false);
            }
        });
    }
}
