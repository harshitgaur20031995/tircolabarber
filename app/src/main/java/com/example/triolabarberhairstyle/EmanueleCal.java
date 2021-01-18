package com.example.triolabarberhairstyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.triolabarberhairstyle.constant.Prefrence;


import com.example.triolabarberhairstyle.models.BarberModel;
import com.example.triolabarberhairstyle.models.EmanBook;
import com.example.triolabarberhairstyle.models.Emanuele;
import com.example.triolabarberhairstyle.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class EmanueleCal extends AppCompatActivity {

    RecyclerView dataList;
    List<BarberModel> orari = new ArrayList<>();
    List<Emanuele> strings = new ArrayList<>();
    Adapter adapter;
    GridLayoutManager gridLayoutManager;

    private String month;
    private String dateA;
    private String position;
    Context context;
    Emanuele user;
    String barber = "emanuele";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emanuele_cal);
        getSupportActionBar().hide();
        context = EmanueleCal.this;
        dataList = findViewById(R.id.dataList);

        orari = new ArrayList<>();
        //  strings = Utils.getBookList();
       // Prefrence.getActiveInstance(context).setName("harshit");
        getData();

        orari.add(new BarberModel("8:00 - 8:45", "", false));
        orari.add(new BarberModel("8:45 - 9:30", "", false));
        orari.add(new BarberModel("9:30 - 10:15", "", false));
        orari.add(new BarberModel("10:15 - 11:00", "", false));


        orari.add(new BarberModel("11:00 - 11:45", "", false));
        orari.add(new BarberModel("11:45 - 12:30", "", false));
        orari.add(new BarberModel("12:30 - 13:15", "", false));
        orari.add(new BarberModel("13:15 - 14:00", "", false));
        orari.add(new BarberModel("14:00 - 14:45", "", false));
        orari.add(new BarberModel("14:45 - 15:30", "", false));
        orari.add(new BarberModel("15:30 - 16:15", "", false));
        orari.add(new BarberModel("16:15 - 17:00", "", false));
        orari.add(new BarberModel("17:00 - 17:45", "", false));
        orari.add(new BarberModel("17:45 - 18:30", "", false));
        orari.add(new BarberModel("18:30 - 19:15", "", false));
        orari.add(new BarberModel("19:15 - 20:00", "", false));

        gridLayoutManager =
                new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);


        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        // month = month_date.format(endDate.getTime());
        month = (String) android.text.format.DateFormat.format("MMMM", new Date());

        dateA = "" + endDate.get(Calendar.DATE);

        Log.v("endDate", "" + month);
        Log.v("endDate", "" + dateA);

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendar)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                month = "";
                dateA = "";
                SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
                month = month_date.format(date.getTime());
                dateA = "" + date.get(Calendar.DATE);
                Log.v("endDate", "" + month);
                Log.v("endDate", "" + dateA);
                setAdapter();
            }
        });


    }


    private void BookingSlot(String time) {
        String name = Prefrence.getActiveInstance(context).getName();
        String email = Prefrence.getActiveInstance(context).getEmail();
        String mobile = Prefrence.getActiveInstance(context).getMobile();
        String bookingDate = dateA + " " + month;
        EmanBook emanBook = new EmanBook(name, time, email, mobile, month.toLowerCase(), dateA, position, bookingDate);

        Log.v("bookingSlot", "" + emanBook.toString());

        checkWaiting(emanBook);
        // Booking(emanBook);
    }

    private void checkWaiting(EmanBook emanBook) {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firestore.collection("emanuelebooking");
        Query query = reference.whereEqualTo("month", emanBook.getMonth()).
                whereEqualTo("date", emanBook.getDate()).
                whereEqualTo("position", emanBook.getPosition());
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                String month = "";
                String date = "";
                String position = "";
                Log.v("logcheck", "onSuccess>>><<<" + task.isSuccessful());
                //  task.getResult().getDocuments();
                assert task.getResult()!=null;
                for (DocumentSnapshot snapshot : task.getResult()) {
                    //   Toast.makeText(EmanueleCal.this, "Accesso Eseguito", Toast.LENGTH_SHORT).show();
                    // startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    month = snapshot.getString("month");
                    date = snapshot.getString("date");
                    position = snapshot.getString("position");
                    Log.v("logcheck", ">>>>>>>>><<<<<<<");
                }



                CheckValidation(month, date, position, emanBook);
                // progressBar.setVisibility(View.GONE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v("logcheck", "Errror   " + e.getMessage());
                // progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void CheckValidation(String month, String date, String position, EmanBook emanBook) {
        if (!(month.equalsIgnoreCase(""))) {
            if (!(date.equalsIgnoreCase(""))) {
                if (!(position).equalsIgnoreCase("")) {
                    Toast.makeText(this, "Already Booked Choose Other Time", Toast.LENGTH_SHORT).show();
                    Log.v("logcheck", "" + month + " " + date + " " + position);
                }
            }
        } else {
            // boolean isbooked, String month, String date, String pos,String userName
            Emanuele emanuele = new Emanuele(true, emanBook.getMonth(), dateA,
                    emanBook.getPosition(), Prefrence.getActiveInstance(context).getName(), barber);
            uploadData(emanuele);
            Booking(emanBook);
            Log.v("logcheck", "uploadyourbooking" + emanBook.toString());
        }

    }

    private void Booking(EmanBook emanBook) {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("emanuelebooking").add(emanBook).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.v("logcheck", "on successs" + documentReference.getId());
                Toast.makeText(EmanueleCal.this, "Slot Time Booked Successfully", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v("logcheck", "on failure" + e.getMessage());
            }
        });
    }


    private void uploadData(Emanuele emanuele) {



        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("alllbooking").add(emanuele).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.v("logcheck", "on emauele chck" + documentReference.getId());
                getData();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v("logcheck", "on failure" + e.getMessage());
            }
        });
    }

    public void goScelta(View view) {
        startActivity(new Intent(getApplicationContext(), Barbieri.class));
        finish();
    }

    void getData() {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("alllbooking").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    try {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            list.add("" + document.getData().toString());

                        }
                        Gson gson =
                                new GsonBuilder()
                                        .serializeNulls()
                                        .serializeSpecialFloatingPointValues()
                                        .setLenient()
                                        .create();


                        Type type = new TypeToken<List<Emanuele>>() {
                        }.getType();
                        List<Emanuele> contactList = gson.fromJson(list.toString(), type);
                        for (Emanuele contact : contactList) {
                            Log.i("allcolection", contact.getMonth());
                        }
                        strings = contactList;
                        setAdapter();
                    } catch (Exception e) {
                        Log.v("allcolection", "" + e.getMessage());
                        e.printStackTrace();
                    }

                    Log.d("allcolection", list.toString());
                } else {
                    Log.d("allcolection", "Error getting documents: ", task.getException());
                }
            }
        });
    }

    private void setAdapter() {
        adapter = new Adapter(EmanueleCal.this, orari, strings, dateA, month.toLowerCase(),barber, new Adapter.onItemClick() {
            @Override
            public void OnClick(List<BarberModel> emanueles, int pos) {
                //   uploadData();
                position = String.valueOf(pos);
                BookingSlot(emanueles.get(pos).getTime());
                //Toast.makeText(EmanueleCal.this, ""+emanueles.get(pos).getTime(), Toast.LENGTH_SHORT).show();
            }
        });
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapter);
    }
}
// So clik on first item then book barber im right, yes and I see the book on firebase with name, email and hours. Now, I shop somethings on APP. See