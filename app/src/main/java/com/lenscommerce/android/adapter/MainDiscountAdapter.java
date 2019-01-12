package com.lenscommerce.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenscommerce.android.Model.MainDiscountModel;
import com.lenscommerce.android.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainDiscountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_Linear = 0;
    private static final int TYPE_GROUP = 1;
    private Context context;
    private List<MainDiscountModel> modelList;

    public MainDiscountAdapter(Context context, List<MainDiscountModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if (modelList.get(position).getDiscountImage() != null) {
            viewType = TYPE_Linear;
        } else {
            viewType = TYPE_GROUP;
        }
        return viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (viewType) {
            case TYPE_Linear:
                view = inflater
                        .inflate(R.layout.item_main_discount_first_row, parent, false);
                return new ViewHolder0(view);
            case TYPE_GROUP:
                view = inflater.
                        inflate(R.layout.item_main_discount_second_row, parent, false);
                return new ViewHolder2(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_Linear:
                ViewHolder0 viewHolder0 = (ViewHolder0) holder;
                viewHolder0.bindViews(modelList.get(position));
                break;

            case TYPE_GROUP:
                ViewHolder2 viewHolder2 = (ViewHolder2) holder;
                viewHolder2.bindViews(modelList.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class ViewHolder0 extends RecyclerView.ViewHolder {
        @BindView(R.id.cv_main_discount_container_first_row)
        CardView cvContainer;
        @BindView(R.id.iv_discount_main_first_row)
        AppCompatImageView imageView;

        public ViewHolder0(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindViews(MainDiscountModel model) {
            Picasso.get().load(model.getDiscountImage()).into(imageView);
        }
    }
}

class ViewHolder2 extends RecyclerView.ViewHolder {
    @BindView(R.id.cv_main_discount_second_row_1)
    CardView cvContainer1;
    @BindView(R.id.cv_main_discount_second_row_2)
    CardView cvContainer2;
    @BindView(R.id.iv_discount_main_second_row_1)
    AppCompatImageView imageView1;
    @BindView(R.id.iv_discount_main_second_row_2)
    AppCompatImageView imageView2;

    public ViewHolder2(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindViews(MainDiscountModel model) {
        Picasso.get().load(model.getDiscountSecondRow1()).into(imageView1);
        Picasso.get().load(model.getDiscountSecondRow2()).into(imageView2);
    }
}
