package com.harry.wallet365.app_final;

/**
 * Created by Harry on 2019/1/9.
 */
public enum UserInfo {

    IS_LOGIN,
    /**
     * 商家登录(1)或者普通会员登录(2)
     */
    LOGIN_TYPE,
    TOKEN,
    HEAD_IMG,
    /**
     * 性别：0-女，1-男
     */
    SEX,
    NICK_NAME,
    USER_NAME,
    SHOP_MOBILE,
    SHOP_NAME,
    LOCATION_HISTORY,
    CITY,
    CURRENT_LOCATION,
    /**
     * 底部导航栏的颜色<br>
     * 1 - 红色
     * 2 - 黑色
     * 3 - 黄色
     */
    BOTTOM_NAVIGATION_ICON_COLOR,
}
