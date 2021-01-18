package com.example.triolabarberhairstyle.models;

import java.util.List;

public class BarberModel {

    String time;
    String user_id;
    boolean Isbooked = false;

    public BarberModel(String time, String user_id, boolean isbooked) {
        this.time = time;
        this.user_id = user_id;
        Isbooked = isbooked;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
