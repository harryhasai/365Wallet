package com.harry.wallet365.function.nearby;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.wallet365.R;

/**
 * Created by Harry on 2019/1/22.
 */
public class NearbyCouponAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public NearbyCouponAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_coupon_name, item);
    }
}
