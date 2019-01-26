package com.harry.wallet365.network.entity;

/**
 * Created by Harry on 2019/1/24.
 */
public class UserInfoEntity {

    /**
     * msg :
     * code : 200
     * data : {"birthday":"2019-1-22","headImg":"http://150.109.16.41:8086/upload/20190123/20190123102723667d93kwuGG.jpg","phone":"189******85","level":"普通会员","idCard":"1236******9525","sex":0,"nickname":"新用户","id":24,"realname":"哈哈"}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * birthday : 2019-1-22
         * headImg : http://150.109.16.41:8086/upload/20190123/20190123102723667d93kwuGG.jpg
         * phone : 189******85
         * level : 普通会员
         * idCard : 1236******9525
         * sex : 0
         * nickname : 新用户
         * id : 24
         * realname : 哈哈
         */

        public String birthday;
        public String headImg;
        public String phone;
        public String level;
        public String idCard;
        public int sex;
        public String nickname;
        public int id;
        public String realname;
    }
}
