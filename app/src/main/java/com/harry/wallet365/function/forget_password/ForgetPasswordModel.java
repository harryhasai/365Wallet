package com.harry.wallet365.function.forget_password;

import android.text.TextUtils;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.network.service.ForgetPasswordService;
import com.harry.wallet365.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2019/1/10.
 */
public class ForgetPasswordModel extends BaseModel {

    private final ForgetPasswordService service;

    public ForgetPasswordModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(ForgetPasswordService.class);
    }

    public void getVerificationCode(String phone, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("username", phone);

        service.getVerificationCode(URLFinal.GET_VERIFICATION_CODE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void forgetPassword(String phone, String password, String code, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("username", phone);
        params.put("password", password);
        params.put("code", code); //验证码

        service.forgetPassword(URLFinal.FORGET_PASSWORD, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
