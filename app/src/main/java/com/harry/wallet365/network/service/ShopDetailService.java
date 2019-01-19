package com.harry.wallet365.network.service;

import com.harry.wallet365.network.entity.HomeUseCouponEntity;
import com.harry.wallet365.network.entity.ShopDetailCouponEntity;
import com.harry.wallet365.network.entity.ShopDetailEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/8/20.
 */
public interface ShopDetailService {

    @FormUrlEncoded
    @POST
    Observable<ShopDetailEntity> getShopDetail(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<ShopDetailCouponEntity> getCoupon(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<HomeUseCouponEntity> useCoupon(@Url String url, @FieldMap Map<String, String> params);

}
