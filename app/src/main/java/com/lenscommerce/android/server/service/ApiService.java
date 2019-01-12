package com.lenscommerce.android.server.service;

import com.lenscommerce.android.Model.MainCatModel;
import com.lenscommerce.android.Model.MainDiscountModel;
import com.lenscommerce.android.Model.MainSpecialOfferModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/json/get/bTEzqvePyq?indent=2")
    Call<List<MainCatModel>> getMainCat();

    @GET("api/json/get/ceFrfZOPDm?indent=2")
    Call<List<MainSpecialOfferModel>> getMainSpecialOffer();

    @GET("api/json/get/coNVySpSwi?indent=2")
    Call<List<MainDiscountModel>> getDiscountModel();
}
