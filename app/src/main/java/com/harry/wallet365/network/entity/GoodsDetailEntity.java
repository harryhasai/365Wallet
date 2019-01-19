package com.harry.wallet365.network.entity;

/**
 * Created by Harry on 2019/1/19.
 */
public class GoodsDetailEntity {

    /**
     * msg :
     * code : 200
     * data : {"headImg":"20190111/20190111102704995iKCkZGGF.jpg","price":10,"name":"番薯","about":"<p style=\"margin-top: 0px; margin-bottom: 15px; padding: 0px; line-height: 24px; text-indent: 2em; color: rgb(51, 51, 51); font-family: arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\">番薯(学名:<em style=\"margin: 0px; padding: 0px;\">Ipomoea batatas<\/em>&nbsp;(L.) Lam.)别称甘储、甘薯、朱薯、金薯、番茹、红山药、玉枕薯、山芋、地瓜、山药、甜薯、红薯、红苕、白薯、阿鹅、萌番薯。一年生草本植物，地下部分具圆形、椭圆形或纺锤形的块根，茎平卧或上升，偶有缠绕，多分枝，叶片形状、颜色常因品种不同而异，通常为宽卵形，叶柄长短不一，聚伞花序腋生，蒴果卵形或扁圆形，种子1-4粒，通常2粒，无毛。<\/p><p style=\"margin-top: 0px; margin-bottom: 15px; padding: 0px; line-height: 24px; text-indent: 2em; color: rgb(51, 51, 51); font-family: arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\">番薯原产<a href=\"https://baike.so.com/doc/5349998-5585454.html\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(19, 110, 194); text-decoration-line: none;\">南美洲<\/a>及大、小安的列斯群岛，全世界的热带、亚热带地区广泛栽培，中国大多数地区普遍栽培。<\/p><p style=\"margin-top: 0px; margin-bottom: 15px; padding: 0px; line-height: 24px; text-indent: 2em; color: rgb(51, 51, 51); font-family: arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\">番薯是一种高产而适应性强的<a href=\"https://baike.so.com/doc/3617141-3802669.html\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(19, 110, 194); text-decoration-line: none;\">粮食作物<\/a>，与工农业生产和人民生活关系密切。块根除作主粮外，也是食品加工、淀粉和酒精制造工业的重要原料，根、茎、叶又是优良的饲料。<\/p><p><br/><\/p>","count":21,"id":1}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * headImg : 20190111/20190111102704995iKCkZGGF.jpg
         * price : 10
         * name : 番薯
         * about : <p style="margin-top: 0px; margin-bottom: 15px; padding: 0px; line-height: 24px; text-indent: 2em; color: rgb(51, 51, 51); font-family: arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">番薯(学名:<em style="margin: 0px; padding: 0px;">Ipomoea batatas</em>&nbsp;(L.) Lam.)别称甘储、甘薯、朱薯、金薯、番茹、红山药、玉枕薯、山芋、地瓜、山药、甜薯、红薯、红苕、白薯、阿鹅、萌番薯。一年生草本植物，地下部分具圆形、椭圆形或纺锤形的块根，茎平卧或上升，偶有缠绕，多分枝，叶片形状、颜色常因品种不同而异，通常为宽卵形，叶柄长短不一，聚伞花序腋生，蒴果卵形或扁圆形，种子1-4粒，通常2粒，无毛。</p><p style="margin-top: 0px; margin-bottom: 15px; padding: 0px; line-height: 24px; text-indent: 2em; color: rgb(51, 51, 51); font-family: arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">番薯原产<a href="https://baike.so.com/doc/5349998-5585454.html" target="_blank" style="margin: 0px; padding: 0px; color: rgb(19, 110, 194); text-decoration-line: none;">南美洲</a>及大、小安的列斯群岛，全世界的热带、亚热带地区广泛栽培，中国大多数地区普遍栽培。</p><p style="margin-top: 0px; margin-bottom: 15px; padding: 0px; line-height: 24px; text-indent: 2em; color: rgb(51, 51, 51); font-family: arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">番薯是一种高产而适应性强的<a href="https://baike.so.com/doc/3617141-3802669.html" target="_blank" style="margin: 0px; padding: 0px; color: rgb(19, 110, 194); text-decoration-line: none;">粮食作物</a>，与工农业生产和人民生活关系密切。块根除作主粮外，也是食品加工、淀粉和酒精制造工业的重要原料，根、茎、叶又是优良的饲料。</p><p><br/></p>
         * count : 21
         * id : 1
         */

        public String headImg;
        public int price;
        public String name;
        public String about;
        public int count;
        public int id;
    }
}
