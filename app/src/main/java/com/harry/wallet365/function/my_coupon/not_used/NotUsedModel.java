package com.harry.wallet365.function.my_coupon.not_used;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.MyCouponEntity;
import com.harry.wallet365.network.service.MyCouponService;
import com.harry.wallet365.utils.RetrofitHelper;
import com.harry.wallet365.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2019/2/25.
 */
public class NotUsedModel extends BaseModel {

    private final MyCouponService service;

    public NotUsedModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(MyCouponService.class);
    }

    public void getCouponList(int page, Observer<MyCouponEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("token", SPUtils.getString(UserInfo.TOKEN.name(), ""));
        params.put("page", String.valueOf(page));
        params.put("size", "10");
        params.put("type", "0");//类型：默认0，0-未使用，1-已使用

        service.getCouponList(URLFinal.GET_ADDRESS_LIST, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
