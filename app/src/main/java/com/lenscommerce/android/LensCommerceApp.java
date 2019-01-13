package com.lenscommerce.android;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LensCommerceApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("lens_db")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
