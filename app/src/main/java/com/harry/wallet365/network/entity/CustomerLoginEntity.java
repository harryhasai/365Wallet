package com.harry.wallet365.network.entity;

/**
 * Created by Harry on 2019/1/10.
 */
public class CustomerLoginEntity {

    /**
     * msg :
     * code : 200
     * data : {"headImg":"http://150.109.16.41:8086/upload/20190123/20190123102723667d93kwuGG.jpg","level":"普通","sex":0,"nickname":"哈哈","username":"18937123685","token":"36e5d022543f44f69ef7ab3fc6fca4e8_M7"}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * headImg : http://150.109.16.41:8086/upload/20190123/20190123102723667d93kwuGG.jpg
         * level : 普通
         * sex : 0
         * nickname : 哈哈
         * username : 18937123685
         * token : 36e5d022543f44f69ef7ab3fc6fca4e8_M7
         */

        public String headImg;
        public String level;
        public int sex;
        public String nickname;
        public String username;
        public String token;
    }
}
