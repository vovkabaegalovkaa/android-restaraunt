package com.oopip.kursach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.oopip.kursach.adapter.PotatoAdapter;
import com.oopip.kursach.model.Potato;

import java.util.ArrayList;
import java.util.List;

public class oficiant_potato_activity extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView potatoRecycler;
    PotatoAdapter potatoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oficiant_potato);

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
        Intent intent = new Intent(oficiant_potato_activity.this, productPage_activity.class);
        switch (position) {
            case 0:
                intent.putExtra("Title","Картошка-фри");
                intent.putExtra("Price","3.00");
                intent.putExtra("Compound","1500 ccal");
                intent.putExtra("Image",R.drawable.french_fries_juliane_kr_r);
                break;
            case 1:
                intent.putExtra("Title","Картошка по-деревенски");
                intent.putExtra("Price","4.00");
                intent.putExtra("Compound","1800 ccal");
                intent.putExtra("Image",R.drawable.french_fries_juliane_kr_r);
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