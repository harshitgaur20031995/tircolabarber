package com.example.triolabarberhairstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Negozio extends AppCompatActivity {

    private ImageView maps,home1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negozio2);
        getSupportActionBar().hide();

        maps = findViewById(R.id.imageView4);
        home1 = findViewById(R.id.home2);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("google.navigation:q=40.845069,14.208318&mode=d"));
                intent.setPackage("com.google.android.apps.maps");

                if(intent.resolveActivity(getPackageManager()) != null) {

                    startActivity(intent);
                }
            }
        });
        home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}