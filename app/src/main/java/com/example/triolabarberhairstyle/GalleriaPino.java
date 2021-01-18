package com.example.triolabarberhairstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class GalleriaPino extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleria_pino);
        getSupportActionBar().hide();

        ImageSlider imageSlider = findViewById(R.id.slider1);


        List<SlideModel> slideModels=new ArrayList<>();

        slideModels.add(new SlideModel("https://i.ibb.co/ZfGvGym/949d0e10-7cb5-44bd-938d-b75a1cc5f9ed.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/b6Pzx8m/aec6868c-325d-42f9-b1f6-e181a00d02bf.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/C0SzMV6/1d9c7b5d-c909-4219-8b0a-1725f5466d6c.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/Wg2H9Vw/eb126b3f-e73d-45ee-a98b-fe1d1eba8f25.jpg"));

        slideModels.add(new SlideModel("https://i.ibb.co/s2VmQFr/7dd37f1c-6949-4904-8164-4b2b3ed960da.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/yN8KR9F/1a11c2c7-58d6-4732-ab97-35a2afde574b.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/p2WG7F6/f0148db8-aaa7-485f-9ece-ce05e00b6459.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/tXP37Kt/b5e4dfc6-7186-4a2b-8519-c17127d71b52.jpg"));
      //  slideModels1.add(new SlideModel("https://i.ibb.co/6RN381X/9fb507f8-8393-48f1-888b-ac8b674a7599.jpg"));
     //   slideModels1.add(new SlideModel("https://i.ibb.co/4Zdn67R/7e2247e1-eb55-4917-bfa5-ad93742702f1.jpg"));
     //   slideModels1.add(new SlideModel("https://i.ibb.co/zmZbQd5/22f97562-e730-4435-b6dd-841baeb51112.jpg"));


        imageSlider.setImageList(slideModels,true);



    }
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(),Galleria.class));
        finish();
    }
}