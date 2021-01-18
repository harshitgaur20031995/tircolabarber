package com.example.triolabarberhairstyle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toolbar;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CardView cardView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        ImageSlider imageSlider = findViewById(R.id.slider);
        cardView = findViewById(R.id.Card_negozio);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNegozio();
            }
        });

        List<SlideModel> slideModels=new ArrayList<>();

        slideModels.add(new SlideModel("https://i.postimg.cc/x1kjYzr6/7346af7a-af3e-418e-8086-45adcb1c7530.jpg"));
        slideModels.add(new SlideModel("https://i.postimg.cc/9FYgM82M/860276f8-580d-406c-8108-8d7b4765c55c.jpg"));
        slideModels.add(new SlideModel("https://i.postimg.cc/xCmtSgg9/0f7e619b-4ea0-46e5-99a8-aa418354e604.jpg"));

        imageSlider.setImageList(slideModels,true);

       // NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
       // NavigationUI.setupWithNavController(navigationView,navController);
    }

    public void logout1(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
    public void openNegozio(){
        Intent intent = new Intent(this, Negozio.class);
        startActivity(intent);
    }
    public void openPrenota(View view){
        startActivity(new Intent(getApplicationContext(),Barbieri.class));
        finish();
    }
    public void openServizi(View view){
        startActivity(new Intent(getApplicationContext(),Servizi.class));
        finish();
    }
    public void openGalleria(View view){
        startActivity(new Intent(getApplicationContext(),Galleria.class));
        finish();
    }
    public void openRecensioni(View view){
        startActivity(new Intent(getApplicationContext(),Recensioni.class));
        finish();
    }
    public void goShop(View view){
        startActivity(new Intent(getApplicationContext(),Shop.class));
        //startActivity(new Intent(getApplicationContext(),GridView.class));
        finish();
    }
}