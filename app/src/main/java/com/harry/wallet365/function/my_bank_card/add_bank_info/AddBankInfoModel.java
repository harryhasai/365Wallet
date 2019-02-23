package com.harry.wallet365.function.my_bank_card.add_bank_info;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.network.service.AddBankInfoService;
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
public class AddBankInfoModel extends BaseModel {

    private final AddBankInfoService service;

    public AddBankInfoModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(AddBankInfoService.class);
    }

    public void getVerificationCode(String phone, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("username", phone);

        service.getVerificationCode(URLFinal.GET_VERIFICATION_CODE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void addBankInfo(String realName, String bankName, String cardNum, String phone, String code, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("token", SPUtils.getString(UserInfo.TOKEN.name(), ""));
        params.put("realname", realName);
        params.put("bankName", bankName);
        params.put("cardNum", cardNum);
        params.put("phone", phone);
        params.put("code", code);

        service.addBankInfo(URLFinal.ADD_BANK_INFO, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void updateBankInfo(String realName, String bankName, String cardNum, String phone, String code, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("token", SPUtils.getString(UserInfo.TOKEN.name(), ""));
        params.put("realname", realName);
        params.put("bankName", bankName);
        params.put("cardNum", cardNum);
        params.put("phone", phone);
        params.put("code", code);

        service.updateBankInfo(URLFinal.UPDATE_BANK_INFO, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
