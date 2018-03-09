package com.example.rosan.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback{

    ListView list_categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Call getCategories method?
        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);

        // OnClickListener on ListView
        list_categories = findViewById(R.id.list_categories);
        list_categories.setOnItemClickListener(new toCategory());


    }

    private class toCategory implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapter, View v, int i, long l){
            // Go to MenuActivity
            String selectedCat = list_categories.getItemAtPosition(i).toString();
            //Toast.makeText(CategoriesActivity.this, selectedCat, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", selectedCat);
            startActivity(intent);
        }

    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        // Create a new ArrayAdapter
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,categories);

        list_categories = findViewById(R.id.list_categories);
        list_categories.setAdapter(categoriesAdapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        // Show a Toast with an error message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
