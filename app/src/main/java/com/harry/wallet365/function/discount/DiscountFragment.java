package com.harry.wallet365.function.discount;

import android.view.View;

import com.harry.wallet365.R;
import com.harry.wallet365.base.BaseFragment;
import com.harry.wallet365.base.presenter.BasePresenter;

import java.util.ArrayList;

/**
 * Created by Harry on 2019/1/10.
 * 优惠广场
 */
public class DiscountFragment extends BaseFragment {

    @Override
    protected int setupView() {
        return R.layout.fragment_discount;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}

