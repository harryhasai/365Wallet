package com.harry.wallet365.function.modify_location;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.blankj.utilcode.util.ToastUtils;
import com.donkingliang.labels.LabelsView;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.CodeFinal;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.base.presenter.BasePresenter;
import com.harry.wallet365.utils.SPUtils;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

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
    AutoCompleteTextView etLocation;
    @BindView(R.id.tv_current_location)
    TextView tvCurrentLocation;
    @BindView(R.id.labels)
    LabelsView labels;
    private ArrayList<String> nameList;

    @Override
    protected int setupView() {
        return R.layout.activity_modify_location;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("修改当前位置");

        String city = getIntent().getStringExtra("city");
        if (TextUtils.isEmpty(city)) {
            tvCurrentLocation.setText("");
        } else {
            tvCurrentLocation.setText(city);
        }
        initAutoCompleteTextView();

        initLabels();
    }

    private void initAutoCompleteTextView() {
        nameList = new ArrayList<>();
        etLocation.setDropDownVerticalOffset(8);
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
                inputTips.setInputtipsListener(new MyInputTipsListener());
                inputTips.requestInputtipsAsyn();

            }
        });
        etLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                saveLocation(nameList.get(position));
                Intent data = new Intent();
                data.putExtra("cityName", nameList.get(position));
                setResult(CodeFinal.COMMON_RESULT_CODE, data);
                finish();
            }
        });

    }

    private class MyInputTipsListener implements Inputtips.InputtipsListener {

        @Override
        public void onGetInputtips(List<Tip> list, int resultCode) {
            nameList.clear();
            for (Tip tip : list) {
                if (tip.getTypeCode().equals("190102") ||
                        tip.getTypeCode().equals("190103") ||
                        tip.getTypeCode().equals("190104")) {
                    nameList.add(tip.getName());
                }
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ModifyLocationActivity.this,
                    R.layout.item_popup_window, nameList);
            etLocation.setAdapter(arrayAdapter);
            etLocation.showDropDown();

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

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    /**
     * @param cityName 保存当前选择的地址
     */
    private void saveLocation(String cityName) {
        List<String> cityList;
        if (SPUtils.getStrListValue(UserInfo.LOCATION_HISTORY.name()) == null) {
            cityList = new ArrayList<>();
        } else {
            cityList = SPUtils.getStrListValue(UserInfo.LOCATION_HISTORY.name());
        }
        if (cityList.size() >= 9) {
            cityList.remove(cityList.size() - 1);
        }
        cityList.add(0, cityName);
        SPUtils.putStrListValue(UserInfo.LOCATION_HISTORY.name(), cityList);
    }

    private void initLabels() {
        List<String> cityList = SPUtils.getStrListValue(UserInfo.LOCATION_HISTORY.name());
        if (cityList.size() > 0) {
            labels.setLabels(cityList);
        }
        labels.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                //label是被点击的标签，data是标签所对应的数据，position是标签的位置。
                Intent intent = new Intent();
                intent.putExtra("cityName", String.valueOf(data));
                setResult(CodeFinal.COMMON_RESULT_CODE, intent);
                finish();
            }
        });
    }
}
