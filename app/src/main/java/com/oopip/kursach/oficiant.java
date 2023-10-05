package com.oopip.kursach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oopip.kursach.model.Order;

import java.util.ArrayList;
import java.util.List;

public class oficiant extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ListView orders_list;
    DatabaseReference orders;
    List<String> orderNumberList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oficiant);
        orders_list = findViewById(R.id.orders_number_list);
        orders = FirebaseDatabase.getInstance("https://kyrsach3sem-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Orders");
        orders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    doResult(ds.getKey());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        System.out.println(orderNumberList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderNumberList);
        orders_list.setAdapter(adapter);
        setOnClickItem();

    }

    void doResult(String result){
        orderNumberList.add(result);
        adapter.notifyDataSetChanged();
    }

    private void setOnClickItem()
    {
        orders_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(oficiant.this, Order_accept.class);
                intent.putExtra("order_number",orderNumberList.get(position));
                startActivity(intent);
            }
        });
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
