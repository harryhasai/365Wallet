package com.harry.wallet365.function.my_bank_card;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.MyBankInfoEntity;
import com.harry.wallet365.network.service.MyBankCardService;
import com.harry.wallet365.utils.RetrofitHelper;
import com.harry.wallet365.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2019/2/23.
 */
public class MyBankCardModel extends BaseModel {

    private final MyBankCardService service;

    public MyBankCardModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(MyBankCardService.class);
    }

    public void getBankInfo(Observer<MyBankInfoEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("token", SPUtils.getString(UserInfo.TOKEN.name(), ""));

        service.getBankInfo(URLFinal.MY_BANK_INFO, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
