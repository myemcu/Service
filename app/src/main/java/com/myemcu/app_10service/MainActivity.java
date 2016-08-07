package com.myemcu.app_10service;

import com.myemcu.app_10service.MyService.MyBinder; // Alt+Enter(选择local package)

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mStart;
    private Button mStop;
    private Button mBind;
    private Button mUnbind;
    private Button mGetData;

    private MyBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStart = (Button) findViewById(R.id.start);
        mStop = (Button) findViewById(R.id.stop);

        mBind = (Button) findViewById(R.id.bind);
        mUnbind = (Button) findViewById(R.id.unBind);

        mGetData = (Button) findViewById(R.id.getData);

        final TextView txt = (TextView) findViewById(R.id.txt);

        final Intent intent = new Intent();
        intent.setAction("com.myemcu.app_10service.MyService");

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
                txt.setText("Service已启动");
            }
        });

        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
                txt.setText("Service已停止");
            }
        });

        mBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, conn, Service.BIND_AUTO_CREATE);
                txt.setText("Service已绑定");
            }
        });

        mUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
                txt.setText("Service已解绑");
            }
        });

        mGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"后台值："+myBinder.getCount(), Toast.LENGTH_SHORT).show();
                txt.setText("后台数据获取");
            }
        });
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("MyService","--onServiceConnected()被调用--");

            myBinder=(MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("MyService","--onServiceDisconnected()被调用--");
        }
    };
}
