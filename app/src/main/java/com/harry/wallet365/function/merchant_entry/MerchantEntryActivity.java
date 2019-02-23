package com.harry.wallet365.function.merchant_entry;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.harry.wallet365.R;
import com.harry.wallet365.app_final.UserInfo;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.network.entity.GetAgreementEntity;
import com.harry.wallet365.network.entity.UploadImageEntity;
import com.harry.wallet365.utils.ImageUtil;
import com.harry.wallet365.utils.MyGlideEngine;
import com.harry.wallet365.utils.SPUtils;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by Harry on 2019/2/20.
 * 商家入驻
 */
public class MerchantEntryActivity extends BaseActivity<MerchantEntryPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    @BindView(R.id.cb_role1)
    CheckBox cbRole1;
    @BindView(R.id.ll_role1)
    LinearLayout llRole1;
    @BindView(R.id.cb_role2)
    CheckBox cbRole2;
    @BindView(R.id.ll_role2)
    LinearLayout llRole2;
    @BindView(R.id.cb_role3)
    CheckBox cbRole3;
    @BindView(R.id.ll_role3)
    LinearLayout llRole3;
    @BindView(R.id.cb_service1)
    CheckBox cbService1;
    @BindView(R.id.ll_service1)
    LinearLayout llService1;
    @BindView(R.id.cb_service2)
    CheckBox cbService2;
    @BindView(R.id.ll_service2)
    LinearLayout llService2;
    @BindView(R.id.cb_service3)
    CheckBox cbService3;
    @BindView(R.id.ll_service3)
    LinearLayout llService3;
    @BindView(R.id.cb_level1)
    CheckBox cbLevel1;
    @BindView(R.id.ll_level1)
    LinearLayout llLevel1;
    @BindView(R.id.cb_level2)
    CheckBox cbLevel2;
    @BindView(R.id.ll_level2)
    LinearLayout llLevel2;
    @BindView(R.id.cb_level3)
    CheckBox cbLevel3;
    @BindView(R.id.ll_level3)
    LinearLayout llLevel3;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_contacts)
    EditText etContacts;
    @BindView(R.id.et_contact_number)
    EditText etContactNumber;
    @BindView(R.id.et_mail)
    EditText etMail;
    @BindView(R.id.et_contact_customer_service_tel)
    EditText etContactCustomerServiceTel;
    @BindView(R.id.btn_address)
    Button btnAddress;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.cb_type1)
    CheckBox cbType1;
    @BindView(R.id.ll_type1)
    LinearLayout llType1;
    @BindView(R.id.cb_type2)
    CheckBox cbType2;
    @BindView(R.id.ll_type2)
    LinearLayout llType2;
    @BindView(R.id.cb_type3)
    CheckBox cbType3;
    @BindView(R.id.ll_type3)
    LinearLayout llType3;
    @BindView(R.id.cb_type4)
    CheckBox cbType4;
    @BindView(R.id.ll_type4)
    LinearLayout llType4;
    @BindView(R.id.et_business_category)
    EditText etBusinessCategory;
    @BindView(R.id.et_legal_person)
    EditText etLegalPerson;
    @BindView(R.id.et_card_number)
    EditText etCardNumber;
    @BindView(R.id.et_unit_number)
    EditText etUnitNumber;
    @BindView(R.id.et_business_number)
    EditText etBusinessNumber;
    @BindView(R.id.cb_settlement_type1)
    CheckBox cbSettlementType1;
    @BindView(R.id.ll_settlement_type1)
    LinearLayout llSettlementType1;
    @BindView(R.id.cb_settlement_type2)
    CheckBox cbSettlementType2;
    @BindView(R.id.ll_settlement_type2)
    LinearLayout llSettlementType2;
    @BindView(R.id.cb_settlement_type3)
    CheckBox cbSettlementType3;
    @BindView(R.id.ll_settlement_type3)
    LinearLayout llSettlementType3;
    @BindView(R.id.cb_type_of_settlement_card1)
    CheckBox cbTypeOfSettlementCard1;
    @BindView(R.id.ll_type_of_settlement_card1)
    LinearLayout llTypeOfSettlementCard1;
    @BindView(R.id.cb_type_of_settlement_card2)
    CheckBox cbTypeOfSettlementCard2;
    @BindView(R.id.ll_type_of_settlement_card2)
    LinearLayout llTypeOfSettlementCard2;
    @BindView(R.id.et_bank)
    EditText etBank;
    @BindView(R.id.et_bank_of_name)
    EditText etBankOfName;
    @BindView(R.id.et_bank_of_card_number)
    EditText etBankOfCardNumber;
    @BindView(R.id.et_bank_of_account)
    EditText etBankOfAccount;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.iv_photo1)
    ImageView ivPhoto1;
    @BindView(R.id.ll_photo1)
    LinearLayout llPhoto1;
    @BindView(R.id.iv_photo2)
    ImageView ivPhoto2;
    @BindView(R.id.ll_photo2)
    LinearLayout llPhoto2;
    @BindView(R.id.iv_photo3)
    ImageView ivPhoto3;
    @BindView(R.id.ll_photo3)
    LinearLayout llPhoto3;
    @BindView(R.id.iv_photo4)
    ImageView ivPhoto4;
    @BindView(R.id.ll_photo4)
    LinearLayout llPhoto4;
    @BindView(R.id.iv_photo5)
    ImageView ivPhoto5;
    @BindView(R.id.ll_photo5)
    LinearLayout llPhoto5;
    @BindView(R.id.iv_photo6)
    ImageView ivPhoto6;
    @BindView(R.id.ll_photo6)
    LinearLayout llPhoto6;
    @BindView(R.id.iv_photo7)
    ImageView ivPhoto7;
    @BindView(R.id.ll_photo7)
    LinearLayout llPhoto7;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private CityPickerView mPicker;
    private String selectedAddress = "";

    //选择照片请求码
    private static final int REQUEST_CODE_PHOTO1 = 1111;
    private static final int REQUEST_CODE_PHOTO2 = 1112;
    private static final int REQUEST_CODE_PHOTO3 = 1113;
    private static final int REQUEST_CODE_PHOTO4 = 1114;
    private static final int REQUEST_CODE_PHOTO5 = 1115;
    private static final int REQUEST_CODE_PHOTO6 = 1116;
    private static final int REQUEST_CODE_PHOTO7 = 1117;
    private int currentPhotoNumber = 0;
    //提交服务器后的图片路径
    private String photoUrl1;
    private String photoUrl2;
    private String photoUrl3;
    private String photoUrl4;
    private String photoUrl5;
    private String photoUrl6;
    private String photoUrl7;

    @Override
    protected int setupView() {
        return R.layout.activity_merchant_entry;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("商家入驻");
        tvAgreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        mPicker = new CityPickerView();
        mPicker.init(this);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected MerchantEntryPresenter bindPresenter() {
        return new MerchantEntryPresenter();
    }

    @OnClick({R.id.iv_back, R.id.tv_agreement, R.id.ll_role1, R.id.ll_role2,
            R.id.ll_role3, R.id.ll_service1, R.id.ll_service2, R.id.ll_service3,
            R.id.ll_level1, R.id.ll_level2, R.id.ll_level3, R.id.btn_address,
            R.id.ll_type1, R.id.ll_type2, R.id.ll_type3, R.id.ll_type4,
            R.id.ll_settlement_type1, R.id.ll_settlement_type2, R.id.ll_settlement_type3,
            R.id.ll_type_of_settlement_card1, R.id.ll_type_of_settlement_card2,
            R.id.ll_photo1, R.id.ll_photo2, R.id.ll_photo3, R.id.ll_photo4,
            R.id.ll_photo5, R.id.ll_photo6, R.id.ll_photo7, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_agreement:
                mPresenter.getAgreement();
                break;
            case R.id.ll_role1:
                cbRole1.setChecked(true);
                cbRole2.setChecked(false);
                cbRole3.setChecked(false);
                break;
            case R.id.ll_role2:
                cbRole1.setChecked(false);
                cbRole2.setChecked(true);
                cbRole3.setChecked(false);
                break;
            case R.id.ll_role3:
                cbRole1.setChecked(false);
                cbRole2.setChecked(false);
                cbRole3.setChecked(true);
                break;
            case R.id.ll_service1:
                cbService1.setChecked(true);
                cbService2.setChecked(false);
                cbService3.setChecked(false);
                break;
            case R.id.ll_service2:
                cbService1.setChecked(false);
                cbService2.setChecked(true);
                cbService3.setChecked(false);
                break;
            case R.id.ll_service3:
                cbService1.setChecked(false);
                cbService2.setChecked(false);
                cbService3.setChecked(true);
                break;
            case R.id.ll_level1:
                cbLevel1.setChecked(true);
                cbLevel2.setChecked(false);
                cbLevel3.setChecked(false);
                break;
            case R.id.ll_level2:
                cbLevel1.setChecked(false);
                cbLevel2.setChecked(true);
                cbLevel3.setChecked(false);
                break;
            case R.id.ll_level3:
                cbLevel1.setChecked(false);
                cbLevel2.setChecked(false);
                cbLevel3.setChecked(true);
                break;
            case R.id.btn_address:
                showCitySelectDialog();
                break;
            case R.id.ll_type1:
                cbType1.setChecked(true);
                cbType2.setChecked(false);
                cbType3.setChecked(false);
                cbType4.setChecked(false);
                break;
            case R.id.ll_type2:
                cbType1.setChecked(false);
                cbType2.setChecked(true);
                cbType3.setChecked(false);
                cbType4.setChecked(false);
                break;
            case R.id.ll_type3:
                cbType1.setChecked(false);
                cbType2.setChecked(false);
                cbType3.setChecked(true);
                cbType4.setChecked(false);
                break;
            case R.id.ll_type4:
                cbType1.setChecked(false);
                cbType2.setChecked(false);
                cbType3.setChecked(false);
                cbType4.setChecked(true);
                break;
            case R.id.ll_settlement_type1:
                cbSettlementType1.setChecked(true);
                cbSettlementType2.setChecked(false);
                cbSettlementType3.setChecked(false);
                break;
            case R.id.ll_settlement_type2:
                cbSettlementType1.setChecked(false);
                cbSettlementType2.setChecked(true);
                cbSettlementType3.setChecked(false);
                break;
            case R.id.ll_settlement_type3:
                cbSettlementType1.setChecked(false);
                cbSettlementType2.setChecked(false);
                cbSettlementType3.setChecked(true);
                break;
            case R.id.ll_type_of_settlement_card1:
                cbTypeOfSettlementCard1.setChecked(true);
                cbTypeOfSettlementCard2.setChecked(false);
                break;
            case R.id.ll_type_of_settlement_card2:
                cbTypeOfSettlementCard1.setChecked(false);
                cbTypeOfSettlementCard2.setChecked(true);
                break;
            case R.id.ll_photo1:
                selectPhoto(REQUEST_CODE_PHOTO1);
                break;
            case R.id.ll_photo2:
                selectPhoto(REQUEST_CODE_PHOTO2);
                break;
            case R.id.ll_photo3:
                selectPhoto(REQUEST_CODE_PHOTO3);
                break;
            case R.id.ll_photo4:
                selectPhoto(REQUEST_CODE_PHOTO4);
                break;
            case R.id.ll_photo5:
                selectPhoto(REQUEST_CODE_PHOTO5);
                break;
            case R.id.ll_photo6:
                selectPhoto(REQUEST_CODE_PHOTO6);
                break;
            case R.id.ll_photo7:
                selectPhoto(REQUEST_CODE_PHOTO7);
                break;
            case R.id.btn_confirm:
                confirmEntry();
                break;
        }
    }

    private void confirmEntry() {
        String name = etName.getText().toString().trim();
        String mobile = SPUtils.getString(UserInfo.USER_NAME.name(), "");
        String linkman = etContacts.getText().toString().trim();
        String phone = etContactNumber.getText().toString().trim();
        String email = etMail.getText().toString().trim();
        String serPhone = etContactCustomerServiceTel.getText().toString().trim();
        String selectAddress = TextUtils.isEmpty(btnAddress.getText().toString().trim()) ? "" : btnAddress.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String sellerType = "";
        if (cbType1.isChecked()) {
            sellerType = "1";
        } else if (cbType2.isChecked()) {
            sellerType = "2";
        } else if (cbType3.isChecked()) {
            sellerType = "3";
        } else if (cbType4.isChecked()) {
            sellerType = "4";
        }
        String payPercent = "";
        if (cbService1.isChecked()) {
            payPercent = "1";
        } else if (cbService2.isChecked()) {
            payPercent = "2";
        } else if (cbService3.isChecked()) {
            payPercent = "3";
        }
        String runType = etBusinessCategory.getText().toString().trim();
        String legalName = etLegalPerson.getText().toString().trim();
        String legalIdCard = etCardNumber.getText().toString().trim();
        String orgNum = etUnitNumber.getText().toString().trim();
        String licenceNum = etBusinessNumber.getText().toString().trim();
        String payType = "";
        if (cbSettlementType1.isChecked()) {
            payType = "1";
        } else if (cbSettlementType2.isChecked()) {
            payType = "2";
        } else if (cbSettlementType3.isChecked()) {
            payType = "3";
        }
        String payCardType = "";
        if (cbTypeOfSettlementCard1.isChecked()) {
            payCardType = "1";
        } else if (cbTypeOfSettlementCard2.isChecked()) {
            payCardType = "2";
        }
        String bankName = etBank.getText().toString().trim();
        String bankUser = etBankOfName.getText().toString().trim();
        String bankUserIdCard = etBankOfCardNumber.getText().toString().trim();
        String bankCardNum = etBankOfAccount.getText().toString().trim();
        String payNote = etRemark.getText().toString().trim();
        String channelMobile = etPhone.getText().toString().trim();
        String role = "";
        if (cbRole1.isChecked()) {
            role = "1";
        } else if (cbRole2.isChecked()) {
            role = "2";
        } else if (cbRole3.isChecked()) {
            role = "3";
        }
        String level = "";
        if (cbLevel1.isChecked()) {
            level = "1";
        } else if (cbLevel2.isChecked()) {
            level = "2";
        } else if (cbLevel3.isChecked()) {
            level = "3";
        }
        if (TextUtils.isEmpty(name)) {
            ToastUtils.showShort("门店名称不能为空");
            return;
        } else if (TextUtils.isEmpty(linkman)) {
            ToastUtils.showShort("联系人不能为空");
            return;
        } else if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("联系电话不能为空");
            return;
        } else if (TextUtils.isEmpty(email)) {
            ToastUtils.showShort("联系邮箱不能为空");
            return;
        } else if (TextUtils.isEmpty(serPhone)) {
            ToastUtils.showShort("联系客服电话不能为空");
            return;
        } else if (TextUtils.isEmpty(address)) {
            ToastUtils.showShort("通讯地址不能为空");
            return;
        } else if (TextUtils.isEmpty(runType)) {
            ToastUtils.showShort("经营类别不能为空");
            return;
        } else if (TextUtils.isEmpty(legalName)) {
            ToastUtils.showShort("法人姓名不能为空");
            return;
        } else if (TextUtils.isEmpty(legalIdCard)) {
            ToastUtils.showShort("法人身份证号不能为空");
            return;
        } else if (TextUtils.isEmpty(orgNum)) {
            ToastUtils.showShort("组织机构代码不能为空");
            return;
        } else if (TextUtils.isEmpty(licenceNum)) {
            ToastUtils.showShort("营业执照号不能为空");
            return;
        } else if (TextUtils.isEmpty(bankName)) {
            ToastUtils.showShort("详细开户行不能为空");
            return;
        } else if (TextUtils.isEmpty(bankUser)) {
            ToastUtils.showShort("开户名不能为空");
            return;
        } else if (TextUtils.isEmpty(bankUserIdCard)) {
            ToastUtils.showShort("开户人身份证不能为空");
            return;
        } else if (TextUtils.isEmpty(bankCardNum)) {
            ToastUtils.showShort("开户账号不能为空");
            return;
        } else if (TextUtils.isEmpty(payNote)) {
            ToastUtils.showShort("结算备注不能为空");
            return;
        } else if (TextUtils.isEmpty(photoUrl1)) {
            ToastUtils.showShort("营业执照图片不能为空");
            return;
        } else if (TextUtils.isEmpty(photoUrl2)) {
            ToastUtils.showShort("组织机构图片不能为空");
            return;
        } else if (TextUtils.isEmpty(photoUrl3)) {
            ToastUtils.showShort("税务登记证不能为空");
            return;
        } else if (TextUtils.isEmpty(photoUrl4)) {
            ToastUtils.showShort("开户许可证不能为空");
            return;
        } else if (TextUtils.isEmpty(photoUrl5)) {
            ToastUtils.showShort("法人身份证-正面不能为空");
            return;
        } else if (TextUtils.isEmpty(photoUrl6)) {
            ToastUtils.showShort("法人身份证-反面不能为空");
            return;
        } else if (TextUtils.isEmpty(photoUrl7)) {
            ToastUtils.showShort("门头照片不能为空");
            return;
        } else if (TextUtils.isEmpty(channelMobile)) {
            ToastUtils.showShort("所属渠道商绑定手机号不能为空");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        //params.put("mobile", mobile);//这个参数暂时废弃
        params.put("linkman", linkman);
        params.put("phone", phone);
        params.put("email", email);
        params.put("serPhone", serPhone);
        params.put("address", selectAddress + address);
        params.put("sellerType", sellerType);
        params.put("payPercent", payPercent);
        params.put("runType", runType);
        params.put("legalName", legalName);
        params.put("legalIdCard", legalIdCard);
        params.put("orgNum", orgNum);
        params.put("licenceNum", licenceNum);
        params.put("payType", payType);
        params.put("payCardType", payCardType);
        params.put("bankName", bankName);
        params.put("bankUser", bankUser);
        params.put("bankUserIdCard", bankUserIdCard);
        params.put("bankCardNum", bankCardNum);
        params.put("payNote", payNote);
        params.put("licenceImg", photoUrl1);
        params.put("orgImg", photoUrl2);
        params.put("taxImg", photoUrl3);
        params.put("openImg", photoUrl4);
        params.put("legalCardPosImg", photoUrl5);
        params.put("legalCardOppImg", photoUrl6);
        params.put("shopImg", photoUrl7);
        params.put("channelMobile", channelMobile);
        params.put("role", role);
        params.put("level", level);
        mPresenter.confirm(params);

    }

    public void uploadImage(UploadImageEntity.DataBean data) {
        switch (currentPhotoNumber) {
            case REQUEST_CODE_PHOTO1:
                photoUrl1 = data.url;
                break;
            case REQUEST_CODE_PHOTO2:
                photoUrl2 = data.url;
                break;
            case REQUEST_CODE_PHOTO3:
                photoUrl3 = data.url;
                break;
            case REQUEST_CODE_PHOTO4:
                photoUrl4 = data.url;
                break;
            case REQUEST_CODE_PHOTO5:
                photoUrl5 = data.url;
                break;
            case REQUEST_CODE_PHOTO6:
                photoUrl6 = data.url;
                break;
            case REQUEST_CODE_PHOTO7:
                photoUrl7 = data.url;
                break;
        }
    }

    public void getAgreement(GetAgreementEntity.DataBean data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_merchant_entry, null);
        dialog.setView(view);
        dialog.show();
        ImageView ivCancel = view.findViewById(R.id.iv_cancel);
        TextView tvContent = view.findViewById(R.id.tv_content);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvContent.setText(Html.fromHtml(data.info, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvContent.setText(Html.fromHtml(data.info));
        }
    }

    /**
     * 显示选择城市的弹窗
     */
    private void showCitySelectDialog() {
        CityConfig cityConfig = new CityConfig.Builder()
                .province("北京市")//默认显示的省份
                .city("北京市")//默认显示省份下面的城市
                .district("东城区")//默认显示省市下面的区县数据
                .build();
        mPicker.setConfig(cityConfig);
        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                //省份province
                //城市city
                //地区district
                selectedAddress = province.getName().trim() + "-" + city.getName().trim() + "-" + district.getName().trim();
                btnAddress.setText(selectedAddress);
            }

            @Override
            public void onCancel() {

            }
        });
        //显示
        mPicker.showCityPicker();
    }

    private void selectPhoto(int requestCode) {
        Matisse.from(this)
                .choose(MimeType.ofImage())
                .countable(false)//true:选中后显示数字;false:选中后显示对号
                .maxSelectable(1)//可选的最大数
                .capture(true)//选择照片时，是否显示拍照
                //参数1 true表示拍照存储在共有目录，false表示存储在私有目录；
                // 参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                .captureStrategy(new CaptureStrategy(true, "com.harry.wallet365.fileprovider"))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)//选择方向
                .thumbnailScale(0.8f)//界面中缩略图的质量
//                        .theme(R.style.Matisse_Zhihu)//蓝色主题
                .theme(R.style.Matisse_Dracula)//黑色主题
                .imageEngine(new MyGlideEngine())//图片加载引擎
                .forResult(requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PHOTO1 && resultCode == RESULT_OK) {//结算备注
//            List<Uri> result = Matisse.obtainResult(data);
            List<String> uriList = Matisse.obtainPathResult(data);
            Luban.with(this)
                    .load(uriList.get(0))// 传人要压缩的图片列表
                    .ignoreBy(100)// 忽略不压缩图片的大小
                    .setTargetDir(getPath())// 设置压缩后文件存储位置
                    .setCompressListener(new OnCompressListener() { //设置回调
                        @Override
                        public void onStart() {
                            //压缩开始前调用，可以在方法内启动 loading UI
                        }

                        @Override
                        public void onSuccess(File file) {
                            //压缩成功后调用，返回压缩后的图片文件
                            Glide.with(MerchantEntryActivity.this)
                                    .load(file)
                                    .into(ivPhoto1);
                            currentPhotoNumber = REQUEST_CODE_PHOTO1;
                            mPresenter.uploadImage(ImageUtil.image2Base64(file.getAbsolutePath()));
                        }

                        @Override
                        public void onError(Throwable e) {
                            //当压缩过程出现问题时调用
                            e.printStackTrace();
                        }
                    }).launch();    //启动压缩
        } else if (requestCode == REQUEST_CODE_PHOTO2 && resultCode == RESULT_OK) {//组织机构证
            List<String> uriList = Matisse.obtainPathResult(data);
            Luban.with(this)
                    .load(uriList.get(0))// 传人要压缩的图片列表
                    .ignoreBy(100)// 忽略不压缩图片的大小
                    .setTargetDir(getPath())// 设置压缩后文件存储位置
                    .setCompressListener(new OnCompressListener() { //设置回调
                        @Override
                        public void onStart() {
                            //压缩开始前调用，可以在方法内启动 loading UI
                        }

                        @Override
                        public void onSuccess(File file) {
                            //压缩成功后调用，返回压缩后的图片文件
                            Glide.with(MerchantEntryActivity.this)
                                    .load(file)
                                    .into(ivPhoto2);
                            currentPhotoNumber = REQUEST_CODE_PHOTO2;
                            mPresenter.uploadImage(ImageUtil.image2Base64(file.getAbsolutePath()));
                        }

                        @Override
                        public void onError(Throwable e) {
                            //当压缩过程出现问题时调用
                            e.printStackTrace();
                        }
                    }).launch();    //启动压缩

        } else if (requestCode == REQUEST_CODE_PHOTO3 && resultCode == RESULT_OK) {//税务登记证
            List<String> uriList = Matisse.obtainPathResult(data);
            Luban.with(this)
                    .load(uriList.get(0))// 传人要压缩的图片列表
                    .ignoreBy(100)// 忽略不压缩图片的大小
                    .setTargetDir(getPath())// 设置压缩后文件存储位置
                    .setCompressListener(new OnCompressListener() { //设置回调
                        @Override
                        public void onStart() {
                            //压缩开始前调用，可以在方法内启动 loading UI
                        }

                        @Override
                        public void onSuccess(File file) {
                            //压缩成功后调用，返回压缩后的图片文件
                            Glide.with(MerchantEntryActivity.this)
                                    .load(file)
                                    .into(ivPhoto3);
                            currentPhotoNumber = REQUEST_CODE_PHOTO3;
                            mPresenter.uploadImage(ImageUtil.image2Base64(file.getAbsolutePath()));
                        }

                        @Override
                        public void onError(Throwable e) {
                            //当压缩过程出现问题时调用
                            e.printStackTrace();
                        }
                    }).launch();    //启动压缩

        } else if (requestCode == REQUEST_CODE_PHOTO4 && resultCode == RESULT_OK) {//开户许可证
            List<String> uriList = Matisse.obtainPathResult(data);
            Luban.with(this)
                    .load(uriList.get(0))// 传人要压缩的图片列表
                    .ignoreBy(100)// 忽略不压缩图片的大小
                    .setTargetDir(getPath())// 设置压缩后文件存储位置
                    .setCompressListener(new OnCompressListener() { //设置回调
                        @Override
                        public void onStart() {
                            //压缩开始前调用，可以在方法内启动 loading UI
                        }

                        @Override
                        public void onSuccess(File file) {
                            //压缩成功后调用，返回压缩后的图片文件
                            Glide.with(MerchantEntryActivity.this)
                                    .load(file)
                                    .into(ivPhoto4);
                            currentPhotoNumber = REQUEST_CODE_PHOTO4;
                            mPresenter.uploadImage(ImageUtil.image2Base64(file.getAbsolutePath()));
                        }

                        @Override
                        public void onError(Throwable e) {
                            //当压缩过程出现问题时调用
                            e.printStackTrace();
                        }
                    }).launch();    //启动压缩

        } else if (requestCode == REQUEST_CODE_PHOTO5 && resultCode == RESULT_OK) {//法人身份证正面
            List<String> uriList = Matisse.obtainPathResult(data);
            Luban.with(this)
                    .load(uriList.get(0))// 传人要压缩的图片列表
                    .ignoreBy(100)// 忽略不压缩图片的大小
                    .setTargetDir(getPath())// 设置压缩后文件存储位置
                    .setCompressListener(new OnCompressListener() { //设置回调
                        @Override
                        public void onStart() {
                            //压缩开始前调用，可以在方法内启动 loading UI
                        }

                        @Override
                        public void onSuccess(File file) {
                            //压缩成功后调用，返回压缩后的图片文件
                            Glide.with(MerchantEntryActivity.this)
                                    .load(file)
                                    .into(ivPhoto5);
                            currentPhotoNumber = REQUEST_CODE_PHOTO5;
                            mPresenter.uploadImage(ImageUtil.image2Base64(file.getAbsolutePath()));
                        }

                        @Override
                        public void onError(Throwable e) {
                            //当压缩过程出现问题时调用
                            e.printStackTrace();
                        }
                    }).launch();    //启动压缩

        } else if (requestCode == REQUEST_CODE_PHOTO6 && resultCode == RESULT_OK) {//法人身份证反面
            List<String> uriList = Matisse.obtainPathResult(data);
            Luban.with(this)
                    .load(uriList.get(0))// 传人要压缩的图片列表
                    .ignoreBy(100)// 忽略不压缩图片的大小
                    .setTargetDir(getPath())// 设置压缩后文件存储位置
                    .setCompressListener(new OnCompressListener() { //设置回调
                        @Override
                        public void onStart() {
                            //压缩开始前调用，可以在方法内启动 loading UI
                        }

                        @Override
                        public void onSuccess(File file) {
                            //压缩成功后调用，返回压缩后的图片文件
                            Glide.with(MerchantEntryActivity.this)
                                    .load(file)
                                    .into(ivPhoto6);
                            currentPhotoNumber = REQUEST_CODE_PHOTO6;
                            mPresenter.uploadImage(ImageUtil.image2Base64(file.getAbsolutePath()));
                        }

                        @Override
                        public void onError(Throwable e) {
                            //当压缩过程出现问题时调用
                            e.printStackTrace();
                        }
                    }).launch();    //启动压缩

        } else if (requestCode == REQUEST_CODE_PHOTO7 && resultCode == RESULT_OK) {//门头照
            List<String> uriList = Matisse.obtainPathResult(data);
            Luban.with(this)
                    .load(uriList.get(0))// 传人要压缩的图片列表
                    .ignoreBy(100)// 忽略不压缩图片的大小
                    .setTargetDir(getPath())// 设置压缩后文件存储位置
                    .setCompressListener(new OnCompressListener() { //设置回调
                        @Override
                        public void onStart() {
                            //压缩开始前调用，可以在方法内启动 loading UI
                        }

                        @Override
                        public void onSuccess(File file) {
                            //压缩成功后调用，返回压缩后的图片文件
                            Glide.with(MerchantEntryActivity.this)
                                    .load(file)
                                    .into(ivPhoto7);
                            currentPhotoNumber = REQUEST_CODE_PHOTO7;
                            mPresenter.uploadImage(ImageUtil.image2Base64(file.getAbsolutePath()));
                        }

                        @Override
                        public void onError(Throwable e) {
                            //当压缩过程出现问题时调用
                            e.printStackTrace();
                        }
                    }).launch();    //启动压缩

        }
    }

    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/LuBan/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }
}
