package com.harry.wallet365.function.shop_detail.goods_list;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.GoodsListEntity;
import com.harry.wallet365.network.service.GoodsListService;
import com.harry.wallet365.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2019/1/18.
 */
public class GoodsListModel extends BaseModel {

    private final GoodsListService service;

    public GoodsListModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(GoodsListService.class);
    }

    public void getGoodsList(String sellerId, int page, Observer<GoodsListEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("sellerId", sellerId);
        params.put("page", String.valueOf(page));
        params.put("size", "10");

        service.getGoodsList(URLFinal.GET_GOODS_LIST, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
