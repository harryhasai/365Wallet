package com.harry.wallet365.network.entity;

import java.util.List;

/**
 * Created by Harry on 2019/1/21.
 */
public class NearbyShopListEntity {

    /**
     * msg :
     * code : 200
     * data : {"totalRow":8,"pageNumber":1,"firstPage":true,"lastPage":true,"totalPage":1,"pageSize":10,"list":[{"score":5,"headImg":"20181221/201812211057382873ZxImO1S.jpg","distance":126,"voucherList":["8折优惠券","10元优惠券"],"latitude":"23.129941","name":"22","id":4,"longitude":"113.367163","status":0}]}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * totalRow : 8
         * pageNumber : 1
         * firstPage : true
         * lastPage : true
         * totalPage : 1
         * pageSize : 10
         * list : [{"score":5,"headImg":"20181221/201812211057382873ZxImO1S.jpg","distance":126,"voucherList":["8折优惠券","10元优惠券"],"latitude":"23.129941","name":"22","id":4,"longitude":"113.367163","status":0}]
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
             * score : 5
             * headImg : 20181221/201812211057382873ZxImO1S.jpg
             * distance : 126
             * voucherList : ["8折优惠券","10元优惠券"]
             * latitude : 23.129941
             * name : 22
             * id : 4
             * longitude : 113.367163
             * status : 0
             */

            public int score;
            public String headImg;
            public int distance;
            public String latitude;
            public String name;
            public int id;
            public String longitude;
            public int status;
            public List<String> voucherList;
        }
    }
}
