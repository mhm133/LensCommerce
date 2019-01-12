package com.lenscommerce.android.server.client;

import com.lenscommerce.android.Model.MainDiscountModel;
import com.lenscommerce.android.server.ApiUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainDiscountClient {
    public static void getDiscountItems(OnMainDiscountItemReceived onMainDiscountItemReceived) {
        ApiUtil.getServiceClass().getDiscountModel().enqueue(new Callback<List<MainDiscountModel>>() {
            @Override
            public void onResponse(Call<List<MainDiscountModel>> call, Response<List<MainDiscountModel>> response) {
                onMainDiscountItemReceived.onDiscountResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<MainDiscountModel>> call, Throwable t) {
                onMainDiscountItemReceived.onDiscountError(t.getMessage());
            }
        });
    }

    public interface OnMainDiscountItemReceived {
        void onDiscountResponse(List<MainDiscountModel> modelList);
        void onDiscountError(String errorMessage);
    }
}
