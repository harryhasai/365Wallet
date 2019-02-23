package com.harry.wallet365.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.harry.wallet365.R;
import com.harry.wallet365.application.WalletApplication;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.base.view.BaseActivityImpl;
import com.harry.wallet365.function.login.LoginActivity;
import com.harry.wallet365.rx.DisposableManager;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

/**
 * Created by Harry on 2018/8/13.
 */
public abstract class BaseActivity<P extends BasePresenter> extends BaseActivityImpl<P> {

    private WalletApplication application;
    protected Bundle savedInstanceState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.savedInstanceState = savedInstanceState;

        setContentView(setupView());
        application = (WalletApplication) getApplication();
        application.addActivity(this);

        //只是手机竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置状态栏颜色
        StatusBarUtil.setColor(this, getResources().getColor(R.color.black1), 0);

        initView();

    }

    /**
     * @return 布局文件的ID
     */
    protected abstract int setupView();

    /**
     * 初始化布局(例如findViewById)
     */
    protected abstract void initView();

    /**
     * @return RxJava中的Disposable方法
     */
    protected abstract ArrayList<Object> cancelNetWork();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        application.finishActivity(this);   //清除栈中的Activity

        ArrayList<Object> tags = cancelNetWork();
        if (tags != null && tags.size() != 0) {
            for (Object tag : tags) {
                DisposableManager.get().cancel(tag);
            }
        }
    }

    public void showLoginDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("登录失效, 是否重新登录");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(context, LoginActivity.class));
                dialog.dismiss();
            }
        }).show();
    }

}
