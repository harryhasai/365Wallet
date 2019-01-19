package com.harry.wallet365.function.shop_detail.goods_list;

import android.text.TextUtils;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.wallet365.R;
import com.harry.wallet365.network.entity.GoodsListEntity;

/**
 * Created by Harry on 2019/1/19.
 */
public class GoodsListAdapter extends BaseQuickAdapter<GoodsListEntity.DataBean.ListBean, BaseViewHolder> {

    public GoodsListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsListEntity.DataBean.ListBean item) {
        ImageView ivHeader = helper.getView(R.id.iv_header);
        RequestOptions requestOptions = new RequestOptions()
//                .error(R.drawable.ic_place_hold)
                .placeholder(R.drawable.ic_place_hold)
                .centerCrop()
//                .circleCrop() //圆形图片
                .transform(new RoundedCorners(ConvertUtils.dp2px(5)))//圆角矩形
                .override(ConvertUtils.dp2px(80), ConvertUtils.dp2px(80));
        Glide.with(mContext)
                .load(item.headImg)
                .apply(requestOptions)
                .into(ivHeader);

        helper.setText(R.id.tv_name, item.name)
                .setText(R.id.tv_sale_volume, "已售" + item.count)
                .setText(R.id.tv_price, "￥" + item.price);
    }
}
