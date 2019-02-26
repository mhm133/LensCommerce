package com.lenscommerce.android.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.lenscommerce.android.R;
import com.lenscommerce.android.adapter.main.MainCatAdapter;
import com.lenscommerce.android.adapter.main.MainDiscountAdapter;
import com.lenscommerce.android.adapter.main.MainLatestProductsAdapter;
import com.lenscommerce.android.adapter.main.MainSliderAdapter;
import com.lenscommerce.android.adapter.main.MainSpecialOfferAdapter;
import com.lenscommerce.android.model.MainCatModel;
import com.lenscommerce.android.model.MainDiscountModel;
import com.lenscommerce.android.model.MainLatestProductsModel;
import com.lenscommerce.android.model.MainSpecialOfferModel;
import com.lenscommerce.android.server.ApiUtil;
import com.lenscommerce.android.server.client.MainDiscountClient;
import com.lenscommerce.android.server.client.MainLatestProductsClient;
import com.lenscommerce.android.server.client.MainPopularProductsClient;
import com.lenscommerce.android.server.client.MainSpecialOfferClient;
import com.lenscommerce.android.storage.ProductCategoryDAO;
import com.lenscommerce.android.ui.activity.ProductsActivity;
import com.lenscommerce.android.ui.widget.MaterialProgressBar;
import com.lenscommerce.android.ui.widget.slider.Slider;
import com.lenscommerce.android.ui.widget.slider.event.OnSlideClickListener;
import com.lenscommerce.android.util.PicassoImageLoadingService;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment implements OnSlideClickListener,
        Callback<List<MainCatModel>>, MainSpecialOfferClient.OnSpecialOfferItemResponse,
        MainDiscountClient.OnMainDiscountItemReceived,
        MainLatestProductsClient.OnLatestProductsReceived,
        MainPopularProductsClient.OnPopularItemReceived {
    private static final String TAG = MainFragment.class.getSimpleName();
    @BindView(R.id.slider_main) Slider slider;
    @BindView(R.id.rv_cat_main) RecyclerView rvCat;
    @BindView(R.id.rv_special_offer_main) RecyclerView rvOffer;
    @BindView(R.id.rv_discount_products_main) RecyclerView rvDiscount;
    @BindView(R.id.rv_latest_products_main) RecyclerView rvLatestProducts;
    @BindView(R.id.rv_popular_products) RecyclerView rvPopularProducts;
    @BindView(R.id.tv_latest_products_see_all) AppCompatTextView tvLatestProductsSeeAll;
    @BindView(R.id.tv_popular_products_see_all) AppCompatTextView tvPopularProductsSeeAll;
    @BindView(R.id.main_layout_container)
    NestedScrollView container;
    @BindView(R.id.main_progress)
    MaterialProgressBar progressBar;
    private LinearLayout noConnectionLayout;
    @BindView(R.id.btn_no_connection)
    MaterialButton btnRetry;
    private ProductCategoryDAO productCategoryDAO;
    private Context context;

    public MainFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productCategoryDAO = new ProductCategoryDAO();
        Slider.init(new PicassoImageLoadingService());
    }

    private void setupSlider() {
        slider.setAdapter(new MainSliderAdapter());
        slider.setOnSlideClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        ApiUtil.getServiceClass().getMainCat().enqueue(this);
        setupSlider();
        setupSeeAllBtns();
        return view;
    }

    private void setupSeeAllBtns() {
        tvLatestProductsSeeAll.setOnClickListener(view -> {
            Intent i = new Intent(getContext(), ProductsActivity.class);
            i.putExtra("cat_id", 1);
            startActivity(i);
        });
        tvPopularProductsSeeAll.setOnClickListener(view -> {
            Intent i = new Intent(getContext(), ProductsActivity.class);
            i.putExtra("cat_id", 1);
            startActivity(i);
        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        productCategoryDAO.closeRealm();
    }

    @Override
    public void onSlideClick(int position) {
        switch (position) {
            case 0:
                Toast.makeText(getContext(), "item 1", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getContext(), "item 2", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getContext(), "item 3", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onResponse(Call<List<MainCatModel>> call, Response<List<MainCatModel>> response) {
        container.setVisibility(View.VISIBLE);
        progressBar.setVisibility(MaterialProgressBar.GONE);

        MainCatAdapter adapter = new MainCatAdapter(getContext(), response.body());
        rvCat.setAdapter(adapter);
        rvCat.setHasFixedSize(true);
        rvCat.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                false));
        MainDiscountClient.getDiscountItems(this);
        MainSpecialOfferClient.getSpecialOfferContent(MainFragment.this);
    }

    @Override
    public void onFailure(Call<List<MainCatModel>> call, Throwable t) {
        progressBar.setVisibility(MaterialProgressBar.GONE);
        container.setVisibility(View.GONE);
        noConnectionLayout.setVisibility(View.VISIBLE);
        btnRetry.setOnClickListener(view -> call.enqueue(MainFragment.this));
    }

    @Override
    public void onResponse(List<MainSpecialOfferModel> modelList) {
        MainSpecialOfferAdapter adapter = new MainSpecialOfferAdapter(context, modelList);
        rvOffer.setAdapter(adapter);
        rvOffer.setHasFixedSize(true);
        rvOffer.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                false));
        MainLatestProductsClient.getLatestProducts(MainFragment.this);
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDiscountResponse(List<MainDiscountModel> modelList) {
        MainDiscountAdapter adapter = new MainDiscountAdapter(context, modelList);
        rvDiscount.setAdapter(adapter);
        rvDiscount.setHasFixedSize(true);
        rvDiscount.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,
                false));
    }

    @Override
    public void onDiscountError(String errorMessage) {
        Log.e(TAG, "onDiscountError: " + errorMessage);
    }

    @Override
    public void onLatestProductsResponse(List<MainLatestProductsModel> modelList) {
        MainLatestProductsAdapter adapter = new MainLatestProductsAdapter(context, modelList);
        rvLatestProducts.setAdapter(adapter);
        rvLatestProducts.setHasFixedSize(true);
        rvLatestProducts.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                false));
        MainPopularProductsClient.getPopularProducts(MainFragment.this);
    }

    @Override
    public void onLatestProductsError(String errorMessage) {
        Log.e(TAG, "onLatestProductsError: " + errorMessage);
    }

    @Override
    public void onPopularItemResponse(List<MainLatestProductsModel> modelList) {
        MainLatestProductsAdapter adapter = new MainLatestProductsAdapter(context, modelList);
        rvPopularProducts.setAdapter(adapter);
        rvPopularProducts.setHasFixedSize(true);
        rvPopularProducts.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                false));

    }

    @Override
    public void onPopularItemError(String errorMessage) {
        Log.e(TAG, "onPopularItemError: " + errorMessage);
    }

}
