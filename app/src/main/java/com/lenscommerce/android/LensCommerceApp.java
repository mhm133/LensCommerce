package com.lenscommerce.android;

import android.app.Application;

import com.lenscommerce.android.util.ConnectivityReceiver;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LensCommerceApp extends Application {
    private static LensCommerceApp instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("lens_db")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public static synchronized LensCommerceApp getInstance() {
        return instance;
    }

    public void setConnectvityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
