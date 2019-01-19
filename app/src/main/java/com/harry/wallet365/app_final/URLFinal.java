package com.harry.wallet365.app_final;

/**
 * URL地址类
 */
public class URLFinal {
    /**
     * BaseUrl
     */
    public static final String BASE_URL = "http://150.109.16.41:9000/";

    /**
     * 登录
     */
    public static final String LOGIN = "api/entry/login";
    /**
     * 获取验证码
     */
    public static final String GET_VERIFICATION_CODE = "api/common/getCode";
    /**
     * 注册
     */
    public static final String REGISTER = "api/entry/register";
    /**
     * 忘记密码
     */
    public static final String FORGET_PASSWORD = "api/entry/iforget";
    /**
     * 获取轮播图
     */
    public static final String HOME_GET_BANNER = "api/index/getSlidePage";
    /**
     * 获取促销活动列表分页
     */
    public static final String HOME_GET_BOTTOM_LIST = "api/index/getActPage";
    /**
     * 获取附近优惠券分页
     */
    public static final String HOME_GET_COUPON = "api/index/getVoucherPage";
    /**
     * 领取优惠券
     */
    public static final String HOME_USE_COUPON = "api/voucher/getVoucher";
    /**
     * 获取商家详情
     */
    public static final String GET_SHOP_DETAIL = "api/seller/getSeller";
    /**
     * 获取商家的优惠券分页
     */
    public static final String GET_SHOP_DETAIL_COUPON = "api/voucher/getVoucherPage";
    /**
     * 获取商家的商品分页
     */
    public static final String GET_GOODS_LIST = "api/seller/getProductPage";

}
