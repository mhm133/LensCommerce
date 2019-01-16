package com.lenscommerce.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductsCatModel implements Serializable {
    @SerializedName("product_cat_id")
    @Expose
    private String productCatId;

    @SerializedName("product_cat_image")
    @Expose
    private String productCatImage;

    @SerializedName("product_cat_title")
    @Expose
    private String productCatTitle;

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getProductCatImage() {
        return productCatImage;
    }

    public void setProductCatImage(String productCatImage) {
        this.productCatImage = productCatImage;
    }

    public String getProductCatTitle() {
        return productCatTitle;
    }

    public void setProductCatTitle(String productCatTitle) {
        this.productCatTitle = productCatTitle;
    }
}
