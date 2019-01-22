package com.harry.wallet365.network.service;

import com.harry.wallet365.network.entity.NearbyBannerEntity;
import com.harry.wallet365.network.entity.NearbyCategoryEntity;
import com.harry.wallet365.network.entity.NearbyShopListEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/8/20.
 */
public interface NearbyService {

    @FormUrlEncoded
    @POST
    Observable<NearbyBannerEntity> getBanner(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<NearbyCategoryEntity> getCategory(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<NearbyShopListEntity> getShopList(@Url String url, @FieldMap Map<String, String> params);

}
