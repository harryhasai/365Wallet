package com.harry.wallet365.function.address;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.AddressEntity;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.network.service.AddressService;
import com.harry.wallet365.utils.RetrofitHelper;
import com.harry.wallet365.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2019/2/19.
 */
public class AddressModel extends BaseModel {

    private final AddressService service;

    public AddressModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(AddressService.class);
    }

    public void getAddress(int page, Observer<AddressEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("token", SPUtils.getString(UserInfo.TOKEN.name(), ""));
        params.put("page", String.valueOf(page));
        params.put("size", "10");

        service.getAddress(URLFinal.GET_ADDRESS_LIST, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void deleteAddress(int id, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("token", SPUtils.getString(UserInfo.TOKEN.name(), ""));
        params.put("id", String.valueOf(id));

        service.deleteAddress(URLFinal.DELETE_ADDRESS, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
