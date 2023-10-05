package com.oopip.kursach;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oopip.kursach.users.User;

import java.util.ArrayList;
import java.util.List;

public class login_activity extends AppCompatActivity {

    EditText username, password;
    ImageButton loginbutton;

    FirebaseAuth auth;
    DatabaseReference users;

    RelativeLayout root;

    List<User> listTemp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        users = FirebaseDatabase.getInstance("https://kyrsach3sem-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users");

        username = findViewById(R.id.edit_user);
        password = findViewById(R.id.edit_password);
        loginbutton = findViewById(R.id.imageButtonlogin);

        root = findViewById(R.id.login_root);

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(listTemp.size()>0)
                    listTemp.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    assert user != null;
                    doResult(user);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(TextUtils.isEmpty(username.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Введите адрес", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(), "Вход выполнен!", Toast.LENGTH_SHORT).show();
                    intent = new Intent(login_activity.this, admi_activity.class);
                    startActivity(intent);
                }
                else{
                auth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                for(User user: listTemp){
                                    System.out.println(user.getName());
                                    if(username.getText().toString().equals(user.getName()) && password.getText().toString().equals(user.getPass())){
                                        if(!user.isAccses()){
                                            Toast.makeText(login_activity.this, "Ошибка входа в аккаунт!", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if(user.getRole().equals("Cooker")){
                                            Intent intent = new Intent(login_activity.this, oficiant.class);
                                            startActivity(intent);
                                        }
                                        else {
                                            Intent intent = new Intent(login_activity.this, cooker_activity.class);
                                            startActivity(intent);
                                        }
                                    }

                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Ошибка авторизации", Toast.LENGTH_SHORT).show();
                            }
                        });
            }}
        });

    }
    void doResult(User result){
        listTemp.add(result);
    }
}