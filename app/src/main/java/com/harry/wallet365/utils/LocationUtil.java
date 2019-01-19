package com.harry.wallet365.utils;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;

/**
 * Created by Harry on 2018/11/12.
 */
public class LocationUtil {

    private static AMapLocationClient locationClient;

    /**
     * 开始定位
     */
    public static void startLocation(Context context, AMapLocationListener locationListener) {
        locationClient = new AMapLocationClient(context);
        locationClient.setLocationListener(locationListener);
        locationClient.startLocation();
    }

    /**
     * 停止定位
     */
    public static void stopLocation() {
        if (locationClient != null) {
            locationClient.stopLocation();
        }
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    public static void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
        }
    }

    /**
     * 根据城市名称查询当前城市的经纬度
     *
     * @param context  上下文
     * @param cityName 城市名称
     * @param listener 查询结果的回调
     */
    public static void getLocationFromCityName(Context context, String cityName, GeocodeSearch.OnGeocodeSearchListener listener) {
        //地理编码与逆地理编码类。 地理编码又称地址匹配，指的是从已知的地址描述到对应的经纬 度坐标的转换，
        // 即根据地址信息，获取地址所对应的点坐标等。 逆地理编码即地址解析服务，
        // 具体是指从已知的经纬度坐标到对 应的地址描述（如省市、街区、楼层、房间等）的转换。
        // 通过该类提供的方法可获取对应位置的地理描述，分为3种地物 类型：交叉路口、兴趣点、道路。
        GeocodeSearch geocodeSearch = new GeocodeSearch(context);
        geocodeSearch.setOnGeocodeSearchListener(listener);
        //locationName - 查询地址。
        //city - 可选值：cityname（中文或中文全拼）、citycode、adcode。如传入null或空字符串则为“全国”，
        GeocodeQuery geocodeQuery = new GeocodeQuery(cityName.trim(), null);
        //地理编码查询的异步处理调用。 根据给定的地理名称和查询城市返回地理编码的结果列表。地理编码返回结果集默认最大返回数目为10。
        geocodeSearch.getFromLocationNameAsyn(geocodeQuery);
    }
}
