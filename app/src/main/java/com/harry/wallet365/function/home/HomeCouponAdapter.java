package com.harry.wallet365.function.home;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
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
        TextView tvPrice = helper.getView(R.id.tv_price);
        TextView tvUnit = helper.getView(R.id.tv_unit);
        if (item.source == 1) {//平台
            if (item.type == 1) {
                //折扣券
                tvPrice.setText("折");
                tvPrice.setTypeface(Typeface.DEFAULT);
                tvPrice.setTextSize(13);
                tvUnit.setText(String.valueOf(item.amount));
                tvUnit.setTypeface(Typeface.DEFAULT_BOLD);
                tvUnit.setTextSize(25);
                helper.setText(R.id.tv_fulfil, "满" + item.price + "元可用")
                        .setText(R.id.tv_type, "平台券");
            } else if (item.type == 2) {
                //现金优惠券
                helper.setText(R.id.tv_price, String.valueOf(item.amount))
                        .setText(R.id.tv_fulfil, "满" + item.price + "元可用")
                        .setText(R.id.tv_type, "平台券");
            }
        } else if (item.source == 2) { //商家
            if (item.type == 1) {
                //折扣券
                tvPrice.setText("折");
                tvPrice.setTypeface(Typeface.DEFAULT);
                tvPrice.setTextSize(13);
                tvUnit.setText(String.valueOf(item.amount));
                tvUnit.setTypeface(Typeface.DEFAULT_BOLD);
                tvUnit.setTextSize(25);
                helper.setText(R.id.tv_fulfil, "满" + item.price + "元可用")
                        .setText(R.id.tv_type, item.sellerName + "专享");
            } else if (item.type == 2) {
                //现金优惠券
                helper.setText(R.id.tv_price, String.valueOf(item.amount))
                        .setText(R.id.tv_fulfil, "满" + item.price + "元可用")
                        .setText(R.id.tv_type, item.sellerName + "专享");
            }
        }

    }
}
