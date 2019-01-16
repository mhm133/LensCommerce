package com.lenscommerce.android.ui.activity;

import android.os.Bundle;

import com.lenscommerce.android.R;
import com.lenscommerce.android.adapter.CategoryAdapter;
import com.lenscommerce.android.model.CategoryModel;
import com.lenscommerce.android.server.ApiUtil;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity implements Callback<List<CategoryModel>> {
    @BindView(R.id.iv_cat_back)
    AppCompatImageButton ivBack;
    @BindView(R.id.rv_category)
    RecyclerView categoryRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        initToolbar();
        setupComponent();
    }

    private void setupComponent() {
        ApiUtil.getServiceClass().getCategoryList().enqueue(this);
    }

    private void initToolbar() {
        ivBack.setOnClickListener(view -> finish());
    }

    @Override
    public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
        CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this, response.body());
        categoryRecycler.setAdapter(adapter);
        categoryRecycler.setHasFixedSize(true);
        categoryRecycler.setLayoutManager(new GridLayoutManager(CategoryActivity.this,
                2));
    }

    @Override
    public void onFailure(Call<List<CategoryModel>> call, Throwable t) {

    }
}
