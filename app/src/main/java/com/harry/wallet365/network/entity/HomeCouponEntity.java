package com.harry.wallet365.network.entity;

import java.util.List;

/**
 * Created by Harry on 2019/1/12.
 */
public class HomeCouponEntity {

    /**
     * msg :
     * code : 200
     * data : {"totalRow":2,"pageNumber":1,"firstPage":true,"lastPage":true,"totalPage":1,"pageSize":10,"list":[{"amount":100,"sellerId":1,"price":200,"sellerName":"3","id":1,"source":2,"type":1},{"amount":1,"sellerId":1,"price":1,"sellerName":"3","id":2,"source":2,"type":1}]}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * totalRow : 2
         * pageNumber : 1
         * firstPage : true
         * lastPage : true
         * totalPage : 1
         * pageSize : 10
         * list : [{"amount":100,"sellerId":1,"price":200,"sellerName":"3","id":1,"source":2,"type":1},{"amount":1,"sellerId":1,"price":1,"sellerName":"3","id":2,"source":2,"type":1}]
         */

        public int totalRow;
        public int pageNumber;
        public boolean firstPage;
        public boolean lastPage;
        public int totalPage;
        public int pageSize;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * amount : 100
             * sellerId : 1
             * price : 200
             * sellerName : 3
             * id : 1
             * source : 2
             * type : 1
             */

            public double amount;
            public int sellerId;
            public double price;
            public String sellerName;
            public int id;
            public int source;
            public int type;
        }
    }
}
