package com.harry.wallet365.function.main;

import com.harry.wallet365.R;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.base.presenter.BasePresenter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    @Override
    protected int setupView() {
        return R.layout.activity_main;
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
