package com.oopip.kursach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oopip.kursach.users.User;

import java.util.Objects;

public class register_activity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    FirebaseUser user1;

    RelativeLayout root;

    ImageButton registerbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance("https://kyrsach3sem-default-rtdb.europe-west1.firebasedatabase.app/");
        users = db.getReference();

        root = findViewById(R.id.root_element);

        registerbutton = findViewById(R.id.imageButtonRegister);

        EditText name = findViewById(R.id.edit_user);
        EditText pass = findViewById(R.id.edit_password);
        EditText passcheck = findViewById(R.id.edit_password1);
        EditText role = findViewById(R.id.edit_role);

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Введите адрес", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(role.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Введите роль", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass.getText().toString().length() <= 5 ){
                    Toast.makeText(getApplicationContext(), "Введите пароль, содержащий более 5 символов", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass.getText().toString().equals(passcheck.getText().toString()) == false){
                    Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(role.getText().toString().equals("Cooker") == false && role.getText().toString().equals("Oficiant") == false){
                    Toast.makeText(getApplicationContext(), "Роль неккоректна", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Регистрация пользователя
                auth.createUserWithEmailAndPassword(name.getText().toString(), pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    user1 = FirebaseAuth.getInstance().getCurrentUser();
                                    String personUid = user1.getUid();

                                    User user = new User(name.getText().toString().trim(), pass.getText().toString().trim(), role.getText().toString(), personUid, false);

                                    users.child("Users").child(personUid).setValue(user);
                                    finish();

                                    Toast.makeText(getApplicationContext(), "Пользователь успешно зарегестрирован!" , Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(register_activity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Ошибка регистрации!" , Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });

    }
}