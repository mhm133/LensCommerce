package com.lenscommerce.android.server.client;

import com.lenscommerce.android.model.MainLatestProductsModel;
import com.lenscommerce.android.server.ApiUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPopularProductsClient {
    public static void getPopularProducts(OnPopularItemReceived onPopularItemReceived) {
        ApiUtil.getServiceClass().getPopularProducts().enqueue(new Callback<List<MainLatestProductsModel>>() {
            @Override
            public void onResponse(Call<List<MainLatestProductsModel>> call, Response<List<MainLatestProductsModel>> response) {
                onPopularItemReceived.onPopularItemResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<MainLatestProductsModel>> call, Throwable t) {
                onPopularItemReceived.onPopularItemError(t.getMessage());
            }
        });
    }

    public interface OnPopularItemReceived {
        void onPopularItemResponse(List<MainLatestProductsModel> modelList);
        void onPopularItemError(String errorMessage);
    }
}
