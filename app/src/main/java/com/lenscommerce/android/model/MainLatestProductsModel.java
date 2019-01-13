package com.lenscommerce.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MainLatestProductsModel implements Serializable
{

    @SerializedName("latest_pr_id")
    @Expose
    private int latestPrId;
    @SerializedName("latest_pr_image")
    @Expose
    private String latestPrImage;
    @SerializedName("latest_pr_title")
    @Expose
    private String latestPrTitle;
    @SerializedName("latest_pr_price")
    @Expose
    private String latestPrPrice;
    private final static long serialVersionUID = -2501070611869791682L;

    public int getLatestPrId() {
        return latestPrId;
    }

    public void setLatestPrId(int latestPrId) {
        this.latestPrId = latestPrId;
    }

    public String getLatestPrImage() {
        return latestPrImage;
    }

    public void setLatestPrImage(String latestPrImage) {
        this.latestPrImage = latestPrImage;
    }

    public String getLatestPrTitle() {
        return latestPrTitle;
    }

    public void setLatestPrTitle(String latestPrTitle) {
        this.latestPrTitle = latestPrTitle;
    }

    public String getLatestPrPrice() {
        return latestPrPrice;
    }

    public void setLatestPrPrice(String latestPrPrice) {
        this.latestPrPrice = latestPrPrice;
    }

}