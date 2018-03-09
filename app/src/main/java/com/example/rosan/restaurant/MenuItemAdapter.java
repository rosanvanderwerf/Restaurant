package com.example.rosan.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.transform.ErrorListener;

/* Created by rosan on 9-3-2018. */

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    public Context context;
    public ArrayList menuItems;
    private int resource;


    public MenuItemAdapter(Context context, ArrayList<MenuItem> menuItems) {
        super(context, 0, menuItems);
        this.context = context;
        this.resource = resource;
        this.menuItems = menuItems;
    }

    TextView title;
    TextView price;
    ImageView image;

    @Override
    public View getView(int i, View v, @NonNull ViewGroup p){
        // Get the data from this position
        MenuItem item = getItem(i);
        // Inflate view
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, p, false);
        }
        // Find Views
        title = v.findViewById(R.id.title);
        price = v.findViewById(R.id.price);
        image = v.findViewById(R.id.image);
        // Populate
        assert item != null;
        title.setText(item.getName());
        price.setText("$" + item.getPrice() + ".00");

        // Something to display an image from url - Using Picasso
        try {
            URL url = new URL(item.getImageUrl());
            Picasso.with(getContext()).load(String.valueOf(url)).resize(150,150).centerCrop().into(image);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return v;
    }
}
