package com.harry.wallet365.function.shop_detail.user_comment;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.wallet365.R;

/**
 * Created by Harry on 2019/1/19.
 */
public class UserCommentContentImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public UserCommentContentImageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView ivCommentImg = helper.getView(R.id.iv_comment_img);
        Glide.with(mContext).load(item).into(ivCommentImg);
    }
}
