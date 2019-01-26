package com.harry.wallet365.network.entity;

/**
 * Created by Harry on 2019/1/25.
 */
public class AboutEntity {

    /**
     * msg :
     * code : 200
     * data : {"info":"<p><br/><\/p><p><br/><\/p><p>&nbsp; &nbsp; &nbsp; &nbsp; 中联三六五平台是一家专业O2O电商平台，主要服务于<\/p><p>线上线下商户，为商户提供各类营销服务、会员管理、订单<\/p><p>管理、优惠促销等功能<\/p><p><br/><\/p>"}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * info : <p><br/></p><p><br/></p><p>&nbsp; &nbsp; &nbsp; &nbsp; 中联三六五平台是一家专业O2O电商平台，主要服务于</p><p>线上线下商户，为商户提供各类营销服务、会员管理、订单</p><p>管理、优惠促销等功能</p><p><br/></p>
         */

        public String info;
    }


}
