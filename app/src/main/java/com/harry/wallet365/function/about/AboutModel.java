package com.harry.wallet365.function.about;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.AboutEntity;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.network.service.AboutService;
import com.harry.wallet365.utils.RetrofitHelper;
import com.harry.wallet365.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2019/1/25.
 * 关于
 */
public class AboutModel extends BaseModel {

    private final AboutService service;

    public AboutModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(AboutService.class);
    }

    public void about(Observer<AboutEntity> observer) {
        Map<String, String> params = new HashMap<>();

        service.about(URLFinal.ABOUT, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
