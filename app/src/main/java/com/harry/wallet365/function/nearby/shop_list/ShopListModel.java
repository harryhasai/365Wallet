package com.harry.wallet365.function.nearby.shop_list;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.base.model.BaseModel;
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
 * Created by Harry on 2019/1/23.
 */
public class ShopListModel extends BaseModel {

    private final NearbyService service;

    public ShopListModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(NearbyService.class);
    }

    public void getShopList(String location, String type, int pageNum, Observer<NearbyShopListEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("location", location);
        params.put("type", type);
        params.put("page", String.valueOf(pageNum));
        params.put("size", "10");

        service.getShopList(URLFinal.NEARBY_GET_SHOP_LIST, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
