package com.harry.wallet365.network.entity;

import java.util.List;

/**
 * Created by Harry on 2019/1/19.
 */
public class UserCommentEntity {

    /**
     * msg :
     * code : 200
     * data : {"totalRow":1,"pageNumber":1,"lastPage":true,"firstPage":true,"totalPage":1,"pageSize":10,"list":[{"headImg":"http://150.109.16.41:8086/upload/20190116/20190116193327195RdklLaVE.png","envScore":5,"createTime":"2018-12-12 19:06:10","serScore":3,"nickname":"张三","id":1,"userId":1,"content":"东西不错，买了6斤","upScore":4,"imgList":["http://150.109.16.41:8086/upload/20190111/201901111142401114BcNEkuk.jpg","http://150.109.16.41:8086/upload/20190111/201901111142401114BcNEkuk.jpg"]}]}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * totalRow : 1
         * pageNumber : 1
         * lastPage : true
         * firstPage : true
         * totalPage : 1
         * pageSize : 10
         * list : [{"headImg":"http://150.109.16.41:8086/upload/20190116/20190116193327195RdklLaVE.png","envScore":5,"createTime":"2018-12-12 19:06:10","serScore":3,"nickname":"张三","id":1,"userId":1,"content":"东西不错，买了6斤","upScore":4,"imgList":["http://150.109.16.41:8086/upload/20190111/201901111142401114BcNEkuk.jpg","http://150.109.16.41:8086/upload/20190111/201901111142401114BcNEkuk.jpg"]}]
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
             * headImg : http://150.109.16.41:8086/upload/20190116/20190116193327195RdklLaVE.png
             * envScore : 5
             * createTime : 2018-12-12 19:06:10
             * serScore : 3
             * nickname : 张三
             * id : 1
             * userId : 1
             * content : 东西不错，买了6斤
             * upScore : 4
             * imgList : ["http://150.109.16.41:8086/upload/20190111/201901111142401114BcNEkuk.jpg","http://150.109.16.41:8086/upload/20190111/201901111142401114BcNEkuk.jpg"]
             */

            public String headImg;
            public int envScore;
            public String createTime;
            public int serScore;
            public String nickname;
            public int id;
            public int userId;
            public String content;
            public int upScore;
            public List<String> imgList;
        }
    }
}
