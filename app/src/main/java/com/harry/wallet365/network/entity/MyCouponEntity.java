package com.harry.wallet365.network.entity;

import java.util.List;

/**
 * Created by Harry on 2019/2/25.
 */
public class MyCouponEntity {

    /**
     * msg :
     * code : 200
     * data : {"totalRow":2,"pageNumber":1,"firstPage":true,"lastPage":true,"totalPage":1,"pageSize":10,"list":[{"id":"1","name":"大礼包券","amount":"100","price":"200","type":"1","expireDate":"2018-12-10","sellerName":"友谊商店"},{"id":"1","name":"大礼包券","amount":"100","price":"200","type":"1","expireDate":"2018-12-10","sellerName":"友谊商店"}]}
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
         * list : [{"id":"1","name":"大礼包券","amount":"100","price":"200","type":"1","expireDate":"2018-12-10","sellerName":"友谊商店"},{"id":"1","name":"大礼包券","amount":"100","price":"200","type":"1","expireDate":"2018-12-10","sellerName":"友谊商店"}]
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
             * id : 1
             * name : 大礼包券
             * amount : 100
             * price : 200
             * type : 1
             * expireDate : 2018-12-10
             * sellerName : 友谊商店
             */

            public String id;
            public String name;
            public String amount;
            public String price;
            public String type;
            public String expireDate;
            public String sellerName;
        }
    }
}
