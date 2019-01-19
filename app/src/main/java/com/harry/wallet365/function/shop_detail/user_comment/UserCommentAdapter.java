package com.harry.wallet365.function.shop_detail.user_comment;

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
import com.harry.wallet365.network.entity.UserCommentEntity;
import com.harry.wallet365.utils.StarView;

/**
 * Created by Harry on 2019/1/19.
 */
public class UserCommentAdapter extends BaseQuickAdapter<UserCommentEntity.DataBean.ListBean, BaseViewHolder> {

    public UserCommentAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserCommentEntity.DataBean.ListBean item) {
        ImageView ivHeader = helper.getView(R.id.iv_header);
        RequestOptions requestOptions = new RequestOptions()
//                .error(R.drawable.ic_place_hold)
                .placeholder(R.drawable.ic_place_hold)
                .centerCrop()
                .circleCrop() //圆形图片
//                .transform(new RoundedCorners(ConvertUtils.dp2px(5)))//圆角矩形
                .override(ConvertUtils.dp2px(34), ConvertUtils.dp2px(34));
        Glide.with(mContext)
                .load(item.headImg)
                .apply(requestOptions)
                .into(ivHeader);

        helper.setText(R.id.tv_username, item.nickname)
                .setText(R.id.tv_time, item.createTime)
                .setText(R.id.tv_content, item.content);

        StarView starView1 = helper.getView(R.id.star_view1);
        StarView starView2 = helper.getView(R.id.star_view2);
        StarView starView3 = helper.getView(R.id.star_view3);
        starView1.setCheckCount(item.upScore);
        starView2.setCheckCount(item.envScore);
        starView3.setCheckCount(item.serScore);

        RecyclerView rvImgList = helper.getView(R.id.rv_img_list);
        rvImgList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        UserCommentContentImageAdapter imageAdapter = new UserCommentContentImageAdapter(R.layout.item_user_comment_image);
        rvImgList.setAdapter(imageAdapter);
        imageAdapter.setNewData(item.imgList);
    }
}
