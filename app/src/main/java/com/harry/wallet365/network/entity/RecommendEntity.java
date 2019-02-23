package com.harry.wallet365.network.entity;

import java.util.List;

/**
 * Created by Harry on 2019/2/20.
 */
public class RecommendEntity {

    /**
     * msg :
     * code : 200
     * data : {"totalRow":2,"pageNumber":1,"firstPage":true,"lastPage":true,"totalPage":1,"pageSize":10,"list":[{"id":"123456","getInterCount":2000,"createDate":"2018-10-10 20:00:00"},{"id":"123456","getInterCount":2000,"createDate":"2018-10-10 20:00:00"}]}
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
         * list : [{"id":"123456","getInterCount":2000,"createDate":"2018-10-10 20:00:00"},{"id":"123456","getInterCount":2000,"createDate":"2018-10-10 20:00:00"}]
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
             * id : 123456
             * getInterCount : 2000
             * createDate : 2018-10-10 20:00:00
             */

            public String id;
            public int getInterCount;
            public String createDate;
        }
    }
}
