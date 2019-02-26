package com.lenscommerce.android.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.lenscommerce.android.R;
import com.lenscommerce.android.adapter.ProductsAdapter;
import com.lenscommerce.android.model.ProductsModel;
import com.lenscommerce.android.server.ApiUtil;
import com.lenscommerce.android.util.endlessrecycler.InfiniteScrollProvide;

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
    private int page = 1;
    private int sortType = 1;

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
        setupRecyclerView();
        setupViews();
    }

    private void setupRecyclerView() {
        setupRecyclerLayoutManager();
        adapter = new ProductsAdapter(context);
        rvProductsList.setAdapter(adapter);
        rvProductsList.setHasFixedSize(true);
        setupLoadMore();
    }

    private void setupLoadMore() {
        InfiniteScrollProvide infiniteScrollProvide = new InfiniteScrollProvide();
        infiniteScrollProvide.attach(rvProductsList, () -> {
            lnrLazyLoading.setVisibility(LinearLayout.VISIBLE);
            setupApiService();
        });
    }

    private void setupApiService() {
        if (extras != null)
            catId = extras.getString("cat_id");
        ApiUtil.getServiceClass().getProducts(catId,
                page, sortType)
                .enqueue(this);
    }

    @Override
    public void onResponse(Call<List<ProductsModel>> call, Response<List<ProductsModel>> response) {
        progressBar.setVisibility(ProgressBar.GONE);
        lnrLazyLoading.setVisibility(LinearLayout.GONE);
        adapter.addList(response.body());
        page += 1;
        setupGoToTopBtn();
    }

    @Override
    public void onFailure(Call<List<ProductsModel>> call, Throwable t) {
        Log.e("TAG", "onFailure: ", t);
        progressBar.setVisibility(ProgressBar.GONE);
        lnrLazyLoading.setVisibility(LinearLayout.GONE);
        // TODO: 21/01/2019 write error
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
        btnChangeViews.setOnClickListener(view -> {
            setupRecyclerLayoutManager();
        });
    }

    private void setupRecyclerLayoutManager() {
        SharedPreferences.Editor editor = itemViewPref.edit();
        if (itemViewPref.getString(RV_VIEW_TYPE, "1").contains("1")) {
            editor.putString(RV_VIEW_TYPE, "2").apply();
            btnChangeViews.setImageDrawable(getResources().getDrawable(R.drawable.ic_grid));
            rvProductsList.setLayoutManager(new GridLayoutManager(context, 2));
            if (adapter != null)
                adapter.notifyDataSetChanged();
        } else if (itemViewPref.getString(RV_VIEW_TYPE, "2").contains("2")) {
            editor.putString(RV_VIEW_TYPE, "3").apply();
            btnChangeViews.setImageDrawable(getResources().getDrawable(R.drawable.ic_list_large));
            rvProductsList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,
                    false));
            if (adapter != null)
                adapter.notifyDataSetChanged();
        } else {
            editor.putString(RV_VIEW_TYPE, "1").apply();
            btnChangeViews.setImageDrawable(getResources().getDrawable(R.drawable.ic_list_small));
            rvProductsList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,
                    false));
            if (adapter != null)
                adapter.notifyDataSetChanged();
        }
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
        // TODO: 21/01/2019 setup filter list
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapter = new ProductsAdapter(context);
        switch (i) {
            case 0:
                if (adapterView.getSelectedItemPosition() != 0) {
                    sortType = 0;
                    rvProductsList.setAdapter(adapter);
                    setupApiService();
                }
                break;
            case 1:
                if (adapterView.getSelectedItemPosition() != 1) {
                    sortType = 1;
                    rvProductsList.setAdapter(adapter);
                    setupApiService();
                }
                break;
            case 2:
                if (adapterView.getSelectedItemPosition() != 2) {
                    sortType = 2;
                    rvProductsList.setAdapter(adapter);
                    setupApiService();
                }
                break;
            case 3:
                if (adapterView.getSelectedItemPosition() != 3) {
                    sortType = 3;
                    rvProductsList.setAdapter(adapter);
                    setupApiService();
                }
                break;
            case 4:
                if (adapterView.getSelectedItemPosition() != 4) {
                    sortType = 4;
                    rvProductsList.setAdapter(adapter);
                    setupApiService();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //do nothing
    }

}
