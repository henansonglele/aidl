package com.example.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.servicetestaidl.R;

public class CheckActivity extends Activity {

    private Button bindService,unbindService,addBtn,getUserName;
    private Button btn1,btn2,btn3;
    private TextView tvData;
    private MyAIDLService myAIDLService;


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAIDLService = MyAIDLService.Stub.asInterface(service);
            try {
                String str =  myAIDLService.getString();
                tvData.setText(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAIDLService = null;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        addBtn = (Button) findViewById(R.id.add);
        getUserName = (Button) findViewById(R.id.get_user_name);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });




        tvData = (TextView) findViewById(R.id.tv_data);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(myAIDLService == null)
                {
                    Toast.makeText(CheckActivity.this,"error",Toast.LENGTH_SHORT).show();
                    return;
                }

                String addStr = "";
                try {
                    addStr = myAIDLService.getAddStr();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                tvData.setText(addStr);
            }

        });
        getUserName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(myAIDLService == null)
                {
                    Toast.makeText(CheckActivity.this,"error",Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    UserBean user = myAIDLService.getUser();
                    tvData.setText("name:"+user.getName()+"age:"+user.getAge()+"sex:"+user.getSex());

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * ????????????
         */
        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.service.MyService");
                //??? Android 5.0?????? ??????Intent????????????????????????????????????,????????????????????????Service????????????????????????
                intent.setPackage("com.example.service");
                bindService(intent, connection, BIND_AUTO_CREATE);

            }
        });

        /**
         * ????????????
         */
        unbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });
    }
}
