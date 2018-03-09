package com.example.rosan.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback {

    ListView menu_items;

    static String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Retrieve category from CategoriesActivity
        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        // Do a request
        MenuItemsRequest request = new MenuItemsRequest(this);
        request.getMenuItems(this);


        //ArrayList<MenuItem> items = new ArrayList<>();
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> items) {
        // Add custom adapter to ListView
        MenuItemAdapter adapter = new MenuItemAdapter(this, items);
        // Attach adapter to ListView
        menu_items = findViewById(R.id.menu_items);
        menu_items.setAdapter(adapter);
    }

    @Override
    public void gotMenuItemsError(String message) {
        // Display error message
    }
}
