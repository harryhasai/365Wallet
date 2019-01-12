package com.harry.wallet365.network.entity;

/**
 * Created by Harry on 2019/1/10.
 */
public class ShopLoginEntity {

    /**
     * msg :
     * code : 200
     * data : {"mobile":"admin","shopName":"广西手工艺专营店","token":"33e496f34f13411089a8ccb42e841f16_Mf"}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * mobile : admin
         * shopName : 广西手工艺专营店
         * token : 33e496f34f13411089a8ccb42e841f16_Mf
         */

        public String mobile;
        public String shopName;
        public String token;
    }
}
