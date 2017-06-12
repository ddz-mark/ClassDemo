package com.markable.classdemo.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.orhanobut.logger.Logger;

/**
 * Created by Dudaizhong on 2016/9/13.
 * 继承自Application,封装
 */

public class BaseApplication extends Application {

    public static String cacheDir;
    public static Context context;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();

        getScreenSize();
        //初始化Logger
        Logger.init(getPackageName()).hideThreadInfo();
//        //初始化SP
//        SharedPreferencesUtil.init(this);
//        //初始化内存泄露
//        LeakCanary.install(this);
//        //界面卡顿检测
//        BlockCanary.install(this, new AppBlockCanaryContext()).start();
        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        if (getApplicationContext().getExternalCacheDir() != null) {
            cacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }

    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static Context getAppContext() {
        return context;
    }

    public static String getCachedir(){
        return cacheDir;
    }

}
