package com.lenscommerce.android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MainDiscountModel implements Serializable {
    @SerializedName("discount_id")
    @Expose
    private String discountId;
    @SerializedName("discount_image")
    @Expose
    private String discountImage;
    @SerializedName("discount_second_row_1")
    @Expose
    private String discountSecondRow1;
    @SerializedName("discount_second_row_2")
    @Expose
    private String discountSecondRow2;

    private final static long serialVersionUID = -2947581312509205178L;

    public String getDiscountId() {
        return discountId;
    }

    public String getDiscountImage() {
        return discountImage;
    }

    public String getDiscountSecondRow1() {
        return discountSecondRow1;
    }

    public String getDiscountSecondRow2() {
        return discountSecondRow2;
    }
}
