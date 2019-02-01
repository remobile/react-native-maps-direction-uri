package com.remobile.mapsdirectionuri;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Callback;

import java.util.List;
import java.util.Locale;


public class MapsDirectionUriModule extends BaseModule{
    private ReactApplicationContext reactContext;

    public MapsDirectionUriModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    public String getName() {
        return "MapsDirectionUri";
    }

    private boolean isInstallApp(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager

        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName.toLowerCase(Locale.ENGLISH);
                if (pn.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @ReactMethod
    public void startDirection(ReadableMap option, Callback callback) {
        Log.i("----------------startDirection", String.valueOf(option));
        double startLatitude = option.getDouble("startLatitude");
        double startLongitude = option.getDouble("startLongitude");
        double endtLatitude = option.getDouble("endLatitude");
        double endLongitude = option.getDouble("endLongitude");
        String type = option.getString("type");
        if (type.equals("baidu")) {
            String uri ="http://api.map.baidu.com/direction?origin=latlng:%1$s,%2$s|name:%3$s" +
                    "&destination=latlng:%4$s,%5$s|name:%6$s&mode=driving&region=贵阳&output=html";
            if (isInstallApp(this.reactContext,"com.baidu.BaiduMap")) {
                uri = "baidumap://map/direction?origin=latlng:%1$s,%2$s|name:%3$s" +
                               "&destination=latlng:%4$s,%5$s|name:%6$s&mode=driving#Intent;" +
                               "scheme=bdapp;package=com.baidu.BaiduMap;end";
            }
            Uri mapUri = Uri.parse(String.format(uri, startLatitude, startLongitude, "我的位置", endtLatitude, endLongitude, "目的地"));
            Log.i("----------------mapUri", String.valueOf(mapUri));
            try{
                Intent loction = new Intent(Intent.ACTION_VIEW, mapUri);
                this.getCurrentActivity().startActivity(loction);
                callback.invoke(true);
            }catch (ActivityNotFoundException a){
                Log.i("----------------a.getMessage()", a.getMessage());
                callback.invoke(false);
            }
        } else if (type.equals("iosa")) {
            String uri = "http://uri.amap.com/navigation?from=%1$s,%2$s,%3$s&to=%4$s,%5$s,%6$s&mode=car&policy=1&src=mypage&coordinate=gaode&callnative=1";
            Uri mapUri = Uri.parse(String.format(uri,  startLongitude, startLatitude, "我的位置", endLongitude, endtLatitude, "目的地"));
            Log.i("----------------mapUri", String.valueOf(mapUri));
            try{
                Intent loction = new Intent(Intent.ACTION_VIEW, mapUri);
                this.getCurrentActivity().startActivity(loction);
                callback.invoke(true);
            }catch (ActivityNotFoundException a){
                Log.i("----------------a.getMessage()", a.getMessage());
                callback.invoke(false);
            }
        } else if (type.equals("google")) {
            String uri = "http://maps.google.com/maps?saddr=%1$s,%2$s&daddr=%3$s,%4$s&dirflg=d";
            Uri mapUri = Uri.parse(String.format(uri,  startLatitude, startLongitude, endtLatitude, endLongitude));
            Log.i("----------------mapUri", String.valueOf(mapUri));
            try{
                Intent loction = new Intent(Intent.ACTION_VIEW, mapUri);
                this.getCurrentActivity().startActivity(loction);
                callback.invoke(true);
            }catch (ActivityNotFoundException a){
                Log.i("----------------a.getMessage()", a.getMessage());
                callback.invoke(false);
            }
        } else if (type.equals("qq")) {
            String uri = "http://apis.map.qq.com/uri/v1/routeplan?type=drive&coord_type=1&from=%1$s&fromcoord=%2$s,%3$s&to=%4$s&tocoord=%5$s,%6$s&policy=0&referer=AEJBZ-TAICD-44F4N-PZTHZ-JNEAO-VCBMX";
            if (isInstallApp(this.reactContext,"com.tencent.map")) {
                uri = "qqmap://map/routeplan?type=drive&coord_type=1&from=%1$s&fromcoord=%2$s,%3$s&to=%4$s&tocoord=%5$s,%6$s&policy=0&referer=AEJBZ-TAICD-44F4N-PZTHZ-JNEAO-VCBMX";
            }
            Uri mapUri = Uri.parse(String.format(uri, "我的位置", startLatitude, startLongitude, "目的地",  endtLatitude, endLongitude));
            Log.i("----------------mapUri", String.valueOf(mapUri));
            try{
                Intent loction = new Intent(Intent.ACTION_VIEW, mapUri);
                this.getCurrentActivity().startActivity(loction);
                callback.invoke(true);
            }catch (ActivityNotFoundException a){
                Log.i("----------------a.getMessage()", a.getMessage());
                callback.invoke(false);
            }
        }
    }
}
