package com.lenscommerce.android.model;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MainSpecialOfferModel implements Serializable {
    @SerializedName("offer_id")
    @Expose
    private String offerId;
    @SerializedName("offer_image")
    @Expose
    private String offerImage;
    @SerializedName("offer_title")
    @Expose
    private String offerTitle;
    @SerializedName("offer_previous_price")
    @Expose
    private String offerPreviousPrice;
    @SerializedName("offer_current_price")
    @Expose
    private String offerCurrentPrice;

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferImage() {
        return offerImage;
    }

    public void setOfferImage(String offerImage) {
        this.offerImage = offerImage;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public SpannableString getOfferPreviousPrice() {
        SpannableString spannableString = new SpannableString(offerPreviousPrice);
        spannableString.setSpan(new StrikethroughSpan(), 0, offerPreviousPrice.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public void setOfferPreviousPrice(String offerPreviousPrice) {
        this.offerPreviousPrice = offerPreviousPrice;
    }

    public String getOfferCurrentPrice() {
        return offerCurrentPrice;
    }

    public void setOfferCurrentPrice(String offerCurrentPrice) {
        this.offerCurrentPrice = offerCurrentPrice;
    }
}
