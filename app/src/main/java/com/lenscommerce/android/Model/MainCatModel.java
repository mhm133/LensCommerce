package com.lenscommerce.android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MainCatModel implements Serializable {
    @SerializedName("cat_id")
    @Expose
    private String id;
    @SerializedName("cat_title")
    @Expose
    private String catTitle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public void setCatTitle(String catTitle) {
        this.catTitle = catTitle;
    }
}
