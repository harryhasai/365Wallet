package com.harry.wallet365.function.login;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.CustomerLoginEntity;
import com.harry.wallet365.network.entity.ShopLoginEntity;
import com.harry.wallet365.network.service.LoginService;
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
public class LoginModel extends BaseModel {

    private final LoginService service;

    public LoginModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(LoginService.class);
    }

    public void shopLogin(String username, String password, Observer<ShopLoginEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("username", username);
        params.put("password", password);
        params.put("type", "0");   //用户类型：0-商家，1-用户

        service.shopLogin(URLFinal.LOGIN, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void customerLogin(String username, String password, Observer<CustomerLoginEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("username", username);
        params.put("password", password);
        params.put("type", "1");   //用户类型：0-商家，1-用户

        service.customerLogin(URLFinal.LOGIN, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
