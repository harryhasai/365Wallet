package com.harry.wallet365.function.nearby;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.wallet365.R;
import com.harry.wallet365.network.entity.NearbyCategoryEntity;

/**
 * Created by Harry on 2019/1/21.
 */
public class NearbyCategoryAdapter extends BaseQuickAdapter<NearbyCategoryEntity.DataBean.ListBean, BaseViewHolder> {

    public NearbyCategoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NearbyCategoryEntity.DataBean.ListBean item) {
        ImageView ivCategory = helper.getView(R.id.iv_category_img);
        Glide.with(mContext).load(item.cover).into(ivCategory);
        helper.setText(R.id.iv_category_name, item.name);
    }
}
