package com.example.triolabarberhairstyle.models;

import com.google.gson.Gson;

public class EmanBook {


    String username;
    String bookingtime;
    String useremail;
    String mobile;
    String month;
    String date;
    String position;
    String bookingDate;




    public EmanBook(String username, String bookingtime, String useremail, String mobile, String month, String date, String position,String bookingDate) {
        this.username = username;
        this.bookingtime = bookingtime;
        this.useremail = useremail;
        this.mobile = mobile;
        this.month = month;
        this.date = date;
        this.position = position;
        this.bookingDate = bookingDate;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookingtime() {
        return bookingtime;
    }

    public void setBookingtime(String bookingtime) {
        this.bookingtime = bookingtime;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
