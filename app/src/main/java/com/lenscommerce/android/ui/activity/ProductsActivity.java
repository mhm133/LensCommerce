package com.lenscommerce.android.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.lenscommerce.android.R;
import com.lenscommerce.android.adapter.ProductsAdapter;
import com.lenscommerce.android.model.ProductsModel;
import com.lenscommerce.android.server.ApiUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity implements Callback<List<ProductsModel>>,
        AdapterView.OnItemSelectedListener {
    public static final String RV_VIEW_TYPE = "item_view_type";
    @BindView(R.id.tv_products_toolbar_title)
    AppCompatTextView toolbarTitle;
    @BindView(R.id.loading)
    ProgressBar progressBar;
    @BindView(R.id.plist_sort)
    LinearLayout lnrSort;
    @BindView(R.id.plist_filtering)
    LinearLayout lnrFiltering;
    @BindView(R.id.product_list_change_view)
    AppCompatImageButton btnChangeViews;
    @BindView(R.id.sort_spinner)
    AppCompatSpinner sortSpinner;
    @BindView(R.id.productList_recyclerView)
    RecyclerView rvProductsList;
    @BindView(R.id.activityProductList_button_goToTop)
    AppCompatImageButton btnGoToTop;
    @BindView(R.id.lazyLoading)
    LinearLayout lnrLazyLoading;
    @BindView(R.id.ib_products_back)
    AppCompatImageButton backBtn;
    @BindView(R.id.tv_products_cart_size)
    AppCompatTextView tvCartSize;
    @BindView(R.id.ib_products_search)
    AppCompatImageButton searchBtn;
    private SharedPreferences itemViewPref;
    private Context context;
    private Bundle extras;
    private String catId;
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        context = getApplicationContext();
        extras = getIntent().getExtras();
        itemViewPref = getSharedPreferences(RV_VIEW_TYPE, Context.MODE_PRIVATE);
        setupComponent();
    }

    private void setupComponent() {
        if (extras != null)
            toolbarTitle.setText(extras.getString("cat_title"));
        setupApiService(0);
        setupViews();
    }

    private void setupApiService(int sortType) {
        if (extras != null)
            catId = extras.getString("cat_id");
        ApiUtil.getServiceClass().getProducts(catId, String.valueOf(sortType)).enqueue(this);
    }

    @Override
    public void onResponse(Call<List<ProductsModel>> call, Response<List<ProductsModel>> response) {
        progressBar.setVisibility(ProgressBar.GONE);
        adapter = new ProductsAdapter(context, response.body());
        rvProductsList.setAdapter(adapter);
        rvProductsList.setHasFixedSize(true);
        setupGoToTopBtn();
    }

    @Override
    public void onFailure(Call<List<ProductsModel>> call, Throwable t) {
        //todo show error
    }

    private void setupViews() {
        setupBackBtn();
        setupCartSize();
        setupSearchBtn();
        setupChangeViews();
        setupFilterList();
    }

    private void setupGoToTopBtn() {
        btnGoToTop.setOnClickListener(view -> {
            if (rvProductsList.getLayoutManager() != null)
                rvProductsList.getLayoutManager().scrollToPosition(0);
        });
        if (rvProductsList != null && rvProductsList.getLayoutManager() != null) {
            rvProductsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy > 1) {
                        btnGoToTop.setVisibility(View.VISIBLE);
                    }
                    if (dy < 1) {
                        btnGoToTop.setVisibility(View.INVISIBLE);
                    }
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager)
                            rvProductsList.getLayoutManager();
                    int totalItemCount = rvProductsList.getLayoutManager().getItemCount();
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (totalItemCount <= (lastVisibleItem + 5)) {
                        lnrLazyLoading.setVisibility(LinearLayout.VISIBLE);
                    }
                }
            });
        }
    }

    private void setupSearchBtn() {
        searchBtn.setOnClickListener(view -> {
            // TODO: 15/01/2019 create search activity
        });
    }

    private void setupCartSize() {
        // TODO: 15/01/2019 setup cart size
    }

    private void setupBackBtn() {
        backBtn.setOnClickListener(view -> finish());
    }

    private void setupChangeViews() {
        SharedPreferences.Editor editor = itemViewPref.edit();
        btnChangeViews.setOnClickListener(view -> {
            if (itemViewPref.getString(RV_VIEW_TYPE, "1").contains("1")) {
                editor.putString(RV_VIEW_TYPE, "2").apply();
                btnChangeViews.setImageDrawable(getResources().getDrawable(R.drawable.ic_grid));
                rvProductsList.setLayoutManager(new GridLayoutManager(context, 2));
                adapter.notifyDataSetChanged();
            } else if (itemViewPref.getString(RV_VIEW_TYPE, "2").contains("2")) {
                editor.putString(RV_VIEW_TYPE, "3").apply();
                btnChangeViews.setImageDrawable(getResources().getDrawable(R.drawable.ic_list_large));
                rvProductsList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,
                        false));
                adapter.notifyDataSetChanged();
            } else {
                editor.putString(RV_VIEW_TYPE, "1").apply();
                btnChangeViews.setImageDrawable(getResources().getDrawable(R.drawable.ic_list_small));
                rvProductsList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,
                        false));
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setupFilterList() {
        setupSort();
        setupFiltering();
    }

    private void setupSort() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sort_spinner, R.layout.sort_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_style);
        sortSpinner.setAdapter(adapter);
        lnrSort.setOnClickListener(view -> sortSpinner.performClick());
        sortSpinner.setOnItemSelectedListener(this);
    }

    private void setupFiltering() {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                if (adapterView.getSelectedItemPosition() != 0)
                    setupApiService(0);
                break;
            case 1:
                if (adapterView.getSelectedItemPosition() != 1)
                    setupApiService(1);
                break;
            case 2:
                if (adapterView.getSelectedItemPosition() != 2)
                    setupApiService(2);
                break;
            case 3:
                if (adapterView.getSelectedItemPosition() != 3)
                    setupApiService(3);
                break;
            case 4:
                if (adapterView.getSelectedItemPosition() != 4)
                    setupApiService(4);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //do nothing
    }
}
