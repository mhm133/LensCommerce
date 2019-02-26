package com.lenscommerce.android.server.service;

import com.lenscommerce.android.model.CategoryModel;
import com.lenscommerce.android.model.FakeData.FakeModel;
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
import retrofit2.http.Query;

public interface ApiService {
    @GET("main_cat.json?key=5a5257e0")
    Call<List<MainCatModel>> getMainCat();

    @GET("main_special_offer.json?key=5a5257e0")
    Call<List<MainSpecialOfferModel>> getMainSpecialOffer();

    @GET("api/json/get/cdYttXnnAO?indent=2")
    Call<List<MainDiscountModel>> getDiscountModel();

    @GET("latest_products.json?key=5a5257e0")
    Call<List<MainLatestProductsModel>> getLatestProducts();

    @GET("latest_products.json?key=5a5257e0")
    Call<List<MainLatestProductsModel>> getPopularProducts();

    @GET("category.json?key=5a5257e0")
    Call<List<CategoryModel>> getCategoryList();

    @FormUrlEncoded
    @POST("api/json/get/ceHJNyOVVe?indent=2")
    Call<List<ProductsCatModel>> getProductsCat(String catId);

    @FormUrlEncoded
    @POST("api/json/get/ceHJNyOVVe?indent=2")
    Call<List<ProductsModel>> getProducts(String catId, int page, int sortType);

    @GET("top_rated")
    Call<FakeModel> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int pageIndex
    );
}
