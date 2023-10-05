package com.oopip.kursach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.oopip.kursach.adapter.PotatoAdapter;
import com.oopip.kursach.model.Potato;

import java.util.ArrayList;
import java.util.List;

public class activity_potato_menu extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView potatoRecycler;
    PotatoAdapter potatoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potato_menu);

        List<Potato> potatoList = new ArrayList<>();
        potatoList.add(new Potato(1,"Картофель-фри","3.00","1500 ccal"));
        potatoList.add(new Potato(2,"Картошка по деревенски","4.00","1800 ccal"));

        setPotatoRecycler(potatoList);

    }

    private void setPotatoRecycler(List<Potato> potatoList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        potatoRecycler = findViewById(R.id.potatomenu);
        potatoRecycler.setLayoutManager(layoutManager);

        potatoAdapter = new PotatoAdapter(this, potatoList, this);
        potatoRecycler.setAdapter(potatoAdapter);

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
