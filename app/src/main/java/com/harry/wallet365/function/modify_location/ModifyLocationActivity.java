package com.harry.wallet365.function.modify_location;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.donkingliang.labels.LabelsView;
import com.harry.wallet365.R;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.zyyoona7.popup.EasyPopup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/1/15.
 * 修改当前位置
 */
public class ModifyLocationActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_location)
    EditText etLocation;
    @BindView(R.id.tv_current_location)
    TextView tvCurrentLocation;
    @BindView(R.id.labels)
    LabelsView labels;

    @Override
    protected int setupView() {
        return R.layout.activity_modify_location;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("修改当前位置");

        initPopupWindow();
        initAutoCompleteTextView();
    }

    private void initPopupWindow() {
        EasyPopup mCirclePop = EasyPopup.create()
//                .setContentView(this, R.layout.layout_circle_comment)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                .apply();
    }

    private void initAutoCompleteTextView() {
        etLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
                InputtipsQuery inputQuery = new InputtipsQuery(s.toString(), null);
                Inputtips inputTips = new Inputtips(ModifyLocationActivity.this, inputQuery);
                inputTips.setInputtipsListener(new Inputtips.InputtipsListener() {
                    @Override
                    public void onGetInputtips(List<Tip> list, int resultCode) {
                        List<String> nameList = new ArrayList<>();
                        for (Tip tip : list) {
                            if (tip.getTypeCode().equals("190102") ||
                                    tip.getTypeCode().equals("190103") ||
                                    tip.getTypeCode().equals("190104")) {
                                nameList.add(tip.getName());
                            }
                        }
                    }
                });
                inputTips.requestInputtipsAsyn();
            }
        });

    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
