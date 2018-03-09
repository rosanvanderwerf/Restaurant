package com.example.rosan.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback {

    TextView cat;
    ListView menu_items;
    static String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Retrieve category from CategoriesActivity
        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        // Create a request and get MenuItems
        MenuItemsRequest request = new MenuItemsRequest(this);
        request.getMenuItems(this);

        // Find cat view and set text
        cat = findViewById(R.id.cat);
        cat.setText(category);

        // Attach listener to listView
        menu_items = findViewById(R.id.menu_items);
        menu_items.setOnItemClickListener(new toMenu());
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> items) {
        // Add custom adapter to ListView
        MenuItemAdapter adapter = new MenuItemAdapter(this, items);
        menu_items = findViewById(R.id.menu_items);
        menu_items.setAdapter(adapter);
    }

    @Override
    public void gotMenuItemsError(String message) {
        // Display error message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private class toMenu implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int i, long id) {
            // Get MenuItem
            MenuItem selectedItem = (MenuItem) menu_items.getItemAtPosition(i);
            // Create and start intent
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("menuItem", selectedItem);
            startActivity(intent);
        }
    }
}
