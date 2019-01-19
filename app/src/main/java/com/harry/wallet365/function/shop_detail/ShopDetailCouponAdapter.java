package com.harry.wallet365.function.shop_detail;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.wallet365.R;
import com.harry.wallet365.network.entity.ShopDetailCouponEntity;

/**
 * Created by Harry on 2019/1/18.
 * 优惠券的适配器
 */
public class ShopDetailCouponAdapter extends BaseQuickAdapter<ShopDetailCouponEntity.DataBean.ListBean, BaseViewHolder> {

    public ShopDetailCouponAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopDetailCouponEntity.DataBean.ListBean item) {
        helper.setText(R.id.tv_coupon, item.name);
    }
}
