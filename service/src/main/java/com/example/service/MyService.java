package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    int  count = 0 ;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Mybind();
    }

    class Mybind extends MyAIDLService.Stub {

        @Override
        public String getString() throws RemoteException {
            String string = "我是从服务起返回的";
            return string;
        }

        @Override
        public String getAddStr() throws RemoteException {
            count ++;
            return String.valueOf(count);
        }

        @Override
        public  UserBean getUser() throws RemoteException {
            return new UserBean("张三",20,1);
        }
    }


}
