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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.oopip.kursach.users.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class admi_activity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    List<String> listData;
    List<User> listTemp;
    List<User> change_role;
    Gson gson = new Gson();

    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admi);
        init();
        getDataFromDb();
        setOnClickItem();

    }

    private void init(){
        listView = findViewById(R.id.admin_listview);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        change_role = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
        users = FirebaseDatabase.getInstance("https://kyrsach3sem-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users");
    }

    private void getDataFromDb() {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(listData.size() > 0 )
                    listData.clear();
                if(listTemp.size() > 0 )
                    listTemp.clear();
                if(change_role.size() > 0 )
                    change_role.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    assert user != null;
                    if(user.isAccses()) {
                        listData.add(user.getName() + " Ограничить доступ");
                    }
                    else{
                        listData.add(user.getName() + " Предоставить доступ");
                    }
                    listTemp.add(user);
                    change_role.add(user);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        users.addValueEventListener(vListener);
    }

    private void setOnClickItem()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = listTemp.get(position);
                if(!user.isAccses())
                    users.child(user.getUid()).child("accses").setValue(true);
                else
                    users.child(user.getUid()).child("accses").setValue(false);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void start_change_role(View view){
        Intent intent = new Intent(admi_activity.this, com.oopip.kursach.change_role.class);
        String json = gson.toJson(change_role);
        intent.putExtra("List", json);
        startActivity(intent);
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