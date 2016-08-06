package com.myemcu.app_10service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    public MyService() { //空构造器
    }

    @Override
    // 本案例不会运行，但必须写，不然报错。
    public IBinder onBind(Intent intent) {
        Log.d("MyService","--onBind()被调用--");
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("MyService","--onCreate()被调用--");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d("MyService","--onDestroy()被调用--");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","--onStartCommand()被调用--");
        return super.onStartCommand(intent, flags, startId);
    }
}
