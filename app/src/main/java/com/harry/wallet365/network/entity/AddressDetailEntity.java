package com.harry.wallet365.network.entity;

/**
 * Created by Harry on 2019/2/19.
 */
public class AddressDetailEntity {

    /**
     * msg :
     * code : 200
     * data : {"user_id":2,"mobile":"15521130663","id":1,"type":1,"content":"广东省广州市","realname":"张三","status":1}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * user_id : 2
         * mobile : 15521130663
         * id : 1
         * type : 1
         * content : 广东省广州市
         * realname : 张三
         * status : 1
         */

        public int user_id;
        public String mobile;
        public int id;
        public int type;
        public String content;
        public String realname;
        public int status;
    }
}
