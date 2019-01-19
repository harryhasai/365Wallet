package com.harry.wallet365.network.entity;

/**
 * Created by Harry on 2019/1/16.
 */
public class HomeUseCouponEntity {

    /**
     * msg :
     * code : 200
     * data : {"id":1,"sellerId":1,"name":"大礼包券","sellerName":"友谊商店","amount":100,"price":200,"type":1,"expireDate":"2018-01-11 10:11:12"}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * id : 1
         * sellerId : 1
         * name : 大礼包券
         * sellerName : 友谊商店
         * amount : 100
         * price : 200
         * type : 1
         * expireDate : 2018-01-11 10:11:12
         */

        public int id;
        public int sellerId;
        public String name;
        public String sellerName;
        public int amount;
        public int price;
        public int type;
        public String expireDate;
    }
}
