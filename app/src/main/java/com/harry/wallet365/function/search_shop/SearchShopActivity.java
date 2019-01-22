package com.harry.wallet365.function.search_shop;

import com.harry.wallet365.R;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.base.presenter.BasePresenter;

import java.util.ArrayList;

/**
 * Created by Harry on 2019/1/22.
 * 店家搜索
 */
public class SearchShopActivity extends BaseActivity {

    @Override
    protected int setupView() {
        return R.layout.activity_search_shop;
    }

    @Override
    protected void initView() {

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
