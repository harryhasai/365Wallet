package com.harry.wallet365.function.feedback;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/1/25.
 * 意见反馈
 */
public class FeedbackActivity extends BaseActivity<FeedbackPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    @Override
    protected int setupView() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("意见反馈");
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.FEEDBACK_ACTIVITY_FEEDBACK);
        return tags;
    }

    @Override
    protected FeedbackPresenter bindPresenter() {
        return new FeedbackPresenter();
    }

    public void needLogin() {
        showLoginDialog(this);
    }

    public void feedback() {
        ToastUtils.showShort("提交成功");
        finish();
    }

    @OnClick({R.id.iv_back, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                String content = etContent.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.showShort("请填写详细问题");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showShort("请填写联系电话");
                    return;
                }
                mPresenter.feedback(content, phone);
                break;
        }
    }
}
