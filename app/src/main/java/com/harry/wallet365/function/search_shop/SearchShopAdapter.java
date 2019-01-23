package com.harry.wallet365.function.search_shop;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.wallet365.R;
import com.harry.wallet365.function.nearby.NearbyCouponAdapter;
import com.harry.wallet365.network.entity.NearbyShopListEntity;
import com.harry.wallet365.utils.StarView;

/**
 * Created by Harry on 2019/1/21.
 */
public class SearchShopAdapter extends BaseQuickAdapter<NearbyShopListEntity.DataBean.ListBean, BaseViewHolder> {

    public SearchShopAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NearbyShopListEntity.DataBean.ListBean item) {
        ImageView ivHeader = helper.getView(R.id.iv_header);
        RequestOptions requestOptions = new RequestOptions()
//                .error(R.drawable.ic_place_hold)
                .placeholder(R.drawable.ic_place_hold)
                .centerCrop()
//                .circleCrop() //圆形图片
                .transform(new RoundedCorners(ConvertUtils.dp2px(5)));//圆角矩形
//                .override(ConvertUtils.dp2px(80), ConvertUtils.dp2px(80));
        Glide.with(mContext)
                .load(item.headImg)
                .apply(requestOptions)
                .into(ivHeader);

        StarView starView = helper.getView(R.id.star_view);
        starView.setCheckCount(item.score);
        helper.setText(R.id.tv_shop_name, item.name)
                .setText(R.id.tv_distance, "|\t\t" + item.distance + "m");

        RecyclerView rvContainer = helper.getView(R.id.rv_container);
        rvContainer.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        NearbyCouponAdapter couponAdapter = new NearbyCouponAdapter(R.layout.item_text_view);
        rvContainer.setAdapter(couponAdapter);
        couponAdapter.setNewData(item.voucherList);
    }
}
