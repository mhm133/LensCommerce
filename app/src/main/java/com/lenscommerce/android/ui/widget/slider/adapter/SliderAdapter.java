package com.lenscommerce.android.ui.widget.slider.adapter;

import com.lenscommerce.android.ui.widget.slider.SlideType;
import com.lenscommerce.android.ui.widget.slider.viewholder.ImageSlideViewHolder;

public abstract class SliderAdapter {
    public abstract int getItemCount();

    public final SlideType getSlideType(int position) {
        return SlideType.IMAGE;
    }

    public abstract void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder);
}
