package com.harry.wallet365.function.home;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.wallet365.R;
import com.harry.wallet365.network.entity.HomeCouponEntity;

/**
 * Created by Harry on 2019/1/15.
 */
public class HomeCouponAdapter extends BaseQuickAdapter<HomeCouponEntity.DataBean.ListBean, BaseViewHolder> {

    public HomeCouponAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeCouponEntity.DataBean.ListBean item) {
        helper.setText(R.id.tv_price, String.valueOf(item.amount))
                .setText(R.id.tv_fulfil, "满" + item.price + "元可用")
                .setText(R.id.tv_type, item.sellerName + "专享");
    }
}
