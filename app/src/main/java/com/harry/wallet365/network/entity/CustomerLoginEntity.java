package com.harry.wallet365.network.entity;

/**
 * Created by Harry on 2019/1/10.
 */
public class CustomerLoginEntity {

    /**
     * msg :
     * code : 200
     * data : {"headImg":"123.jpg","level":{"shop_id":1,"create_time":"2018-12-12 20:44:43","integral":100,"name":"普通","id":1,"status":1,"is_delete":0},"sex":1,"nickname":"张三","username":"15521130663","token":"95b894c90e1e418f85ee6b5fd7379c04_h3"}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * headImg : 123.jpg
         * level : {"shop_id":1,"create_time":"2018-12-12 20:44:43","integral":100,"name":"普通","id":1,"status":1,"is_delete":0}
         * sex : 1
         * nickname : 张三
         * username : 15521130663
         * token : 95b894c90e1e418f85ee6b5fd7379c04_h3
         */

        public String headImg;
        public LevelBean level;
        public int sex;
        public String nickname;
        public String username;
        public String token;

        public static class LevelBean {
            /**
             * shop_id : 1
             * create_time : 2018-12-12 20:44:43
             * integral : 100
             * name : 普通
             * id : 1
             * status : 1
             * is_delete : 0
             */

            public int shop_id;
            public String create_time;
            public int integral;
            public String name;
            public int id;
            public int status;
            public int is_delete;
        }
    }
}
