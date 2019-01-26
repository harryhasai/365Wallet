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
    /**
     * 获取商家详情
     */
    public static final String GET_GOODS_DETAIL = "api/seller/getProduct";
    /**
     * 获取商家的用户评价分页
     */
    public static final String GET_COMMENT_LIST = "api/seller/getCommentPage";
    /**
     * 附近 - 获取顶部广告图
     */
    public static final String NEARBY_GET_BANNER = "api/seller/getSlide";
    /**
     * 获取行业列表分页
     */
    public static final String NEARBY_GET_CATEGORY = "api/seller/getCategory";
    /**
     * 获取商家列表分页
     */
    public static final String NEARBY_GET_SHOP_LIST = "api/seller/getSellerPage";
    /**
     * 获取会员用户信息
     */
    public static final String GET_USER_INFO = "api/user/getUserInfo";
    /**
     * 修改用户密码
     */
    public static final String MODIFY_PASSWORD = "api/user/update";
    /**
     * 关于我们
     */
    public static final String ABOUT = "api/info/about";
    /**
     * 帮助中心
     */
    public static final String HELP_CENTER = "api/info/help";
    /**
     * 意见反馈
     */
    public static final String FEEDBACK = "api/info/feedback";

}
