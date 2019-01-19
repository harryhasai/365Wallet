package com.harry.wallet365.function.shop_detail.shop_introduction;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.ShopDetailEntity;
import com.harry.wallet365.network.service.ShopDetailService;
import com.harry.wallet365.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2019/1/19.
 */
public class ShopIntroductionModel extends BaseModel {

    private final ShopDetailService service;

    public ShopIntroductionModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(ShopDetailService.class);
    }

    public void getShopDetail(String sellerId, String location, Observer<ShopDetailEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("sellerId", sellerId);
        params.put("location", location);
        params.put("type", "1");

        service.getShopDetail(URLFinal.GET_SHOP_DETAIL, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
