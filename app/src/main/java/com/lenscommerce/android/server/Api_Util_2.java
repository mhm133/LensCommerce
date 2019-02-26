package com.lenscommerce.android.server;

import com.lenscommerce.android.server.client.Client;
import com.lenscommerce.android.server.service.ApiService;

public class Api_Util_2 {
    public static ApiService getServiceClass() {
        return Client.getClient().create(ApiService.class);
    }
}
