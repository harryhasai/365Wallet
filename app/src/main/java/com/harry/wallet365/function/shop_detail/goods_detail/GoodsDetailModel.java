package com.harry.wallet365.function.shop_detail.goods_detail;

import com.harry.wallet365.app_final.URLFinal;
import com.harry.wallet365.base.model.BaseModel;
import com.harry.wallet365.network.entity.GoodsDetailEntity;
import com.harry.wallet365.network.service.GoodsDetailService;
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
public class GoodsDetailModel extends BaseModel {

    private final GoodsDetailService service;

    public GoodsDetailModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(GoodsDetailService.class);
    }

    public void getGoodsDetail(String goodsId, Observer<GoodsDetailEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("productId", goodsId);

        service.getGoodsDetail(URLFinal.GET_GOODS_DETAIL, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
