package com.example.triolabarberhairstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Recensioni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recensioni);
        getSupportActionBar().hide();
    }

    public void gohome3(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    public void openRecEma(View view) {
        startActivity(new Intent(getApplicationContext(),RecensioneEmanuele.class));
        finish();
    }
    public void openRecPino(View view) {
        startActivity(new Intent(getApplicationContext(),RecensioniPino.class));
        finish();
    }
}