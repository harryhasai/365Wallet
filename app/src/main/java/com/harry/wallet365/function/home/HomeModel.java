package com.harry.wallet365.function.home;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.HomeBannerEntity;
import com.harry.wallet365.network.entity.HomeCouponEntity;
import com.harry.wallet365.network.entity.HomeUseCouponEntity;
import com.harry.wallet365.network.service.HomeService;
import com.harry.wallet365.utils.RetrofitHelper;
import com.harry.wallet365.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2019/1/12.
 */
public class HomeModel extends BaseModel {

    private final HomeService service;

    public HomeModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(HomeService.class);
    }

    public void getBanner(Observer<HomeBannerEntity> observer) {
        Map<String, String> params = new HashMap<>();

//        params.put("page", page);
//        params.put("size", size);

        service.getBanner(URLFinal.HOME_GET_BANNER, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getBottomList(int pageNum, Observer<HomeBannerEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("page", String.valueOf(pageNum));
        params.put("size", "10");

        service.getBottomList(URLFinal.HOME_GET_BOTTOM_LIST, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getCoupon(String location, Observer<HomeCouponEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("location", location);

        service.getCoupon(URLFinal.HOME_GET_COUPON, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void useCoupon(String voucherId, Observer<HomeUseCouponEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("token", SPUtils.getString(UserInfo.TOKEN.name(), ""));
        params.put("voucherId", voucherId);

        service.useCoupon(URLFinal.HOME_USE_COUPON, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
