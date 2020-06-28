package com.ll.common.app;

import android.app.Application;
import android.content.Context;

public abstract class BaseAppcation  extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        initOtherConfig();
    }

    protected abstract void initOtherConfig();

    public static Context getAppContext(){
        return context;
    }
}
