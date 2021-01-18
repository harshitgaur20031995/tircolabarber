package com.example.triolabarberhairstyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RecensioneEmanuele extends AppCompatActivity {
    TextView feedback;
    ImageView facebook,instagram;
    EditText feed;
    RatingBar stelle;
    Button button;

    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recensione_emanuele);
        getSupportActionBar().hide();

        feedback = findViewById(R.id.textView4);
        stelle = findViewById(R.id.ratingBar3);
        feed = findViewById(R.id.editTextTextMultiLine2);
        button = findViewById(R.id.button);
        facebook = findViewById(R.id.imageView6);
        instagram = findViewById(R.id.imageView7);
        mFirestore = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        stelle.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating==1){
                    feedback.setText("Disastro");
                    ratingBar.setNumStars(1);
                }
                else if(rating==2){
                    feedback.setText("Non Male");
                    ratingBar.setNumStars(2);
                }
                else if(rating==3){
                    feedback.setText("OK");
                    ratingBar.setNumStars(3);
                }
                else if(rating==4){
                    feedback.setText("Bene");
                    ratingBar.setNumStars(4);
                }
                else if(rating==5){
                    feedback.setText("Ottimo");
                    ratingBar.setNumStars(5);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(user != null) {
                    String name = user.getDisplayName();
                    String email = user.getEmail();
                    String uid = user.getUid();

                    int voto = stelle.getNumStars();
                    String feedback = feed.getText().toString();
                    Map<String, String> userMap = new HashMap<>();

                    userMap.put("Name", name);
                    userMap.put("Email",email);
                    userMap.put("Rating", String.valueOf(voto));
                    userMap.put("Feedback", feedback);
                    userMap.put("UID",uid);

                    mFirestore.collection("Feedback-Emanuele").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(RecensioneEmanuele.this, "Feedback Inviato", Toast.LENGTH_SHORT).show();
                            stelle.setNumStars(5);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String error = e.getMessage();
                            Toast.makeText(RecensioneEmanuele.this, "Errore" + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFacebook("110522157384878");
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/triolaemanuelebarber/");
                Intent instagram = new Intent(Intent.ACTION_VIEW,uri);
                instagram.setPackage("com.instagram.android");
                try{
                    startActivity(instagram);
                } catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/triolaemanuelebarber/")));
                }
            }
        });
    }
    public void goRecensione(View view) {
        startActivity(new Intent(getApplicationContext(),Recensioni.class));
        finish();
    }
    private void goToFacebook(String id){
        try{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" +id));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/" +id));
            startActivity(intent);
        }
    }

}