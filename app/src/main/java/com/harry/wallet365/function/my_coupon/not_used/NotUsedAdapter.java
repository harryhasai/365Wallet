package com.harry.wallet365.function.my_coupon.not_used;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.wallet365.R;
import com.harry.wallet365.network.entity.MyCouponEntity;

/**
 * Created by Harry on 2019/2/25.
 */
public class NotUsedAdapter extends BaseQuickAdapter<MyCouponEntity.DataBean.ListBean, BaseViewHolder> {

    public NotUsedAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCouponEntity.DataBean.ListBean item) {
        helper.setText(R.id.tv_coupon_name, item.name)
                .setText(R.id.tv_shop_name, item.sellerName + "专享")
                .setText(R.id.tv_time, "满" + item.price + "元可用" + " | " + "有效期至" + item.expireDate);
        if (item.type.equals("1")) {
            //折扣券
            helper.setText(R.id.tv_price, item.amount + "折");
        } else {
            //现金券
            helper.setText(R.id.tv_price, item.amount + "元");
        }
        if (helper.getAdapterPosition() % 2 == 0) {
            helper.setBackgroundRes(R.id.rl_container, R.drawable.shape_coupon_list_item);
        } else {
            helper.setBackgroundRes(R.id.rl_container, R.drawable.shape_coupon_list_item_yellow);
        }
    }
}
