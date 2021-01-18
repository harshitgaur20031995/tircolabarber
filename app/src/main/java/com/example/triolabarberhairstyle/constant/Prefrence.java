package com.example.triolabarberhairstyle.constant;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/*
 *   TODO - Created By HarshIt ......harshitr0025@gmail.com
 * */
public class Prefrence {

    private static Prefrence preferences = null;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor editor;
    private String Name = "Name";
    private String Email = "Email";
    private String Mobile = "Mobile";


    public Prefrence(Context context) {
        setmPreferences(PreferenceManager.getDefaultSharedPreferences(context));
    }


    public SharedPreferences getmPreferences() {
        return mPreferences;
    }

    public void setmPreferences(SharedPreferences mPreferences) {
        this.mPreferences = mPreferences;
    }


    public static Prefrence getActiveInstance(Context context) {
        if (preferences == null) {
            preferences = new Prefrence(context);
        }
        return preferences;
    }

    public String getName() {
        return mPreferences.getString(this.Name, "");
    }

    public void setName(String mobilenumber) {
        editor = mPreferences.edit();
        editor.putString(this.Name, mobilenumber);
        editor.commit();
    }

    public void setEmail(String LaunchMode) {
        editor = mPreferences.edit();
        editor.putString(this.Email, LaunchMode);
        editor.commit();
    }

    public String getEmail() {
        return mPreferences.getString(this.Email, "");
    }


    public void setMobile(String LaunchMode) {
        editor = mPreferences.edit();
        editor.putString(this.Mobile, LaunchMode);
        editor.commit();
    }

    public String getMobile() {
        return mPreferences.getString(this.Mobile, "");
    }


}
