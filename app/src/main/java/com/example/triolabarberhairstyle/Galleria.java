package com.example.triolabarberhairstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Galleria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleria);
        getSupportActionBar().hide();
    }

    public void homepr(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    public void OpenGalleriaEmanuele(View view){
        startActivity(new Intent(getApplicationContext(),GalleriaEmanuele.class));
        finish();
    }
    public void OpenGalleriaPino(View view){
        startActivity(new Intent(getApplicationContext(),GalleriaPino.class));
        finish();
    }
}