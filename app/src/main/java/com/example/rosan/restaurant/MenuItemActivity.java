package com.example.rosan.restaurant;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

import static java.security.AccessController.getContext;

public class MenuItemActivity extends AppCompatActivity {

    MenuItem item;
    TextView name;
    TextView description;
    TextView price;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // Retrieve data/details from extra
        item = (MenuItem) getIntent().getSerializableExtra("menuItem");

        fillActivity();
    }

    private void fillActivity() {
        // Find Views
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
        image = findViewById(R.id.image);

        // Set texts and images
        name.setText(item.getName());
        description.setText(item.getDescription());
        price.setText("$"+item.getPrice()+".00");
        Picasso.with(getApplicationContext()).load(item.getImageUrl()).resize(700, 600).centerCrop().into(image);
    }
}
