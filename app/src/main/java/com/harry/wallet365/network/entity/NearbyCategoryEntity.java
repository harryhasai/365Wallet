package com.harry.wallet365.network.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Harry on 2019/1/21.
 */
public class NearbyCategoryEntity {

    /**
     * msg :
     * code : 200
     * data : {"totalRow":6,"pageNumber":1,"firstPage":true,"lastPage":true,"totalPage":1,"pageSize":10,"list":[{"cover":"123","name":"日用","id":3},{"cover":"20181226/20181226174405567y9zCpQuf.jpg","name":"234","id":9}]}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * totalRow : 6
         * pageNumber : 1
         * firstPage : true
         * lastPage : true
         * totalPage : 1
         * pageSize : 10
         * list : [{"cover":"123","name":"日用","id":3},{"cover":"20181226/20181226174405567y9zCpQuf.jpg","name":"234","id":9}]
         */

        public int totalRow;
        public int pageNumber;
        public boolean firstPage;
        public boolean lastPage;
        public int totalPage;
        public int pageSize;
        public List<ListBean> list;

        public static class ListBean implements Serializable {
            /**
             * cover : 123
             * name : 日用
             * id : 3
             */

            public String cover;
            public String name;
            public int id;
        }
    }
}
