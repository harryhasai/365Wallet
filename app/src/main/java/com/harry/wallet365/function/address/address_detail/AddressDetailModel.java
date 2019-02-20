package com.harry.wallet365.function.address.address_detail;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.AddressDetailEntity;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.network.service.AddressDetailService;
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
public class AddressDetailModel extends BaseModel {

    private final AddressDetailService service;

    public AddressDetailModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(AddressDetailService.class);
    }

    public void getAddress(int addressId, Observer<AddressDetailEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("token", SPUtils.getString(UserInfo.TOKEN.name(), ""));
        params.put("id", String.valueOf(addressId));

        service.getAddressInfo(URLFinal.GET_ADDRESS_INFO, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void AddAddress(String realName, String phone, String address, int status, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("token", SPUtils.getString(UserInfo.TOKEN.name(), ""));
        params.put("realname", realName);
        params.put("phone", phone);
        params.put("address", address);
        params.put("status", String.valueOf(status));//1-默认，0-非默认

        service.AddAddress(URLFinal.ADD_ADDRESS, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void updateAddress(int addressId, String realName, String phone, String address, int status, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("token", SPUtils.getString(UserInfo.TOKEN.name(), ""));
        params.put("id", String.valueOf(addressId));
        params.put("realname", realName);
        params.put("phone", phone);
        params.put("address", address);
        params.put("status", String.valueOf(status));//1-默认，0-非默认

        service.updateAddress(URLFinal.UPDATE_ADDRESS, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
