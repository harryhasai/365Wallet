package com.harry.wallet365.function.shop_detail;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.wallet365.R;
import com.harry.wallet365.network.entity.ShopDetailCouponEntity;

/**
 * Created by Harry on 2019/1/18.
 */
public class DialogCouponAdapter extends BaseQuickAdapter<ShopDetailCouponEntity.DataBean.ListBean, BaseViewHolder> {

    public DialogCouponAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopDetailCouponEntity.DataBean.ListBean item) {
        String time;
        if (TextUtils.isEmpty(item.expireDate)) {
            time = "";
        } else {
            time = item.expireDate.split(" ")[0];
        }
        helper.setText(R.id.tv_coupon_name, item.name)
                .setText(R.id.tv_meet_amount, "满" + item.price + "元可用")
                .setText(R.id.tv_time, "有效期至" + time)
                .addOnClickListener(R.id.btn_get);

        if (item.type == 1) {
            //折扣券
            helper.setText(R.id.tv_price, item.amount + "折");
        } else {
            //现金券
            helper.setText(R.id.tv_price, item.amount + "元");
        }
    }
}
