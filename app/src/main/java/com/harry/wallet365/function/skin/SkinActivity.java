package com.harry.wallet365.function.skin;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.harry.wallet365.R;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.utils.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/2/18.
 * 皮肤设置
 */
public class SkinActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.cb_red)
    CheckBox cbRed;
    @BindView(R.id.fl_red)
    FrameLayout flRed;
    @BindView(R.id.cb_yellow)
    CheckBox cbYellow;
    @BindView(R.id.fl_yellow)
    FrameLayout flYellow;
    @BindView(R.id.cb_black)
    CheckBox cbBlack;
    @BindView(R.id.fl_black)
    FrameLayout flBlack;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    @Override
    protected int setupView() {
        return R.layout.activity_skin;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("皮肤设置");

        int navigationColor = SPUtils.getInt(UserInfo.BOTTOM_NAVIGATION_ICON_COLOR.name(), 0);

        if (navigationColor == 2) {//1 - 红色 2 - 黑色 3 - 黄色 默认红色
            cbBlack.setChecked(true);
            cbYellow.setChecked(false);
            cbRed.setChecked(false);
        } else if (navigationColor == 3) {
            cbYellow.setChecked(true);
            cbBlack.setChecked(false);
            cbRed.setChecked(false);
        } else {
            cbRed.setChecked(true);
            cbBlack.setChecked(false);
            cbYellow.setChecked(false);
        }
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @OnClick({R.id.iv_back, R.id.fl_red, R.id.fl_yellow, R.id.fl_black, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.fl_red:
                SPUtils.putInt(UserInfo.BOTTOM_NAVIGATION_ICON_COLOR.name(), 1);
                cbRed.setChecked(true);
                cbBlack.setChecked(false);
                cbYellow.setChecked(false);
                break;
            case R.id.fl_yellow:
                SPUtils.putInt(UserInfo.BOTTOM_NAVIGATION_ICON_COLOR.name(), 3);
                cbRed.setChecked(false);
                cbBlack.setChecked(false);
                cbYellow.setChecked(true);
                break;
            case R.id.fl_black:
                SPUtils.putInt(UserInfo.BOTTOM_NAVIGATION_ICON_COLOR.name(), 2);
                cbRed.setChecked(false);
                cbBlack.setChecked(true);
                cbYellow.setChecked(false);
                break;
            case R.id.btn_confirm:
                setResult(CodeFinal.COMMON_RESULT_CODE);
                finish();
                break;
        }
    }
}
