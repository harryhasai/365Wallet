package com.harry.wallet365.network.service;

import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.network.entity.XPubEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/8/20.
 */
public interface XPubService {

    @FormUrlEncoded
    @POST
    Observable<XPubEntity> getXPubInfo(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommonEntity> updateXPubInfo(@Url String url, @FieldMap Map<String, String> params);
}
