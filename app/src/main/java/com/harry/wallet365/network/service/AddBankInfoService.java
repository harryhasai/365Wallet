package com.harry.wallet365.network.service;

import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.network.entity.MyBankInfoEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2019/2/23.
 */
public interface AddBankInfoService {

    @FormUrlEncoded
    @POST
    Observable<CommonEntity> addBankInfo(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommonEntity> updateBankInfo(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommonEntity> getVerificationCode(@Url String url, @FieldMap Map<String, String> params);
}
