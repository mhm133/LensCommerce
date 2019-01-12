package com.lenscommerce.android.server.client;

import com.lenscommerce.android.Model.MainSpecialOfferModel;
import com.lenscommerce.android.server.ApiUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainSpecialOfferClient {

    public static void getSpecialOfferContent(OnSpecialOfferItemResponse onSpecialOfferItemResponse) {
        ApiUtil.getServiceClass().getMainSpecialOffer().enqueue(new Callback<List<MainSpecialOfferModel>>() {
            @Override
            public void onResponse(Call<List<MainSpecialOfferModel>> call, Response<List<MainSpecialOfferModel>> response) {
                onSpecialOfferItemResponse.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<MainSpecialOfferModel>> call, Throwable t) {
                onSpecialOfferItemResponse.onError(t.getMessage());
            }
        });
    }

    public interface OnSpecialOfferItemResponse {
        void onResponse(List<MainSpecialOfferModel> modelList);
        void onError(String errorMessage);
    }
}
