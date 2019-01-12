package com.lenscommerce.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenscommerce.android.Model.MainSpecialOfferModel;
import com.lenscommerce.android.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainSpecialOfferAdapter extends RecyclerView.Adapter<MainSpecialOfferAdapter.ViewHolder> {
    private Context context;
    private List<MainSpecialOfferModel> modelList;

    public MainSpecialOfferAdapter(Context context, List<MainSpecialOfferModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_special_offer, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindViews(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_special_offer)
        AppCompatImageView ivOffer;
        @BindView(R.id.tv_special_offer_title)
        AppCompatTextView tvOffer;
        @BindView(R.id.tv_offer_previous_price)
        AppCompatTextView tvPreviousPrice;
        @BindView(R.id.tv_offer_current_price)
        AppCompatTextView tvCurrentPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindViews(MainSpecialOfferModel model) {
            Picasso.get().load(model.getOfferImage()).into(ivOffer);
            tvOffer.setText(model.getOfferTitle());
            tvPreviousPrice.setText(model.getOfferPreviousPrice());
            tvCurrentPrice.setText(model.getOfferCurrentPrice());
        }
    }
}
