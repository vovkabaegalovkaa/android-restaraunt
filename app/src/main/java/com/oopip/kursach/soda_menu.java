package com.oopip.kursach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.oopip.kursach.adapter.SodaAdapter;
import com.oopip.kursach.model.Soda;

import java.util.ArrayList;
import java.util.List;

public class soda_menu extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView sodaRecycler;
    SodaAdapter sodaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soda_menu);

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
        Toast.makeText(this, "Авторизируйтесь в качестве официанта\nчтобы добавить блюдо в заказ!", Toast.LENGTH_SHORT).show();
    }

    public void contacts(View view){
        Intent intent = new Intent(this, Contacts.class);
        intent.putExtra("about","Наши контакты:\nvovagotovko2204@gmail.com");
        startActivity(intent);
    }

    public void main(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void aboutUs(View view){
        Intent intent = new Intent(this, AboutUs.class);
        intent.putExtra("about","Готовко\nВладимир");
        startActivity(intent);
    }
}