package com.harry.wallet365.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.base.view.BaseFragmentImpl;
import com.harry.wallet365.rx.DisposableManager;

import java.util.ArrayList;

/**
 * Created by Harry on 2018/8/13.
 */
public abstract class BaseFragment<P extends BasePresenter> extends BaseFragmentImpl<P> {

    protected Activity mActivity;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        if (view == null) {
            view = inflater.inflate(setupView(), container, false);
            initView(view);
        }

        //判断Fragment对应的Activity是否存在这个视图
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            //如果存在,那么我就干掉,重写添加,这样的方式我们就可以缓存视图
            parent.removeView(view);
        }

        return view;
    }

    /**
     * @return 布局文件的ID
     */
    protected abstract int setupView();

    /**
     * 初始化布局(例如findViewById)
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * @return RxJava中的Disposable方法
     */
    protected abstract ArrayList<Object> cancelNetWork();

    @Override
    public void onDestroy() {
        super.onDestroy();

        ArrayList<Object> tags = cancelNetWork();
        if (tags != null && tags.size() != 0) {
            for (Object tag : tags) {
                DisposableManager.get().cancel(tag);
            }
        }
    }
}
