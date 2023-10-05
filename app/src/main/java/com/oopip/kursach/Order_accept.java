package com.oopip.kursach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oopip.kursach.model.Product;
import com.oopip.kursach.users.User;

import java.util.ArrayList;
import java.util.List;

public class Order_accept extends AppCompatActivity {

    String number;
    ArrayAdapter<String> adapter;
    DatabaseReference orders;
    List<String> order_reception = new ArrayList<>();
    ListView order_reception_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_accept);
        order_reception_view = findViewById(R.id.order_reception);
        orders = FirebaseDatabase.getInstance("https://kyrsach3sem-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Orders");
        number = getIntent().getStringExtra("order_number");
        orders.child(number).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    Product product = ds.getValue(Product.class);
                    assert product != null;
                    doResult(product.getProductName()+" "+product.getProductKolvo());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, order_reception);
        order_reception_view.setAdapter(adapter);
    }

    void doResult(String result){
        order_reception.add(result);
        adapter.notifyDataSetChanged();
    }

    public void acceptOrder(View view){
        orders.child(number).removeValue();
        Toast.makeText(this, "Заказ выполнел и удален из базы!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, oficiant.class);
        startActivity(intent);
    }

    public void cancelOrder(View view){
        Intent intent = new Intent(this, oficiant.class);
        Toast.makeText(this, "Заказ отменен!", Toast.LENGTH_SHORT).show();
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