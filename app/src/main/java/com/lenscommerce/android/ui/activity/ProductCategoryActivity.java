package com.lenscommerce.android.ui.activity;

import android.content.Context;
import android.os.Bundle;

import com.lenscommerce.android.R;
import com.lenscommerce.android.adapter.ProductsCategoryAdapter;
import com.lenscommerce.android.model.ProductsCatModel;
import com.lenscommerce.android.server.ApiUtil;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductCategoryActivity extends AppCompatActivity implements Callback<List<ProductsCatModel>> {
    private String catId;
    private Context context;
    @BindView(R.id.tv_product_cat_tool_title)
    AppCompatTextView toolbarTitle;
    @BindView(R.id.rv_products_cat)
    RecyclerView rvProductsCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);
        context = getApplicationContext();
        ButterKnife.bind(this);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            catId = extra.getString("cat_id");
            String catTitle = extra.getString("cat_title");
            String title = catTitle + " " + getString(R.string.category);
            toolbarTitle.setText(title);
        }
        ApiUtil.getServiceClass().getProductsCat(catId).enqueue(this);
    }

    @Override
    public void onResponse(Call<List<ProductsCatModel>> call, Response<List<ProductsCatModel>> response) {
        ProductsCategoryAdapter adapter = new ProductsCategoryAdapter(context, response.body());
        rvProductsCat.setHasFixedSize(true);
        rvProductsCat.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,
                false));
        rvProductsCat.setAdapter(adapter);
        rvProductsCat.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onFailure(Call<List<ProductsCatModel>> call, Throwable t) {

    }
}
