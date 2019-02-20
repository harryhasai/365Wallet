package com.harry.wallet365.function.address.address_detail;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.network.entity.AddressDetailEntity;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/2/19.
 * 收货地址详情以及添加新的收货地址的页面
 */
public class AddressDetailActivity extends BaseActivity<AddressDetailPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.cb_default)
    CheckBox cbDefault;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private int addressId;
    private CityPickerView mPicker;

    @Override
    protected int setupView() {
        return R.layout.activity_address_detail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        addressId = getIntent().getIntExtra("addressId", 0);
        if (addressId == 0) {//添加新地址
            tvTitle.setText("新添加收货地址");
            btnConfirm.setText("确认添加");
        } else {//编辑地址
            tvTitle.setText("编辑收货地址");
            btnConfirm.setText("确认修改");
            mPresenter.getAddress(addressId);
        }

        mPicker = new CityPickerView();
        mPicker.init(this);

    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.ADDRESS_DETAIL_ACTIVITY_GET_ADDRESS);
        tags.add(DisposableFinal.ADDRESS_DETAIL_ACTIVITY_ADD_ADDRESS);
        tags.add(DisposableFinal.ADDRESS_DETAIL_ACTIVITY_UPDATE_ADDRESS);
        return tags;
    }

    @Override
    protected AddressDetailPresenter bindPresenter() {
        return new AddressDetailPresenter();
    }

    @OnClick({R.id.iv_back, R.id.btn_confirm, R.id.tv_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_confirm:
                editAddress();
                break;
            case R.id.tv_address:
                CityConfig cityConfig = new CityConfig.Builder().build();
                mPicker.setConfig(cityConfig);
                //监听选择点击事件及返回结果
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        //省份province
                        //城市city
                        //地区district
                        tvAddress.setText(province.getName().trim() + "-" + city.getName().trim() + "-" + district.getName().trim());
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                //显示
                mPicker.showCityPicker();
                break;
        }
    }

    public void getAddress(AddressDetailEntity.DataBean data) {
        etName.setText(data.realname);
        etPhone.setText(data.mobile);
        etAddress.setText(data.content);
        if (data.status == 1) {
            cbDefault.setChecked(true);
        } else {
            cbDefault.setChecked(false);
        }
    }

    /**
     * 编辑地址
     */
    private void editAddress() {
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String detailAddress = etAddress.getText().toString().trim();
        String address = tvAddress.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            ToastUtils.showShort("收货人不能为空");
            return;
        } else if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("联系电话不能为空");
            return;
        } else if (TextUtils.isEmpty(detailAddress)) {
            ToastUtils.showShort("详细地址不能为空");
            return;
        }

        String area = (TextUtils.isEmpty(address) ? "" : address) + detailAddress;
        if (addressId == 0) {//添加新地址
            mPresenter.AddAddress(name, phone, area, cbDefault.isChecked() ? 1 : 0);
        } else {//编辑地址
            mPresenter.updateAddress(addressId, name, phone, area, cbDefault.isChecked() ? 1 : 0);
        }
    }

}
