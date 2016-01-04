package com.psykar.cookiemanager;

import android.content.Context;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

import android.webkit.CookieManager;
import android.webkit.ValueCallback;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CookieManagerModule extends ReactContextBaseJavaModule {

    private Context context;
    private CookieManager cookieManager;
    private String url;

    public CookieManagerModule(ReactApplicationContext reactContext) {
        super(reactContext);

        this.context = (Context) reactContext;
        this.cookieManager = CookieManager.getInstance();
    }

    @Override
    public String getName() {
        return "RNCookieManagerAndroid";
    }

    @ReactMethod
    public void setUrl(String url) {
        this.url = url;
    }

    @ReactMethod
    public void set(ReadableMap cookie, final Callback callback) throws Exception {
        if (this.url == null) {
            throw new Exception("Must set url first.");
        }
        ValueCallback newCallback = new ValueCallback() {
            @Override
            public void onReceiveValue(Object value) {
                callback.invoke(value);
            }
        };

        this.cookieManager.setCookie(this.url, cookie.getString("value"), newCallback);
    }

    @ReactMethod
    public void getAll(Callback callback) throws Exception {
        throw new Exception("Cannot get all cookies on android, try getCookieHeader(url)");
    }

    @ReactMethod
    public void getCookieHeader(String url, Callback callback) throws Exception {
        String cookieString = this.cookieManager.getCookie(url);
        callback.invoke(cookieString);
    }

    @ReactMethod
    public void clearAll(final Callback callback) {


        ValueCallback newCallback = new ValueCallback() {
            @Override
            public void onReceiveValue(Object value) {
                callback.invoke(value);
            }
        };

        this.cookieManager.removeAllCookies(newCallback);
    }
}