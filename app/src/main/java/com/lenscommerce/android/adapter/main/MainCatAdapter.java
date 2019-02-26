package com.lenscommerce.android.adapter.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenscommerce.android.model.MainCatModel;
import com.lenscommerce.android.R;
import com.lenscommerce.android.ui.activity.ProductsActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainCatAdapter extends RecyclerView.Adapter<MainCatAdapter.MainCatViewHolder> {
    private Context context;
    private List<MainCatModel> modelList;

    public MainCatAdapter(Context context, List<MainCatModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MainCatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_cat_, parent,
                false);
        return new MainCatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainCatViewHolder holder, int position) {
        holder.bindCat(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MainCatViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_main_cat)
        AppCompatTextView tvTitle;

        public MainCatViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindCat(MainCatModel model) {
            tvTitle.setText(model.getCatTitle());
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("cat_id", model.getId());
                context.startActivity(intent);
            });
        }
    }
}
