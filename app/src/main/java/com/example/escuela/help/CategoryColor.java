package com.example.escuela.help;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

public class CategoryColor {
    private String categoryId;
    private String title;
    private String desc;
    private int color;


    public CategoryColor(String categoryId, String title, String desc,int color)
    {
        this.title=title;
        this.categoryId=categoryId;
        this.desc=desc;
        this.color=color;
    }
    public CategoryColor()
    {
        this.title="";
        this.categoryId="";
        this.desc="";
        this.color=0;
    }

    public String getTitle(){return this.title;}

    public String getDesc(){return this.desc;}

    public void setTittle(String title) {
        this.title = title;
    }


    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getColor(){return this.color;}

    public void setColor(int color){this.color=color;}
}

