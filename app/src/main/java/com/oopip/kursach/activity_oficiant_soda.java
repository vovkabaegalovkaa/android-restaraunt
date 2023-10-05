package com.oopip.kursach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.oopip.kursach.adapter.SodaAdapter;
import com.oopip.kursach.model.Soda;

import java.util.ArrayList;
import java.util.List;

public class activity_oficiant_soda extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView sodaRecycler;
    SodaAdapter sodaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oficiant_soda);

        List<Soda> sodaList = new ArrayList<>();
        sodaList.add(new Soda(1,"Кока-кола","2.50","1500 ccal"));
        sodaList.add(new Soda(2,"Спрайт","2.50","1500 ccal"));

        setSodaRecycler(sodaList);

    }

    private void setSodaRecycler(List<Soda> sodaList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        sodaRecycler = findViewById(R.id.sodamenu);
        sodaRecycler.setLayoutManager(layoutManager);

        sodaAdapter = new SodaAdapter(this, sodaList, this);
        sodaRecycler.setAdapter(sodaAdapter);

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(activity_oficiant_soda.this, productPage_activity.class);
        switch (position) {
            case 0:
                intent.putExtra("Title","Кока-кола");
                intent.putExtra("Price","2.50");
                intent.putExtra("Compound","1500 ccal");
                intent.putExtra("Image",R.drawable.soda_svgrepo_com);
                break;
            case 1:
                intent.putExtra("Title","Спрайт");
                intent.putExtra("Price","2.50");
                intent.putExtra("Compound","1500 ccal");
                intent.putExtra("Image",R.drawable.soda_svgrepo_com);
                break;
            default:
                return;
        }
        startActivity(intent);
    }

    public void contacts(View view){
        Intent intent = new Intent(this, Contacts.class);
        intent.putExtra("about","Наши контакты:\nvovagotovko2204@gmail.com");
        FirebaseAuth.getInstance().signOut();
        startActivity(intent);
    }

    public void main(View view){
        Intent intent = new Intent(this, MainActivity.class);
        FirebaseAuth.getInstance().signOut();
        startActivity(intent);
    }

    public void aboutUs(View view){
        Intent intent = new Intent(this, AboutUs.class);
        intent.putExtra("about","Готовко\nВладимир");
        FirebaseAuth.getInstance().signOut();
        startActivity(intent);
    }
}