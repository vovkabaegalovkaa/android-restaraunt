package com.oopip.kursach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.oopip.kursach.adapter.BurgerAdapter;
import com.oopip.kursach.model.Burger;

import java.util.ArrayList;
import java.util.List;

public class oficiant_burger_activity extends AppCompatActivity implements RecyclerViewInterface{

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
        Intent intent = new Intent(oficiant_burger_activity.this, productPage_activity.class);
        switch (position) {
            case 0:
                intent.putExtra("Title","Гамбургер");
                intent.putExtra("Price","4.00");
                intent.putExtra("Compound","2000 ccal");
                intent.putExtra("Image",R.drawable.burger_svgrepo_com);
                break;
            case 1:
                intent.putExtra("Title","Чизбургер");
                intent.putExtra("Price","5.00");
                intent.putExtra("Compound","2200 ccal");
                intent.putExtra("Image",R.drawable.burger_svgrepo_com);
                break;
            case 2:
                intent.putExtra("Title","Двойной чизбургер");
                intent.putExtra("Price","8.00");
                intent.putExtra("Compound","3500 ccal");
                intent.putExtra("Image",R.drawable.burger_svgrepo_com);
                break;
            case 3:
                intent.putExtra("Title","Чикенбургер");
                intent.putExtra("Price","4.00");
                intent.putExtra("Compound","1800 ccal");
                intent.putExtra("Image",R.drawable.burger_svgrepo_com);
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