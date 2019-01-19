package com.harry.wallet365.network.entity;

import java.util.List;

/**
 * Created by Harry on 2019/1/18.
 */
public class GoodsListEntity {

    /**
     * msg :
     * code : 200
     * data : {"totalRow":11,"pageNumber":1,"lastPage":false,"firstPage":true,"totalPage":2,"pageSize":10,"list":[{"headImg":"20190111/20190111102704995iKCkZGGF.jpg","price":10,"name":"番薯","count":11,"id":1}]}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * totalRow : 11
         * pageNumber : 1
         * lastPage : false
         * firstPage : true
         * totalPage : 2
         * pageSize : 10
         * list : [{"headImg":"20190111/20190111102704995iKCkZGGF.jpg","price":10,"name":"番薯","count":11,"id":1}]
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
             * headImg : 20190111/20190111102704995iKCkZGGF.jpg
             * price : 10
             * name : 番薯
             * count : 11
             * id : 1
             */

            public String headImg;
            public double price;
            public String name;
            public int count;
            public int id;
        }
    }
}
