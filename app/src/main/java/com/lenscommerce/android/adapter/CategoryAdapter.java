package com.lenscommerce.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenscommerce.android.R;
import com.lenscommerce.android.model.CategoryModel;
import com.lenscommerce.android.ui.activity.ProductCategoryActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context context;
    private List<CategoryModel> modelList;

    public CategoryAdapter(Context context, List<CategoryModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category_layout,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindViews(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_category_item)
        AppCompatImageView imageView;
        @BindView(R.id.tv_category_title)
        AppCompatTextView title;
        @BindView(R.id.cv_category)
        CardView container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindViews(CategoryModel model) {
            Picasso.get().load(model.getCatImage()).into(imageView);
            title.setText(model.getCatTitle());
            container.setOnClickListener(view -> {
                Intent intent = new Intent(context, ProductCategoryActivity.class);
                intent.putExtra("cat_id", model.getCatId());
                intent.putExtra("cat_title", model.getCatTitle());
                context.startActivity(intent);
            });
        }
    }
}
