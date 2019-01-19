package com.harry.wallet365.function.shop_detail.user_comment;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.UserCommentEntity;
import com.harry.wallet365.network.service.UserCommentService;
import com.harry.wallet365.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2019/1/19.
 */
public class UserCommentModel extends BaseModel {

    private final UserCommentService service;

    public UserCommentModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(UserCommentService.class);
    }

    public void getCommentList(String sellerId, int page, Observer<UserCommentEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("sellerId", sellerId);
        params.put("page", String.valueOf(page));
        params.put("size", "10");

        service.getCommentList(URLFinal.GET_COMMENT_LIST, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
