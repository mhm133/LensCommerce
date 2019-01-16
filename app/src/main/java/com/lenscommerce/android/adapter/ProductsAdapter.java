package com.lenscommerce.android.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenscommerce.android.R;
import com.lenscommerce.android.model.ProductsModel;
import com.lenscommerce.android.ui.activity.ProductsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {
    private Context context;
    private List<ProductsModel> modelList;

    public ProductsAdapter(Context context, List<ProductsModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public int getItemViewType(int position) {
        SharedPreferences preferences = context.getSharedPreferences(ProductsActivity.RV_VIEW_TYPE, Context.MODE_PRIVATE);
        int viewType;
        if (preferences.getString(ProductsActivity.RV_VIEW_TYPE, "1").contains("1")) {
            viewType = 1;
            notifyDataSetChanged();
        } else  if (preferences.getString(ProductsActivity.RV_VIEW_TYPE, "2").contains("2")){
            viewType = 2;
            notifyDataSetChanged();
        } else {
            viewType = 3;
            notifyDataSetChanged();
        }
        return viewType;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (viewType) {
            case 1:
                view = inflater.inflate(R.layout.item_product_list_small, parent, false);
                return new MyViewHolder(view);
            case 2:
                view = inflater.inflate(R.layout.item_product_list_grid, parent, false);
                return new MyViewHolder(view);
            case 3:
                view = inflater.inflate(R.layout.item_product_list_large, parent, false);
                return new MyViewHolder(view);
            default:
                return null;
        }
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
        @BindView(R.id.image_product)
        AppCompatImageView imageView;
        @BindView(R.id.text_title)
        AppCompatTextView title;
        @BindView(R.id.text_previous_price)
        AppCompatTextView previousPrice;
        @BindView(R.id.text_current_price)
        AppCompatTextView currentPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(ProductsModel model) {
            Picasso.get().load(model.getImageProduct()).into(imageView);
            title.setText(model.getTitle());
            previousPrice.setText(model.getPreviousPrice());
            currentPrice.setText(model.getCurrentPrice());
        }
    }
}
