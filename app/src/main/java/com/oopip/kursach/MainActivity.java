package com.oopip.kursach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.oopip.kursach.adapter.CategoryAdapter;
import com.oopip.kursach.model.Category;
import com.oopip.kursach.model.Order;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView categoryRecycler;
    CategoryAdapter categoryAdapter;

    ImageButton btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1,"Бургеры"));
        categoryList.add(new Category(2,"Картофель-фри"));
        categoryList.add(new Category(3,"Напитки"));
        categoryList.add(new Category(4,"Соусы"));


        setCategoryRecycler(categoryList);

        btnLogin = findViewById(R.id.LoginButton);
        btnRegister = findViewById(R.id.RegisterButton);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order.productList.clear();
                Order.productNameList.clear();
                Intent intent = new Intent(MainActivity.this, login_activity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, register_activity.class);
                startActivity(intent);
            }
        });

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
            case 0: intent = new Intent(MainActivity.this, burgers_menu.class);
                    break;
            case 1: intent = new Intent(MainActivity.this, activity_potato_menu.class);
                    break;
            case 2: intent = new Intent(MainActivity.this, soda_menu.class);
                break;
            case 3: intent = new Intent(MainActivity.this, sauce_menu.class);
                break;
            default: return ;
        }
        startActivity(intent);
    }

    public void aboutUs(View view){
        Intent intent = new Intent(this, AboutUs.class);
        intent.putExtra("about","Готовко\nВладимир");
        startActivity(intent);
    }

    public void contacts(View view){
        Intent intent = new Intent(this, Contacts.class);
        intent.putExtra("about","Наши контакты:\nvovagotovko2204@gmail.com");
        startActivity(intent);
    }
}