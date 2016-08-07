package com.myemcu.app_10service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private int cnt=0;
    private boolean quit = false;

    public MyService() { //空构造器
    }

    //----------------------------------------------------------------------------------------------

    private MyBinder myBinder = new MyBinder();

    @Override
    // 本案例不会运行，但必须写，不然报错。
    // 本方法用于后来的通信。
    public IBinder onBind(Intent intent) {
        Log.d("MyService","--onBind()被调用--");
        return myBinder;    // 为其手打出对象
    }

    // 自定义内部类MyBinder
    class MyBinder extends Binder{
        public MyBinder() {
            Log.d("MyService", "--构造器：MyBinder()被调用--");
        }
        public int getCount() {
            return cnt;
        }
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onCreate() {
        Log.d("MyService","--onCreate()被调用--");
        super.onCreate();

        new Thread() {
            public void  run() {
                while (!quit) {
                    try {
                            Thread.sleep(500); // 休眠0.5s
                            cnt++;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    //----------------------------------------------------------------------------------------------

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

    @Override
    public boolean onUnbind(Intent intent) { // 绑定
        Log.d("MyService","--onUnbind()被调用--");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) { // 解绑
        Log.d("MyService","--onRebind()被调用--");
        super.onRebind(intent);
    }

}
