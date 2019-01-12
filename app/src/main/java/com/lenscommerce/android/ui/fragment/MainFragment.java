package com.lenscommerce.android.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lenscommerce.android.Model.MainCatModel;
import com.lenscommerce.android.Model.MainDiscountModel;
import com.lenscommerce.android.Model.MainSpecialOfferModel;
import com.lenscommerce.android.R;
import com.lenscommerce.android.adapter.MainCatAdapter;
import com.lenscommerce.android.adapter.MainDiscountAdapter;
import com.lenscommerce.android.adapter.MainSliderAdapter;
import com.lenscommerce.android.adapter.MainSpecialOfferAdapter;
import com.lenscommerce.android.server.ApiUtil;
import com.lenscommerce.android.server.client.MainDiscountClient;
import com.lenscommerce.android.server.client.MainSpecialOfferClient;
import com.lenscommerce.android.ui.widget.slider.Slider;
import com.lenscommerce.android.ui.widget.slider.event.OnSlideClickListener;
import com.lenscommerce.android.util.PicassoImageLoadingService;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment implements OnSlideClickListener,
        Callback<List<MainCatModel>>,MainSpecialOfferClient.OnSpecialOfferItemResponse,
        MainDiscountClient.OnMainDiscountItemReceived {
    @BindView(R.id.slider_main)
    Slider slider;
    @BindView(R.id.rv_cat_main)
    RecyclerView rvCat;
    @BindView(R.id.rv_special_offer_main)
    RecyclerView rvOffer;
    @BindView(R.id.rv_discount_products_main)
    RecyclerView rvDiscount;
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
        Slider.init(new PicassoImageLoadingService());
        ApiUtil.getServiceClass().getMainCat().enqueue(this);
        MainSpecialOfferClient.getSpecialOfferContent(this);
        MainDiscountClient.getDiscountItems(this);
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
        setupSlider();

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
        MainCatAdapter adapter = new MainCatAdapter(getContext(), response.body());
        rvCat.setAdapter(adapter);
        rvCat.setHasFixedSize(true);
        rvCat.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                false));
    }

    @Override
    public void onFailure(Call<List<MainCatModel>> call, Throwable t) {
        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(List<MainSpecialOfferModel> modelList) {
        MainSpecialOfferAdapter adapter = new MainSpecialOfferAdapter(context, modelList);
        rvOffer.setAdapter(adapter);
        rvOffer.setHasFixedSize(true);
        rvOffer.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                false));
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

    }
}
