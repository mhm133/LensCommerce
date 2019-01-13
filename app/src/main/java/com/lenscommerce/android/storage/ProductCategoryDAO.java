package com.lenscommerce.android.storage;

import android.util.Log;

import com.lenscommerce.android.model.MainCatModel;

import java.util.List;

import io.realm.Realm;

public class ProductCategoryDAO implements Realm.Transaction, Realm.Transaction.OnError,
        Realm.Transaction.OnSuccess {
    private static final String TAG = ProductCategoryDAO.class.getSimpleName();
    private Realm realm;
    private OnProductCatInsert onProductCatInsert;
    private MainCatModel model1;

    public ProductCategoryDAO() {
        realm = Realm.getDefaultInstance();
    }

    public void insertCat(List<MainCatModel> model, OnProductCatInsert onProductCatInsert1) {
        onProductCatInsert = onProductCatInsert1;
        for (int i = 0; i < model.size(); i++) {
            model1 = model.get(i);
            realm.executeTransactionAsync(this, this, this);
        }
    }

    public void getCat(List<MainCatModel> modelList) {

    }

    @Override
    public void execute(Realm realm) {
        MainCatModel model = realm.createObject(MainCatModel.class, model1.getId());
        model.setCatTitle(model1.getCatTitle());
    }

    @Override
    public void onError(Throwable error) {
        Log.e(TAG, "onError: " + error.getMessage());
    }

    @Override
    public void onSuccess() {
        onProductCatInsert.onSuccess();
    }

    public interface OnProductCatInsert {
        void onSuccess();
    }

    public void closeRealm() {
        realm.close();
    }
}
