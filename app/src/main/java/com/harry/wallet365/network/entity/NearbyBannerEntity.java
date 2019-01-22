package com.harry.wallet365.network.entity;

import java.util.List;

/**
 * Created by Harry on 2019/1/21.
 */
public class NearbyBannerEntity {

    /**
     * msg :
     * code : 200
     * data : [{"img":"http://www.baidu.com","location":"113.367163,23.129941","id":19,"url":1},{"img":"20181225/201812251616492336FLVgkBI.jpg","location":"113.367163,23.129941","id":20,"url":2}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * img : http://www.baidu.com
         * location : 113.367163,23.129941
         * id : 19
         * url : 1
         */

        public String img;
        public String location;
        public int id;
        public int url;
    }
}
