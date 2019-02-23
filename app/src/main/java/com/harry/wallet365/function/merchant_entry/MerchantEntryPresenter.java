package com.harry.wallet365.function.merchant_entry;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.network.entity.CommonEntity;
import com.harry.wallet365.network.entity.GetAgreementEntity;
import com.harry.wallet365.network.entity.UploadImageEntity;
import com.harry.wallet365.rx.DisposableManager;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2019/2/22.
 */
public class MerchantEntryPresenter extends BasePresenter<MerchantEntryActivity> {

    private final MerchantEntryModel model;

    public MerchantEntryPresenter() {
        model = new MerchantEntryModel();
    }

    public void uploadImage(String imgBaseStr) {
        model.uploadImage(imgBaseStr, new Observer<UploadImageEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.MERCHANT_ENTRY_ACTIVITY_UPLOAD_IMAGE, d);
            }

            @Override
            public void onNext(UploadImageEntity uploadImageEntity) {
                if (uploadImageEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.uploadImage(uploadImageEntity.data);
                } else if (uploadImageEntity.code == CodeFinal.RESPONSE_NEED_LOGIN) {
                    view.showLoginDialog(view);
                } else {
                    ToastUtils.showShort(uploadImageEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("上传图片失败, 请重新点击图片上传");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getAgreement() {
        model.getAgreement(new Observer<GetAgreementEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.MERCHANT_ENTRY_ACTIVITY_GET_AGREEMENT, d);
            }

            @Override
            public void onNext(GetAgreementEntity getAgreementEntity) {
                if (getAgreementEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    view.getAgreement(getAgreementEntity.data);
                } else {
                    ToastUtils.showShort(getAgreementEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("获取合作协议失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void confirm(Map<String, String> params) {
        model.confirm(params, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.MERCHANT_ENTRY_ACTIVITY_CONFIRM, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == CodeFinal.RESPONSE_SUCCESS) {
                    ToastUtils.showShort("入驻成功");
                    view.finish();
                } else if (commonEntity.code == CodeFinal.RESPONSE_NEED_LOGIN) {
                    view.showLoginDialog(view);
                } else {
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("入驻失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
