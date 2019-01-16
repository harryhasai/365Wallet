package com.harry.wallet365.function.home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.wallet365.R;
import com.harry.wallet365.function.web_page.WebViewPageActivity;
import com.harry.wallet365.network.entity.HomeBannerEntity;

/**
 * Created by Harry on 2019/1/15.
 * 底部优惠活动适配器
 */
public class HomeBottomListAdapter extends BaseQuickAdapter<HomeBannerEntity.DataBean.ListBean, BaseViewHolder> {

    public HomeBottomListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, final HomeBannerEntity.DataBean.ListBean item) {
        ImageView ivActivity = helper.getView(R.id.iv_activity);
        Glide.with(mContext).load(item.img).into(ivActivity);

        ivActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebViewPageActivity.class);
                intent.putExtra("url", item.url);
                mContext.startActivity(intent);
            }
        });
    }
}
