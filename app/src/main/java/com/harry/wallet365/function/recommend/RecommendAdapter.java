package com.harry.wallet365.function.recommend;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.wallet365.R;
import com.harry.wallet365.network.entity.RecommendEntity;

/**
 * Created by Harry on 2019/2/20.
 */
public class RecommendAdapter extends BaseQuickAdapter<RecommendEntity.DataBean.ListBean, BaseViewHolder> {

    public RecommendAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendEntity.DataBean.ListBean item) {
        helper.setText(R.id.tv_user_ID, "ID：" + item.id)
                .setText(R.id.tv_time, "注册时间：" + item.createDate)
                .setText(R.id.tv_points, "" + item.getInterCount);
    }
}
