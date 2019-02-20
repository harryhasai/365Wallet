package com.harry.wallet365.network.entity;

import java.util.List;

/**
 * Created by Harry on 2019/2/19.
 */
public class AddressEntity {

    /**
     * msg :
     * code : 200
     * data : {"totalRow":2,"pageNumber":1,"lastPage":true,"firstPage":true,"totalPage":1,"pageSize":10,"list":[{"user_id":1,"mobile":"15521130663","id":1,"type":1,"content":"广东省广州市","realname":"张三","status":1},{"user_id":1,"mobile":"15521130663","id":2,"type":1,"content":"广东省深圳市","realname":"张三","status":0}]}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * totalRow : 2
         * pageNumber : 1
         * lastPage : true
         * firstPage : true
         * totalPage : 1
         * pageSize : 10
         * list : [{"user_id":1,"mobile":"15521130663","id":1,"type":1,"content":"广东省广州市","realname":"张三","status":1},{"user_id":1,"mobile":"15521130663","id":2,"type":1,"content":"广东省深圳市","realname":"张三","status":0}]
         */

        public int totalRow;
        public int pageNumber;
        public boolean lastPage;
        public boolean firstPage;
        public int totalPage;
        public int pageSize;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * user_id : 1
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
}
