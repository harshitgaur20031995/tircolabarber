package com.example.triolabarberhairstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Servizi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servizi);
        getSupportActionBar().hide();
    }

    public void homepr(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    public void goGalleria(View view) {
        startActivity(new Intent(getApplicationContext(),Galleria.class));
        finish();
    }
}