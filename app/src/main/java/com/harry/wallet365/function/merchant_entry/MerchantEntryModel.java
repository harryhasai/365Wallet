package com.harry.wallet365.function.merchant_entry;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.network.entity.GetAgreementEntity;
import com.harry.wallet365.network.entity.UploadImageEntity;
import com.harry.wallet365.network.service.MerchantEntryService;
import com.harry.wallet365.utils.RetrofitHelper;
import com.harry.wallet365.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2019/2/22.
 */
public class MerchantEntryModel extends BaseModel {

    private final MerchantEntryService service;

    public MerchantEntryModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(MerchantEntryService.class);
    }

    public void uploadImage(String imgBaseStr, Observer<UploadImageEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("token", SPUtils.getString(UserInfo.TOKEN.name(), ""));
        params.put("imgBaseStr", imgBaseStr);
        //params.put("ext", ext);//文件拓展名，默认：jpg

        service.uploadImage(URLFinal.UPLOAD_IMAGE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getAgreement(Observer<GetAgreementEntity> observer) {
        Map<String, String> params = new HashMap<>();

        service.getAgreement(URLFinal.GET_AGREEMENT, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void confirm(Map<String, String> params, Observer<CommonEntity> observer) {

        params.put("token", SPUtils.getString(UserInfo.TOKEN.name(), ""));

        service.confirm(URLFinal.MERCHANT_ENTRY, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
