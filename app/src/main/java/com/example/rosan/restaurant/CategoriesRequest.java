package com.example.rosan.restaurant;

/* Created by rosan on 8-3-2018. */

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public Context context;
    public Callback cb;

    /* Constructor */
    CategoriesRequest(Context c){
        context = c;
    }

    public void getCategories(Callback activity){
        /* Retrieve categories from the API, if so: notify activity
        Attempt to retrieve the categories from the API */
        RequestQueue queue = Volley.newRequestQueue((Context) activity);

        String url = "https://resto.mprog.nl/categories";
        JsonObjectRequest request = new JsonObjectRequest(url, null,this,this);
        queue.add(request);

        /* Callback: to notify activity that retrieval successful */
        // Something gotCategories, or gotCategoriesError
        cb = activity;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // Among other things: an error message
        cb.gotCategoriesError("Something went wrong. Please try again.");
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray catRes = response.getJSONArray("categories");
            Log.d("response", catRes.toString());
            // Object to ArrayList<String>
            ArrayList<String> categories = new ArrayList<>();

            for (int i=0;i<catRes.length();i++){
                String category = catRes.getString(i);
                categories.add(category);
            }
            // Give list with categories to CategoriesActivity
            //CategoriesActivity.gotCategories(categories);
            if(categories.isEmpty()){
                // Give error
                cb.gotCategoriesError("something went wrong");
            } else {
                cb.gotCategories(categories);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            cb.gotCategoriesError("Something went wrong. Please try again.");
        }
    }
}
