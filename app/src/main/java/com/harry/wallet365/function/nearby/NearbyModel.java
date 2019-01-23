package com.harry.wallet365.function.nearby;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.NearbyBannerEntity;
import com.harry.wallet365.network.entity.NearbyCategoryEntity;
import com.harry.wallet365.network.entity.NearbyShopListEntity;
import com.harry.wallet365.network.service.NearbyService;
import com.harry.wallet365.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2019/1/21.
 */
public class NearbyModel extends BaseModel {

    private final NearbyService service;

    public NearbyModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(NearbyService.class);
    }

    public void getBanner(String location, Observer<NearbyBannerEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("location", location);

        service.getBanner(URLFinal.NEARBY_GET_BANNER, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getCategory(Observer<NearbyCategoryEntity> observer) {
        Map<String, String> params = new HashMap<>();
        service.getCategory(URLFinal.NEARBY_GET_CATEGORY, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getShopList(String location, int pageNum, Observer<NearbyShopListEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("location", location);
        params.put("page", String.valueOf(pageNum));
        params.put("size", "1");

        service.getShopList(URLFinal.NEARBY_GET_SHOP_LIST, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
