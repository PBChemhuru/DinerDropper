package com.example.dinerdropper.model;

import java.io.Serializable;

public class Orders implements Serializable
{
    private String Address,Date,Name,PhoneNumber,Time,Totalamount;
    public Orders(){}

    public Orders(String address, String date, String name, String phoneNumber, String time, String totalamount) {
        Address = address;
        Date = date;
        Name = name;
        PhoneNumber = phoneNumber;
        Time = time;
        Totalamount = totalamount;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTotalamount() {
        return Totalamount;
    }

    public void setTotalamount(String totalamount) {
        Totalamount = totalamount;
    }
}
