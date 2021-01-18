 package com.example.triolabarberhairstyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triolabarberhairstyle.constant.Prefrence;
import com.example.triolabarberhairstyle.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

 public class Register1 extends AppCompatActivity {
    EditText Name3,Email3,Password3,Phone3;
    Button RegisterBtn3;
    TextView LoginBtn3;
    FirebaseAuth fAuth3;
    ProgressBar progressBar3;
    FirebaseFirestore fStore3;
    String userID3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        getSupportActionBar().hide();

        Name3 = findViewById(R.id.mName);
        Email3 = findViewById(R.id.mEmail);
        Password3 = findViewById(R.id.mPassword);
        Phone3 = findViewById(R.id.mPhone);
        RegisterBtn3 = findViewById(R.id.mRegisterBtn);
        LoginBtn3 = findViewById(R.id.mLoginBtn);

        fAuth3 = FirebaseAuth.getInstance();
        fStore3 = FirebaseFirestore.getInstance();
        progressBar3 = findViewById(R.id.progressBar);

        if(fAuth3.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        RegisterBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email3.getText().toString().trim();
                String password = Password3.getText().toString().trim();
                String fullName = Name3.getText().toString();
                String phone = Phone3.getText().toString();



                progressBar3.setVisibility(View.VISIBLE);


                setRegister();

//                fAuth3.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(Register1.this, "Utente Creato",Toast.LENGTH_SHORT).show();
//                            userID3 = fAuth3.getCurrentUser().getUid();
//                            DocumentReference documentReference = fStore3.collection("utenti").document(userID3);
//                            Map<String, Object> user = new HashMap<>();
//                            user.put("Name",fullName);
//                            user.put("email",email);
//                            user.put("phone",phone);
//                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    Log.d("TAG", "utente creato correttamente"+userID3);
//                                }
//                            });
//                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                        }else {
//                            Toast.makeText(Register1.this, "Errore!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            progressBar3.setVisibility(View.GONE);
//                        }
//                    }
//                });
            }
        });

        LoginBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });


    }

     private void setRegister() {
        String name = Name3.getText().toString().toLowerCase();
        String email = Email3.getText().toString();
        String mobile = Phone3.getText().toString();
        String password = Password3.getText().toString();
         User user = new User(name,mobile,email,password);
        FirebaseFirestore  firestore = FirebaseFirestore.getInstance();
        firestore.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                progressBar3.setVisibility(View.GONE);
                Log.v("logcheck","on successs"+documentReference.getId());
                Prefrence.getActiveInstance(Register1.this).setMobile(mobile);
                Prefrence.getActiveInstance(Register1.this).setName(name);
                Prefrence.getActiveInstance(Register1.this).setEmail(email);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v("logcheck","on failure"+e.getMessage());
            }
        });
     }
 }