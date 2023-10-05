package com.oopip.kursach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.oopip.kursach.adapter.CategoryAdapter;
import com.oopip.kursach.model.Category;

import java.util.ArrayList;
import java.util.List;

public class cooker_activity extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView categoryRecycler;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooker);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1,"Бургеры"));
        categoryList.add(new Category(2,"Картофель-фри"));
        categoryList.add(new Category(3,"Напитки"));
        categoryList.add(new Category(4,"Соусы"));

        setCategoryRecycler(categoryList);
    }

    public void openOrderList(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }

    private void setCategoryRecycler(List<Category> categoryList) {


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList,this);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent;
        switch(position){
            case 0: intent = new Intent(cooker_activity.this, oficiant_burger_activity.class);
                break;
            case 1: intent = new Intent(cooker_activity.this, oficiant_potato_activity.class);
                break;
            case 2: intent = new Intent(cooker_activity.this, activity_oficiant_soda.class);
                break;
            case 3: intent = new Intent(cooker_activity.this, oficiant_sauce_activity.class);
                break;
            default: return ;
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