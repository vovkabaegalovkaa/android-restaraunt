package com.oopip.kursach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.oopip.kursach.adapter.BurgerAdapter;
import com.oopip.kursach.model.Burger;

import java.util.ArrayList;
import java.util.List;

public class burgers_menu extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView  burgerRecycler;
    BurgerAdapter burgerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burgers_menu);

        List<Burger> burgerList = new ArrayList<>();
        burgerList.add(new Burger(1,"Гамбургер","4.00","2000 ccal"));
        burgerList.add(new Burger(2,"Чизбургер","5.00","2200 ccal"));
        burgerList.add(new Burger(3,"Двойной чизбургер","8.00","3500 ccal"));
        burgerList.add(new Burger(4,"Чикенбургер","4.00","1800 ccal"));

        setBurgerRecycler(burgerList);

    }

    private void setBurgerRecycler(List<Burger> burgerList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        burgerRecycler = findViewById(R.id.bu);
        burgerRecycler.setLayoutManager(layoutManager);

        burgerAdapter = new BurgerAdapter(this, burgerList, this);
        burgerRecycler.setAdapter(burgerAdapter);

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