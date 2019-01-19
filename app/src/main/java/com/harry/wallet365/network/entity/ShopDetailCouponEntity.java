package com.harry.wallet365.network.entity;

import java.util.List;

/**
 * Created by Harry on 2019/1/18.
 */
public class ShopDetailCouponEntity {

    /**
     * msg :
     * code : 200
     * data : {"totalRow":9,"pageNumber":1,"lastPage":true,"firstPage":true,"totalPage":1,"pageSize":10,"list":[{"amount":8,"sellerId":1,"price":200,"name":"8折优惠","expireDate":"2019-01-25 00:00:00","id":31,"type":1},{"amount":8,"sellerId":1,"price":200,"name":"8折优惠","expireDate":"2019-01-25 00:00:00","id":32,"type":0},{"amount":8,"sellerId":1,"price":200,"name":"本店开店优惠","expireDate":"2019-01-12 00:00:00","id":30,"type":1},{"amount":20,"sellerId":1,"price":1564,"name":"优惠券","expireDate":"2018-12-29 11:43:03","id":22,"type":2},{"amount":8,"sellerId":1,"price":50,"name":"优惠券","expireDate":"2018-12-27 00:00:00","id":21,"type":2},{"amount":60,"sellerId":1,"price":20,"name":"优惠券","expireDate":"2019-01-05 15:44:47","id":13,"type":2},{"amount":100,"sellerId":1,"price":200,"name":"优惠券","expireDate":null,"id":5,"type":1},{"amount":100,"sellerId":1,"price":200,"name":"优惠券","expireDate":"2018-01-11 10:11:12","id":1,"type":1},{"amount":1,"sellerId":1,"price":1,"name":"优惠券","expireDate":null,"id":2,"type":1}]}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * totalRow : 9
         * pageNumber : 1
         * lastPage : true
         * firstPage : true
         * totalPage : 1
         * pageSize : 10
         * list : [{"amount":8,"sellerId":1,"price":200,"name":"8折优惠","expireDate":"2019-01-25 00:00:00","id":31,"type":1},{"amount":8,"sellerId":1,"price":200,"name":"8折优惠","expireDate":"2019-01-25 00:00:00","id":32,"type":0},{"amount":8,"sellerId":1,"price":200,"name":"本店开店优惠","expireDate":"2019-01-12 00:00:00","id":30,"type":1},{"amount":20,"sellerId":1,"price":1564,"name":"优惠券","expireDate":"2018-12-29 11:43:03","id":22,"type":2},{"amount":8,"sellerId":1,"price":50,"name":"优惠券","expireDate":"2018-12-27 00:00:00","id":21,"type":2},{"amount":60,"sellerId":1,"price":20,"name":"优惠券","expireDate":"2019-01-05 15:44:47","id":13,"type":2},{"amount":100,"sellerId":1,"price":200,"name":"优惠券","expireDate":null,"id":5,"type":1},{"amount":100,"sellerId":1,"price":200,"name":"优惠券","expireDate":"2018-01-11 10:11:12","id":1,"type":1},{"amount":1,"sellerId":1,"price":1,"name":"优惠券","expireDate":null,"id":2,"type":1}]
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
             * amount : 8
             * sellerId : 1
             * price : 200
             * name : 8折优惠
             * expireDate : 2019-01-25 00:00:00
             * id : 31
             * type : 1
             */

            public double amount;
            public int sellerId;
            public double price;
            public String name;
            public String expireDate;
            public int id;
            public int type;
        }
    }
}
