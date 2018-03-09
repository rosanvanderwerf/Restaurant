package com.example.rosan.restaurant;

/* Created by rosan on 9-3-2018. */

import java.io.Serializable;

public class MenuItem implements Serializable{
    private String name;
    private String description;
    private String imageUrl;
    private Integer price;
    private String category;

    /* Constructor */
    public MenuItem(String name, String description, String imageUrl, Integer price, String category){
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }

    /* Getter(s) */
    public String getName(){ return name;}

    public String getDescription(){return description;}

    public String getImageUrl(){ return imageUrl;}

    public Integer getPrice(){ return price;}

    public String getCategory(){ return category;}

    /* Setter(s) */
    public void setName(String string){
        this.name = string;
    }

    public void setDescription(String string){
        this.description = string;
    }

    public void setImageUrl(String string){
        this.imageUrl = string;
    }

    public void setPrice(Integer i){
        this.price = i;
    }

    public void setCategory(String string){
        this.category = string;
    }




}
