package com.harry.wallet365.function.my_bank_card;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.harry.wallet365.R;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.function.my_bank_card.add_bank_info.AddBankInfoActivity;
import com.harry.wallet365.network.entity.MyBankInfoEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/2/23.
 * 我的银行卡
 */
public class MyBankCardActivity extends BaseActivity<MyBankCardPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_bank_of_user)
    TextView tvBankOfUser;
    @BindView(R.id.tv_bank_of_name)
    TextView tvBankOfName;
    @BindView(R.id.tv_bank_of_number)
    TextView tvBankOfNumber;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private MyBankInfoEntity.DataBean bean = null;

    @Override
    protected int setupView() {
        return R.layout.activity_my_bank_card;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("我的银行卡");

        mPresenter.getBankInfo();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.MY_BANK_CARD_ACTIVITY_GET_BANK_INFO);
        return tags;
    }

    @Override
    protected MyBankCardPresenter bindPresenter() {
        return new MyBankCardPresenter();
    }

    public void getBankInfo(MyBankInfoEntity.DataBean data) {
        bean = data;
        btnConfirm.setText("修改");
        tvBankOfName.setText(data.bankName);
        tvBankOfNumber.setText(data.cardNumber);
        tvBankOfUser.setText(data.username);
    }

    @OnClick({R.id.iv_back, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_confirm:
                Intent intent = new Intent(this, AddBankInfoActivity.class);
                if (bean != null) {
                    intent.putExtra("bankName", bean.bankName);
                    intent.putExtra("cardNumber", bean.cardNumber);
                    intent.putExtra("username", bean.username);
                }
                startActivity(intent);
                finish();
                break;
        }
    }
}
