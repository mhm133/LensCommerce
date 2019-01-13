package com.lenscommerce.android.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenscommerce.android.R;
import com.lenscommerce.android.model.MainLatestProductsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainLatestProductsAdapter extends RecyclerView.Adapter<MainLatestProductsAdapter.ViewHolder> {
    private Context context;
    private List<MainLatestProductsModel> modelList;

    public MainLatestProductsAdapter(Context context, List<MainLatestProductsModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_latest_products,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cv_latest_products_container)
        CardView container;
        @BindView(R.id.iv_latest_products_offer)
        AppCompatImageView image;
        @BindView(R.id.tv_latest_products_title)
        AppCompatTextView title;
        @BindView(R.id.tv_latest_products_price)
        AppCompatTextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindView(MainLatestProductsModel model) {
            Picasso.get().load(model.getLatestPrImage()).into(image);
            title.setText(model.getLatestPrTitle());
            price.setText(model.getLatestPrPrice());
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
