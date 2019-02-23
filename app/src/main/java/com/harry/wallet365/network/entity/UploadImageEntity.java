package com.harry.wallet365.network.entity;

/**
 * Created by Harry on 2019/2/22.
 */
public class UploadImageEntity {

    /**
     * msg :
     * code : 200
     * data : {"url":"/upload/1.jpg"}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * url : /upload/1.jpg
         */

        public String url;
    }
}
