package com.harry.wallet365.function.shop_detail.goods_detail;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.harry.wallet365.R;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.GoodsDetailEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/1/19.
 * 商品详情
 */
public class GoodsDetailActivity extends BaseActivity<GoodsDetailPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_cover_img)
    ImageView ivCoverImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_sale_volume)
    TextView tvSaleVolume;
    @BindView(R.id.tv_html)
    TextView tvHtml;

    @Override
    protected int setupView() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        String goodsId = getIntent().getStringExtra("goodsId");
        tvTitle.setText("商品详情");

        mPresenter.getGoodsDetail(goodsId);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected GoodsDetailPresenter bindPresenter() {
        return new GoodsDetailPresenter();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    public void getGoodsDetail(GoodsDetailEntity.DataBean data) {
        RequestOptions requestOptions = new RequestOptions()
//                .error(R.drawable.ic_place_hold)
                .placeholder(R.drawable.ic_place_hold)
                .centerCrop()
//                .circleCrop() //圆形图片
                .transform(new RoundedCorners(ConvertUtils.dp2px(5)));//圆角矩形
//                .override(ConvertUtils.dp2px(80), ConvertUtils.dp2px(80));
        Glide.with(this)
                .load(data.headImg)
                .apply(requestOptions)
                .into(ivCoverImg);

        tvName.setText(data.name);
        tvPrice.setText("￥" + data.price);
        tvSaleVolume.setText("已售" + data.count);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvHtml.setText(Html.fromHtml(data.about, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvHtml.setText(Html.fromHtml(data.about));
        }
    }
}
