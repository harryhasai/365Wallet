package com.harry.wallet365.network.entity;

/**
 * Created by Harry on 2019/2/23.
 */
public class XPubEntity {

    /**
     * msg :
     * code : 200
     * data : {"id":1,"account":"123456"}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * id : 1
         * account : 123456
         */

        public int id;
        public String account;
    }
}
