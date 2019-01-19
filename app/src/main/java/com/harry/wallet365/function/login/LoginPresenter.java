package com.harry.wallet365.function.login;

import android.content.Intent;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.function.main.MainActivity;
import com.harry.wallet365.network.entity.CustomerLoginEntity;
import com.harry.wallet365.network.entity.ShopLoginEntity;
import com.harry.wallet365.rx.DisposableManager;
import com.harry.wallet365.utils.SPUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/1/10.
 */
public class LoginPresenter extends BasePresenter<LoginActivity> {

    private final LoginModel model;

    public LoginPresenter() {
        model = new LoginModel();
    }

    public void shopLogin(String username, String password) {
        model.shopLogin(username, password, new Observer<ShopLoginEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.LOGIN_ACTIVITY_SHOP_LOGIN, d);
            }

            @Override
            public void onNext(ShopLoginEntity shopLoginEntity) {
                switch (shopLoginEntity.code) {
                    case CodeFinal.RESPONSE_SUCCESS:
                        cacheShopInfo(shopLoginEntity.data);
                        Intent intent = new Intent(view, MainActivity.class);
                        view.startActivity(intent);
                        SPUtils.putInt(UserInfo.LOGIN_TYPE.name(), 1);
                        SPUtils.putBoolean(UserInfo.IS_LOGIN.name(), true);
                        view.finish();
                        break;
                    default:
                        ToastUtils.showShort(shopLoginEntity.msg);
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("登录失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void customerLogin(String username, String password) {
        model.customerLogin(username, password, new Observer<CustomerLoginEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.LOGIN_ACTIVITY_CUSTOMER_LOGIN, d);
            }

            @Override
            public void onNext(CustomerLoginEntity customerLoginEntity) {
                switch (customerLoginEntity.code) {
                    case CodeFinal.RESPONSE_SUCCESS:
                        cacheCustomerInfo(customerLoginEntity.data);
                        Intent intent = new Intent(view, MainActivity.class);
                        SPUtils.putBoolean(UserInfo.IS_LOGIN.name(), true);
                        SPUtils.putInt(UserInfo.LOGIN_TYPE.name(), 2);
                        view.startActivity(intent);
                        view.finish();
                        break;
                    default:
                        ToastUtils.showShort(customerLoginEntity.msg);
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("登录失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * @param data 缓存会员信息
     */
    private void cacheCustomerInfo(CustomerLoginEntity.DataBean data) {
        SPUtils.putString(UserInfo.TOKEN.name(), data.token);
        SPUtils.putString(UserInfo.HEAD_IMG.name(), data.headImg);
        SPUtils.putString(UserInfo.SEX.name(), String.valueOf(data.sex));
        SPUtils.putString(UserInfo.NICK_NAME.name(), String.valueOf(data.nickname));
        SPUtils.putString(UserInfo.USER_NAME.name(), String.valueOf(data.username));
    }

    /**
     * @param data 缓存店家信息
     */
    private void cacheShopInfo(ShopLoginEntity.DataBean data) {
        SPUtils.putString(UserInfo.TOKEN.name(), data.token);
        SPUtils.putString(UserInfo.SHOP_NAME.name(), data.shopName);
        SPUtils.putString(UserInfo.SHOP_MOBILE.name(), data.mobile);
    }
}
