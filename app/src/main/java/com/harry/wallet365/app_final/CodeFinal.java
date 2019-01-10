package com.harry.wallet365.app_final;

/**
 * Created by Harry on 2019/1/9.
 * 返回码+常量
 */
public class CodeFinal {

    /**
     * 响应成功
     */
    public static final int RESPONSE_SUCCESS = 200;
    /**
     * 执行错误
     */
    public static final int RESPONSE_ERROR = 1001;
    /**
     * 未经授权-需要登陆
     */
    public static final int RESPONSE_NEED_LOGIN = 1002;
    /**
     * 缺失参数
     */
    public static final int RESPONSE_MISSING_PARAM = 1003;
    /**
     * 通用请求码
     */
    public static final int COMMON_REQUEST_CODE = 2001;
    /**
     * 通用结果码
     */
    public static final int COMMON_RESULT_CODE = 2002;
}
