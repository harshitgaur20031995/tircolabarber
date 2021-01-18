package com.example.triolabarberhairstyle.models;

import com.google.gson.Gson;

public class Emanuele {




    String month;
    String date;
    String pos;
    String userName;
    boolean Isbooked = false;
    String barber;

    public Emanuele(boolean isbooked, String month, String date, String pos,String userName,String barber) {
        Isbooked = isbooked;
        this.month = month;
        this.date = date;
        this.pos = pos;
        this.userName =userName;
        this.barber = barber;
    }


    public String getBarber() {
        return barber;
    }

    public void setBarber(String barber) {
        this.barber = barber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public boolean isIsbooked() {
        return Isbooked;
    }

    public void setIsbooked(boolean isbooked) {
        Isbooked = isbooked;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
