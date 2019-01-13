package com.lenscommerce.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MainCatModel extends RealmObject implements Serializable {
    @SerializedName("cat_id")
    @Expose
    @PrimaryKey
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
