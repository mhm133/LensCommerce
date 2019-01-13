package com.lenscommerce.android.server.service;

import com.lenscommerce.android.model.MainCatModel;
import com.lenscommerce.android.model.MainDiscountModel;
import com.lenscommerce.android.model.MainLatestProductsModel;
import com.lenscommerce.android.model.MainSpecialOfferModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/json/get/bTEzqvePyq?indent=2")
    Call<List<MainCatModel>> getMainCat();

    @GET("api/json/get/ceFrfZOPDm?indent=2")
    Call<List<MainSpecialOfferModel>> getMainSpecialOffer();

    @GET("api/json/get/cdYttXnnAO?indent=2")
    Call<List<MainDiscountModel>> getDiscountModel();

    @GET("api/json/get/cfDtAEMsvC?indent=2")
    Call<List<MainLatestProductsModel>> getLatestProducts();

    @GET("api/json/get/cfDtAEMsvC?indent=2")
    Call<List<MainLatestProductsModel>> getPopularProducts();
}
