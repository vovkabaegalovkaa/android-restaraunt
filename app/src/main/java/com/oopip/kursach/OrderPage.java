package com.oopip.kursach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oopip.kursach.model.Order;
import com.oopip.kursach.model.Product;


public class OrderPage extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ListView orders_list;
    DatabaseReference orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
        orders_list = findViewById(R.id.orders_list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Order.productNameList);
        orders_list.setAdapter(adapter);
        orders = FirebaseDatabase.getInstance("https://kyrsach3sem-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Orders");
        orders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    Order.orderNumber = Integer.parseInt(ds.getKey());
                    System.out.println("Order number "+ ds.getKey());
                    for(DataSnapshot ds2: ds.getChildren()){
                        System.out.println("Product "+ ds2.getValue());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setOnClickItem();
    }

    private void setOnClickItem()
    {
        orders_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order.productList.remove(position);
                Order.productNameList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void createOrder(View view){
        Order.orderNumber++;
        int i =0;
        if (Order.productList.size() == 0)
        {
            Toast.makeText(this, "Корзина пустая!", Toast.LENGTH_SHORT).show();
            return;
        }
        for(int j = 0; j<Order.productList.size();j++) {
            for(int k =j+1; k<Order.productList.size(); k++){
                if(Order.productList.get(j).getProductName().equals(Order.productList.get(k).getProductName())){
                    Order.productList.get(j).setProductKolvo(Order.productList.get(j).getProductKolvo()+1);
                    Order.productList.remove(k);
                    k--;
                }
            }
        }
        for(Product m:Order.productList) {
            i +=1;
            String number = String.valueOf(i);
            String stroka = "product ";
            orders.child(String.valueOf(Order.orderNumber)).child(stroka+number).setValue(m);
        }
        Toast.makeText(this, "Заказ оформлен!", Toast.LENGTH_SHORT).show();
        Order.productList.clear();
        Order.productNameList.clear();
        adapter.notifyDataSetChanged();
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