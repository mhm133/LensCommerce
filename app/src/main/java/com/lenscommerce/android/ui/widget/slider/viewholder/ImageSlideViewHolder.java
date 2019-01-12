package com.lenscommerce.android.ui.widget.slider.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.lenscommerce.android.ui.widget.slider.Slider;

import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.RecyclerView;

public class ImageSlideViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public ImageSlideViewHolder(View itemView) {
        super(itemView);
        this.imageView = (ImageView) itemView;
    }

    public void bindImageSlide(String imageUrl) {
        if (imageUrl != null) {
            Slider.getImageLoadingService().loadImage(imageUrl, imageView);
        }
    }

    public void bindImageSlide(@DrawableRes int imageResourceId) {
        Slider.getImageLoadingService().loadImage(imageResourceId, imageView);
    }

    public void bindImageSlide(String url, @DrawableRes int placeHolder, @DrawableRes int error) {
        Slider.getImageLoadingService().loadImage(url, placeHolder, error, imageView);
    }

}