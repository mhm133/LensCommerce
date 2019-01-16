package com.lenscommerce.android.server.service;

import com.lenscommerce.android.model.CategoryModel;
import com.lenscommerce.android.model.MainCatModel;
import com.lenscommerce.android.model.MainDiscountModel;
import com.lenscommerce.android.model.MainLatestProductsModel;
import com.lenscommerce.android.model.MainSpecialOfferModel;
import com.lenscommerce.android.model.ProductsCatModel;
import com.lenscommerce.android.model.ProductsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    @GET("api/json/get/ceHJNyOVVe?indent=2")
    Call<List<CategoryModel>> getCategoryList();

    @FormUrlEncoded
    @POST("api/json/get/ceHJNyOVVe?indent=2")
    Call<List<ProductsCatModel>> getProductsCat(String catId);

    @FormUrlEncoded
    @POST("api/json/get/ceHJNyOVVe?indent=2")
    Call<List<ProductsModel>> getProducts(String catId, String sortType);
}
