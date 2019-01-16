package com.lenscommerce.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenscommerce.android.R;
import com.lenscommerce.android.model.ProductsCatModel;
import com.lenscommerce.android.ui.activity.ProductsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsCategoryAdapter extends RecyclerView.Adapter<ProductsCategoryAdapter.MyViewHolder> {
    private Context context;
    private List<ProductsCatModel> modelList;

    public ProductsCategoryAdapter(Context context, List<ProductsCatModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_products_category_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindView(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_products_cat)
        AppCompatImageView productImage;
        @BindView(R.id.tv_products_cat_title)
        AppCompatTextView productTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(ProductsCatModel model) {
            Picasso.get().load(model.getProductCatImage()).into(productImage);
            productTitle.setText(model.getProductCatTitle());
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("cat_id", model.getProductCatId());
                intent.putExtra("cat_title", model.getProductCatTitle());
                context.startActivity(intent);
            });
        }
    }
}
