package com.oopip.kursach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.oopip.kursach.model.Order;
import com.oopip.kursach.model.Product;

public class productPage_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        ImageView productPageImage = findViewById(R.id.productPageImage);
        TextView productPageTitle = findViewById(R.id.productPageText);
        TextView productPagePrice = findViewById(R.id.productPagePrice);
        TextView productPageCompound = findViewById(R.id.productPageCompound);

        productPageImage.setImageResource(getIntent().getIntExtra("Image",0));
        productPageTitle.setText(getIntent().getStringExtra("Title"));
        productPagePrice.setText(getIntent().getStringExtra("Price"));
        productPageCompound.setText(getIntent().getStringExtra("Compound"));
    }

    public void addToOrder(View view){
        Product product = new Product(getIntent().getStringExtra("Title"),getIntent().getStringExtra("Price"),1);
        Order.productList.add(product);
        Order.productNameList.add(product.getProductName());
        Toast.makeText(this,"Добавлено!",Toast.LENGTH_SHORT).show();
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