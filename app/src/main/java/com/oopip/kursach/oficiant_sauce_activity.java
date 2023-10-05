package com.oopip.kursach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.oopip.kursach.adapter.SauceAdapter;
import com.oopip.kursach.model.Sauce;

import java.util.ArrayList;
import java.util.List;

public class oficiant_sauce_activity extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView sauceRecycler;
    SauceAdapter sauceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauce_menu);

        List<Sauce> sauceList = new ArrayList<>();
        sauceList.add(new Sauce(1,"Кисло-сладкий","0.80","100 ccal"));
        sauceList.add(new Sauce(2,"Сырный","0.80","100 ccal"));

        setSodaRecycler(sauceList);

    }

    private void setSodaRecycler(List<Sauce> sauceList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        sauceRecycler = findViewById(R.id.saucemenu);
        sauceRecycler.setLayoutManager(layoutManager);

        sauceAdapter = new SauceAdapter(this, sauceList, this);
        sauceRecycler.setAdapter(sauceAdapter);

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(oficiant_sauce_activity.this, productPage_activity.class);
        switch (position) {
            case 0:
                intent.putExtra("Title","Кисло-сладкий");
                intent.putExtra("Price","0.80");
                intent.putExtra("Compound","100 ccal");
                intent.putExtra("Image",R.drawable.sauce_svgrepo_com);
                break;
            case 1:
                intent.putExtra("Title","Сырный");
                intent.putExtra("Price","0.80");
                intent.putExtra("Compound","100 ccal");
                intent.putExtra("Image",R.drawable.sauce_svgrepo_com);
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