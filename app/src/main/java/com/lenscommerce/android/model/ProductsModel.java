package com.lenscommerce.android.model;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductsModel implements Serializable {
    @SerializedName("image_product")
    @Expose
    private String imageProduct;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("previous_price")
    @Expose
    private String previousPrice;
    @SerializedName("current_price")
    @Expose
    private String currentPrice;
    @SerializedName("page")
    @Expose
    private String page;

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SpannableString getPreviousPrice() {
        SpannableString spannableString = new SpannableString(previousPrice);
        spannableString.setSpan(new StrikethroughSpan(), 0, previousPrice.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public void setPreviousPrice(String previousPrice) {
        this.previousPrice = previousPrice;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }
}
