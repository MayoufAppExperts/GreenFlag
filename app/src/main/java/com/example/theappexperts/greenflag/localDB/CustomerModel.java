package com.example.theappexperts.greenflag.localDB;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by TheAppExperts on 22/09/2017.
 */

public class CustomerModel extends RealmObject {

    @Required
    String name, password, userName, age;
    byte[]imageInByte;


    public CustomerModel(String name, String password, String userName, String age, byte [] imageInByte) {
        this.name = name;
        this.password = password;
        this.userName = userName;
        this.age= age;
        this.imageInByte = imageInByte;

    }

    public CustomerModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public byte[] getImageInByte() {
        return imageInByte;
    }

    public void setImageInByte(byte[] imageInByte) {
        this.imageInByte = imageInByte;
    }
}
