package com.remobile.mapsdirectionuri;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Callback;


public class MapsDirectionUriModule extends BaseModule{
    private ReactApplicationContext reactContext;

    public MapsDirectionUriModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    public String getName() {
        return "MapsDirectionUri";
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
            String uri = "baidumap://map/direction?origin=latlng:%1$s,%2$s|name:%3$s" +
                    "&destination=latlng:%4$s,%5$s|name:%6$s&mode=driving#Intent;" +
                    "scheme=bdapp;package=com.baidu.BaiduMap;end";
            Uri mapUri = Uri.parse(String.format(uri, startLatitude, startLongitude, "我的位置", endtLatitude, endLongitude, "目的地"));
            Log.i("----------------mapUri", String.valueOf(mapUri));
            try{
                Intent loction = new Intent(Intent.ACTION_VIEW, mapUri);
                this.getCurrentActivity().startActivity(loction);
            }catch (ActivityNotFoundException a){
                Log.i("----------------a.getMessage()", a.getMessage());
                callback.invoke(false);
            }
        } else if (type.equals("iosa")) {
            String uri = "http://uri.amap.com/navigation?from=%1$s,%2$s,%3$s&to=%4$s,%5$s,%6$s&mode=car&policy=1&src=mypage&coordinate=gaode&callnative=0";

            Uri mapUri = Uri.parse(String.format(uri,  startLatitude, startLongitude, "我的位置", endtLatitude, endLongitude, "目的地"));
            Log.i("----------------mapUri", String.valueOf(mapUri));
            try{
                Intent loction = new Intent(Intent.ACTION_VIEW, mapUri);
                this.getCurrentActivity().startActivity(loction);
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
            }catch (ActivityNotFoundException a){
                Log.i("----------------a.getMessage()", a.getMessage());
                callback.invoke(false);
            }
        } else if (type.equals("qq")) {
            String uri = "http://apis.map.qq.com/uri/v1/routeplan?type=drive&from=%1$s&fromcoord=%2$s,%3$s&to=%4$s&tocoord=%5$s,%6$s&policy=0&referer=myapp";
            Uri mapUri = Uri.parse(String.format(uri, "我的位置", startLatitude, startLongitude, "目的地",  endtLatitude, endLongitude));
            Log.i("----------------mapUri", String.valueOf(mapUri));
            try{
                Intent loction = new Intent(Intent.ACTION_VIEW, mapUri);
                this.getCurrentActivity().startActivity(loction);
            }catch (ActivityNotFoundException a){
                Log.i("----------------a.getMessage()", a.getMessage());
                callback.invoke(false);
            }
        }
    }
}
