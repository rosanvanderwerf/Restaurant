package com.example.rosan.restaurant;

/* Created by rosan on 9-3-2018. */

import android.content.Context;
import android.util.Log;
import android.view.Menu;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener{



    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> items);
        void gotMenuItemsError(String message);
    }

    public Context context;
    public Callback cb;


    /* Constructor */
    MenuItemsRequest(Context c){
        context = c;
    }

    public void getMenuItems(Callback activity){
        RequestQueue queue = Volley.newRequestQueue((Context) activity);

        String url = "https://resto.mprog.nl/menu";
        JsonObjectRequest request = new JsonObjectRequest(url, null,this,this);
        queue.add(request);

        /* Callback: to notify activity that retrieval successful */
        // Something gotCategories, or gotCategoriesError
        cb = activity;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray catRes = response.getJSONArray("items");
            // Which category, and: extract menuItems
            String cat = MenuActivity.category;

            ArrayList<MenuItem> menuItems = new ArrayList<>();

            for(int i=0;i<catRes.length();i++){
                JSONObject item = catRes.getJSONObject(i);
                if (item.getString("category").equals(cat)){
                    /* Extract data and create instances of menuItem */
                    String name = item.getString("name");
                    String description = item.getString("description");
                    String url = item.getString("image_url");
                    Integer price = item.getInt("price");
                    String category = item.getString("category");
                    MenuItem menuItem = new MenuItem(name, description, url, price, category);
                    menuItems.add(menuItem);
                }
                /* Give list with menuItems to MenuActivity */
                if (menuItems.isEmpty()){
                    cb.gotMenuItemsError("something went wrong");
                } else {
                    cb.gotMenuItems(menuItems);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            //callback.gotCategoriesError("Something went wrong. Please try again.");
        }

    }
}
