// MyAIDLService.aidl
package com.example.service;
import com.example.service.UserBean;

// Declare any non-default types here with import statements

interface MyAIDLService {
    //获取String数据
    String getString();
    String getAddStr();
    UserBean getUser();

}
