package com.lenscommerce.android.server;

import com.lenscommerce.android.server.client.ApiClient;
import com.lenscommerce.android.server.service.ApiService;

public class ApiUtil {
    public static ApiService getServiceClass() {
        return ApiClient.getClient().create(ApiService.class);
    }
}
