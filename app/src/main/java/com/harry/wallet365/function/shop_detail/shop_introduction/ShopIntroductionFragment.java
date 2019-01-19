package com.harry.wallet365.function.shop_detail.shop_introduction;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.harry.wallet365.R;
import com.harry.wallet365.base.BaseFragment;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.ShopDetailEntity;
import com.harry.wallet365.utils.StarView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Harry on 2019/1/18.
 * 商家介绍
 */
public class ShopIntroductionFragment extends BaseFragment<ShopIntroductionPresenter> {

    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.star_view)
    StarView starView;
    @BindView(R.id.tv_distance_and_time)
    TextView tvDistanceAndTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.tv_html)
    TextView tvHtml;
    Unbinder unbinder;

    @Override
    protected int setupView() {
        return R.layout.fragment_shop_introduction;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        String sellerId = bundle.getString("sellerId");
        String location = bundle.getString("location");
        mPresenter.getShopDetail(sellerId, location);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected ShopIntroductionPresenter bindPresenter() {
        return new ShopIntroductionPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void getShopDetail(ShopDetailEntity.DataBean data) {
        tvShopName.setText(data.name);
        starView.setCheckCount(data.score);
        String time = data.workTime.substring(0, 5) + "-" + data.workTime.substring(5);
        tvDistanceAndTime.setText("|\t\t" + data.distance + "m\t\t|\t\t" + time);
        tvAddress.setText(data.address);
        tvTel.setText(data.tel);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvHtml.setText(Html.fromHtml(data.about, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvHtml.setText(Html.fromHtml(data.about));
        }
    }
}
