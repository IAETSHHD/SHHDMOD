package com.example.mohamed.shhd;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by mohamed alaa on 25/08/2015.
 */
public class item extends AppCompatActivity {
    public  String uid;
    private String country;
    private String capital;
    private int flag;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
