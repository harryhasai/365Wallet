package com.harry.wallet365.network.entity;

import java.util.List;

/**
 * Created by Harry on 2019/1/12.
 */
public class HomeBannerEntity {

    /**
     * msg :
     * code : 200
     * data : {"totalRow":2,"pageNumber":1,"firstPage":true,"lastPage":true,"totalPage":1,"pageSize":10,"list":[{"id":1,"img":"/1.jpg","url":"http://www.baidu.com"},{"id":2,"img":"/1.jpg","url":"http://www.baidu.com"}]}
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
         * list : [{"id":1,"img":"/1.jpg","url":"http://www.baidu.com"},{"id":2,"img":"/1.jpg","url":"http://www.baidu.com"}]
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
             * img : /1.jpg
             * url : http://www.baidu.com
             */

            public int id;
            public String img;
            public String url;
        }
    }
}
