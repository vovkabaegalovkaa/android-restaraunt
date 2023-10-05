    package com.oopip.kursach;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.ListView;

    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.gson.Gson;
    import com.google.gson.reflect.TypeToken;
    import com.oopip.kursach.users.User;

    import java.lang.reflect.Type;
    import java.util.ArrayList;
    import java.util.List;

    public class change_role extends AppCompatActivity {

        ListView listView;
        ArrayAdapter<String> adapter;
        DatabaseReference users_db;
        List<User> users = new ArrayList<>();
        ArrayList<String> users_end = new ArrayList<>();
        Gson gson = new Gson();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_change_role);
            listView = findViewById(R.id.changerole_listview);
            users_db = FirebaseDatabase.getInstance("https://kyrsach3sem-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users");
            String json = getIntent().getStringExtra("List");
            Type type = new TypeToken<List<User>>(){}.getType();
            users = gson.fromJson(json, type);
            users_end.clear();
            for(User m:users){
                users_end.add(m.getName()+" "+m.getRole());
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,users_end);
            listView.setAdapter(adapter);
            setOnClickItem();
        }

        private void setOnClickItem()
        {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(users.get(position).getRole().equals("Cooker")){
                        users.get(position).setRole("Oficiant");
                        users_db.child(users.get(position).getUid()).child("role").setValue(users.get(position).getRole());
                    }
                    else {
                        users.get(position).setRole("Cooker");
                        users_db.child(users.get(position).getUid()).child("role").setValue(users.get(position).getRole());
                    }
                    users_end.set(position, users.get(position).getName() +" "+ users.get(position).getRole());
                    adapter.notifyDataSetChanged();
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