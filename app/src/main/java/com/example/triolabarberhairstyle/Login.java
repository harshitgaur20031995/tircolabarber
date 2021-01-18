package com.example.triolabarberhairstyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText Email, Password;
    Button LoginBtn;
    TextView CreateBtn, passdim;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        context = Login.this;
        Email = findViewById(R.id.mEmail1);
        Password = findViewById(R.id.mPassword1);
        progressBar = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();
        LoginBtn = findViewById(R.id.mLoginBtn1);
        CreateBtn = findViewById(R.id.textView8);
        passdim = findViewById(R.id.textView10);


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email Richiesta");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("Password Richiesta");
                    return;
                }

                if (password.length() < 6) {
                    Password.setError("La Password deve essere più di 6 caratteri");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                getLogin();

//                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()) {
//                            Toast.makeText(Login.this, "Accesso Eseguito",Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                        }else{
//                            Toast.makeText(Login.this, "Errore!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
//                        }
//
//                    }
//                });

            }
        });

        CreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register1.class));
            }
        });

        passdim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Password Dimenticata?");
                passwordResetDialog.setMessage("Scrivi qui la tua Mail per resettarla");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "Reset la tua mail", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Errore! Reset non disponibile" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                passwordResetDialog.create().show();

            }
        });
    }

    private void getLogin() {
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        User user = new User(email, password);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firestore.collection("users");
        Query query = reference.whereEqualTo("email", user.getEmail()).
                whereEqualTo("password", user.getPassword());
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                Log.v("logcheck", "onSuccess" + task.isSuccessful());
                for (DocumentSnapshot snapshot : task.getResult()) {
                    Log.v("logcheck", "onSuccess" + snapshot.getString("name"));
                    String name = snapshot.getString("name");
                    String email = snapshot.getString("email");
                    String mobile = snapshot.getString("mobile");
                    Prefrence.getActiveInstance(context).setName(name);
                    Prefrence.getActiveInstance(context).setEmail(email);
                    Prefrence.getActiveInstance(context).setMobile(mobile);
                    MoveScreen(name, email, mobile);
                }
                progressBar.setVisibility(View.GONE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v("logcheck", "onSuccess" + e.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void MoveScreen(String name, String email, String mobile) {
        if (!(name.equalsIgnoreCase(""))) {
            if (!(email.equalsIgnoreCase(""))) {
                if (!(mobile.equalsIgnoreCase(""))) {
                    startActivity(new Intent(context, MainActivity.class));
                }
            }
        } else {
            Email.setText("");
            Password.setText("");
            Toast.makeText(Login.this, "user not found", Toast.LENGTH_SHORT).show();
        }
    }
}