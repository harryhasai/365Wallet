package com.harry.wallet365.network.entity;

/**
 * Created by Harry on 2019/2/22.
 */
public class GetAgreementEntity {

    /**
     * msg :
     * code : 200
     * data : {"info":"<div>365钱包<\/div>"}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * info : <div>365钱包</div>
         */

        public String info;
    }
}
