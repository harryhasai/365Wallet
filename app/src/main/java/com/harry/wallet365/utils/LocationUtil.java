package com.harry.wallet365.utils;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

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
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    public static void destroyLocation(){
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
        }
    }
}
