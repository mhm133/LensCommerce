package com.lenscommerce.android.server.client;

import com.lenscommerce.android.model.MainLatestProductsModel;
import com.lenscommerce.android.server.ApiUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainLatestProductsClient {
    public static void getLatestProducts(OnLatestProductsReceived onLatestProductsReceived) {
        ApiUtil.getServiceClass().getLatestProducts().enqueue(new Callback<List<MainLatestProductsModel>>() {
            @Override
            public void onResponse(Call<List<MainLatestProductsModel>> call, Response<List<MainLatestProductsModel>> response) {
                onLatestProductsReceived.onLatestProductsResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<MainLatestProductsModel>> call, Throwable t) {
                onLatestProductsReceived.onLatestProductsError(t.getMessage());
            }
        });
    }

    public interface OnLatestProductsReceived {
        void onLatestProductsResponse(List<MainLatestProductsModel> modelList);
        void onLatestProductsError(String errorMessage);
    }
}
