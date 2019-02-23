package com.harry.wallet365.function.x_pub;

import android.text.TextUtils;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.network.entity.XPubEntity;
import com.harry.wallet365.network.service.XPubService;
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
public class XPubModel extends BaseModel {

    private final XPubService service;

    public XPubModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(XPubService.class);
    }

    public void getXPubInfo(Observer<XPubEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("token", SPUtils.getString(UserInfo.TOKEN.name(), ""));

        service.getXPubInfo(URLFinal.GET_X_PUB, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void updateXPubInfo(String account, String password, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("token", SPUtils.getString(UserInfo.TOKEN.name(), ""));
        if (!TextUtils.isEmpty(account)) {
            params.put("account", account);
        }
        params.put("password", password);

        service.updateXPubInfo(URLFinal.UPDATE_X_PUB, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
