package com.example.triolabarberhairstyle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triolabarberhairstyle.constant.Utils;
import com.example.triolabarberhairstyle.models.BarberModel;
import com.example.triolabarberhairstyle.models.Emanuele;

import java.util.List;

import butterknife.OnItemClick;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<BarberModel> titles;
    List<Emanuele> strings;
    LayoutInflater inflater;
    onItemClick onItemClick;
    String date;
    String month;
    String barber;

    public Adapter(Context ctx, List<BarberModel> titles,
                   List<Emanuele> strings,String date,String month,String barber, onItemClick onItemClick) {
        this.titles = titles;
        this.inflater = LayoutInflater.from(ctx);
        this.onItemClick = onItemClick;
        this.strings = strings;
        this.date = date;
        this.month = month;
        this.barber = barber;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BarberModel emanuele = titles.get(position);
        for (int i=0;i<strings.size();i++) {
            if (strings.get(i).getBarber().equalsIgnoreCase(barber)){
                if (strings.get(i).getMonth().equalsIgnoreCase(month)){
                    if (strings.get(i).getDate().equalsIgnoreCase(date)){
                        if (strings.get(i).getPos().equalsIgnoreCase(""+position)){
                            holder.txt_time_slot_description.setText("Already booked");
                        }
                    }

                }
            }

        }
        holder.title.setText(emanuele.getTime());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.OnClick(titles, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,txt_time_slot_description;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView200);
            txt_time_slot_description = itemView.findViewById(R.id.txt_time_slot_description);
            layout = itemView.findViewById(R.id.ltn);


        }
    }

    public interface onItemClick {
        void OnClick(List<BarberModel> emanueles, int pos);
    }
}
