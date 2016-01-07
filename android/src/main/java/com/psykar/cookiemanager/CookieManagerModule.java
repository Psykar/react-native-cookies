package com.psykar.cookiemanager;

import android.content.Context;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Callback;

import android.webkit.CookieManager;
import android.webkit.ValueCallback;

import java.lang.Exception;


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
    public void blahblahblah(String url) {
        return;
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
        if (this.url == null) {
            throw new Exception("Must set url first.");
        }
        callback.invoke(this.cookieManager.getCookie(this.url));
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