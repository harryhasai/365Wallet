package com.harry.wallet365.network.entity;

/**
 * Created by Harry on 2019/1/18.
 */
public class ShopDetailEntity {

    /**
     * msg :
     * code : 200
     * data : {"score":4,"address":"天河大道","headImg":"http://150.109.16.41:8086/upload/20190111/20190111103440242OAbKmzGx.jpg","distance":500,"name":"广西手工艺专营店","about":"<p><span style=\"color: rgb(51, 51, 51); font-family: arial, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);\">手工艺是指以手工劳动进行<\/span><a href=\"https://baike.so.com/doc/5386375-5622833.html\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);\">制作<\/a><span style=\"color: rgb(51, 51, 51); font-family: arial, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);\">的具有独特艺术风格的工艺美术。有别于以大工业机械化方式批量生产规格化日用工艺品的工艺美术。&nbsp;<\/span><a href=\"https://baike.so.com/doc/2732917-2884668.html\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);\">手工艺品<\/a><span style=\"color: rgb(51, 51, 51); font-family: arial, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);\">指的是纯手工或借助工具制作的产品。可以使用机械工具，但前提是<\/span><a href=\"https://baike.so.com/doc/739303-782617.html\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);\">工艺师<\/a><span style=\"color: rgb(51, 51, 51); font-family: arial, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);\">直接的手工作业仍然为成品的最主要来源。手工艺品由自然材料制成，能够无限量制作。此类产品实用、美观，具有艺术性和创新性，能传达文化内涵，富有装饰性、功能性和传统性，同时具有宗教或社会象征意义和重要性。&quot;工艺&quot;一词具有多重含义，其中重要的内涵之一，是指一种特殊的工艺技能，尤其指手工艺术的诸多门类。<\/span><\/p>","tel":"18666864772","id":1,"workTime":"10:0022:00"}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * score : 4
         * address : 天河大道
         * headImg : http://150.109.16.41:8086/upload/20190111/20190111103440242OAbKmzGx.jpg
         * distance : 500
         * name : 广西手工艺专营店
         * about : <p><span style="color: rgb(51, 51, 51); font-family: arial, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);">手工艺是指以手工劳动进行</span><a href="https://baike.so.com/doc/5386375-5622833.html" target="_blank" style="margin: 0px; padding: 0px; color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);">制作</a><span style="color: rgb(51, 51, 51); font-family: arial, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);">的具有独特艺术风格的工艺美术。有别于以大工业机械化方式批量生产规格化日用工艺品的工艺美术。&nbsp;</span><a href="https://baike.so.com/doc/2732917-2884668.html" target="_blank" style="margin: 0px; padding: 0px; color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);">手工艺品</a><span style="color: rgb(51, 51, 51); font-family: arial, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);">指的是纯手工或借助工具制作的产品。可以使用机械工具，但前提是</span><a href="https://baike.so.com/doc/739303-782617.html" target="_blank" style="margin: 0px; padding: 0px; color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, sans-serif; font-size: 14px; text-indent: 28px; white-space: normal; background-color: rgb(255, 255, 255);">工艺师</a><span style="color: rgb(51, 51, 51); font-family: arial, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);">直接的手工作业仍然为成品的最主要来源。手工艺品由自然材料制成，能够无限量制作。此类产品实用、美观，具有艺术性和创新性，能传达文化内涵，富有装饰性、功能性和传统性，同时具有宗教或社会象征意义和重要性。&quot;工艺&quot;一词具有多重含义，其中重要的内涵之一，是指一种特殊的工艺技能，尤其指手工艺术的诸多门类。</span></p>
         * tel : 18666864772
         * id : 1
         * workTime : 10:0022:00
         */

        public int score;
        public String address;
        public String headImg;
        public int distance;
        public String name;
        public String about;
        public String tel;
        public int id;
        public String workTime;
    }
}
