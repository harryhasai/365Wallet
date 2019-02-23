package com.harry.wallet365.network.entity;

/**
 * Created by Harry on 2019/2/23.
 */
public class MyBankInfoEntity {

    /**
     * msg :
     * code : 200
     * data : {"id":1,"bankName":"建设银行","cardNumber":"***************0000","username":"123456"}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * id : 1
         * bankName : 建设银行
         * cardNumber : ***************0000
         * username : 123456
         */

        public int id;
        public String bankName;
        public String cardNumber;
        public String username;
    }
}
