package com.example.triolabarberhairstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class GalleriaEmanuele extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleria_emanuele);
        getSupportActionBar().hide();

        ImageSlider imageSlider = findViewById(R.id.slider);
        ImageSlider imageSlider1 = findViewById(R.id.slider1);


        List<SlideModel> slideModels=new ArrayList<>();
        List<SlideModel> slideModels1=new ArrayList<>();

        slideModels.add(new SlideModel("https://i.ibb.co/F4250zG/1d676e9f-b165-4618-9eb6-b6f953e43638.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/MNxVPBt/0f7e619b-4ea0-46e5-99a8-aa418354e604.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/HVCtcbw/860276f8-580d-406c-8108-8d7b4765c55c.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/6gLbpvb/7346af7a-af3e-418e-8086-45adcb1c7530.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/NWNj4j3/e5ded1be-8cba-4d5d-ae7c-7a03483540ec.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/Gv0cfdq/dbd88d43-41aa-4902-afaa-7e3e5ecb7a42.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/9qN98st/d5e57195-22aa-4846-997a-2f56a6e95e76.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/KbNxh4x/barba.jpg"));

        slideModels1.add(new SlideModel("https://i.ibb.co/NtWT55S/bbdd43e9-18d1-4e94-b0a5-09d4ec410d9c.jpg"));
        slideModels1.add(new SlideModel("https://i.ibb.co/cvfkxgh/b05a9996-3972-4b20-b5cb-7a1d8688524c.jpg"));
        slideModels1.add(new SlideModel("https://i.ibb.co/nbPrDmq/25216ce6-5d1c-435a-bd66-f9f945eef488.jpg"));
        slideModels1.add(new SlideModel("https://i.ibb.co/NrdFm0P/6465aadc-9240-4168-ba93-3f52d326e860.jpg"));
        slideModels1.add(new SlideModel("https://i.ibb.co/6RN381X/9fb507f8-8393-48f1-888b-ac8b674a7599.jpg"));
        slideModels1.add(new SlideModel("https://i.ibb.co/4Zdn67R/7e2247e1-eb55-4917-bfa5-ad93742702f1.jpg"));
        slideModels1.add(new SlideModel("https://i.ibb.co/zmZbQd5/22f97562-e730-4435-b6dd-841baeb51112.jpg"));


        imageSlider.setImageList(slideModels,true);
        imageSlider1.setImageList(slideModels1,true);
    }

    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(),Galleria.class));
        finish();
    }
}