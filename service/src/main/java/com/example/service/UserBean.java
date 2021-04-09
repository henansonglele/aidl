package com.example.service;


import android.os.Parcel;
import android.os.Parcelable;

public class UserBean implements Parcelable {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    int age;
    int sex;

    UserBean(String name,int age,int sex){
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeInt(this.sex);
    }

    protected UserBean(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
        this.sex = in.readInt();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {return new UserBean(source);}

        @Override
        public UserBean[] newArray(int size) {return new UserBean[size];}
    };
}

